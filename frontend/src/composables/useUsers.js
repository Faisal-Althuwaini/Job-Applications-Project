import { useQuery } from '@tanstack/vue-query'
import { getAllUsers } from '../services/usersService'

export function useUsers() {
    return useQuery({
        queryKey: ['users'],
        queryFn: getAllUsers
    })

}
