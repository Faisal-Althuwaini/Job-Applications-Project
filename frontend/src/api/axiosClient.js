import axios from "axios";
import { useAuthStore } from "../stores/auth";

const axiosClient = axios.create({
    baseURL: 'http://localhost:8080'
})

axiosClient.interceptors.request.use(
    (config) => {
        const auth = useAuthStore()
        const token = auth.token;
        
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }
        return config
    },
        (error) => Promise.reject(error)

)

export default axiosClient