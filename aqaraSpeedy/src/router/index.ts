import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import company from '@/views/company.vue'
import person from '@/views/person.vue'
import team from '@/views/team.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'company',
    component: company
  },
  {
    path: '/person',
    name: 'person',
    component: person
  },
  {
    path: '/team',
    name: 'team',
    component: team
  }
]

const router = new VueRouter({
  routes
})

export default router
