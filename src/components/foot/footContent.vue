<template>
  <div>
    <div class="footDiv">
      <a-menu mode="horizontal">
        <a-menu-item key="Info" @click="clickInfo()">信息</a-menu-item>
        <a-menu-item key="collect" @click="clickCollect()">收款</a-menu-item>
        <a-menu-item key="scheme" @click="clickScheme()">方案</a-menu-item>
        <a-menu-item key="bindUser" @click="clickbindUser()">绑定</a-menu-item>
      </a-menu>
    </div>
    <div v-if="infoShow">
      <infoEdit @reload="reload()"></infoEdit>
    </div>
    <div v-if="collectShow">
      <collectEdit @close="clickCollect()"></collectEdit>
    </div>
    <div v-if="schemeShow">
      <schemeEdit :upload="true" @close="clickScheme()"></schemeEdit>
    </div>
    <div v-if="bindUser">
      <bindEdit :status="true" @close="clickbindUser()"></bindEdit>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { Icon, Menu } from 'ant-design-vue'
import bindEdit from '@/components/foot/footEdit/bindEdit.vue'
import infoEdit from '@/components/foot/footEdit/infoEdit.vue'
import collectEdit from '@/components/foot/footEdit/collectEdit.vue'
import schemeEdit from '@/components/foot/footEdit/schemeEdit.vue'
@Component({
  name: 'foot',
  components: {
    'a-menu': Menu,
    'a-menu-item': Menu.Item,
    'a-icon': Icon,
    infoEdit: infoEdit,
    collectEdit: collectEdit,
    schemeEdit: schemeEdit,
    bindEdit: bindEdit
  }
})
export default class Home extends Vue {
  infoShow: any = false;
  collectShow: any = false;
  schemeShow: any = false;
  bindUser: any = false;
  // 回调 infoEdit
  reload () {
    this.$store.dispatch('updateReload')
    if (this.infoShow) {
      this.infoShow = false
    } else {
      this.infoShow = true
    }
  }

  clickInfo () {
    if (this.infoShow) {
      this.infoShow = false
    } else {
      this.infoShow = true
    }
  }

  clickCollect () {
    if (this.collectShow) {
      this.collectShow = false
    } else {
      this.collectShow = true
    }
  }

  clickScheme () {
    if (this.schemeShow) {
      this.schemeShow = false
    } else {
      this.schemeShow = true
    }
  }

  clickbindUser () {
    if (this.bindUser) {
      this.bindUser = false
    } else {
      this.bindUser = true
    }
  }
}
</script>
<style scoped>
</style>
