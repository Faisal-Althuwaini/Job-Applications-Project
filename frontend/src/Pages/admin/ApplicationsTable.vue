<script setup>
import { useApplications } from '../../composables/useApplications';

const { applications, isLoading, error, deleteApplication } = useApplications();
</script>

<template>
  <div class="max-w-7xl mx-auto px-4 py-12">
    <h2 class="text-3xl font-bold mb-6">Applications Management</h2>
    <p class="mb-6 text-gray-600">Review submitted applications here.</p>

    <!-- Loading & Error States -->
    <div v-if="isLoading" class="text-center py-12 text-gray-600">Loading applications...</div>
    <div v-if="error" class="text-red-600 text-center py-12">Error loading applications. Please try again later.</div>

    <!-- Applications Table -->
    <table
      v-if="applications && applications.length > 0"
      class="min-w-full border-collapse rounded-lg overflow-hidden shadow-md"
    >
      <thead class="bg-sky-100">
        <tr>
          <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
            Applicant Name
          </th>
          <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
            Job Title
          </th>
          <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
            Applied On
          </th>
          <th class="px-6 py-3 text-center text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
            Actions
          </th>
        </tr>
      </thead>
      <tbody class="bg-white divide-y divide-gray-200">
        <tr v-for="application in applications" :key="application.id" class="hover:bg-sky-50 transition-colors duration-150">
          <td class="px-6 py-4 whitespace-nowrap text-gray-900 font-medium">
            {{ application.userName || application.user?.name || 'N/A' }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-gray-700">
            {{ application.jobTitle || application.job?.title || 'N/A' }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-gray-500 text-sm">
            {{ new Date(application.appliedAt || application.createdAt || application.appliedDate).toLocaleDateString() }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-center">
            <button
              @click="deleteApplication(application.id)"
              class="inline-block bg-red-200 hover:bg-red-300 text-red-800 font-semibold px-3 py-1.5 rounded-md transition"
              title="Delete"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- No applications -->
    <div v-if="applications && applications.length === 0" class="text-center text-gray-600 py-12">
      No applications found.
    </div>
  </div>
</template>
