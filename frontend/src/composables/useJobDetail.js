import { ref } from 'vue'
import { useQuery, useMutation } from '@tanstack/vue-query'
import { getJobById, submitJobApplication } from '../services/jobsServices'
import { useRouter } from 'vue-router'
import { toast } from 'vue-sonner'

export function useJobDetail(jobId) {
  const selectedFile = ref(null)
  const resumeUrl = ref('')
  const successMessage = ref('')
  const errorMessage = ref('')
  const router = useRouter()
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
    mutationFn: ({ jobId, file }) => submitJobApplication(jobId, file),
    onSuccess: () => {
      successMessage.value = 'Application submitted successfully!'
      errorMessage.value = ''
      selectedFile.value = null
      toast.success("Application submitted successfully!")
      router.push('/')
    },
    onError: (err) => {
      errorMessage.value = err.response?.data?.message || 'Failed to apply.'
      toast.error('Failed to apply')
      successMessage.value = ''
    },
  })

  const submitApplication = (file) => {
    if (!file) {
      // errorMessage.value = 'Please upload your resume file.'
      toast.error('Please upload your resume file.')

      return
    }
    selectedFile.value = file

    applyForJob({ jobId, file })
  }

  return {
    job,
    isLoading,
    isError,
    error,
    selectedFile,
    resumeUrl,
    successMessage,
    errorMessage,
    isSubmitting,
    submitApplication,
  }
}
