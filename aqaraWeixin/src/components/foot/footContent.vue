<template>
  <div>
    <div class="footDiv" @click="selectOption()">+</div>
    <div class="footContent" v-show="optionStatus">
      <div @click="clickInfo()" v-show="singleStatus">信息</div>
      <div @click="clickChat()" v-show="chatStatus">信息</div>
      <div @click="clickScheme()" v-show="singleStatus">方案</div>
      <div @click="clickCollect()" v-show="singleStatus">收款</div>
    </div>
    <div v-if="infoShow">
      <info-edit @reload="reload()"></info-edit>
    </div>
    <div v-if="chatShow">
      <chat-edit @reload="reload()"></chat-edit>
    </div>
    <div v-if="schemeShow">
      <scheme-edit @close="clickScheme()"></scheme-edit>
    </div>
    <div v-if="collectShow">
      <collect-edit @close="clickCollect()"></collect-edit>
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
import surveyEdit from '@/components/foot/footEdit/surveyEdit.vue'
import followEdit from '@/components/foot/footEdit/followEdit.vue'
@Component({
  name: 'foot',
  components: {
    'a-menu': Menu,
    'a-menu-item': Menu.Item,
    'a-icon': Icon,
    'info-edit': infoEdit,
    'collect-edit': collectEdit,
    'scheme-edit': schemeEdit,
    'chat-edit': chatEdit,
    'survey-edit': surveyEdit,
    'follow-edit': followEdit
  }
})
export default class Home extends Vue {
  infoShow: any = false;
  chatShow = false;
  singleStatus = false;
  chatStatus = false;
  collectShow: any = false;
  schemeShow: any = false;
  followStatus = false;
  optionStatus = false;
  surveyStatus = false;
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
    this.followStatus = false
    this.chatShow = false
    this.optionStatus = false
    this.surveyStatus = false
  }

  clickChat () {
    if (this.chatShow) {
      this.chatShow = false
    } else {
      this.followStatus = false
      this.optionStatus = false
      this.chatShow = true
      this.schemeShow = false
      this.collectShow = false
      this.surveyStatus = false
    }
  }

  clickInfo () {
    if (this.infoShow) {
      this.infoShow = false
    } else {
      this.followStatus = false
      this.optionStatus = false
      this.infoShow = true
      this.schemeShow = false
      this.collectShow = false
      this.surveyStatus = false
    }
  }

  clickCollect () {
    if (this.collectShow) {
      this.collectShow = false
    } else {
      this.followStatus = false
      this.optionStatus = false
      this.collectShow = true
      this.schemeShow = false
      this.infoShow = false
      this.chatShow = false
      this.surveyStatus = false
    }
  }

  clickScheme () {
    if (this.schemeShow) {
      this.schemeShow = false
    } else {
      this.followStatus = false
      this.optionStatus = false
      this.schemeShow = true
      this.collectShow = false
      this.infoShow = false
      this.chatShow = false
      this.surveyStatus = false
    }
  }

  clickSurvey () {
    if (this.surveyStatus) {
      this.surveyStatus = false
    } else {
      this.followStatus = false
      this.surveyStatus = true
      this.optionStatus = false
      this.chatShow = false
      this.schemeShow = false
      this.collectShow = false
    }
  }

  clickFollow () {
    if (this.followStatus) {
      this.followStatus = false
    } else {
      this.followStatus = true
      this.optionStatus = false
      this.chatShow = false
      this.schemeShow = false
      this.collectShow = false
      this.surveyStatus = false
    }
  }
}
</script>
<style scoped></style>
