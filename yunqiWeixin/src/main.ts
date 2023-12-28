import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Antd, { ConfigProvider } from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import 'ant-design-vue/dist/reset.css'
import './css/main.css'
import * as ww from '@wecom/jssdk'
import { httpGet } from './config/interFace'

ww.register({
  corpId: 'ww9a717b03b06063e3', // 必填，当前用户企业所属企业ID
  suiteId: 'ww2fd0def5ad11e2cf',
  agentId: '1000089',
  jsApiList: ['getContext', 'shareAppMessage', 'getCurExternalContact','getCurExternalChat'], 	 // 必填，需要使用的JSAPI列表
  async getAgentConfigSignature() { // 必填，根据url生成应用签名的回调函数
    const jsToken = await httpGet("/qy/AppTicket", {
      type: 'API'
    });
    return ww.getSignature(jsToken)
  }
})

ww.getContext({
  success(result) {
    console.log(result)
    if (result.entry === 'single_chat_tools') {
      ww.getCurExternalContact({
        async success(result) {
          console.log(result)
          const res = await httpGet("/qy/externalContact",{
            userId: result.userId
          })
          console.log(res)
        }
      })
    } else if(result.entry === 'group_chat_tools') {
      ww.getCurExternalChat({
        async success(result) {
          console.log(result)
        }
      })
    }
  }
})

const app = createApp(App)
app.use(ConfigProvider);
app.use(createPinia())
app.use(router)
app.use(Antd)
app.mount('#app')
