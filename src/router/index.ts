import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import projectInfo from '@/views/projectInfo.vue'
import followUp from '@/views/followUp.vue'
import workSheet from '@/views/workSheet.vue'
import sendOut from '@/views/sendOut.vue'
import scheme from '@/views/scheme.vue'

import Action from '@/views/test/Actions.vue'
import ExternalUser from '@/views/test/ExternalUser.vue'
import ExternalChat from '@/views/test/ExternalChat.vue'

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
    path: '/scheme',
    name: 'scheme',
    component: scheme
  },
  {
    path: '/upload',
    name: 'scheme',
    component: scheme
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
