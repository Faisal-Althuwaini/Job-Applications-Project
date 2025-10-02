import axiosClient from "../api/axiosClient";

export const getApplications = async () => {
  try {
    const response = await axiosClient.get('/applications');
    return response.data;
  } catch (error) {
    console.error('Failed to fetch applications:', error);
    throw error.response?.data || 'Failed to fetch applications';
  }
}

export const getApplicationById = async (applicationId) => {
  const response = await axiosClient.get(`/applications/${applicationId}`);
  return response.data;
}

export const deleteApplication = async (applicationId) => {
  const response = await axiosClient.delete(`/applications/${applicationId}`);
  return response.data;
}
