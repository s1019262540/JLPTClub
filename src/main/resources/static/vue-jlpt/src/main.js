import Vue from 'vue'
import App from './App.vue'
//1. yarn add vue-router@3.x.x 下载VueRouter模块 vue2选择3开头版本， vue3选择4开头版本
//2. 引入vue-router
import VueRouter from 'vue-router'

Vue.config.productionTip = false
//3.安装注册
Vue.use(VueRouter)
//4.创建VueRouter路由对象
const router=new VueRouter()
new Vue({
  render: h => h(App),
  //5. 将路由对象注入实例，关联
  router
}).$mount('#app')
