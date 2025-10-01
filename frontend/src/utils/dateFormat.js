
export const formatPostedDate = (isoDate) => {
  const now = new Date()
  const postedDate = new Date(isoDate)

  const diffTime = now - postedDate
  const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24))

  if (diffDays === 0) return 'today'
  if (diffDays === 1) return '1 day ago'
  return `${diffDays} days ago`
}