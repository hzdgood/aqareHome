import axios from 'axios'
import { SignRes } from 'wecom-sidebar-jssdk'

const httpUrl = 'http://localhost:8081' // 测试url
// const httpUrl = 'http://aqara.club:8081' // 生产环境

export const config = {
  /* 8081 */
  corpId: 'ww9a717b03b06063e3', // 企业ID
  agentId: '1000038' // 应用ID

  /* 8091 */
  // corpId: 'ww728dd07f7641e567',
  // agentId: '1000004'
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
    url: httpUrl + '/wechat/externalcontact',
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
    url: httpUrl + '/wechat/groupchat',
    params: {
      chatId: chatId,
      type: httpUrl
    }
  })
  return response.data
}

// 获取联系人
export const externalList = async (userId: string) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/externalList',
    params: {
      userId: userId,
      type: httpUrl
    }
  })
  return response.data
}
// 获取群联系人
export const groupList = async (chatId: string) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/grouplist',
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
