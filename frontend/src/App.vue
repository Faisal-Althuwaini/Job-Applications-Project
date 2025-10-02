<script setup>
import { onMounted } from 'vue'
import { useAuthStore } from './stores/auth'
import Navbar from './components/Navbar.vue'
import { VueQueryDevtools } from '@tanstack/vue-query-devtools'
import { Toaster } from 'vue-sonner'
import 'vue-sonner/style.css'

const authStore = useAuthStore()

onMounted(() => {
  authStore.initializeAuth()
})
</script>

<template>
          <Toaster closeButton="true" richColors   />

  <div class="min-h-screen bg-gradient-to-br from-sky-50 to-blue-50">
    <Navbar />
    <router-view />
      <VueQueryDevtools />


  </div>
</template>