<template>
  <div id="app">
    <div v-if="uploadStaus == true">
      <div v-if="userStatus">
        <user></user>
        <customerTag></customerTag>
        <stage></stage>
        <sidebar-nav></sidebar-nav>
        <router-view />
        <footContent></footContent>
      </div>
      <div v-if="userStatus == false">
        <bindEdit :status="false" @close="close()"></bindEdit>
      </div>
    </div>
    <div v-if="uploadStaus == false">
      <schemeEdit :upload="false"></schemeEdit>
    </div>
  </div>
</template>

<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import bindEdit from '@/components/foot/footEdit/bindEdit.vue'
import customerTag from '@/components/infoEdit/customerTag.vue'
import stage from '@/views/stage.vue'
import footContent from '@/components/foot/footContent.vue'
import schemeEdit from '@/components/foot/footEdit/schemeEdit.vue'
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field } from '@/config/config'

@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    user: user,
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
  customerInfo = table.customerInfo;
  userId = localStorage.getItem('userId');
  async mounted () {
    // 如果前段url为uplaod展示方案上传页面
    const url = window.location.href
    if (url.split('#')[1] === '/upload') {
      this.uploadStaus = false
      return
    }
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    // 根据ID获取用户数据 有显示数据 没有绑定
    const result = await SearchInfo(this.customerInfo, data)
    if (result.length === 0) {
      this.userStatus = false
    } else {
      this.userStatus = true
    }
  }

  close () {
    this.userStatus = true
  }
}
</script>
