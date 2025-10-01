<script setup>
defineProps({
    searchTerm: {
        type: String,
        default: ''
    },
    selectedDepartment: {
        type: String,
        default: 'all'
    }
})

const emit = defineEmits(['update:searchTerm', 'update:selectedDepartment'])
</script>

<template>
    <div class="relative bg-gradient-to-br from-indigo-600 via-purple-600 to-pink-500 text-white py-20 overflow-hidden">
        <!-- Decorative background elements -->
        <div class="absolute inset-0 opacity-10">
            <div class="absolute top-0 left-0 w-96 h-96 bg-white rounded-full filter blur-3xl"></div>
            <div class="absolute bottom-0 right-0 w-96 h-96 bg-white rounded-full filter blur-3xl"></div>
        </div>

        <div class="relative max-w-6xl mx-auto px-4 text-center">
            <!-- Heading Section -->
            <div class="mb-8">
                <h2 class="text-6xl font-extrabold mb-4 animate-fade-in">
                    Find Your Dream Career
                </h2>
                <p class="text-xl text-indigo-100 font-light">
                    Join our team of innovators and make a real impact
                </p>
            </div>

            <!-- Search Bar Container -->
            <div class="max-w-4xl mx-auto">
                <div class="bg-white/95 backdrop-blur-sm rounded-2xl shadow-2xl p-3">
                    <div class="flex flex-col md:flex-row gap-3">
                        <!-- Search Input -->
                        <div class="flex-1 relative group">
                            <div class="absolute inset-y-0 left-4 flex items-center pointer-events-none">
                                <!-- Search Icon (Lucide) -->
                                <svg 
                                    class="w-5 h-5 text-gray-400 group-focus-within:text-indigo-600 transition-colors" 
                                    fill="none" 
                                    stroke="currentColor" 
                                    viewBox="0 0 24 24"
                                    stroke-width="2"
                                >
                                    <circle cx="11" cy="11" r="8"></circle>
                                    <path d="m21 21-4.35-4.35"></path>
                                </svg>
                            </div>
                            <input 
                                type="text" 
                                placeholder="Search job titles, keywords..." 
                                :value="searchTerm"
                                @input="emit('update:searchTerm', $event.target.value)"
                                class="w-full pl-12 pr-4 py-3.5 border-2 border-gray-200 rounded-xl text-gray-800 placeholder-gray-400 focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 outline-none transition-all"
                            />
                        </div>

                        <!-- Department Select -->
                        <div class="relative md:w-64 group">
                            <div class="absolute inset-y-0 left-4 flex items-center pointer-events-none">
                                <!-- Filter Icon (Lucide) -->
                                <svg 
                                    class="w-5 h-5 text-gray-400 group-focus-within:text-indigo-600 transition-colors" 
                                    fill="none" 
                                    stroke="currentColor" 
                                    viewBox="0 0 24 24"
                                    stroke-width="2"
                                >
                                    <polygon points="22 3 2 3 10 12.46 10 19 14 21 14 12.46 22 3"></polygon>
                                </svg>
                            </div>
                            <select 
                                :value="selectedDepartment" 
                                @change="emit('update:selectedDepartment', $event.target.value)"
                                class="w-full pl-12 pr-10 py-3.5 border-2 border-gray-200 rounded-xl text-gray-800 focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 appearance-none bg-white outline-none cursor-pointer transition-all font-medium"
                            >
                                <option value="all">All Departments</option>
                                <option value="Engineering">Engineering</option>
                                <option value="Product">Product</option>
                                <option value="Design">Design</option>
                                <option value="Marketing">Marketing</option>
                                <option value="Sales">Sales</option>
                            </select>
                            <!-- Chevron Down Icon -->
                            <div class="absolute inset-y-0 right-3 flex items-center pointer-events-none">
                                <svg 
                                    class="w-5 h-5 text-gray-400" 
                                    fill="none" 
                                    stroke="currentColor" 
                                    viewBox="0 0 24 24"
                                    stroke-width="2"
                                >
                                    <path d="m6 9 6 6 6-6"></path>
                                </svg>
                            </div>
                        </div>

                        <!-- Search Button -->
                        <button class="bg-indigo-600 hover:bg-indigo-700 text-white px-8 py-3.5 rounded-xl font-semibold shadow-lg hover:shadow-xl transform hover:scale-105 transition-all duration-200 flex items-center justify-center gap-2">
                            <svg 
                                class="w-5 h-5" 
                                fill="none" 
                                stroke="currentColor" 
                                viewBox="0 0 24 24"
                                stroke-width="2"
                            >
                                <circle cx="11" cy="11" r="8"></circle>
                                <path d="m21 21-4.35-4.35"></path>
                            </svg>
                            Search
                        </button>
                    </div>
                </div>

                <!-- Quick Stats -->
                <div class="mt-8 flex flex-wrap justify-center gap-8 text-sm">
                    <div class="flex items-center gap-2">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <path d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"></path>
                        </svg>
                        <span class="text-white/90">500+ Open Positions</span>
                    </div>
                    <div class="flex items-center gap-2">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <path d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"></path>
                        </svg>
                        <span class="text-white/90">50+ Companies</span>
                    </div>
                    <div class="flex items-center gap-2">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <path d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                        </svg>
                        <span class="text-white/90">Verified Jobs</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
@keyframes fade-in {
    from {
        opacity: 0;
        transform: translateY(-10px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.animate-fade-in {
    animation: fade-in 0.8s ease-out;
}
</style>