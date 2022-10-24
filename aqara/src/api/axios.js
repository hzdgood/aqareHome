import axios from 'axios'

// const httpUrl = 'http://aqara.club:8081'
const httpUrl = 'http://localhost:8081'

const getReq = async (url, data) => {
    const response = await axios.request({
      method: 'GET',
      url: url,
      params: data
    })
    return response
}

const postReq = async (url, data) => {
    const response = await axios({
      method: 'post',
      url: url,
      data: data
    })
    return response
}

export const getThemeData = async (data) => {
    const url = httpUrl + '/speedy/theme/select'
    const response = await postReq(url, data)
    return response
}

export const themeInsert = async (data) => {
    const url = httpUrl + '/speedy/theme/insert'
    const response = await getReq(url, data)
    return response
}

export const themeUpdate = async (data) => {
    const url = httpUrl + '/speedy/theme/update'
    const response = await getReq(url, data)
    return response
}

export const themeDelete = async (data) => {
    const url = httpUrl + '/speedy/theme/delete'
    const response = await getReq(url, data)
    return response
}

export const getContentData = async (data) => {
    const url = httpUrl + '/speedy/content/select'
    const response = await postReq(url, data)
    return response
}

export const contentInsert = async (data) => {
    const url = httpUrl + '/speedy/content/insert'
    const response = await getReq(url, data)
    return response
}

export const contentUpdate = async (data) => {
    const url = httpUrl + '/speedy/content/update'
    const response = await getReq(url, data)
    return response
}

export const contentDelete = async (data) => {
    const url = httpUrl + '/speedy/content/delete'
    const response = await getReq(url, data)
    return response
}

export const getTeamData = async (data) => {
    const url = httpUrl + '/speedy/team/select'
    const response = await postReq(url, data)
    return response
}

export const teamInsert = async (data) => {
    const url = httpUrl + '/speedy/team/insert'
    const response = await getReq(url, data)
    return response
}

export const teamUpdate = async (data) => {
    const url = httpUrl + '/speedy/team/update'
    const response = await getReq(url, data)
    return response
}

export const teamDelete = async (data) => {
    const url = httpUrl + '/speedy/team/delete'
    const response = await getReq(url, data)
    return response
}
