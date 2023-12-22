import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Antd, { ConfigProvider } from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import 'ant-design-vue/dist/reset.css'
import './css/main.css'
import * as ww from '@wecom/jssdk'

ww.register({
  corpId: 'ww7f2a6c3afad0ece1',       // 必填，当前用户企业所属企业ID
  suiteId: 'ww07fc28e86a4cb184',               // 必填，当前授权的SuiteID
  jsApiList: ['shareAppMessage'], 	 // 必填，需要使用的JSAPI列表
  getSuiteConfigSignature,			 // 必填，生成应用登录授权的签名
})
  
async function getSuiteConfigSignature(url: any) {
  let timestamp = "";
  let nonceStr = "";
  let signature = "";

  // 根据 url 生成应用签名，生成方法同上，但需要使用应用登录授权的 jsapi_ticket
  return { timestamp, nonceStr, signature }
}

const app = createApp(App)
app.use(ConfigProvider);
app.use(createPinia())
app.use(router)
app.use(Antd)
app.mount('#app')
