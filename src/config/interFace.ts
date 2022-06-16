import axios from 'axios'
import { SignRes } from 'wecom-sidebar-jssdk'

const httpUrl = 'http://localhost:8081' // 测试url
// const httpUrl = 'http://aqara.club:8081' // 生产环境
const huobanUrl = 'https://api.huoban.com'
// const ticket: any = localStorage.getItem("ticket");

export const config = {
  corpId: 'ww9a717b03b06063e3', // 企业ID
  agentId: '1000046' // 应用ID
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
  console.log(response.data)

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

// 获取联系人
export const externalcontact = async (formData: object) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/externalcontact',
    params: formData
  })
  return response.data
}

// 获取联系人
export const externalList = async (formData: object) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/externalList',
    params: formData
  })
  return response.data
}

// 获取群联系人
export const groupchat = async (formData: object) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/groupchat',
    params: formData
  })
  return response.data
}

// 获取群联系人
export const groupList = async () => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/grouplist'
  })
  return response.data
}

export const userInfo = async () => {
  const url = httpUrl + '/huoban/getTicket'
  return await post(url, {})
}

export const uploadFile = async (formData: object) => {
  const url = httpUrl + '/file/upload'
  const info = await UploadPost(url, formData)
  return info.data
}

export const getCoordinate = async (formData: object) => {
  const url = httpUrl + '/user/getCoordinate'
  const info = await axios({
    method: 'post',
    url: url,
    data: formData
  })
  return info.data
}

export const logInsert = async (formData: object) => {
  const url = httpUrl + '/log/insert'
  const info = await axios({
    method: 'post',
    url: url,
    data: formData
  })
  return info.data
}

export const logSelect = async () => {
  const url = httpUrl + '/log/select'
  const info = await post(url, {})
  return info.data
}

export const SearchInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/find'
  const info = await post(url, data)
  return info.data.items
}

export const procedure = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/procedure/' + tableId + '/run'
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

export const updateTable = async (item_id: string, data: object) => {
  const ticket: any = localStorage.getItem('ticket')
  const info = await axios.put(huobanUrl + '/v2/item/' + item_id, data, {
    headers: {
      'X-Huoban-Ticket': ticket
    }
  })
  return info.data
}

export const uploadImg = async (formData: object) => {
  const url = huobanUrl + '/v2/file'
  const info = await post(url, formData)
  return info.data
}

export const uploadPdf = async (formData: object) => {
  const url = 'https://upload.huoban.com/v2/file'
  const info = await post(url, formData)
  return info.data
}

export const batchAddPlan = async (tableId: string, data: object) => {
  const ticket: any = localStorage.getItem('ticket')
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
