import { useQuery } from '@tanstack/vue-query'
import axiosClient from '../api/axiosClient'

export const useHasAppliedQuery = (jobId) => {
  return useQuery({
    queryKey: ['hasApplied', jobId],
    queryFn: async () => {
      const response = await axiosClient.get('/applications/check', {
        params: { jobId },
      })
      return response.data // true or false
    },
    enabled: !!jobId, // don't run if jobId is not available
  })
}
