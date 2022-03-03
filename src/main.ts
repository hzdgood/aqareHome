import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// import { checkRedirect, initSdk } from 'wecom-sidebar-jssdk'
// import config from '@/config'
// import { fetchSignatures, fetchUserId } from '@/api'
import 'ant-design-vue/dist/antd.css'
import '@/css/aqara.css'
import { userInfo } from '@/config/interFace'
async function getTicket () {
  const ticket = await userInfo()
  localStorage.setItem('ticket', ticket)
  Vue.config.productionTip = false
  new Vue({
    router,
    store,
    render: h => h(App)
  }).$mount('#app')
}
getTicket()
// checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
//   .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
//   .then(() => {
//     new Vue({
//       router,
//       store,
//       render: h => h(App)
//     }).$mount('#app')
//   })
