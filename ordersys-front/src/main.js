import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import 'xe-utils'
import VXETable from 'vxe-table'
import 'vxe-table/lib/index.css'

axios.defaults.baseURL = 'http://localhost:8086'
Vue.prototype.$http = axios

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(VXETable)
Vue.prototype.$XModal = VXETable.modal

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
