import axiosClient from "../api/axiosClient"

export const getAllUsers = async () => {
  const response = await axiosClient.get('/users')
  return response.data
}

export const deleteUser = async (email) => {
    const response = await axiosClient.delete(`/users/${email}`)
    return response.data
}


export const updateUserRole = async (email, role) => {
  try {
    const response = await axiosClient.put(
      `/users/${email}/role`,
      { role }
    );
    return response.data;
  } catch (error) {
    console.error('❌ Role update failed:', error.response?.data || error.message);
    throw error;
  }
};