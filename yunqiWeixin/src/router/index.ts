import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      props: true,
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
