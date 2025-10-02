import { useQuery, useQueryClient } from '@tanstack/vue-query';
import { getApplications, deleteApplication as deleteApplicationAPI } from '../services/applicationsServices';

export function useApplications() {
  const queryClient = useQueryClient();

  // Fetch applications
  const { data: applications, isLoading, error, refetch } = useQuery({
    queryKey: ['applications'],
    queryFn: getApplications,
    staleTime: 1000 * 60 * 5,
    retry: 1,
  });

  // Delete application
  async function deleteApplication(applicationId) {
    if (!confirm('Are you sure you want to delete this application?')) return;
    try {
      await deleteApplicationAPI(applicationId);
      await queryClient.invalidateQueries(['applications']);
    } catch (e) {
      alert(e.message || 'Error deleting application.');
    }
  }

  return {
    applications,
    isLoading,
    error,
    refetch,
    deleteApplication,
  };
}
