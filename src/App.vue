<template>
  <div class="appbody">
    <div v-if="userStatus" style="background-color: #f2f3fb;">
      <div class="appdiv">
        <user-info></user-info>
        <div class="lineType"></div>
        <customer-tag></customer-tag>
      </div>
      <div class="appdiv">
        <customer-stage></customer-stage>
      </div>
      <div class="appdiv">
        <sidebar-nav></sidebar-nav>
        <router-view />
        <foot-content></foot-content>
      </div>
    </div>
    <div v-if="userStatus == false">
      <user-bind @close="close()"></user-bind>
    </div>
  </div>
</template>
<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import userBind from '@/components/bind/userBind.vue'
import customerTag from '@/views/customerTag.vue'
import customerStage from '@/views/customerStage.vue'
import footContent from '@/components/foot/footContent.vue'
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    'user-info': user,
    'customer-tag': customerTag,
    'customer-stage': customerStage,
    'foot-content': footContent,
    'user-bind': userBind
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

  // 关闭
  close () {
    this.userStatus = true
    location.reload()
  }
}
</script>
