import axios from 'axios'

const httpUrl = 'http://localhost:8081' // 测试url

const post = async (url: string, data: object) => {
  const ticket: any = localStorage.getItem('ticket')
  const headers = {
    'X-Huoban-Ticket': ticket
  }
  const response = await axios({
    method: 'post',
    url: url,
    data: data,
    headers: headers
  })
  return response
}

export const treeList = async (reqUrl:any, data: any) => {
  const response = await axios({
    method: 'post',
    url: httpUrl + reqUrl,
    data: data
  })
  return response.data
}
