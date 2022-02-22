import axios from 'axios'
export const userInfo = async () => {
  const application = await axios({
    method: 'post',
    url: 'https://api.huoban.com/v2/ticket',
    data: {
      application_id: '1002449',
      application_secret: '5F5aMmUtCBbhNM4ahhYeG1wMK4mstbsG85VpI9Qw'
    },
    timeout: 1000
  })
  return application.data.ticket
}

export const SpaceInfo = async (ticket: any, spaceId: string) => {
  const space = await axios.get('https://api.huoban.com/v2/tables/space/' + spaceId, {
    params: {},
    headers: {
      'X-Huoban-Ticket': ticket
    }
  })
  return space
}

export const TableInfo = async (ticket: any, tableId: string) => {
  console.log(ticket)

  const tableInfo = await axios.get('https://api.huoban.com/v2/table/' + tableId, {
    params: {},
    headers: {
      'X-Huoban-Ticket': ticket
    }
  })
  return tableInfo
}

export const SearchInfo = async (ticket: any, tableId: string, data: object) => {
  const info = await axios({
    method: 'post',
    url: 'https://api.huoban.com/v2/item/table/' + tableId + '/find', // 客户信息表
    data: data,
    headers: {
      'X-Huoban-Ticket': ticket
    },
    timeout: 1000
  })
  return info.data.items
}

export const updateTable = async (ticket: any, item_id: string, data: object) => {
  axios.put('https://api.huoban.com/v2/item/' + item_id, data, {
    headers: {
      'X-Huoban-Ticket': ticket
    }
  }).then(response => (
    console.log(response)
  ))
}