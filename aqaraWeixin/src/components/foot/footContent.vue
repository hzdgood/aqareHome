<template>
  <div>
    <div class="footDiv" @click="selectOption()">+</div>
    <div class="footContent" v-show="optionStatus">
      <div @click="clickInfo()" v-show="singleStatus">信息</div>
      <div @click="clickChat()" v-show="chatStatus">信息</div>
      <div @click="clickScheme()" v-show="singleStatus">工勘</div>
      <div @click="clickSurvey()" v-show="surveyStatus">方案</div>
      <div @click="clickCollect()" v-show="singleStatus">收款</div>
      <div @click="clickProposal()" v-show="singleStatus">报价</div>
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
      <scheme-edit @close="clickScheme()"></scheme-edit>
    </div>
    <div v-if="ProposalStatus">
      <proposal-edit @close="clickProposal()"></proposal-edit>
    </div>
    <div v-if="surveyStatus">
      <survey-edit @close="clickSurvey()"></survey-edit>
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
import ProposalEdit from '@/components/foot/footEdit/ProposalEdit.vue'
import surveyEdit from '@/components/foot/footEdit/surveyEdit.vue'
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
    'proposal-edit': ProposalEdit,
    'survey-edit': surveyEdit
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
  ProposalStatus = false;
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
    this.chatShow = false
    this.optionStatus = false
    this.ProposalStatus = false
    this.surveyStatus = false
  }

  clickChat () {
    if (this.chatShow) {
      this.chatShow = false
    } else {
      this.optionStatus = false
      this.chatShow = true
      this.schemeShow = false
      this.collectShow = false
      this.ProposalStatus = false
      this.surveyStatus = false
    }
  }

  clickInfo () {
    if (this.infoShow) {
      this.infoShow = false
    } else {
      this.optionStatus = false
      this.infoShow = true
      this.schemeShow = false
      this.collectShow = false
      this.ProposalStatus = false
      this.surveyStatus = false
    }
  }

  clickCollect () {
    if (this.collectShow) {
      this.collectShow = false
    } else {
      this.optionStatus = false
      this.collectShow = true
      this.schemeShow = false
      this.infoShow = false
      this.chatShow = false
      this.ProposalStatus = false
      this.surveyStatus = false
    }
  }

  clickScheme () {
    if (this.schemeShow) {
      this.schemeShow = false
    } else {
      this.optionStatus = false
      this.schemeShow = true
      this.collectShow = false
      this.infoShow = false
      this.chatShow = false
      this.ProposalStatus = false
      this.surveyStatus = false
    }
  }

  clickSurvey () {
    if (this.surveyStatus) {
      this.surveyStatus = false
    } else {
      this.surveyStatus = true
      this.optionStatus = false
      this.ProposalStatus = true
      this.chatShow = false
      this.schemeShow = false
      this.collectShow = false
    }
  }

  clickProposal () {
    if (this.ProposalStatus) {
      this.ProposalStatus = false
    } else {
      this.optionStatus = false
      this.ProposalStatus = true
      this.chatShow = false
      this.schemeShow = false
      this.collectShow = false
      this.surveyStatus = false
    }
  }
}
</script>
<style scoped></style>
