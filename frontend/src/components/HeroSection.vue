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

const emit = defineEmits(['update: searchTerm', 'update: selectedDepartment '])

</script>



<template>
    <div class=" bg-gradient-to-b from-indigo-600 to-purple-600 text-white py-16">
        <div class=" max-w-7xl mx-auto text-center px-4">
            <h2 class="text-5xl font-bold mb-4">Find Your Dream Career</h2>
            <p class="text-xl text-indigo-100 mb-8">Join our team and make an impact</p>

            <!-- SearchBar-->
            <div class=" max-w-3xl mx-auto shadow-lg rounded-lg flex gap-5 bg-white">
                <div class="flex-1 relative">
                    <svg class="absolute left-3 top-3 w-5 h-5 text-gray-400" fill="none" stroke="currentColor"
                        viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                    </svg>
                    <input type="text" placeholder="Search jobs..." :value="searchTerm"
                        @input="emit('update: searchTerm', $event.target.value)"
                        class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-md text-gray-800 focus:ring-2 focus:ring-indigo-500 focus:border-transparent outline-none">

                </div>
                <div class="relative">
                    <svg class="absolute left-3 top-3 w-5 h-5 text-gray-400" fill="none" stroke="currentColor"
                        viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M3 4a1 1 0 011-1h16a1 1 0 011 1v2.586a1 1 0 01-.293.707l-6.414 6.414a1 1 0 00-.293.707V17l-4 4v-6.586a1 1 0 00-.293-.707L3.293 7.293A1 1 0 013 6.586V4z" />
                    </svg>
                    <select :value="selectedDepartment" @change="emit('update:selectedDepartment', $event.target.value)"
                        class="pl-10 pr-8 py-2 border border-gray-300 rounded-md text-gray-800 focus:ring-2 focus:ring-indigo-500 focus:border-transparent appearance-none bg-white outline-none cursor-pointer">
                        <option value="all">All Departments</option>
                        <option value="Engineering">Engineering</option>
                        <option value="Product">Product</option>
                        <option value="Design">Design</option>
                        <option value="Marketing">Marketing</option>
                        <option value="Sales">Sales</option>
                    </select>
                </div>
            </div>


        </div>

    </div>
</template>