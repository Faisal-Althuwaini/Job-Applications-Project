<script setup>
import { ref } from 'vue'
import { useJobs } from '../../composables/useJobs'

const {
  jobs,
  isLoading,
  error,
  form,
  formError,
  isEditing,
  showForm,
  saveJob,
  deleteJob,
  resetForm,
  openEditJobForm
} = useJobs()

// Departments options for the select
const departments = [
  'IT',
  'Engineering',
  'Product',
  'Design',
  'Marketing',
  'Sales',
]

// Handler to open form for adding new job
function openNewJobForm() {
  resetForm()
}
</script>

<template>
  <div class="max-w-7xl mx-auto px-6 py-8">
    <!-- Header Section -->
    <div class="mb-8">
      <div class="flex items-center justify-between">
        <div>
          <h2 class="text-3xl font-bold bg-gradient-to-r from-sky-600 to-blue-600 bg-clip-text text-transparent mb-2">
            Jobs Management
          </h2>
          <p class="text-gray-600">Manage and organize all job postings</p>
        </div>
        <button
          @click="openNewJobForm"
          class="bg-gradient-to-r from-sky-500 to-blue-500 hover:from-sky-600 hover:to-blue-600 text-white px-6 py-3 rounded-xl font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-200 flex items-center gap-2"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
            <path d="M12 4v16m8-8H4"></path>
          </svg>
          Add New Job
        </button>
      </div>
    </div>

    <!-- Loading State -->
    <div v-if="isLoading" class="text-center py-20">
      <div class="inline-block animate-spin rounded-full h-12 w-12 border-4 border-sky-200 border-t-sky-500"></div>
      <p class="mt-4 text-gray-600 font-medium">Loading jobs...</p>
    </div>

    <!-- Error State -->
    <div v-if="error" class="bg-red-50 border border-red-200 rounded-xl p-6 text-center">
      <svg class="w-12 h-12 text-red-500 mx-auto mb-3" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
        <path d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
      </svg>
      <p class="text-red-700 font-semibold">Error loading jobs. Please try again later.</p>
    </div>

    <!-- Jobs Table -->
    <div v-if="jobs && jobs.length > 0" class="bg-white rounded-2xl shadow-lg overflow-hidden border border-gray-100">
      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead>
            <tr class="bg-gradient-to-r from-sky-50 to-blue-50">
              <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                <div class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                    <path d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path>
                  </svg>
                  Job Title
                </div>
              </th>
              <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                <div class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                    <path d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4"></path>
                  </svg>
                  Department
                </div>
              </th>
              <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                <div class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                    <path d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                    <path d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                  </svg>
                  Location
                </div>
              </th>
              <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                <div class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                    <path d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                  </svg>
                  Posted Date
                </div>
              </th>
              <th class="px-6 py-4 text-center text-xs font-bold text-gray-700 uppercase tracking-wider">
                Actions
              </th>
            </tr>
          </thead>
          <tbody class="divide-y divide-gray-100">
            <tr 
              v-for="job in jobs" 
              :key="job.id" 
              class="hover:bg-sky-50/50 transition-colors duration-150 group"
            >
              <td class="px-6 py-4 cursor-pointer" @click="openEditJobForm(job)">
                <div class="font-semibold text-gray-900 group-hover:text-sky-600 transition-colors">
                  {{ job.title }}
                </div>
              </td>
              <td class="px-6 py-4 cursor-pointer" @click="openEditJobForm(job)">
                <span class="inline-flex items-center px-3 py-1 rounded-full text-xs font-medium bg-sky-100 text-sky-700">
                  {{ job.department }}
                </span>
              </td>
              <td class="px-6 py-4 text-gray-700 cursor-pointer" @click="openEditJobForm(job)">
                {{ job.location }}
              </td>
              <td class="px-6 py-4 text-gray-600 text-sm cursor-pointer" @click="openEditJobForm(job)">
                {{ new Date(job.postedAt).toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' }) }}
              </td>
              <td class="px-6 py-4">
                <div class="flex items-center justify-center gap-2">
                  <button
                    @click.stop="openEditJobForm(job)"
                    class="inline-flex items-center gap-1.5 px-3 py-1.5 bg-sky-100 hover:bg-sky-200 text-sky-700 font-semibold rounded-lg transition-all duration-200 hover:shadow-md text-sm"
                    title="Edit"
                  >
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                      <path d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                    </svg>
                    Edit
                  </button>
                  <button
                    @click.stop="deleteJob(job.id)"
                    class="inline-flex items-center gap-1.5 px-3 py-1.5 bg-red-100 hover:bg-red-200 text-red-700 font-semibold rounded-lg transition-all duration-200 hover:shadow-md text-sm"
                    title="Delete"
                  >
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                      <path d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                    </svg>
                    Delete
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Empty State -->
    <div v-if="jobs && jobs.length === 0" class="bg-white rounded-2xl shadow-lg border border-gray-100 p-12 text-center">
      <div class="max-w-md mx-auto">
        <div class="w-20 h-20 bg-sky-100 rounded-full flex items-center justify-center mx-auto mb-4">
          <svg class="w-10 h-10 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
            <path d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path>
          </svg>
        </div>
        <h3 class="text-xl font-bold text-gray-900 mb-2">No jobs yet</h3>
        <p class="text-gray-600 mb-6">Get started by creating your first job posting</p>
        <button
          @click="openNewJobForm"
          class="bg-gradient-to-r from-sky-500 to-blue-500 hover:from-sky-600 hover:to-blue-600 text-white px-6 py-2.5 rounded-xl font-semibold shadow-md hover:shadow-lg transition-all duration-200"
        >
          Create First Job
        </button>
      </div>
    </div>

    <!-- Job Form Modal -->
    <div
      v-if="showForm"
      class="fixed inset-0 bg-black/60 backdrop-blur-sm flex items-center justify-center p-4 z-50"
      @click.self="showForm = false"
    >
      <div class="bg-white rounded-2xl shadow-2xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
        <!-- Modal Header -->
        <div class="sticky top-0 bg-gradient-to-r from-sky-500 to-blue-500 text-white px-6 py-4 rounded-t-2xl flex items-center justify-between">
          <h3 class="text-2xl font-bold">
            {{ isEditing ? 'Edit Job' : 'Add New Job' }}
          </h3>
          <button
            @click="showForm = false"
            class="text-white hover:bg-white/20 rounded-lg p-1 transition-colors"
          >
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
              <path d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </button>
        </div>

        <!-- Modal Body -->
        <div class="p-6 space-y-5">
          <div>
            <label class="block mb-2 font-semibold text-gray-700 text-sm">Job Title *</label>
            <input
              v-model="form.title"
              type="text"
              class="w-full border-2 border-gray-200 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-sky-400 focus:border-sky-400 transition-all"
              placeholder="e.g. Senior Software Engineer"
            />
          </div>

          <div>
            <label class="block mb-2 font-semibold text-gray-700 text-sm">Department *</label>
            <div class="relative">
              <select
                v-model="form.department"
                class="w-full border-2 border-gray-200 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-sky-400 focus:border-sky-400 appearance-none bg-white cursor-pointer transition-all font-medium"
              >
                <option value="" disabled>Select department</option>
                <option v-for="dep in departments" :key="dep" :value="dep">
                  {{ dep }}
                </option>
              </select>
              <div class="absolute inset-y-0 right-3 flex items-center pointer-events-none">
                <svg class="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                  <path d="m6 9 6 6 6-6"></path>
                </svg>
              </div>
            </div>
          </div>

          <div>
            <label class="block mb-2 font-semibold text-gray-700 text-sm">Location *</label>
            <input
              v-model="form.location"
              type="text"
              class="w-full border-2 border-gray-200 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-sky-400 focus:border-sky-400 transition-all"
              placeholder="e.g. New York, NY or Remote"
            />
          </div>

          <div>
            <label class="block mb-2 font-semibold text-gray-700 text-sm">Description *</label>
            <textarea
              v-model="form.description"
              rows="5"
              class="w-full border-2 border-gray-200 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-sky-400 focus:border-sky-400 transition-all resize-none"
              placeholder="Describe the role, responsibilities, and requirements..."
            ></textarea>
          </div>

          <div v-if="formError" class="bg-red-50 border border-red-200 rounded-xl p-4 flex items-start gap-3">
            <svg class="w-5 h-5 text-red-600 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
              <path d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <p class="text-red-700 font-semibold text-sm">{{ formError }}</p>
          </div>
        </div>

        <!-- Modal Footer -->
        <div class="px-6 py-4 bg-gray-50 rounded-b-2xl flex justify-end gap-3 border-t border-gray-100">
          <button
            @click="showForm = false"
            class="px-6 py-2.5 rounded-xl border-2 border-gray-300 font-semibold text-gray-700 hover:bg-gray-100 transition-all"
          >
            Cancel
          </button>
          <button
            @click="saveJob"
            class="bg-gradient-to-r from-sky-500 to-blue-500 hover:from-sky-600 hover:to-blue-600 text-white px-6 py-2.5 rounded-xl font-semibold shadow-md hover:shadow-lg transition-all duration-200 flex items-center gap-2"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
              <path d="M5 13l4 4L19 7"></path>
            </svg>
            {{ isEditing ? 'Update Job' : 'Create Job' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>