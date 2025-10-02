<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()
const route = useRoute()

const navItems = [
  { 
    name: 'Jobs', 
    path: '/admin/jobs',
    icon: 'M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z'
  },
  { 
    name: 'Applications', 
    path: '/admin/applications',
    icon: 'M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z'
  },
  { 
    name: 'Users', 
    path: '/admin/users',
    icon: 'M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z'
  },
]

const isActive = (path) => route.path === path
</script>

<template>
  <div class="min-h-screen flex bg-gradient-to-br from-gray-50 to-blue-50">
    <!-- Sidebar -->
    <aside class="w-72 bg-white border-r border-gray-200 shadow-lg sticky top-0 h-screen flex flex-col">
      <!-- Logo/Header -->
      <div class="p-6 border-b border-gray-100">
        <div class="flex items-center gap-3">
          <div class="w-10 h-10 bg-gradient-to-br from-sky-400 to-blue-500 rounded-xl flex items-center justify-center shadow-md">
            <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
              <path d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"></path>
            </svg>
          </div>
          <div>
            <h1 class="text-xl font-bold bg-gradient-to-r from-sky-600 to-blue-600 bg-clip-text text-transparent">Admin Panel</h1>
            <p class="text-xs text-gray-500">Management Dashboard</p>
          </div>
        </div>
      </div>

      <!-- Navigation -->
      <nav class="flex-1 p-4 overflow-y-auto">
        <ul class="space-y-2">
          <li
            v-for="item in navItems"
            :key="item.path"
            @click="router.push(item.path)"
            class="group relative cursor-pointer rounded-xl transition-all duration-200"
            :class="{
              'bg-gradient-to-r from-sky-400 to-blue-500 shadow-md': isActive(item.path),
              'hover:bg-gray-50': !isActive(item.path)
            }"
          >
            <div class="flex items-center gap-3 px-4 py-3">
              <svg 
                class="w-5 h-5 transition-colors" 
                :class="{
                  'text-white': isActive(item.path),
                  'text-gray-400 group-hover:text-sky-500': !isActive(item.path)
                }"
                fill="none" 
                stroke="currentColor" 
                viewBox="0 0 24 24"
                stroke-width="2"
              >
                <path :d="item.icon"></path>
              </svg>
              <span 
                class="font-semibold transition-colors"
                :class="{
                  'text-white': isActive(item.path),
                  'text-gray-700 group-hover:text-gray-900': !isActive(item.path)
                }"
              >
                {{ item.name }}
              </span>
            </div>
            <!-- Active indicator -->
            <div 
              v-if="isActive(item.path)"
              class="absolute right-0 top-1/2 -translate-y-1/2 w-1 h-8 bg-white rounded-l-full"
            ></div>
          </li>
        </ul>
      </nav>

      <!-- Footer -->
      <div class="p-4 border-t border-gray-100">
        <div class="flex items-center gap-3 px-4 py-3 bg-gray-50 rounded-xl">
          <div class="w-8 h-8 bg-gradient-to-br from-sky-400 to-blue-500 rounded-full flex items-center justify-center text-white font-semibold text-sm">
            A
          </div>
          <div class="flex-1 min-w-0">
            <p class="text-sm font-semibold text-gray-900 truncate">Admin User</p>
            <p class="text-xs text-gray-500 truncate">admin@company.com</p>
          </div>
        </div>
      </div>
    </aside>

    <!-- Main content -->
    <main class="flex-1 px-6 overflow-y-auto">
      <router-view />
    </main>
  </div>
</template>