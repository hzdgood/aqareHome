import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Home from '@/views/Home.vue'
import Action from '@/views/Actions.vue'
import followUp from '@/views/followUp.vue'
import workSheet from '@/views/workSheet.vue'
import sendOut from '@/views/sendOut.vue'
import enginInvestiga from '@/views/enginInvestiga.vue'

import ExternalUser from '@/views/ExternalUser.vue'
import ExternalChat from '@/views/ExternalChat.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/followUp',
    name: 'followUp',
    component: followUp
  },
  {
    path: '/workSheet',
    name: 'workSheet',
    component: workSheet
  },
  {
    path: '/sendOut',
    name: 'sendOut',
    component: sendOut
  },
  {
    path: '/enginInvestiga',
    name: 'enginInvestiga',
    component: enginInvestiga
  },

  {
    path: '/actions',
    name: 'Actions',
    component: Action
  },
  {
    path: '/external-user',
    name: 'ExternalUser',
    component: ExternalUser
  },
  {
    path: '/external-chat',
    name: 'ExternalChat',
    component: ExternalChat
  }
]

const router = new VueRouter({
  routes
})

export default router
