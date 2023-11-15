import axios from 'axios'

// export const httpUrl = 'https://aqara.club:8082' // 生产环境
export const httpUrl = 'http://localhost:8082'

const huobanUrl = 'https://api.huoban.com' // 伙伴云

const http = axios.create({
  baseURL:'http://localhost:8081/'
})

export const httpGet = async (url: string, data: object) => {
  const res = await http.get( httpUrl + url,{ params: data })
  return res.data
}

export const httpPost = async (url: string, data: object) => {
  const res = await http.post(httpUrl + url, data )
  return res.data
}

export const userInfo = async () => {
  const url = httpUrl + '/huoban/getTicket'
  return await post(url, {})
}

export const addInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId
  const info = await post(url, data)
  return info.data
}

export const uploadFile = async (formData: object) => {
  const url = 'https://upload.huoban.com/v2/file'
  const info = await post(url, formData)
  return info.data
}

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