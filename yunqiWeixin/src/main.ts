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
  corpId: 'ww9a717b03b06063e3',       // 必填，当前用户企业所属企业ID
  suiteId: 'ww2fd0def5ad11e2cf',               // 必填，当前授权的SuiteID
  jsApiList: ['getExternalContact', 'selectExternalContact'], 	 // 必填，需要使用的JSAPI列表
  async getConfigSignature(url: any) { // 必填，根据url生成企业签名的回调函数
    return await httpGet("/qy/getAppSign", url)
  },
  async getSuiteConfigSignature(url: any) { // 必填，根据url生成应用签名的回调函数
    return await httpGet("/qy/getJsSign", url)
  },			 
})

const app = createApp(App)
app.use(ConfigProvider);
app.use(createPinia())
app.use(router)
app.use(Antd)
app.mount('#app')
