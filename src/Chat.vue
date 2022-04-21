<template>
  <div id="Chat">
    <div v-if="userStatus == true">
      <user></user>
      <projectTag></projectTag>
      <projectStage></projectStage>
      <sidebar-nav></sidebar-nav>
      <router-view />
      <footContent></footContent>
    </div>
    <div v-if="userStatus == false" v-show="bindStatus">
      <chatBind @close="close()"></chatBind>
    </div>
  </div>
</template>
<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import chatBind from '@/components/bind/chatBind.vue'
import projectTag from '@/views/projectTag.vue'
import projectStage from '@/views/projectStage.vue'
import footContent from '@/components/foot/footContent.vue'
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, externalList, externalcontact } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    projectTag: projectTag,
    projectStage: projectStage,
    footContent: footContent,
    chatBind: chatBind,
    user: user
  }
})
export default class Actions extends Vue {
  userStatus = false;
  bindStatus = false;
  async mounted () {
    // 群绑定---未绑定跳转绑定页面
    const chatId = localStorage.getItem('chatID')
    const obj = {
      where: {
        and: [
          {
            query: { or: [{ in: [chatId] }] },
            query_option_mappings: [-1],
            field: field.ChatId
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, obj) // 查询项目信息表
    if (result.length === 0) {
      // 没有就绑定
      this.userStatus = false
      this.bindStatus = true
    } else {
      const userId = ''
      let userName = ''
      for (let i = 0; i < result.length; i++) {
        const fields = result[i].fields
        for (let j = 0; j < fields.length; j++) {
          if (fields[j].field_id === field.projectCustom) {
            userName = fields[j].values[0].value
            console.log(userName)
          }
        }
      }
      localStorage.setItem('userId', userId) // 设置userID
      localStorage.setItem('userName', userName) // 设置用户名称
      this.userStatus = true
      this.bindStatus = false
    }

    const res1: any = await externalList({
      userId: 'Zhuwei'
    })

    // 判断伙伴云是否有这个ID
    // const obj = {
    //   where: {
    //     and: [
    //       {
    //         query: { or: [{ in: [chatId] }] },
    //         query_option_mappings: [-1],
    //         field: 2200000188278958
    //       }
    //     ]
    //   },
    //   offset: 0,
    //   limit: 20
    // }
    // const res = await SearchInfo(table.wechatChat, obj)
    // if (res.length === 0) { //没有就新增
    //   const result: any = await groupchat({
    //     chatId: chatId
    //   })
    //   if (result.errcode !== 0) {
    //     return
    //   }
    //   const member_list = result.group_chat.member_list
    //   // 群人员信息保存到伙伴云
    //   const json: any = {
    //     items: []
    //   }
    //   for (let i = 0; i < member_list.length; i++) {
    //     let type = [1]
    //     if (member_list[i].type === 1) {
    //       type = [1]
    //     } else {
    //       type = [2]
    //     }
    //     const object = {
    //       fields: {
    //         2200000188278958: chatId, // ID
    //         2200000188278959: result.group_chat.name, // 群名称
    //         2200000188278960: member_list[i].userid, // 群成员
    //         2200000188278961: type // 成员类型
    //       }
    //     }
    //     json.items.push(object)
    //   }
    //   await batchAddPlan(table.wechatChat, json)
    // } else { //如果有检查和更新
    // }
  }

  close () {
    this.userStatus = true
    location.reload()
  }
}
</script>
