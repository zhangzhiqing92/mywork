import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
let menu=[
  //默认路径下显示该路由
  {
    path: '/',
    name: 'login',
    component: () => import('@/views/login/login'),
    children:[
      {
        path:'/',
        component: () => import('@/views/login/login')
      }
    ]
  },{
    path: '/index',
    name: 'index',
    component: () => import('@/views/home/index')
  }
]
export default new Router({
  routes: menu
})
