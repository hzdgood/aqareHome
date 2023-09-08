import { createRouter, createWebHistory } from 'vue-router'

import LoginView from '../views/LoginView.vue'
import ForgotView from '../views/ForgotView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/',
      name: 'forgot',
      component: ForgotView
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
      props: true,
      children: [
        {
          path: '/workSheet',
          name: 'workSheet',
          component: import('../components/WorkSheetView.vue')
        },{
          path: '/project',
          name: 'project',
          component: () => import('../components/ProjectView.vue')
        },{
          path: '/measure',
          name: 'measure',
          component: () => import('../components/MeasureView.vue')
        },{
          path: '/commission',
          name: 'commission',
          component: () => import('../components/CommissionView.vue')
        },{
          path: '/write',
          name: 'write',
          component: () => import('../components/WriteView.vue')
        },{
          path: '/subWriter',
          name: 'subWriter',
          component: () => import('../components/subview/writerInfo.vue')
        },
        {
          path: '/subWork',
          name: 'subWork',
          component: () => import('../components/subview/workInfo.vue')
        },{
          path: '/subSchemem',
          name: 'subSchemem',
          component: () => import('../components/subview/schememInfo.vue')
        },{
          path: '/subMeasure',
          name: 'subMeasure',
          component: () => import('../components/subview/measureInfo.vue')
        },{
          path: '/workEdit',
          name: 'workEdit',
          component: () => import('../components/edit/workEdit.vue')
        },{
          path: '/uploadImg',
          name: 'uploadImg',
          component: () => import('../components/edit/uploadImg.vue')
        },{
          path: '/handoverInfo',
          name: 'handoverInfo',
          component: () => import('../components/subview/handoverInfo.vue')
        }
      ]
    }
  ]
})

export default router
