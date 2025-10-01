<script setup>
import { ref } from 'vue'
import Navbar from './components/Navbar.vue'
import HeroSection from './components/HeroSection.vue'
import JopList from './components/JopList.vue'
import AppFooter from './components/AppFooter.vue'

const searchTerm = ref('')
const selectedDepartment = ref('all')

const jobs = ref([
  {
    id: 1,
    title: "Senior Software Engineer",
    department: "Engineering",
    location: "Remote",
    description: "We're looking for an experienced software engineer to join our growing team.",
    posted: "2 days ago",
  },
  {
    id: 2,
    title: "Product Manager",
    department: "Product",
    location: "New York, NY",
    description: "Lead product strategy and work with cross-functional teams.",
    posted: "1 week ago",
  },
  {
    id: 3,
    title: "UX Designer",
    department: "Design",
    location: "San Francisco, CA",
    description: "Create beautiful and intuitive user experiences for our products.",
    posted: "3 days ago",
  },
  {
    id: 4,
    title: "Marketing Specialist",
    department: "Marketing",
    location: "Los Angeles, CA",
    description: "Drive marketing campaigns and brand awareness initiatives.",
    posted: "5 days ago",
  },
  {
    id: 5,
    title: "Sales Executive",
    department: "Sales",
    location: "Chicago, IL",
    description: "Build relationships with clients and drive revenue growth.",
    posted: "1 week ago",
  }
])

const handleApply = (jobId) => {
  alert(`Application submitted for job ID: ${jobId}`)
}
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100">
    <Navbar />
    <HeroSection v-model:searchTerm="searchTerm" v-model:selectedDepartment="selectedDepartment" />
    <JopList :jobs="jobs" :searchTerm="searchTerm" :selectedDepartment="selectedDepartment" @apply="handleApply" />
    <AppFooter />
  </div>
</template>