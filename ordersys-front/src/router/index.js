import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import MyOrdered from '../views/MyOrdered'
import Menu from '../views/Menu'
import ApplyOrder from '../views/ApplyOrder'
import Manager from '../views/Manager'
import Summary from '../views/Summary'
import Food from '../views/Food'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path:'/home',
    name:'menu',
    component:Menu,
    children:[
      {path:'/myOrdered',component:MyOrdered,meta:{title:"个人订餐列表"}},
      {path:'/applyOrder',component:ApplyOrder,meta:{title:"加班订餐申请"}},
      {path:'/manager',component:Manager,meta:{title:"订单确认"}},
      {path:'/summary',component:Summary,meta:{title:"公司汇总"}},
      {path:'/food',component:Food,meta:{title:"餐品管理"}}
    ]
  },

]

const router = new VueRouter({
  routes
})

/**
 * 登录控制
 */
router.beforeEach((to,from,next) => {
  if (to.path == '/' || sessionStorage.getItem("uname") != null){
    next()
  }else {
    next({path:'/'})
  }
})
export default router
