import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { userInfo, fetchUserId, fetchSignatures, config } from '@/config/interFace'
import { checkRedirect, initSdk, invoke } from 'wecom-sidebar-jssdk'
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

const doInfo = async () => {
  const result = await invoke('getContext') // 获取类型
  localStorage.setItem('contactType', result.entry) // 联系类型
  if (result.entry === 'single_chat_tools') {
    getTicket()
  } else if (result.entry === 'group_chat_tools') {
    getTicket()
  }
}

checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => doInfo())
