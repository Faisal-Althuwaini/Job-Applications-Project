import { ref } from 'vue'
import { useQuery, useQueryClient } from '@tanstack/vue-query'
import { getJobs, saveJob as saveJobAPI, deleteJob as deleteJobAPI } from '../services/jobsServices'

export function useJobs() {
    const formError = ref('')
    const isEditing = ref(false)
    const showForm = ref(false)
    const form = ref({
        id: null,
        title: '',
        department: '',
        location: '',
        description: '',
        postedAt: '',
    })

    const queryClient = useQueryClient()

    const { data: jobs, isLoading, error, refetch } = useQuery({
        queryKey: ['jobs'],
        queryFn: getJobs,
        staleTime: 1000 * 60 * 5,
        retry: 1,
    })



    // Save (create/update) job
    async function saveJob() {
        formError.value = ''
        if (!form.value.title || !form.value.department || !form.value.location || !form.value.description) {
            formError.value = 'Please fill in all required fields.'
            return
        }
        try {
            await saveJobAPI(form.value, isEditing.value)
            await queryClient.invalidateQueries(['jobs'])
            showForm.value = false
        } catch (e) {
            formError.value = e.message || 'Error saving job.'
        }
    }

    // Delete job
    async function deleteJob(jobId) {
        if (!confirm('Are you sure you want to delete this job?')) return
        try {
            await deleteJobAPI(jobId)
            await queryClient.invalidateQueries(['jobs'])
        } catch (e) {
            alert(e.message || 'Error deleting job.')
        }
    }

    // Reset form for new job
    function resetForm() {
        form.value = {
            id: null,
            title: '',
            department: '',
            location: '',
            description: '',
            postedAt: '',
        }
        formError.value = ''
        isEditing.value = false
        showForm.value = true
    }

    // Open form with job data for editing
    function openEditJobForm(job) {
        form.value = { ...job }
        formError.value = ''
        isEditing.value = true
        showForm.value = true
    }

    return {
        jobs,
        isLoading,
        error,
        refetch,
        form,
        formError,
        isEditing,
        showForm,
        saveJob,
        deleteJob,
        resetForm,
        openEditJobForm,
    }
}
