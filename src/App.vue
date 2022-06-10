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
    <Loading v-show="loading"></Loading>
    <confirm v-show="confirm" :msg="confirmInfo" @close="closeConfirm()"></confirm>
  </div>
</template>
<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import userBind from '@/components/bind/userBind.vue'
import customerTag from '@/views/customerTag.vue'
import customerStage from '@/views/customerStage.vue'
import footContent from '@/components/foot/footContent.vue'
import Loading from '@/components/common/loading.vue'
import myConfirm from '@/components/common/myConfirm.vue'
import { Component, Vue, Watch } from 'vue-property-decorator'
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
    'user-bind': userBind,
    Loading: Loading,
    confirm: myConfirm
  }
})
export default class Actions extends Vue {
  userStatus = true
  loading = false
  confirm = false
  confirmInfo = ''
  customerInfo = table.customerInfo
  userId = localStorage.getItem('userId')

  @Watch('$store.state.Loading')
  showLoad () {
    console.log(111)

    if (this.loading) {
      this.loading = false
    } else {
      this.loading = true
    }
  }

  @Watch('$store.state.confirm')
  showConfirm () {
    this.confirmInfo = this.$store.state.confirmInfo
    console.log(this.confirmInfo)
    if (this.confirm) {
      this.confirm = false
    } else {
      this.confirm = true
    }
  }

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

  closeConfirm () {
    this.confirm = false
  }
}
</script>
