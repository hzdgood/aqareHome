import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import projectInfo from '@/views/projectInfo.vue'
import followUp from '@/views/followUp.vue'
import scheme from '@/views/scheme.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'projectInfo',
    component: projectInfo
  },
  {
    path: '/followUp',
    name: 'followUp',
    component: followUp
  },
  {
    path: '/scheme',
    name: 'scheme',
    component: scheme
  },
  {
    path: '/upload',
    name: 'scheme',
    component: scheme
  }
]

const router = new VueRouter({
  routes
})

export default router
