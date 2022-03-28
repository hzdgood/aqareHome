import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'ant-design-vue/dist/antd.css'
import '@/css/aqara.css'
import {
  userInfo,
  fetchUserId,
  fetchSignatures,
  externalcontact,
  groupchat,
  config
} from '@/config/interFace'
import { checkRedirect, initSdk, invoke } from 'wecom-sidebar-jssdk'
// 获取伙伴云ticket
async function getTicket () {
  const ticket = await userInfo()
  localStorage.setItem('ticket', ticket)
  Vue.config.productionTip = false
  new Vue({
    router,
    store,
    render: (h) => h(App)
  }).$mount('#app')
}
const doInfo = async () => {
  const res = await invoke('getCurExternalContact')
  console.log(res)
  if (res.err_msg === 'wwapp.initWwOpenData:ok') {
    const userId = res.userId
    localStorage.setItem('userId', userId) // 外部客户userID
    const obj = {
      userId: res.userId
    }
    const res1: any = await externalcontact(obj)
    const userName = res1.follow_user[0].remark
    if (res1.follow_user.length === 1) {
      const localName = res1.follow_user[0].userid
      localStorage.setItem('localName', localName) // 登入用户ID
    } else {
      const localName = res1.follow_user[1].userid
      localStorage.setItem('localName', localName) // 登入用户ID
    }
    localStorage.setItem('userName', userName) // 外部客户名称
  } else {
    const result = await invoke('getCurExternalChat')
    const obj = {
      chatId: result.chatId
    }
    const res1: any = await groupchat(obj)
    console.log(res1)
    const member_list = res1.group_chat.member_list
    for (let i = 0; i < member_list.length; i++) {
      if (member_list[i].type === 2) {
        localStorage.setItem('userId', member_list[i].userid)
        localStorage.setItem('userName', member_list[i].name)
      } else if (member_list[i].type === 1) {

      }
    }
  }
  // const userId = res.userId;
  // localStorage.setItem("userId", userId); // 外部客户userID
  // const obj = {
  //   userId: res.userId,
  // };
  // const res1: any = await externalcontact(obj);
  // const userName = res1.follow_user[0].remark;
  // if (res1.follow_user.length === 1) {
  //   const localName = res1.follow_user[0].userid;
  //   localStorage.setItem("localName", localName); // 登入用户ID
  // } else {
  //   const localName = res1.follow_user[1].userid;
  //   localStorage.setItem("localName", localName); // 登入用户ID
  // }
  // localStorage.setItem("userName", userName); // 外部客户名称
}
checkRedirect(config, fetchUserId) // 重定向获取 code（用户身份）
  .then(() => initSdk(config, fetchSignatures)) // 初始化 JsSdk
  .then(() => doInfo())
  .then(() => {
    getTicket()
  })
