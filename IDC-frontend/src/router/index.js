import Vue from 'vue'
import Router from 'vue-router'
import Index from "../views/Index";
import Login from "../views/Login/Login";
import Register from "../views/Register/Register";

Vue.use(Router)

const router = new Router({
  mode:"history",
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    }
  ]
})

// 全局前置路由守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === "/register") {
    next()
  }
  if (!localStorage.getItem("token")) {
    alert("你还没有登录，请重新登录")
    router.replace("/login")
  }
  next();
})
export default router
