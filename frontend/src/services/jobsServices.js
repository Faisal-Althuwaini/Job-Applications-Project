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


export const saveJob = async (job, isEditing) => {
  try {
    const url = isEditing ? `/jobs/${job.id}` : '/jobs'
    const method = isEditing ? 'put' : 'post'
    const response = await axiosClient({
      method,
      url,
      data: job,
    })
    return response.data
  } catch (error) {
    console.error('Failed to save job:', error)
    throw error.response?.data || 'Failed to save job'
  }
}


export const deleteJob = async (jobId) => {
  try {
    const response = await axiosClient.delete(`/jobs/${jobId}`)
    return response.data
  } catch (error) {
    console.error('Failed to delete job:', error)
    throw error.response?.data || 'Failed to delete job'
  }
}

export const submitJobApplication = async (jobId, file) => {
  const applicationData = { jobId };

  const formData = new FormData();
  formData.append(
    'applicationData',
    new Blob([JSON.stringify(applicationData)], { type: 'application/json' })
  );
  formData.append('resumeFile', file);

  const response = await axiosClient.post('/applications', formData);
  return response.data;
};