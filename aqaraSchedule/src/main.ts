import Vue from 'vue'
import Calendar from '@/calendar/index.vue'
import store from './store'
import { userInfo, fetchUserId, fetchSignatures, config } from '@/config/interFace'
import { checkRedirect, initSdk } from 'wecom-sidebar-jssdk'
import BaiduMap from 'vue-baidu-map'
import 'ant-design-vue/dist/antd.css'

Vue.use(BaiduMap, {
  ak: 'agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi'
})
Vue.config.productionTip = false
localStorage.clear()

const doInfo = async () => {
  await userInfo().then(function (response) {
    localStorage.setItem('ticket', response.data.ticket)
    new Vue({
      store,
      render: (h) => h(Calendar)
    }).$mount('#app')
  })
}

checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => doInfo())
