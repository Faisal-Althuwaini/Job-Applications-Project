<script setup>
// JobCard only receives ONE job object, no filtering needed!
defineProps({
    job: {
        type: Object,
        required: true
    }
})

const emit = defineEmits(['apply'])
</script>

<template>
    <div class="bg-white rounded-lg shadow-md hover:shadow-xl transition-shadow p-6">
        <div class="flex justify-between items-start mb-4">
            <div>
                <h4 class="text-2xl font-bold text-gray-900 mb-2">{{ job.title }}</h4>
                <span class="inline-block bg-indigo-100 text-indigo-800 px-3 py-1 rounded-full text-sm font-medium">
                    {{ job.department }}
                </span>
            </div>
            <button @click="emit('apply', job.id)"
                class="bg-indigo-600 text-white px-6 py-2 rounded-md hover:bg-indigo-700 transition font-medium w-32 h-15">
                Apply Now
            </button>
        </div>

        <p class="text-gray-600 mb-4">{{ job.description }}</p>

        <div class="flex flex-wrap gap-4 text-sm text-gray-500">
            <div class="flex items-center">
                <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
                {{ job.location }}
            </div>
            <div class="ml-auto text-gray-400">
                Posted {{ job.posted }}
            </div>
        </div>
    </div>
</template>