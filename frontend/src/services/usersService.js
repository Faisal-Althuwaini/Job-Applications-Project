import axiosClient from "../api/axiosClient"

export const getAllUsers = async () => {
  const response = await axiosClient.get('/users')
  return response.data
}

export const deleteUser = async (email) => {
    const response = await axiosClient.delete(`/users/${email}`)
    return response.data
}