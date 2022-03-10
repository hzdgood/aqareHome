import axios from 'axios'
import { SignRes } from 'wecom-sidebar-jssdk'

const httpUrl = 'http://localhost:8081'
const huobanUrl = 'https://api.huoban.com'

export const config = {
  // 在 https://work.weixin.qq.com/wework_admin/frame#profile 这里可以找到
  corpId: 'ww9a717b03b06063e3', // 企业ID
  // 在 https://work.weixin.qq.com/wework_admin/frame#apps 里的自建应用里可以找到
  agentId: '1000046'
}

const post = async (url: string, data: object) => {
  const ticket = localStorage.getItem('ticket')
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

const UploadPost = async (url: string, data: object, headers: any) => {
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

export const TableInfo = async (ticket: any, tableId: string) => {
  const tableInfo = await axios.get(huobanUrl + '/v2/table/' + tableId, {
    params: {},
    headers: {
      'X-Huoban-Ticket': ticket
    }
  })
  return tableInfo
}

export const userInfo = async () => {
  const url = huobanUrl + '/v2/ticket'
  const data = {
    application_id: '1002449',
    application_secret: '5F5aMmUtCBbhNM4ahhYeG1wMK4mstbsG85VpI9Qw'
  }
  const application = await post(url, data)
  return application.data.ticket
}

export const SearchInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/find'
  const info = await post(url, data)
  return info.data.items
}

export const filterInfo = async (tableId: string, data: any) => {
  const url = huobanUrl + '/v2/item/table/2100000016791383/view/0/filter'
  const info = await post(url, data)
  return info.data.items
}

export const deleteItem = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId + '/delete'
  const info = await post(url, data)
  return info
}

export const addInfo = async (tableId: string, data: object) => {
  const url = huobanUrl + '/v2/item/table/' + tableId
  const info = await post(url, data)
  return info
}

export const uploadFile = async (formData: object) => {
  const url = httpUrl + '/file/upload'
  const headers = {
    'Content-Type': 'multipart/form-data'
  }
  const info = await UploadPost(url, formData, headers)
  return info
}

export const updateTable = async (item_id: string, data: object) => {
  const ticket: any = localStorage.getItem('ticket')
  axios.put(huobanUrl + '/v2/item/' + item_id, data, {
    headers: {
      'X-Huoban-Ticket': ticket
    }
  }).then(response => (
    console.log(response)
  ))
}
