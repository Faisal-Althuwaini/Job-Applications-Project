<script setup>
import { ref } from 'vue'
import HeroSection from '../components/HeroSection.vue'
import JopList from '../components/JopList.vue'
import AppFooter from '../components/AppFooter.vue'
import { useJobsQuery } from '../composables/useJobsQuery'
import JobCardSkeleton from '../components/JobCardSkeleton.vue'
import { useRouter } from 'vue-router'

const searchTerm = ref('')
const selectedDepartment = ref('all')
const router = useRouter()
// const jobs = ref([
//   {
//     id: 1,
//     title: "Senior Software Engineer",
//     department: "Engineering",
//     location: "Remote",
//     description: "We're looking for an experienced software engineer to join our growing team.",
//     posted: "2 days ago",
//   },
//   {
//     id: 2,
//     title: "Product Manager",
//     department: "Product",
//     location: "New York, NY",
//     description: "Lead product strategy and work with cross-functional teams.",
//     posted: "1 week ago",
//   },
//   {
//     id: 3,
//     title: "UX Designer",
//     department: "Design",
//     location: "San Francisco, CA",
//     description: "Create beautiful and intuitive user experiences for our products.",
//     posted: "3 days ago",
//   },
//   {
//     id: 4,
//     title: "Marketing Specialist",
//     department: "Marketing",
//     location: "Los Angeles, CA",
//     description: "Drive marketing campaigns and brand awareness initiatives.",
//     posted: "5 days ago",
//   },
//   {
//     id: 5,
//     title: "Sales Executive",
//     department: "Sales",
//     location: "Chicago, IL",
//     description: "Build relationships with clients and drive revenue growth.",
//     posted: "1 week ago",
//   }
// ])

const { data: jobs, isLoading , error} = useJobsQuery()


const handleApply = (jobId) => {
  router.push(`/job/${jobId}`)
}
</script>

<template>
  <div>
    <HeroSection v-model:searchTerm="searchTerm" v-model:selectedDepartment="selectedDepartment" />
    
    <div v-if="isLoading" class="max-w-7xl mx-auto px-4 py-12">
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <JobCardSkeleton v-for="n in 6" :key="n" />
      </div>
    </div>


    <JopList v-else :jobs="jobs" :searchTerm="searchTerm" :selectedDepartment="selectedDepartment" :isLoading="isLoading" @apply="handleApply" />
    <div class="max-w-7xl mx-auto px-4 py-12 flex items-center justify-center" v-if="!jobs">
      <p class="text-lg">Ops, something went wrong. please try again later.</p>
    </div>
    <AppFooter />
  </div>
</template>
