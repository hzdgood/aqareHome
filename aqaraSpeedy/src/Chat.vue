<template>
  <div class="appbody">
    <div v-if="userStatus" style="background-color: #f2f3fb;">
      <div class="appdiv">
        <user-info></user-info>
        <div class="lineType"></div>
        <project-tag></project-tag>
      </div>
      <div class="appdiv">
        <project-stage></project-stage>
      </div>
      <div class="appdiv">
        <sidebar-nav></sidebar-nav>
        <router-view />
        <foot-content></foot-content>
      </div>
    </div>
    <div v-if="userStatus == false" v-show="bindStatus">
      <chat-bind @close="close()"></chat-bind>
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
import { SearchInfo, groupchat, updateTable } from '@/config/interFace'
import { table, field } from '@/config/config'
import '@/css/aqara.css'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    'project-tag': projectTag,
    'project-stage': projectStage,
    'foot-content': footContent,
    'chat-bind': chatBind,
    'user-info': user
  }
})
export default class Actions extends Vue {
  userStatus = false;
  bindStatus = false;
  chatId = localStorage.getItem('chatID');
  async mounted () {
    // 群绑定---未绑定跳转绑定页面
    const obj = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.chatId] }] },
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
      // 没有就绑定 获取群客户ID --》查询客户主项目
      let userId = ''
      let userName = ''
      // 查询群人员
      const res: any = await groupchat(this.chatId + '')
      const group_chat = res.group_chat
      const member_list = group_chat.member_list
      for (let i = 0; i < member_list.length; i++) {
        const type = member_list[i].type
        if (type === 2) {
          userId = member_list[i].userid
        }
      }
      // 这边有个多用户问题。
      const data = {
        where: {
          and: [
            {
              query: { or: [{ in: [userId] }] },
              query_option_mappings: [-1],
              field: field.projectUUid
            },
            { field: field.masterProject, query: { in: [1] } }
          ]
        },
        offset: 0,
        limit: 20
      }
      // 查询当前人员是否有主项目
      const result1 = await SearchInfo(table.projectInfo, data)
      if (result1.length === 0) {
        // 没有就绑定
        this.userStatus = false
        this.bindStatus = true
      } else {
        // 有的话绑定这个主项目
        const itemId = result1[0].item_id
        const fields = result1[0].fields
        for (let j = 0; j < fields.length; j++) {
          if (fields[j].field_id === field.projectCustom) {
            userName = fields[j].values[0].value
          }
        }
        const data = {
          fields: {
            [field.ChatId]: this.chatId
          }
        }
        // 更新群ID
        await updateTable(itemId, data)
        localStorage.setItem('userId', userId)
        localStorage.setItem('userName', userName)
        location.reload()
      }
    } else {
      let userId = ''
      let userName = ''
      for (let i = 0; i < result.length; i++) {
        const fields = result[i].fields
        for (let j = 0; j < fields.length; j++) {
          if (fields[j].field_id === field.projectCustom) {
            userName = fields[j].values[0].value
          }
          if (fields[j].field_id === field.projectUUid) {
            userId = fields[j].values[0].value
          }
        }
      }
      localStorage.setItem('userId', userId) // 设置userID
      localStorage.setItem('userName', userName) // 设置用户名称
      this.userStatus = true
      this.bindStatus = false
    }
  }

  close () {
    this.userStatus = true
    location.reload()
  }
}
</script>
