import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { checkRedirect, initSdk, SignRes } from 'wecom-sidebar-jssdk'
import 'ant-design-vue/dist/antd.css'
import axios from 'axios'
import '@/css/aqara.css'
import { userInfo } from '@/config/interFace'

// async function getTicket () {
//   const ticket = await userInfo()
//   localStorage.setItem('ticket', ticket)
//   Vue.config.productionTip = false
//   new Vue({
//     router,
//     store,
//     render: h => h(App)
//   }).$mount('#app')
// }
// getTicket()
// 获取签名接口（需要后端生成）

const config = {
  // 在 https://work.weixin.qq.com/wework_admin/frame#profile 这里可以找到
  corpId: 'ww9a717b03b06063e3', // 企业ID
  // 在 https://work.weixin.qq.com/wework_admin/frame#apps 里的自建应用里可以找到
  agentId: '1000046'
}
const fetchSignatures = async (): Promise<SignRes> => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: '/api/qywx-utils/signatures',
    params: {
      url: window.location.href
    }
  })
  return response.data
}
// code 换取用户身份（需要后端调用企微服务端 API）
const fetchUserId = async (code: string, token: String): Promise<string> => {
  console.log(code)

  const response = await axios.request({
    method: 'GET',
    url: 'http:localhost:8080/wechat/getUserId',
    params: {
      code: code,
      token: token
    }
  })
  console.log(response)
  return response.data
}

const getToken = async (): Promise<string> => {
  const response = await axios.request({
    method: 'GET',
    url: 'http:localhost:8080/wechat/getToken'
  })
  console.log(response)
  return response.data
}

// checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
//   .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
//   .then(() => {
//     new Vue({
//       router,
//       store,
//       render: h => h(App)
//     }).$mount('#app')
//   })
