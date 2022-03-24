import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'ant-design-vue/dist/antd.css'
import '@/css/aqara.css'
import { userInfo, fetchUserId, fetchSignatures, externalcontact, config } from '@/config/interFace'
import { checkRedirect, initSdk, invoke } from 'wecom-sidebar-jssdk'

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
  const res = await invoke('getCurExternalContact')
  const userId = res.userId
  localStorage.setItem('userId', userId) // 外部客户userID
  const obj = {
    userId: res.userId
  }
  const res1: any = await externalcontact(obj)
  const userName = res1.follow_user[0].remark
  if (res1.follow_user.length === 1) {
    const localName = res1.follow_user[0].userid
    localStorage.setItem('userName', userName)
    localStorage.setItem('localName', localName)
  } else {
    const localName = res1.follow_user[1].userid
    localStorage.setItem('localName', localName)
    localStorage.setItem('userName', userName)
  }
  // const localName = res1.follow_user[1].oper_userid
  // 外部客户名称
  // localStorage.setItem('localName', localName) // 登入用户ID
}
checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => testApi())
  .then(() => {
    getTicket()
  })
