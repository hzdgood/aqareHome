import Vue from 'vue'
import Calendar from '@/calendar/index.vue'
import store from './store'
import { userInfo } from '@/config/interFace'
import BaiduMap from 'vue-baidu-map'
import 'ant-design-vue/dist/antd.css'

Vue.use(BaiduMap, {
  ak: 'agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi'
})
Vue.config.productionTip = false
localStorage.clear()

doInfo()

async function doInfo () {
  await userInfo().then(function (response) {
    localStorage.setItem('ticket', response.data.ticket)
    new Vue({
      store,
      render: (h) => h(Calendar)
    }).$mount('#app')
  })
}
