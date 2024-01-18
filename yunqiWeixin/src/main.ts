import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Antd, { ConfigProvider } from 'ant-design-vue'
import App from './App.vue'  // 个人
import group from './group.vue' // 群
import index from './index.vue' // 工作台
import router from './router'
import 'ant-design-vue/dist/reset.css'
import './css/yunqi.css'
import * as ww from '@wecom/jssdk'
import { httpGet } from './config/interFace'
import { getQueryString } from './config/util'

const agentId = '1000032'
const code = getQueryString("code")
const corpId = 'wpKIS_CwAAYvc3SPVEv1X2RV1AP1BcnQ'
const suiteId = 'dk8a8eade3348f24a3'

const getUserInfo = async () => {
  const res = await httpGet("/QyAgent/userinfo",{ // 获取登入人员详细信息
    agentId: agentId,
    code: code
  })
  console.log(res)
  if(res.errcode) {
    window.location.href = 'https://aqara.club'
  } else {
    localStorage.setItem("localUser", res.userid) // 获取登入用户userID
  }
}

ww.register({
  corpId: corpId, // 必填，当前用户企业所属企业ID
  suiteId: suiteId, // 第三方应用
  agentId: agentId,
  jsApiList: ['getContext', 'getCurExternalContact','getCurExternalChat'], 	 // 必填，需要使用的JSAPI列表
  async getAgentConfigSignature() {                                          // 必填，根据url生成应用签名的回调函数
    const jsToken = await httpGet("/QyAgent/AppTicket", {
      agentId: agentId
    });
    return ww.getSignature(jsToken)
  },
  async getConfigSignature() {
    const jsToken = await httpGet("/QyAgent/jsapiTicket", {
      agentId: agentId
    });
    return ww.getSignature(jsToken)
  }
})

ww.getContext({
  async success(result) {
    if(localStorage.getItem("localUser") === null || localStorage.getItem("localUser") === "undefined"){
      await getUserInfo();
    }
    if (result.entry === 'chain_single_chat_tools') {
      ww.getCurExternalContact({
        async success(result) {
          const res = await httpGet("/QyAgent/externalContact",{
            agentId: agentId,
            userId: result.userId
          })
          localStorage.setItem('agentId', agentId)
          localStorage.setItem('avatar', res.external_contact.avatar)
          localStorage.setItem('userName', res.external_contact.name)
          localStorage.setItem("userId", result.userId) // 客户userID
          localStorage.setItem('follow_user', JSON.stringify(res.follow_user))
          toSinglePage()
        }
      })
    } else if(result.entry === 'chain_group_chat_tools') {
      ww.getCurExternalChat({
        async success(result) {
          console.log(result.chatId)
        }
      })
      toGroupPage()
    } else {
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
  const app = createApp(group)
  app.use(ConfigProvider);
  app.use(createPinia())
  app.use(router)
  app.use(Antd)
  app.mount('#app')
}

const toNormalPage = () => {
  const app = createApp(index)
  app.use(ConfigProvider);
  app.use(createPinia())
  app.use(router)
  app.use(Antd)
  app.mount('#app')
}