import { createApp } from 'vue'
import router from './router'
import { createPinia } from 'pinia'
import 'ant-design-vue/dist/antd.css'
import App from './App.vue'

createApp(App).use(router).use(createPinia).mount('#app')
