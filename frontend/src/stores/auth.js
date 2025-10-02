import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import axiosClient from '../api/axiosClient'
import { jwtDecode } from 'jwt-decode'

export const useAuthStore = defineStore('auth', () => {
  const user = ref((localStorage.getItem('user')) || "User")
  const token = ref(localStorage.getItem('token') || null)
  const loading = ref(false)
  const error = ref(null)

  const userRole = computed(() => {
  if (!token.value) return null

  try {
    const decoded = jwtDecode(token.value)
    return decoded.role || null
  } catch (e) {
    console.error('Invalid token:', e)
    return null
  }
})

  // Getters
  const isAuthenticated = computed(() => !!token.value)
  const currentUser = computed(() => user.value)

  // Actions
  const login = async (credentials) => {
    loading.value = true
    error.value = null

    try {
      const response = await axiosClient.post('/auth/login', credentials)

      token.value = response.data.token
      user.value = response.data.user.name

      localStorage.setItem('token', token.value)
      localStorage.setItem('user', (response.data.user.name))

      return { success: true }
    } catch (err) {
      console.log(err);
      
      error.value = err.response?.data || 'Login failed'
      return { success: false, error: error.value }
    } finally {
      loading.value = false
    }
  }

  const register = async (userData) => {
    loading.value = true
    error.value = null

    try {
      const response = await axiosClient.post('/auth/register', userData)

      token.value = response.data.token
      user.value = response.data.user

      localStorage.setItem('token', token.value)
      localStorage.setItem('user', JSON.stringify(user.value))

      return { success: true }
    } catch (err) {
      error.value = err.response?.data || 'Registration failed'
      return { success: false, error: error.value }
    } finally {
      loading.value = false
    }
  }

  const logout = () => {
    user.value = null
    token.value = null
    error.value = null

    localStorage.removeItem('token')
    localStorage.removeItem('user')
  }

  const initializeAuth = () => {
    const storedToken = localStorage.getItem('token')
    const storedUser = localStorage.getItem('user')

    if (storedToken && storedUser) {
      token.value = storedToken
      user.value = storedUser
    }
  }

  return {
    user,
    token,
    loading,
    error,

    isAuthenticated,
    currentUser,
    userRole,

    login,
    register,
    logout,
    initializeAuth
  }
})
