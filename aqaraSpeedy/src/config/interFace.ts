import axios from 'axios'
import { SignRes } from 'wecom-sidebar-jssdk'

// const httpUrl = 'http://localhost:8081' // 测试url
// const typeUrl = 'http://localhost:8081'
const httpUrl = 'http://aqara.club:8081' // 生产环境
const typeUrl = 'http://aqara.club:8082'

export const config = {
  corpId: 'ww9a717b03b06063e3', // 企业ID
  agentId: '1000059' // 应用ID
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
      type: typeUrl
    }
  })
  return response.data
}
export const userInfo = async () => {
  const url = httpUrl + '/huoban/getTicket'
  return await post(url, {})
}
// 获取签名接口（需要后端生成）
export const fetchSignatures = async (): Promise<SignRes> => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/signatures',
    params: {
      url: window.location.href.split('#')[0],
      type: typeUrl
    }
  })
  return response.data
}

export const treeList = async (reqUrl:any, data: any) => {
  const response = await axios({
    method: 'post',
    url: httpUrl + reqUrl,
    data: data
  })
  return response.data
}

// 获取群联系人
export const mediaUpload = async (fileName: any) => {
  const response = await axios.request<SignRes>({
    method: 'GET',
    url: httpUrl + '/wechat/mediaUpload',
    params: {
      type: httpUrl,
      fileName: fileName
    }
  })
  return response.data
}
