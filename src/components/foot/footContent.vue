<template>
  <div>
    <div class="footDiv">
      <a-menu mode="horizontal">
        <a-menu-item key="Info" @click="clickInfo()" v-show="singleStatus">信息</a-menu-item>
        <a-menu-item key="chatInfo" @click="clickChat()" v-show="chatStatus">信息</a-menu-item>
        <a-menu-item key="collect" @click="clickCollect()">收款</a-menu-item>
        <a-menu-item key="scheme" @click="clickScheme()">方案</a-menu-item>
      </a-menu>
    </div>
    <div v-if="infoShow">
      <infoEdit @reload="reload()"></infoEdit>
    </div>
    <div v-if="chatShow">
      <chatEdit @reload="reload()"></chatEdit>
    </div>
    <div v-if="collectShow">
      <collectEdit @close="clickCollect()"></collectEdit>
    </div>
    <div v-if="schemeShow">
      <schemeEdit :upload="true" @close="clickScheme()"></schemeEdit>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Icon, Menu } from 'ant-design-vue'
import infoEdit from '@/components/foot/footEdit/infoEdit.vue'
import chatEdit from '@/components/foot/footEdit/chatEdit.vue'
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
    chatEdit: chatEdit
  }
})
export default class Home extends Vue {
  infoShow: any = false;
  collectShow: any = false;
  schemeShow: any = false;
  chatShow = false;
  singleStatus = false;
  chatStatus = false;
  contactType = localStorage.getItem('contactType');

  mounted () {
    if (this.contactType === 'single_chat_tools') {
      this.singleStatus = true
      this.chatStatus = false
    } else {
      this.singleStatus = false
      this.chatStatus = true
    }
  }

  // 回调 infoEdit
  reload () {
    this.$store.dispatch('updateReload')
    this.infoShow = false
    this.chatShow = false
  }

  clickChat () {
    if (this.chatShow) {
      this.chatShow = false
    } else {
      this.chatShow = true
      this.schemeShow = false
      this.collectShow = false
    }
  }

  clickInfo () {
    if (this.infoShow) {
      this.infoShow = false
    } else {
      this.infoShow = true
      this.schemeShow = false
      this.collectShow = false
    }
  }

  clickCollect () {
    if (this.collectShow) {
      this.collectShow = false
    } else {
      this.collectShow = true
      this.schemeShow = false
      this.infoShow = false
      this.chatShow = false
    }
  }

  clickScheme () {
    if (this.schemeShow) {
      this.schemeShow = false
    } else {
      this.schemeShow = true
      this.collectShow = false
      this.infoShow = false
      this.chatShow = false
    }
  }
}

// 悬浮框自动消失
</script>
<style scoped></style>
