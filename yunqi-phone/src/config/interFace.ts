import axios from 'axios'
// const httpUrl = 'http://192.168.110.48:8081' // 测试url
const httpUrl = 'http://aqara.club:8082' // 生产环境

// const httpUrl = 'http://localhost:8082'

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
