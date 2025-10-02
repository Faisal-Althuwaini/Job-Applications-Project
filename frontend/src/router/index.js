import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '../stores/auth'
import Home from '../Pages/Home.vue'
import Login from '../Pages/Login.vue'
import Register from '../Pages/Register.vue'
import JobDetail from '../Pages/JobDetail.vue'
import AdminLayout from '../layouts/AdminLayout.vue'
import JobsTable from '../Pages/admin/JobsTable.vue'
import ApplicationsTable from '../Pages/admin/ApplicationsTable.vue'
import UsersTable from '../Pages/admin/UsersTable.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { guest: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    meta: { guest: true }
  },
  {
    path: '/job/:id',
    name: "Job",
    component: JobDetail,
    props: true
  },
    {
    path: '/admin',
    component: AdminLayout,
    children: [
      {
        path: '',
        redirect: '/admin/jobs' // default tab
      },
      {
        path: 'jobs',
        name: 'AdminJobs',
        component: JobsTable,
      },
      {
        path: 'applications',
        name: 'AdminApplications',
        component: ApplicationsTable,
      },
      {
        path: 'users',
        name: 'AdminUsers',
        component: UsersTable,
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})


router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()

    if (to.path.startsWith('/admin') && (!authStore.isAuthenticated || authStore.userRole !== 'ADMIN')) {
    return next('/')
  }

  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
  } else if (to.meta.guest && authStore.isAuthenticated) {
    next('/')
  } else {
    next()
  }
})

export default router
