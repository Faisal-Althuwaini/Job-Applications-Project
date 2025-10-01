import axiosClient from "../api/axiosClient"

export const getJobs = async () => {
  try {
    const response = await axiosClient.get('/jobs')
    return response.data
  } catch (error) {
    console.error('Failed to fetch jobs:', error)
    throw error.response?.data || 'Failed to fetch jobs'
  }
}

export const getJobById = async (jobId) => {
  const response = await axiosClient.get(`/jobs/${jobId}`)
  return response.data

}

export const submitJobApplication = async (jobId, resumeUrl) => {
  const response = await axiosClient.post(`/applications`, {
    resumeUrl,
    jobId
  })
  return response.data
}