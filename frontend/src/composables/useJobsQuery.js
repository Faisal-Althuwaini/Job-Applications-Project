import { useQuery } from '@tanstack/vue-query'
import { getJobs } from '../services/jobsServices'

export function useJobsQuery() {
  return useQuery({
    queryKey: ['jobs'],
    queryFn: getJobs,
    staleTime: 1000 * 60 * 5, 
    retry: 1 
  })
}