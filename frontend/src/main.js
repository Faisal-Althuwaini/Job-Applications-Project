import { createApp } from 'vue'
import { createPinia } from 'pinia'
import './style.css'
import App from './App.vue'
import router from './router'
import { QueryClient, VueQueryPlugin } from '@tanstack/vue-query'

const app = createApp(App)
const pinia = createPinia()
const queryClient = new QueryClient()

app.use(pinia)
app.use(router)
app.use(VueQueryPlugin, {
    queryClient,
})
app.mount('#app')
