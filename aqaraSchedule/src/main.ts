import Vue from 'vue'
import Calendar from '@/calendar/index.vue'
import store from './store'
import BaiduMap from 'vue-baidu-map'
import 'ant-design-vue/dist/antd.css'

Vue.use(BaiduMap, {
  ak: 'agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi'
})
Vue.config.productionTip = false
localStorage.clear()

new Vue({
  store,
  render: (h) => h(Calendar)
}).$mount('#app')
