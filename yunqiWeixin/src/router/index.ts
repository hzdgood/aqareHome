import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
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
    }
  ]
})
export default router
