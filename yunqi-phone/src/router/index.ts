import { createRouter, createWebHistory } from 'vue-router'

import LoginView from '../views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
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
    // {
    //   path: '/workSheet',
    //   name: 'workSheet',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../components/WorkSheetView.vue')
    // },
    // {
    //   path: '/project',
    //   name: 'project',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../components/ProjectView.vue')
    // },
    // {
    //   path: '/measure',
    //   name: 'measure',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../components/MeasureView.vue')
    // }
  ]
})

export default router
