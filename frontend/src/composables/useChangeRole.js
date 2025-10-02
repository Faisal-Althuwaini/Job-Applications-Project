import { useMutation, useQueryClient } from "@tanstack/vue-query";
import { updateUserRole } from "../services/usersService";
import { toast } from "vue-sonner";

export function useChangeRole() {
    const queryClient = useQueryClient()

    return useMutation({
        mutationFn: ({email,role}) => updateUserRole(email,role),
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['users'] })
            toast.success("Role changed successfully")
        }
    })
} 