import axios from 'axios'
import { SignRes } from 'wecom-sidebar-jssdk'
// const httpUrl = 'http://localhost:8081' // 测试url
const httpUrl = 'http://aqara.club:8081' // 生产环境
const huobanUrl = 'https://api.huoban.com' // 伙伴云

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
      code: code,
      type: httpUrl
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
      url: window.location.href.split('#')[0],
      type: httpUrl
    }
  })
  return response.data
}

// 获取联系人
export const externalcontact = async (userId: string) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/externalContact',
    params: {
      userId: userId,
      type: httpUrl
    }
  })
  return response.data
}
// 获取群联系人
export const groupchat = async (chatId: string) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/groupChat',
    params: {
      chatId: chatId,
      type: httpUrl
    }
  })
  return response.data
}

export const userInfo = async () => {
  const url = httpUrl + '/huoban/getTicket'
  return await post(url, {})
}

export const uploadFile = async (formData: object, req: any) => {
  const url = httpUrl + req
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

export const getCoordinate1 = async (formData: object) => {
  const url = httpUrl + '/common/getCoordinate'
  const info = await axios({
    method: 'post',
    url: url,
    data: formData
  })
  return info.data
}

export const logInsert = async (info: any) => {
  const response = await axios.request({
    method: 'GET',
    url: httpUrl + '/log/insert',
    params: {
      name: localStorage.getItem('localName'),
      custom: localStorage.getItem('userName'),
      info: info
    }
  })
  return response.data
}

export const logSelect = async () => {
  const response = await axios.request({
    method: 'GET',
    url: httpUrl + '/log/select',
    params: {
      name: localStorage.getItem('localName'),
      custom: localStorage.getItem('userName')
    }
  })
  return response.data
}

export const streams = async (url: any, limit: any, offset: any) => {
  const ticket: any = localStorage.getItem('ticket')
  const headers = {
    'X-Huoban-Ticket': ticket
  }
  const response = await axios.request({
    method: 'GET',
    url: huobanUrl + '/v2/streams/item/' + url,
    headers: headers,
    params: {
      limit: limit,
      offset: offset
    }
  })
  return response.data
}

export const SearchInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/find'
  const info = await post(url, data)
  return info.data.items
}

export const SearchUser = async () => {
  const url = huobanUrl + '/v2/users/find'
  const data = { space_id: 4000000003153003, limit: 50 }
  const info = await post(url, data)
  return info.data
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

// 获取联系人
// export const externalList = async (userId: string) => {
//   const response = await axios.request<SignRes>({
//     method: 'GET',
//     url: httpUrl + '/wechat/externalList',
//     params: {
//       userId: userId,
//       type: httpUrl
//     }
//   })
//   return response.data
// }
// 获取群联系人
// export const groupList = async (chatId: string) => {
//   const response = await axios.request<SignRes>({
//     method: 'GET',
//     url: httpUrl + '/wechat/grouplist',
//     params: {
//       chatId: chatId,
//       type: httpUrl
//     }
//   })
//   return response.data
// }
