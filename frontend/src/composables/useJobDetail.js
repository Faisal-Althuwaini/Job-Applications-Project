import { ref } from 'vue'
import { useQuery, useMutation } from '@tanstack/vue-query'
import { getJobById, submitJobApplication } from '../services/jobsServices'

export function useJobDetail(jobId) {
  const resumeUrl = ref('')
  const successMessage = ref('')
  const errorMessage = ref('')


  const {
    data: job,
    isLoading,
    isError,
    error,
  } = useQuery({
    queryKey: ['job', jobId],
    queryFn: () => getJobById(jobId),
  })


  const { mutate: applyForJob, isPending: isSubmitting } = useMutation({
    mutationFn: () => submitJobApplication(jobId, resumeUrl.value),
    onSuccess: () => {
      successMessage.value = 'Application submitted successfully!'
      errorMessage.value = ''
      resumeUrl.value = ''
    },
    onError: (err) => {
      errorMessage.value = err.response?.data?.message || 'Failed to apply.'
      successMessage.value = ''
    },
  })

  const submitApplication = () => {
    if (!resumeUrl.value.trim()) {
      errorMessage.value = 'Please enter a valid resume URL.'
      return
    }
    applyForJob()
  }

  return {
    job,
    isLoading,
    isError,
    error,
    resumeUrl,
    successMessage,
    errorMessage,
    isSubmitting,
    submitApplication,
  }
}
