import { RouteRecordRaw, createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../component/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  }
]

const router = createRouter({
  history: createWebHashHistory(''),
  routes
})

export default router
