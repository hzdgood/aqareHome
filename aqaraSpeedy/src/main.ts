import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { userInfo, fetchUserId, fetchSignatures, config } from '@/config/interFace'
import { checkRedirect, initSdk, invoke } from 'wecom-sidebar-jssdk'
import Cookies from 'js-cookie'
import 'ant-design-vue/dist/antd.css'

Vue.config.productionTip = false
localStorage.clear()

// 获取伙伴云ticket
async function getTicket () {
  await userInfo().then(function (response) {
    localStorage.setItem('ticket', response.data.ticket)
    new Vue({
      router,
      store,
      render: (h) => h(App)
    }).$mount('#app')
  })
}

const doInfo = async () => {
  const result = await invoke('getContext') // 获取类型
  localStorage.setItem('contactType', result.entry) // 联系类型
  if (result.entry === 'single_chat_tools') {
    const res = await invoke('getCurExternalContact')
    const localName: any = Cookies.get('userId')
    localStorage.setItem('userId', res.userId)
    localStorage.setItem('localName', localName)
    getTicket()
  } else if (result.entry === 'group_chat_tools') {
    const localName: any = Cookies.get('userId')
    localStorage.setItem('localName', localName)
    getTicket()
  }
}

checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => doInfo())
