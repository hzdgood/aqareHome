<template>
  <div>
    <div class="footDiv" @click="selectOption()">+</div>
    <div class="footContent" v-show="optionStatus">
      <div @click="clickInfo()" v-show="singleStatus">信息</div>
      <div @click="clickChat()" v-show="chatStatus">信息</div>
      <div @click="clickCollect()">收款</div>
      <div @click="clickScheme()">方案</div>
    </div>
    <div v-if="infoShow">
      <info-edit @reload="reload()"></info-edit>
    </div>
    <div v-if="chatShow">
      <chat-edit @reload="reload()"></chat-edit>
    </div>
    <div v-if="collectShow">
      <collect-edit @close="clickCollect()"></collect-edit>
    </div>
    <div v-if="schemeShow">
      <scheme-edit :upload="true" @close="clickScheme()"></scheme-edit>
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
    'info-edit': infoEdit,
    'collect-edit': collectEdit,
    'scheme-edit': schemeEdit,
    'chat-edit': chatEdit
  }
})
export default class Home extends Vue {
  infoShow: any = false;
  collectShow: any = false;
  schemeShow: any = false;
  chatShow = false;
  singleStatus = false;
  chatStatus = false;
  optionStatus = false;
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

  selectOption () {
    if (this.optionStatus) {
      this.optionStatus = false
    } else {
      this.optionStatus = true
    }
  }

  // 回调 infoEdit
  reload () {
    this.$store.dispatch('updateReload')
    this.infoShow = false
    this.chatShow = false
    this.optionStatus = false
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
