import { field } from '@/config/config'
export const masterReq = async (userId: any) => {
  const data = {
    where: {
      and: [
        {
          query: { or: [{ eqm: [userId] }] },
          query_option_mappings: [-1],
          field: field.projectUUid
        }
      ]
    },
    offset: 0,
    limit: 20,
    order_by: [
      { field: field.projectUUid, sort: 'desc' },
      { field: field.masterProject, sort: 'asc' }
    ]
  }
  return data
}

export const chatReq = async (chatId: any) => {
  const data = {
    where: {
      and: [
        {
          query: { or: [{ eqm: [chatId] }] },
          query_option_mappings: [-1],
          field: 2200000172376106
        }
      ]
    },
    offset: 0,
    limit: 20
  }
  return data
}
