// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import Home from './views/Home'
import 'element-ui/lib/theme-chalk/index.css';
import Http from './common/http'
import qs from "qs";
import msg from './common/msg'
import DialogDrag from '../static/DialogDrag'

Date.prototype.Format = function (fmt) { //日期格式化
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

Vue.use(ElementUI,{size:'small'});
Vue.use(Http);

Vue.component("home",Home);

Vue.prototype.$qs = qs;
Vue.prototype.msg = msg;


Vue.config.productionTip = false

//使用钩子函数对路由进行权限跳转
// router.beforeEach((to, from, next) => {
//   const role = localStorage.getItem('ms_username');
//   if (!role && to.path !== '/login') {
//     next('/login');
//   } else if (to.meta.permission) {
//     // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
//     role === 'admin' ? next() : next('/403');
//   } else {
//     // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
//     if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
//       Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
//         confirmButtonText: '确定'
//       });
//     } else {
//       next();
//     }
//   }
// })
/* eslint-disable no-new */

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
