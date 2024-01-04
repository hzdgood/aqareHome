import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Antd, { ConfigProvider } from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import 'ant-design-vue/dist/reset.css'
import './css/yunqi.css'
import * as ww from '@wecom/jssdk'
import { httpGet } from './config/interFace'
import { getQueryString } from './config/util'

ww.register({
  corpId: 'ww9a717b03b06063e3', // 必填，当前用户企业所属企业ID
  suiteId: 'ww2fd0def5ad11e2cf',
  agentId: '1000089',
  jsApiList: ['getContext', 'getCurExternalContact','getCurExternalChat'], 	 // 必填，需要使用的JSAPI列表
  async getAgentConfigSignature() { // 必填，根据url生成应用签名的回调函数
    const jsToken = await httpGet("/qy/AppTicket", {
      type: 'API'
    });
    return ww.getSignature(jsToken)
  }
})

ww.getContext({
  async success(result) {
    const userinfo3rd = await httpGet("/qy/userinfo3rd",{
      code: getQueryString("code")
    })
    console.log(userinfo3rd);
    if (result.entry === 'single_chat_tools') {
      ww.getCurExternalContact({
        async success(result) {
          const res = await httpGet("/qy/externalContact",{
            userId: result.userId
          })
          localStorage.setItem("localName", userinfo3rd.userId)
          localStorage.setItem('userName', res.external_contact.name)
          localStorage.setItem("userId", result.userId)
          localStorage.setItem('follow_user', JSON.stringify(res.follow_user))
          toSinglePage()
        }
      })
    } else if(result.entry === 'group_chat_tools') {
      ww.getCurExternalChat({
        async success(result) {
          console.log(result.chatId)
        }
      })
      toGroupPage()
    } else if(result.entry === 'normal') {
      toNormalPage()
    }
  }
})

const toSinglePage = () => {
  const app = createApp(App)
  app.use(ConfigProvider);
  app.use(createPinia())
  app.use(router)
  app.use(Antd)
  app.mount('#app')
}

const toGroupPage = () => {
  const app = createApp(App)
  app.use(ConfigProvider);
  app.use(createPinia())
  app.use(router)
  app.use(Antd)
  app.mount('#app')
}

const toNormalPage = () => {
  const app = createApp(App)
  app.use(ConfigProvider);
  app.use(createPinia())
  app.use(router)
  app.use(Antd)
  app.mount('#app')
}