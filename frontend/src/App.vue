<script setup>
import { onMounted } from 'vue'
import { useAuthStore } from './stores/auth'
import Navbar from './components/Navbar.vue'
import { VueQueryDevtools } from '@tanstack/vue-query-devtools'

const authStore = useAuthStore()

onMounted(() => {
  authStore.initializeAuth()
})
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-sky-50 to-blue-50">
    <Navbar />
    <router-view />
      <VueQueryDevtools />

  </div>
</template>