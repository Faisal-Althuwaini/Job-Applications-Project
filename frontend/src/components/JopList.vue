<script setup>
import { computed } from 'vue';
import JobCard from './JobCard.vue';

// ✅ Make sure prop name is 'jobs' not 'jops'
const props = defineProps({
    jobs: {  // ← THIS MUST BE 'jobs' to match :jobs="jobs" in App.vue
        type: Array,
        required: true
    },
    searchTerm: {
        type: String,
        default: ''
    },
    selectedDepartment: {
        type: String,
        default: 'all'
    }
})

const emit = defineEmits(['apply'])

const filteredJobs = computed(() => {
    return props.jobs.filter(job => {
        const matchesSearch =
            job.title.toLowerCase().includes(props.searchTerm.toLowerCase()) ||
            job.description.toLowerCase().includes(props.searchTerm.toLowerCase())

        const matchesDepartment =
            props.selectedDepartment === 'all' ||
            job.department === props.selectedDepartment

        return matchesSearch && matchesDepartment
    })
})

const handleApply = (jobId) => {
    emit('apply', jobId)
}
</script>

<template>
    <div class="max-w-7xl mx-auto px-4 py-12">
        <h3 class="text-2xl font-bold text-gray-900 mb-6">
            {{ filteredJobs.length }} Open Position{{ filteredJobs.length !== 1 ? 's' : '' }}
        </h3>

        
        <div v-if="filteredJobs.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <JobCard v-for="job in filteredJobs" :key="job.id" :job="job" @apply="handleApply" />
        </div>

        <div v-if="filteredJobs.length === 0" class="text-center py-12">
            <svg class="mx-auto w-16 h-16 text-gray-400 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <p class="text-xl text-gray-600">No jobs found matching your criteria</p>
            <p class="text-gray-500 mt-2">Try adjusting your search or filters</p>
        </div>
    </div>
</template>