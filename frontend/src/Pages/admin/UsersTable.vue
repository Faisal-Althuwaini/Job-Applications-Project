<script setup>
import { ref } from 'vue';
import { useUsers } from '../../composables/useUsers';
import { useDeleteUser } from '../../composables/useDeleteUser';

const { data: users, isLoading, error } = useUsers();
const { mutate: deleteUserMutation, isPending } = useDeleteUser()

const showUserModal = ref(false);
const selectedUser = ref(null);


function closeUserModal() {
    showUserModal.value = false;
    selectedUser.value = null;
}

function deleteUser(email) {
    if (!confirm('Are you sure you want to delete this user?')) return
    deleteUserMutation(email)
}
</script>

<template>
    <div class="max-w-7xl mx-auto px-6 py-8">
        <!-- Header Section -->
        <div class="mb-8">
            <div class="flex items-center justify-between">
                <div>
                    <h2
                        class="text-3xl font-bold bg-gradient-to-r from-sky-600 to-blue-600 bg-clip-text text-transparent mb-2">
                        User Management
                    </h2>
                    <p class="text-gray-600">Review and manage all registered users</p>
                </div>
                <div class="flex items-center gap-4 text-sm">
                    <div class="flex items-center gap-2 bg-white px-4 py-2 rounded-xl shadow-sm border border-gray-100">
                        <svg class="w-5 h-5 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                            stroke-width="2">
                            <path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                        </svg>
                        <span class="font-semibold text-gray-700">{{ users?.length || 0 }} Total</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Loading State -->
        <div v-if="isLoading" class="text-center py-20">
            <div class="inline-block animate-spin rounded-full h-12 w-12 border-4 border-sky-200 border-t-sky-500">
            </div>
            <p class="mt-4 text-gray-600 font-medium">Loading users...</p>
        </div>

        <!-- Error State -->
        <div v-if="error" class="bg-red-50 border border-red-200 rounded-xl p-6 text-center">
            <svg class="w-12 h-12 text-red-500 mx-auto mb-3" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                stroke-width="2">
                <path d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <p class="text-red-700 font-semibold">Error loading users. Please try again later.</p>
        </div>

        <!-- Users Table -->
        <div v-if="users && users.length > 0"
            class="bg-white rounded-2xl shadow-lg overflow-hidden border border-gray-100">
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead>
                        <tr class="bg-gradient-to-r from-sky-50 to-blue-50">
                            <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                                <div class="flex items-center gap-2">
                                    <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor"
                                        viewBox="0 0 24 24" stroke-width="2">
                                        <path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z">
                                        </path>
                                    </svg>
                                    Name
                                </div>
                            </th>
                            <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                                <div class="flex items-center gap-2">
                                    <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor"
                                        viewBox="0 0 24 24" stroke-width="2">
                                        <path
                                            d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z">
                                        </path>
                                    </svg>
                                    Email
                                </div>
                            </th>
                            <th class="px-6 py-4 text-left text-xs font-bold text-gray-700 uppercase tracking-wider">
                                <div class="flex items-center gap-2">
                                    <svg class="w-4 h-4 text-sky-500" fill="none" stroke="currentColor"
                                        viewBox="0 0 24 24" stroke-width="2">
                                        <path d="M12 14l9-5-9-5-9 5 9 5z"></path>
                                        <path d="M12 14l6.16-3.422a12.083 12.083 0 01.34 6.086L12 14z"></path>
                                        <path d="M12 14L5.84 10.578a12.083 12.083 0 01.34-6.086L12 14z"></path>
                                    </svg>
                                    Role
                                </div>
                            </th>

                            <th class="px-6 py-4 text-center text-xs font-bold text-gray-700 uppercase tracking-wider">
                                Actions
                            </th>
                        </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-100">
                        <tr v-for="user in users" :key="user.id"
                            class="hover:bg-sky-50/50 transition-colors duration-150 group">
                            <td class="px-6 py-4">
                                <div class="flex items-center gap-3">
                                    <div
                                        class="w-10 h-10 bg-gradient-to-br from-sky-400 to-blue-500 rounded-full flex items-center justify-center text-white font-semibold text-sm flex-shrink-0">
                                        {{ (user.name || 'N')[0].toUpperCase() }}
                                    </div>
                                    <div class="font-semibold text-gray-900 group-hover:text-sky-600 transition-colors">
                                        {{ user.name || 'N/A' }}
                                    </div>
                                </div>
                            </td>
                            <td class="px-6 py-4 text-gray-700">
                                {{ user.email || 'N/A' }}
                            </td>
                            <td class="px-6 py-4">
                                <span
                                    class="inline-flex items-center px-3 py-1 rounded-full text-xs font-medium bg-sky-100 text-sky-700">
                                    {{ user.role || 'User' }}
                                </span>
                            </td>

                            <td class="px-6 py-4">
                                <div class="flex items-center justify-center gap-2">
                                    <button @click="deleteUser(user.email)" :disabled="isPending"
                                        class="inline-flex items-center gap-1.5 px-3 py-1.5 bg-red-100 hover:bg-red-200 text-red-700 font-semibold rounded-lg transition-all duration-200 hover:shadow-md text-sm"
                                        title="Delete">
                                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                                            stroke-width="2">
                                            <path
                                                d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16">
                                            </path>
                                        </svg>
                                        Delete
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Empty State -->
        <div v-if="users && users.length === 0"
            class="bg-white rounded-2xl shadow-lg border border-gray-100 p-12 text-center">
            <div class="max-w-md mx-auto">
                <div class="w-20 h-20 bg-sky-100 rounded-full flex items-center justify-center mx-auto mb-4">
                    <svg class="w-10 h-10 text-sky-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                        stroke-width="2">
                        <path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                    </svg>
                </div>
                <h3 class="text-xl font-bold text-gray-900 mb-2">No users found</h3>
                <p class="text-gray-600 mb-6">Users will appear here once registered in the system</p>
            </div>
        </div>

        <!-- User Modal -->
        <div v-if="showUserModal && selectedUser"
            class="fixed inset-0 bg-black/60 backdrop-blur-sm flex items-center justify-center p-4 z-50"
            @click.self="closeUserModal">
            <div class="bg-white rounded-2xl shadow-2xl max-w-3xl w-full p-6">
                <div class="flex justify-between items-center mb-6">
                    <h3 class="text-2xl font-semibold text-gray-900">User Details</h3>
                    <button class="text-gray-500 hover:text-gray-900 transition" @click="closeUserModal"
                        aria-label="Close">
                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" stroke-width="2">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                    </button>
                </div>

                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <h4 class="text-lg font-semibold text-sky-600 mb-2">Name</h4>
                        <p class="text-gray-800">{{ selectedUser.name }}</p>
                    </div>
                    <div>
                        <h4 class="text-lg font-semibold text-sky-600 mb-2">Email</h4>
                        <p class="text-gray-800">{{ selectedUser.email }}</p>
                    </div>
                    <div>
                        <h4 class="text-lg font-semibold text-sky-600 mb-2">Role</h4>
                        <p class="text-gray-800">{{ selectedUser.role }}</p>
                    </div>
                    <div>
                        <h4 class="text-lg font-semibold text-sky-600 mb-2">Joined</h4>
                        <p class="text-gray-800">{{ new Date(selectedUser.joinedAt ||
                            selectedUser.createdAt).toLocaleDateString() }}</p>
                    </div>
                    <div class="md:col-span-2">
                        <h4 class="text-lg font-semibold text-sky-600 mb-2">About</h4>
                        <p class="text-gray-700 whitespace-pre-line">{{ selectedUser.about || 'No description provided.'
                            }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
