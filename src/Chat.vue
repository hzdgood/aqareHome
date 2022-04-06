<template>
  <div id="Chat">
    <div v-if="uploadStaus == true">

        <footContent></footContent>
    </div>
    <div v-if="uploadStaus == false">
      <schemeEdit :upload="false"></schemeEdit>
    </div>
  </div>
</template>
<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import bindEdit from '@/components/foot/footEdit/bindEdit.vue'
import customerTag from '@/views/customerTag.vue'
import stage from '@/views/stage.vue'
import footContent from '@/components/foot/footContent.vue'
import schemeEdit from '@/components/foot/footEdit/schemeEdit.vue'
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, groupchat, batchAddPlan } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    customerTag: customerTag,
    stage: stage,
    footContent: footContent,
    schemeEdit: schemeEdit,
    bindEdit: bindEdit
  }
})
export default class Actions extends Vue {
  uploadStaus = true;
  userStatus = true;
  async mounted () {
    // 方案上传
    const url = window.location.href
    if (url.split('#')[1] === '/upload') {
      this.uploadStaus = false
      return
    }
    // 群绑定---未绑定跳转绑定页面

    const chatId = localStorage.getItem('chatID')
    // 判断伙伴云是否有这个ID
    const obj = {
      where: {
        and: [
          {
            query: { or: [{ in: [chatId] }] },
            query_option_mappings: [-1],
            field: 2200000188278958
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const res = await SearchInfo(table.wechatChat, obj)
    if (res.length === 0) {
      const result: any = await groupchat({
        chatId: chatId
      })
      console.log(result)
      if (result.errcode !== 0) {
        return
      }
      const member_list = result.group_chat.member_list
      // 群人员信息保存到伙伴云
      const json: any = {
        items: []
      }
      for (let i = 0; i < member_list.length; i++) {
        let type = [1]
        if (member_list[i].type === 1) {
          type = [1]
        } else {
          type = [2]
        }
        const object = {
          fields: {
            2200000188278958: chatId, // ID
            2200000188278959: result.group_chat.name, // 群名称
            2200000188278960: member_list[i].userid, // 群成员
            2200000188278961: type // 成员类型
          }
        }
        json.items.push(object)
      }
      console.log(json)
      await batchAddPlan(table.wechatChat, json)
    }
  }
}
</script>
