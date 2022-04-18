<template>
  <div id="app">
    <div v-if="userStatus">
      <user></user>
      <customerTag></customerTag>
      <stage></stage>
      <sidebar-nav></sidebar-nav>
      <router-view />
      <footContent></footContent>
    </div>
    <div v-if="userStatus == false">
      <userBind @close="close()"></userBind>
    </div>
  </div>
</template>
<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import userBind from '@/components/bind/userBind.vue'
import customerTag from '@/views/customerTag.vue'
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
    userBind: userBind
  }
})
export default class Actions extends Vue {
  userStatus = true;
  customerInfo = table.customerInfo;
  userId = localStorage.getItem('userId');
  async mounted () {
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
    location.reload()
  }
}
</script>
