<template>
  <div>
    <div class="footDiv">
      <a-menu mode="horizontal">
        <a-menu-item key="Info" @click="clickInfo()">信息</a-menu-item>
        <a-menu-item key="collect" @click="clickCollect()">收款</a-menu-item>
        <a-menu-item key="follow" @click="clickFollow()">跟进</a-menu-item>
        <a-menu-item key="Survey" @click="clickSurvey()">工勘</a-menu-item>
        <a-menu-item key="scheme" @click="clickScheme()">方案</a-menu-item>
        <a-menu-item key="workSteet" @click="clickSteet()">工单</a-menu-item>
      </a-menu>
    </div>
    <div v-if="infoShow">
      <infoEdit @reload="reload()"></infoEdit>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { Icon, Menu } from 'ant-design-vue'
import infoEdit from '@/components/foot/infoEdit.vue'
@Component({
  name: 'foot',
  components: {
    'a-menu': Menu,
    'a-menu-item': Menu.Item,
    'a-icon': Icon,
    infoEdit: infoEdit
  }
})
export default class Home extends Vue {
  // async mounted () {}
  infoShow: any = false;
  collectShow: any = false;
  followShow: any = false;
  surveyShow: any = false;
  schemeShow: any = false;
  steetShow: any = false;

  reload () {
    this.$store.dispatch('updateReload')
    this.infoShow = false
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

  clickFollow () {
    if (this.followShow) {
      this.followShow = false
    } else {
      this.followShow = true
    }
  }

  clickSurvey () {
    if (this.surveyShow) {
      this.surveyShow = false
    } else {
      this.surveyShow = true
    }
  }

  clickScheme () {
    if (this.schemeShow) {
      this.schemeShow = false
    } else {
      this.schemeShow = true
    }
  }

  clickSteet () {
    if (this.steetShow) {
      this.steetShow = false
    } else {
      this.steetShow = true
    }
  }
}
</script>
<style scoped>
.footDiv {
  position: fixed;
  bottom: 0px;
  z-index: 15;
  width: 100%;
}
</style>
