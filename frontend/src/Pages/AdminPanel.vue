<script setup>
import { ref } from 'vue'

const currentTab = ref('jobs')

const navItems = [
  { name: 'Jobs', key: 'jobs' },
  { name: 'Applications', key: 'applications' },
  { name: 'Users', key: 'users' }
]
</script>

<template>
  <div class="min-h-screen flex bg-gray-100">
    <!-- Sidebar -->
    <aside class="w-64 bg-white shadow-md hidden md:block">
      <div class="p-6 text-2xl font-bold text-sky-600">Admin Panel</div>
      <nav class="mt-4">
        <ul>
          <li
            v-for="item in navItems"
            :key="item.key"
            @click="currentTab = item.key"
            class="px-6 py-3 cursor-pointer hover:bg-sky-50 text-gray-700"
            :class="{ 'bg-sky-100 font-semibold text-sky-700': currentTab === item.key }"
          >
            {{ item.name }}
          </li>
        </ul>
      </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
      <h1 class="text-2xl font-bold mb-4 text-gray-800 capitalize">{{ currentTab }}</h1>

      <div v-if="currentTab === 'jobs'">
        <!-- Jobs Content -->
        <p class="text-gray-600">Here you can manage job listings.</p>
        <!-- You can include a JobTable.vue here -->
      </div>

      <div v-if="currentTab === 'applications'">
        <!-- Applications Content -->
        <p class="text-gray-600">Here you can manage submitted applications.</p>
        <!-- You can include an ApplicationsTable.vue here -->
      </div>

      <div v-if="currentTab === 'users'">
        <!-- Users Content -->
        <p class="text-gray-600">Here you can manage user accounts.</p>
        <!-- You can include a UsersTable.vue here -->
      </div>
    </main>
  </div>
</template>

<style scoped>
/* Optional mobile support */
@media (max-width: 768px) {
  aside {
    display: none;
  }
}
</style>
