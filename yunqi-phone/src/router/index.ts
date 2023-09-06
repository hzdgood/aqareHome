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
        },
        {
          path: '/project',
          name: 'project',
          component: () => import('../components/ProjectView.vue')
        },
        {
          path: '/measure',
          name: 'measure',
          component: () => import('../components/MeasureView.vue')
        },
        {
          path: '/commission',
          name: 'commission',
          component: () => import('../components/CommissionView.vue')
        },
        {
          path: '/write',
          name: 'write',
          component: () => import('../components/WriteView.vue')
        }        
      ]
    },
  ]
})

export default router
