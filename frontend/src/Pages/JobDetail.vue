<script setup>
import { useRoute } from 'vue-router'
import { useJobDetail } from '../composables/useJobDetail'
import { formatPostedDate } from '../utils/dateFormat'
import { useAuthStore } from '../stores/auth'
import { useHasAppliedQuery } from '../composables/useHasAppliedQuery'

const route = useRoute()
const jobId = route.params.id
const auth = useAuthStore()
const {
    job,
    isLoading,
    isError,
    error,
    resumeUrl,
    successMessage,
    errorMessage,
    isSubmitting,
    submitApplication,
} = useJobDetail(jobId)

const { data: hasApplied, isLoading: isChecking } = useHasAppliedQuery(jobId)

</script>

<template>
    <div class="max-w-4xl mx-auto px-4 py-12">
        <div v-if="isLoading" class="text-center text-gray-600">
            Loading job details...
        </div>

        <div v-else-if="isError" class="text-center text-red-600">
            {{ error.message || 'Failed to load job.' }}
        </div>

        <div v-else class="bg-white rounded-2xl shadow-lg transition-all duration-300 p-8 border border-gray-100">
            <div class="flex justify-between items-start mb-6">
                <div class="flex-1">
                    <h1 class="text-3xl font-bold text-gray-900 mb-3">
                        {{ job.title }}
                    </h1>
                    <div class="flex items-center gap-2">
                        <span
                            class="inline-flex items-center bg-gradient-to-r from-sky-100 to-blue-100 text-sky-700 px-3 py-1.5 rounded-full text-xs font-semibold">
                            <svg class="w-3.5 h-3.5 mr-1.5" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                                stroke-width="2">
                                <rect x="2" y="7" width="20" height="14" rx="2" ry="2" />
                                <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path>
                            </svg>
                            {{ job.department }}
                        </span>
                    </div>
                </div>
            </div>

            <p class="text-gray-600 text-sm leading-relaxed mb-6">
                {{ job.description }}
            </p>

            <div class="flex flex-wrap items-center gap-4 pt-4 border-t border-gray-100 mb-8">
                <div class="flex items-center text-sm text-gray-500">
                    <svg class="w-4 h-4 mr-1.5 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                        stroke-width="2">
                        <path d="M20 10c0 6-8 12-8 12s-8-6-8-12a8 8 0 0 1 16 0Z"></path>
                        <circle cx="12" cy="10" r="3"></circle>
                    </svg>
                    <span class="font-medium">{{ job.location }}</span>
                </div>

                <div class="flex items-center text-xs text-gray-400 ml-auto">
                    <svg class="w-4 h-4 mr-1.5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                        <circle cx="12" cy="12" r="10"></circle>
                        <polyline points="12 6 12 12 16 14"></polyline>
                    </svg>
                    Posted {{ formatPostedDate(job.postedAt) }}
                </div>
            </div>

            <!-- Application Section -->
            <div class="mt-8">
                <!-- If Authenticated -->
                <div v-if="auth.isAuthenticated" class="bg-sky-50 p-6 rounded-xl border border-sky-100">
                    <h2 class="text-lg font-semibold text-gray-800 mb-4">
                        Apply for this job
                    </h2>

                    <div v-if="isChecking" class="text-gray-500">Checking application status...</div>

                    <div v-else-if="hasApplied" class="text-green-600 font-medium">
                        ✅ You have already applied to this job.
                    </div>

                    <div v-else>
                        <div class="mb-4">
                            <label for="resumeUrl" class="block text-sm font-medium text-gray-700 mb-1">
                                Resume URL
                            </label>
                            <input v-model="resumeUrl" id="resumeUrl" type="url" placeholder="https://your-resume.com"
                                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-sky-400" />
                        </div>

                        <button :disabled="isSubmitting" @click="submitApplication"
                            class="bg-gradient-to-r from-sky-500 to-blue-500 hover:from-sky-600 hover:to-blue-600 text-white px-5 py-2.5 rounded-xl font-semibold text-sm shadow-md hover:shadow-lg transition-all duration-200">
                            {{ isSubmitting ? 'Submitting...' : 'Submit Application' }}
                        </button>

                        <p v-if="successMessage" class="text-green-600 mt-4">{{ successMessage }}</p>
                        <p v-if="errorMessage" class="text-red-600 mt-4">{{ errorMessage }}</p>
                    </div>
                </div>

                <!-- If NOT Authenticated -->
                <div v-else class="text-center text-gray-600 mt-8 border border-yellow-100 bg-blue-50 p-6 rounded-xl">
                    <p class="text-lg font-medium mb-2">You need to be logged in to apply for this job.</p>
                    <p class="text-sm">
                        <router-link to="/login" class="text-sky-600 font-semibold hover:underline">
                            Click here to log in
                        </router-link>
                    </p>
                </div>
            </div>


        </div>
    </div>
</template>
