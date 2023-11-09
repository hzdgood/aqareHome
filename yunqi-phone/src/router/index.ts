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
      component: () => import('../views/PageView.vue'),
      children: [
        {
          path: '/workCom',
          name: 'workCom',
          component: import('../components/workComView.vue')
        }, {
          path: '/sendSheet',
          name: 'sendSheet',
          component: import('../components/SendSheetView.vue')
        }, {
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
        },{
          path: '/subWork',
          name: 'subWork',
          component: () => import('../components/subview/workInfo.vue')
        },{
          path: '/subSchemem',
          name: 'subSchemem',
          component: () => import('../components/info/schememInfo.vue')
        },{
          path: '/measureInfo',
          name: 'measureInfo',
          component: () => import('../components/subview/measureInfo.vue')
        },{
          path: '/workEdit',
          name: 'workEdit',
          component: () => import('../components/edit/workEdit.vue')
        },{
          path: '/handoverInfo',
          name: 'handoverInfo',
          component: () => import('../components/info/handoverInfo.vue')
        },{
          path: '/personEdit',
          name: 'personEdit',
          component: () => import('../components/edit/personEdit.vue')
        }
      ]
    }
  ]
})

// router.beforeEach(async (to, _from, next) => {
//   const version = localStorage.getItem("version");
//   console.log(to.path);
//   if(version !== '1.0') {
//     next({path: '/'})
//   } else {
//     next()
//   }
// })

export default router
