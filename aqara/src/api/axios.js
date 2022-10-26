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

const UploadPost = async (url, data) => {
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

export const getData = async (reqPath, data) => {
    const url = httpUrl + reqPath
    const response = await getReq(url, data)
    return response
}

export const getPostData = async (reqPath, data) => {
    const url = httpUrl + reqPath
    const response = await postReq(url, data)
    return response
}

export const uploadFile = async (formData, req) => {
    const url = httpUrl + req
    const info = await UploadPost(url, formData)
    return info.data
}
