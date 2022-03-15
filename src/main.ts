import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'ant-design-vue/dist/antd.css'
import '@/css/aqara.css'
import { userInfo, fetchUserId, fetchSignatures, config } from '@/config/interFace'
import { checkRedirect, initSdk, invoke, asyncCall, call, SignRes } from 'wecom-sidebar-jssdk'

const data = {
  application_id: '1002449',
  application_secret: '5F5aMmUtCBbhNM4ahhYeG1wMK4mstbsG85VpI9Qw'
}

// 获取伙伴云ticket
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

const testApi = async () => {
  try {
    const res1 = await invoke('getCurExternalContact')
    localStorage.setItem('userId', res1.userId)
  } catch (e: any) {
    console.log(e.message)
  }
}

checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => testApi())
  .then(() => {
    getTicket()
  })
