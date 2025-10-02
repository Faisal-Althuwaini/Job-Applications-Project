<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()
const route = useRoute()

const navItems = [
  { name: 'Jobs', path: '/admin/jobs' },
  { name: 'Applications', path: '/admin/applications' },
  { name: 'Users', path: '/admin/users' },
]

const isActive = (path) => route.path === path
</script>

<template>
  <div class="min-h-screen flex bg-gray-100">
    <!-- Sidebar -->
    <aside class="w-64 bg-white border-r shadow-sm sticky top-0 h-screen">
      <div class="p-6 text-2xl font-bold text-sky-600">Admin Panel</div>
      <nav class="mt-4">
        <ul>
          <li
            v-for="item in navItems"
            :key="item.path"
            @click="router.push(item.path)"
            class="px-6 py-3 cursor-pointer hover:bg-sky-50"
            :class="{
              'bg-sky-100 font-semibold text-sky-700': isActive(item.path),
              'text-gray-700': !isActive(item.path)
            }"
          >
            {{ item.name }}
          </li>
        </ul>
      </nav>
    </aside>

    <!-- Main content -->
    <main class="flex-1 p-6 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>
