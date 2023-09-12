import axios from 'axios'
const httpUrl = 'http://localhost:8081' // 测试url
// const httpUrl = 'http://aqara.club:8081' // 生产环境

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

// const UploadPost = async (url: string, data: object) => {
//   const headers = {
//     'Content-Type': 'multipart/form-data',
//   }
//   const response = await axios({
//     method: 'post',
//     url: url,
//     data: data,
//     headers: headers
//   })
//   return response
// }
// export const uploadFile = async (formData: object, req: any) => {
//   const url = httpUrl + req
//   const info = await UploadPost(url, formData)
//   return info.data
// }
// export const getCoordinate = async (formData: object) => {
//   const url = httpUrl + '/user/getCoordinate'
//   const info = await axios({
//     method: 'post',
//     url: url,
//     data: formData,
//   })
//   return info.data
// }
