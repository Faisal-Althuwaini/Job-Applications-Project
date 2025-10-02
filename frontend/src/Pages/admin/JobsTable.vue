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
  <div class="max-w-7xl mx-auto px-4 py-12">
    <h2 class="text-3xl font-bold mb-6">Jobs Management</h2>

    <button
      @click="openNewJobForm"
      class="mb-6 bg-gradient-to-r from-sky-500 to-blue-500 text-white px-5 py-2.5 rounded-xl font-semibold text-sm shadow-md hover:shadow-lg transition-all duration-200"
    >
      + Add New Job
    </button>

    <!-- Loading and error -->
    <div v-if="isLoading" class="text-center py-12 text-gray-600">
      Loading jobs...
    </div>
    <div v-if="error" class="text-red-600 text-center py-12">
      Error loading jobs. Please try again later.
    </div>
<!-- Jobs Table -->
<table
  v-if="jobs && jobs.length > 0"
  class="min-w-full border-collapse rounded-lg overflow-hidden shadow-md"
>
  <thead class="bg-sky-100">
    <tr>
      <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
        Title
      </th>
      <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
        Department
      </th>
      <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
        Location
      </th>
      <th class="px-6 py-3 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
        Posted
      </th>
      <th class="px-6 py-3 text-center text-sm font-semibold text-gray-700 uppercase tracking-wider border-b border-gray-300">
        Actions
      </th>
    </tr>
  </thead>
  <tbody class="bg-white divide-y divide-gray-200">
    <tr v-for="job in jobs" :key="job.id" class="hover:bg-sky-50 transition-colors duration-150 cursor-pointer">
      <td class="px-6 py-4 whitespace-nowrap text-gray-900 font-medium" @click="openEditJobForm(job)">
        {{ job.title }}
      </td>
      <td class="px-6 py-4 whitespace-nowrap text-gray-700" @click="openEditJobForm(job)">
        {{ job.department }}
      </td>
      <td class="px-6 py-4 whitespace-nowrap text-gray-700" @click="openEditJobForm(job)">
        {{ job.location }}
      </td>
      <td class="px-6 py-4 whitespace-nowrap text-gray-500 text-sm" @click="openEditJobForm(job)">
        {{ new Date(job.postedAt).toLocaleDateString() }}
      </td>
      <td class="px-6 py-4 whitespace-nowrap text-center">
        <button
          @click.stop="openEditJobForm(job)"
          class="inline-block mr-2 bg-sky-200 hover:bg-sky-300 text-sky-800 font-semibold px-3 py-1.5 rounded-md transition"
          title="Edit"
        >
          Edit
        </button>
        <button
          @click.stop="deleteJob(job.id)"
          class="inline-block bg-red-200 hover:bg-red-300 text-red-800 font-semibold px-3 py-1.5 rounded-md transition"
          title="Delete"
        >
          Delete
        </button>
      </td>
    </tr>
  </tbody>
</table>


    <!-- No jobs message -->
    <div v-if="jobs && jobs.length === 0" class="text-center text-gray-600 py-12">
      No jobs available. Please add a new job.
    </div>

    <!-- Job Form Modal -->
    <div
      v-if="showForm"
      class="fixed inset-0 bg-black/70 backdrop-blur-sm bg-opacity-50 flex items-center justify-center p-4 z-50"
    >
      <div
        class="bg-white rounded-2xl shadow-lg max-w-lg w-full p-6 relative"
      >
        <h3 class="text-2xl font-bold mb-4">
          {{ isEditing ? 'Edit Job' : 'Add New Job' }}
        </h3>

        <div class="space-y-4">
          <div>
            <label class="block mb-1 font-medium text-gray-700">Title</label>
            <input
              v-model="form.title"
              type="text"
              class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-sky-400"
              placeholder="Job title"
            />
          </div>

          <div>
            <label class="block mb-1 font-medium text-gray-700">Department</label>
            <select
              v-model="form.department"
              class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-sky-400"
            >
              <option value="" disabled>Select department</option>
              <option v-for="dep in departments" :key="dep" :value="dep">
                {{ dep }}
              </option>
            </select>
          </div>

          <div>
            <label class="block mb-1 font-medium text-gray-700">Location</label>
            <input
              v-model="form.location"
              type="text"
              class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-sky-400"
              placeholder="Location"
            />
          </div>

          <div>
            <label class="block mb-1 font-medium text-gray-700">Description</label>
            <textarea
              v-model="form.description"
              rows="4"
              class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-sky-400"
              placeholder="Job description"
            ></textarea>
          </div>

          <p v-if="formError" class="text-red-600 font-semibold">{{ formError }}</p>
        </div>

        <div class="mt-6 flex justify-end space-x-4">
          <button
            @click="showForm = false"
            class="px-5 py-2.5 rounded-xl border border-gray-300 font-semibold text-gray-700 hover:bg-gray-100 transition"
          >
            Cancel
          </button>
          <button
            @click="saveJob"
            class="bg-gradient-to-r from-sky-500 to-blue-500 text-white px-5 py-2.5 rounded-xl font-semibold text-sm shadow-md hover:shadow-lg transition-all duration-200"
          >
            {{ isEditing ? 'Update Job' : 'Create Job' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
