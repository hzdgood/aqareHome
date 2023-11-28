import axios from 'axios'

export const httpUrl = 'https://aqara.club:8082' // 生产环境
// export const httpUrl = 'http://localhost:8082'

const http = axios.create({
  baseURL: 'http://localhost:8082/'
})

export const httpGet = async (url: string, data: object) => {
  const res = await http.get(httpUrl + url, { params: data })
  return res.data
}

export const httpPost = async (url: string, data: object) => {
  const res = await http.post(httpUrl + url, data)
  return res.data
}

export const getCoordinate = async (formData: object) => {
  const url = httpUrl + '/common/getCoordinate'
  const info = await axios({
    method: 'post',
    url: url,
    data: formData
  })
  return info.data
}
