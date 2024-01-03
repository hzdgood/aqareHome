import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component:  () => import('../views/LoginView.vue'),
    },
    {
      path: '/page',
      name: 'page',
      component: () => import('../views/PageView.vue')
    }
  ]
})
export default router
