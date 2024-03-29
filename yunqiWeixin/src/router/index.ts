import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/project',
      name: 'project',
      component:  () => import('../views/projectView.vue'),
    }, {
      path: '/scheme',
      name: 'scheme',
      component:  () => import('../views/schemeView.vue'),
    }, {
      path: '/collent',
      name: 'collent',
      component:  () => import('../views/collentView.vue'),
    }, {
      path: '/follow',
      name: 'follow',
      component:  () => import('../views/followView.vue'),
    }, {
      path: '/customer',
      name: 'customer',
      component:  () => import('../views/customerView.vue'),
    }
  ]
})
export default router
