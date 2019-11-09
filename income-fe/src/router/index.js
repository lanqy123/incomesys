import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Demo from '@/views/Demo'



Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [{
    path: '/',
    name: '首页',
    component: Home,
    meta:{
      breadNumber:0
    }
  },{
    path: '/demo1',
    name: 'Demo',
    component: Demo
  }
  ]
})
