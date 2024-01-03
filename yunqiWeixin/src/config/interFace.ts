import axios from 'axios'

export const httpUrl = 'http://aqara.club:8081' // 生产环境
// export const httpUrl = 'http://localhost:8081'

const http = axios.create({
  baseURL:'http://aqara.club:8081/'
})

export const httpGet = async (url: string, data: object) => {
  const res = await http.get( httpUrl + url,{ params: data })
  return res.data
}

export const httpPost = async (url: string, data: object) => {
  const res = await http.post(httpUrl + url, data )
  return res.data
}

export const getUrl = async (url: string, data: object) => {
  const res = await http.get( url,{ params: data })
  return res.data
}

export const httpCode = async (url: string, data: object) => {
  const res = await http.get( httpUrl + url,{ params: data })
  return res.data
}
