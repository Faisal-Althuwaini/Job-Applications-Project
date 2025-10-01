<script setup>
import { formatPostedDate } from '../utils/dateFormat';

defineProps({
    job: {
        type: Object,
        required: true
    }
})

const emit = defineEmits(['apply'])
</script>

<template>
    <div class="bg-white rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 p-6 border border-gray-100 hover:border-sky-200 transform hover:-translate-y-1">
        <div class="flex justify-between items-start mb-5">
            <div class="flex-1">
                <h4 class="text-xl font-bold text-gray-900 mb-3 hover:text-sky-500 transition-colors">
                    {{ job.title }}
                </h4>
                <div class="flex items-center gap-2">
                    <span class="inline-flex items-center bg-gradient-to-r from-sky-100 to-blue-100 text-sky-700 px-3 py-1.5 rounded-full text-xs font-semibold">
                       
                        <svg class="w-3.5 h-3.5 mr-1.5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect>
                            <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path>
                        </svg>
                        {{ job.department }}
                    </span>
                </div>
            </div>
            
            <button 
                @click="emit('apply', job.id)"
                class="bg-gradient-to-r from-sky-400 to-blue-400 hover:from-sky-500 hover:to-blue-500 text-white px-5 py-2.5 rounded-xl font-semibold text-sm shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-200 flex items-center gap-2 whitespace-nowrap ml-4"
            >
                
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                    <line x1="22" y1="2" x2="11" y2="13"></line>
                    <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
                </svg>
                Apply
            </button>
        </div>

        <p class="text-gray-600 text-sm leading-relaxed mb-5 line-clamp-2">
            {{ job.description }}
        </p>

        <div class="flex flex-wrap items-center gap-4 pt-4 border-t border-gray-100">
            <div class="flex items-center text-sm text-gray-500">
                <svg class="w-4 h-4 mr-1.5 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
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
                Posted {{ formatPostedDate( job.postedAt) }}
            </div>
        </div>
    </div>
</template>

