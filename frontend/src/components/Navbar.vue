<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()
const isMobileMenuOpen = ref(false)

const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value
}

const handleLogout = () => {
  authStore.logout()
  router.push('/login')
}

const navigateToLogin = () => {
  router.push('/login')
}
</script>

<template>
    <header class="bg-white/80 backdrop-blur-md shadow-sm sticky top-0 z-50 border-b border-gray-100">
        <div class="max-w-7xl mx-auto px-4 py-4">
            <div class="flex items-center justify-between">
                <!-- Logo Section -->
                <router-link to="/" class="flex items-center space-x-3 group cursor-pointer">
                    <div class="relative">
                        <div class="absolute inset-0 bg-gradient-to-r from-sky-400 to-blue-400 rounded-lg blur opacity-30 group-hover:opacity-50 transition-opacity"></div>
                        <svg class="relative w-10 h-10 text-sky-500 transform group-hover:scale-110 transition-transform duration-200" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect>
                            <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path>
                        </svg>
                    </div>
                    <h1 class="text-2xl font-bold bg-gradient-to-r from-sky-400 to-blue-400 bg-clip-text text-transparent">
                        HIRE
                    </h1>
                </router-link>
         
              
                <nav class="hidden md:flex items-center space-x-1">
                    <router-link to="/" class="relative px-4 py-2 text-gray-700 hover:text-sky-500 font-medium transition-colors duration-200 group cursor-pointer">
                        <span class="relative z-10 flex items-center gap-2">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                                <rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect>
                                <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path>
                            </svg>
                            Jobs
                        </span>
                        <span class="absolute bottom-0 left-0 w-0 h-0.5 bg-gradient-to-r from-sky-400 to-blue-400 group-hover:w-full transition-all duration-300"></span>
                    </router-link>
                    
                    <router-link to="/about" class="relative px-4 py-2 text-gray-700 hover:text-sky-500 font-medium transition-colors duration-200 group cursor-pointer">
                        <span class="relative z-10 flex items-center gap-2">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                                <circle cx="12" cy="12" r="10"></circle>
                                <line x1="12" y1="16" x2="12" y2="12"></line>
                                <line x1="12" y1="8" x2="12.01" y2="8"></line>
                            </svg>
                            About
                        </span>
                        <span class="absolute bottom-0 left-0 w-0 h-0.5 bg-gradient-to-r from-sky-400 to-blue-400 group-hover:w-full transition-all duration-300"></span>
                    </router-link>
                    
                    <router-link to="/contact" class="relative px-4 py-2 text-gray-700 hover:text-sky-500 font-medium transition-colors duration-200 group cursor-pointer">
                        <span class="relative z-10 flex items-center gap-2">
                            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                                <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path>
                            </svg>
                            Contact
                        </span>
                        <span class="absolute bottom-0 left-0 w-0 h-0.5 bg-gradient-to-r from-sky-400 to-blue-400 group-hover:w-full transition-all duration-300"></span>
                    </router-link>
                </nav>


                <div class="hidden md:flex items-center space-x-3">
                    <!-- Show user info and logout if authenticated -->
                    <div v-if="authStore.isAuthenticated" class="flex items-center gap-3">
                        <span class="text-gray-700 font-medium">
                            {{ authStore.user || 'User' }}
                        </span>
                        <button
                            @click="handleLogout"
                            class="relative px-5 py-2.5 text-gray-700 hover:text-white font-semibold rounded-xl transition-all duration-300 flex items-center gap-2 overflow-hidden group cursor-pointer"
                        >
                            <span class="absolute inset-0 bg-gradient-to-r from-red-600 to-red-700 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-300 origin-left"></span>
                            <svg class="relative z-10 w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                                <path d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
                            </svg>
                            <span class="relative z-10">Logout</span>
                        </button>
                    </div>
                    <!-- Show sign in button if not authenticated -->
                    <button
                        v-else
                        @click="navigateToLogin"
                        class="relative px-5 py-2.5 text-gray-700 hover:text-white font-semibold rounded-xl transition-all duration-300 flex items-center gap-2 overflow-hidden group cursor-pointer"
                    >
                        <span class="absolute inset-0 bg-gradient-to-r from-sky-400 to-blue-400 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-300 origin-left"></span>
                        <svg class="relative z-10 w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
                            <circle cx="12" cy="7" r="4"></circle>
                        </svg>
                        <span class="relative z-10">Sign In</span>
                    </button>
                </div>

                <!-- Mobile Menu Button -->
                <button 
                    @click="toggleMobileMenu"
                    class="md:hidden p-2 text-gray-700 hover:text-sky-500 transition-colors cursor-pointer"
                    aria-label="Toggle menu"
                >
                    <svg v-if="!isMobileMenuOpen" class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                        <line x1="3" y1="12" x2="21" y2="12"></line>
                        <line x1="3" y1="6" x2="21" y2="6"></line>
                        <line x1="3" y1="18" x2="21" y2="18"></line>
                    </svg>
                    <svg v-else class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                        <line x1="18" y1="6" x2="6" y2="18"></line>
                        <line x1="6" y1="6" x2="18" y2="18"></line>
                    </svg>
                </button>
            </div>

            <!-- Mobile Menu -->
            <transition
                enter-active-class="transition-all duration-300 ease-out"
                enter-from-class="opacity-0 -translate-y-4"
                enter-to-class="opacity-100 translate-y-0"
                leave-active-class="transition-all duration-200 ease-in"
                leave-from-class="opacity-100 translate-y-0"
                leave-to-class="opacity-0 -translate-y-4"
            >
                <div v-if="isMobileMenuOpen" class="md:hidden mt-4 pb-4 space-y-2">
                    <a href="#jobs" class="flex items-center gap-3 px-4 py-3 text-gray-700 hover:text-sky-500 hover:bg-sky-50 rounded-lg font-medium transition-all duration-200 cursor-pointer">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect>
                            <path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path>
                        </svg>
                        Jobs
                    </a>
                    
                    <a href="#about" class="flex items-center gap-3 px-4 py-3 text-gray-700 hover:text-sky-500 hover:bg-sky-50 rounded-lg font-medium transition-all duration-200 cursor-pointer">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <circle cx="12" cy="12" r="10"></circle>
                            <line x1="12" y1="16" x2="12" y2="12"></line>
                            <line x1="12" y1="8" x2="12.01" y2="8"></line>
                        </svg>
                        About
                    </a>
                    
                    <a href="#contact" class="flex items-center gap-3 px-4 py-3 text-gray-700 hover:text-sky-500 hover:bg-sky-50 rounded-lg font-medium transition-all duration-200 cursor-pointer">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path>
                        </svg>
                        Contact
                    </a>

                    <div class="border-t border-gray-200 my-2 pt-2">
                        <div v-if="authStore.isAuthenticated" class="space-y-2">
                            <div class="px-4 py-2 text-gray-700 font-medium">
                                {{ authStore.user?.name || 'User' }}
                            </div>
                            <button
                                @click="handleLogout"
                                class="w-full flex items-center justify-center gap-2 px-4 py-3 bg-gradient-to-r from-red-600 to-red-700 text-white rounded-lg font-semibold shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-200 cursor-pointer"
                            >
                                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                                    <path d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
                                </svg>
                                Logout
                            </button>
                        </div>
                        <button
                            v-else
                            @click="navigateToLogin"
                            class="w-full flex items-center justify-center gap-2 px-4 py-3 bg-gradient-to-r from-sky-400 to-blue-400 text-white rounded-lg font-semibold shadow-md hover:shadow-lg transform hover:scale-105 transition-all duration-200 cursor-pointer"
                        >
                            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                                <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
                                <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                            Sign In
                        </button>
                    </div>
                </div>
            </transition>
        </div>
    </header>
</template>

