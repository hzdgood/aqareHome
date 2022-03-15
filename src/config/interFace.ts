import axios from 'axios'
import { SignRes } from 'wecom-sidebar-jssdk'

let url = window.location.href
url = url.split('#')[0]
let httpUrl = url.substring(0, url.length - 1)
if (httpUrl === 'http://localhost:8080') {
  httpUrl = 'http://localhost:8081'
} else {
  httpUrl = 'http://aqara.club:8081'
}
const huobanUrl = 'https://api.huoban.com'
const ticket: any = localStorage.getItem('ticket')

export const config = {
  // 在 https://work.weixin.qq.com/wework_admin/frame#profile 这里可以找到
  corpId: 'ww9a717b03b06063e3', // 企业ID
  // 在 https://work.weixin.qq.com/wework_admin/frame#apps 里的自建应用里可以找到
  agentId: '1000046'
}

const post = async (url: string, data: object) => {
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

const UploadPost = async (url: string, data: object) => {
  const headers = {
    'Content-Type': 'multipart/form-data'
  }
  const response = await axios({
    method: 'post',
    url: url,
    data: data,
    headers: headers
  })
  return response
}

// code 换取用户身份（需要后端调用企微服务端 API）
export const fetchUserId = async (code: string): Promise<string> => {
  const response = await axios.request({
    method: 'GET',
    url: httpUrl + '/wechat/getUserId',
    params: {
      code: code
    }
  })
  return response.data
}

// 获取签名接口（需要后端生成）
export const fetchSignatures = async (): Promise<SignRes> => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/signatures',
    params: {
      url: window.location.href.split('#')[0]
    }
  })
  return response.data
}

export const userInfo = async () => { // 后期封装到后台实现
  const url = httpUrl + '/huoban/getTicket'
  const application = await post(url, {})
  return application.data.ticket
}

// 暂时测试使用
export const userInfo1 = async (data: any) => {
  const url = huobanUrl + '/v2/ticket'
  const application = await post(url, data)
  return application.data.ticket
}

export const SearchInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/find'
  const info = await post(url, data)
  return info.data.items
}

export const filterInfo = async (tableId: string, data: any) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/view/0/filter'
  const info = await post(url, data)
  return info.data.items
}

export const deleteItem = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/delete'
  const info = await post(url, data)
  return info.data
}

export const addInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId
  const info = await post(url, data)
  return info.data
}

export const uploadFile = async (formData: object) => {
  const url = httpUrl + '/file/upload'
  const info = await UploadPost(url, formData)
  return info.data
}

export const updateTable = async (item_id: string, data: object) => {
  axios.put(huobanUrl + '/v2/item/' + item_id, data, {
    headers: {
      'X-Huoban-Ticket': ticket
    }
  })
}

export const uploadImg = async (formData: object) => {
  const url = huobanUrl + '/v2/file'
  const info = await post(url, formData)
  return info.data
}

export const batchAddPlan = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/create'
  const headers: any = {
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
