import Vue from 'vue'
import App from './App.vue'
import Chat from './Chat.vue'
import Calendar from '@/calendar/index.vue'
import upload from '@/upload/index.vue'
import router from './router'
import store from './store'
import { userInfo, fetchUserId, fetchSignatures, externalcontact, config } from '@/config/interFace'
import { checkRedirect, initSdk, invoke } from 'wecom-sidebar-jssdk'
import Cookies from 'js-cookie'
import BaiduMap from 'vue-baidu-map'
import 'ant-design-vue/dist/antd.css'

Vue.use(BaiduMap, {
  ak: 'agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi'
})
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

// 进入群页面
async function getChat () {
  await userInfo().then(function (response) {
    localStorage.setItem('ticket', response.data.ticket)
    new Vue({
      router,
      store,
      render: (h) => h(Chat)
    }).$mount('#app')
  })
}

const doInfo = async () => {
  const result = await invoke('getContext') // 获取类型
  localStorage.setItem('contactType', result.entry) // 联系类型
  if (result.entry === 'single_chat_tools') {
    // 单个客户
    const res = await invoke('getCurExternalContact')
    const userId = res.userId
    localStorage.setItem('userId', userId) // 外部客户userID
    const res1: any = await externalcontact(userId)
    const userName = res1.external_contact.name
    const avatar = res1.external_contact.avatar
    const localName: any = Cookies.get('userId')
    localStorage.setItem('userName', userName)
    localStorage.setItem('avatar', avatar)
    localStorage.setItem('localName', localName)

    // await invoke('sendChatMessage', {
    //   msgtype: 'text',
    //   enterChat: false,
    //   text: {
    //     content: '111111'
    //   }
    // })

    getTicket()
  } else if (result.entry === 'group_chat_tools') {
    const localName: any = Cookies.get('userId')
    const result = await invoke('getCurExternalChat')
    localStorage.setItem('userName', '')
    localStorage.setItem('localName', localName)
    localStorage.setItem('chatID', result.chatId) // 群ID
    getChat()
  } else {
    const url = window.location.href
    await userInfo().then(function (response) {
      localStorage.setItem('ticket', response.data.ticket)
      if (url.split('#')[1] === '/Calendar') {
        new Vue({
          router,
          store,
          render: (h) => h(Calendar)
        }).$mount('#app')
      } else if (url.split('#')[1] === '/upload') {
        new Vue({
          router,
          store,
          render: (h) => h(upload)
        }).$mount('#app')
      }
    })
  }
}

checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => doInfo())
