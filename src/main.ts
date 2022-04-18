import Vue from 'vue'
import App from './App.vue'
import Chat from './Chat.vue'
import schemeEdit from '@/components/foot/footEdit/schemeEdit.vue'
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
    render: (h) => h(App)
  }).$mount('#app')
}

// 进入群页面
async function getChat () {
  const ticket = await userInfo()
  localStorage.setItem('ticket', ticket)
  Vue.config.productionTip = false
  new Vue({
    router,
    store,
    render: (h) => h(Chat)
  }).$mount('#app')
}

// 检查URl
function checkUrl () {
  const url = window.location.href
  if (url.split('#')[1] === '/upload') {
    new Vue({
      router,
      store,
      render: (h) => h(schemeEdit)
    }).$mount('#app')
    return false
  } else {
    return true
  }
}

const doInfo = async () => {
  localStorage.clear()
  const result = await invoke('getContext') // 获取类型
  localStorage.setItem('contactType', result.entry) // 联系类型
  if (result.entry === 'single_chat_tools') {
    // 单个客户
    const res = await invoke('getCurExternalContact')
    const userId = res.userId
    localStorage.setItem('userId', userId) // 外部客户userID
    const res1: any = await externalcontact({
      userId: res.userId
    })
    const userName = res1.follow_user[0].remark
    if (res1.follow_user.length === 1) {
      const localName = res1.follow_user[0].userid
      localStorage.setItem('localName', localName) // 登入用户名称
    } else {
      const localName = res1.follow_user[1].userid
      localStorage.setItem('localName', localName) // 登入用户名称
    }
    localStorage.setItem('userName', userName) // 外部客户名称
    if (checkUrl()) {
      getTicket()
    }
  } else {
    // 群或者其他
    const result = await invoke('getCurExternalChat')
    localStorage.setItem('chatID', result.chatId) // 群ID
    if (checkUrl()) {
      getChat()
    }
  }
}

checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => doInfo())
