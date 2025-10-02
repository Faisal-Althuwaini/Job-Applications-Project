import { useMutation, useQueryClient } from "@tanstack/vue-query";
import { deleteUser } from "../services/usersService";

export function useDeleteUser() {
    const queryClient = useQueryClient()

    return useMutation({
        mutationFn: (email) => deleteUser(email),
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['users'] })
        }
    })
} 