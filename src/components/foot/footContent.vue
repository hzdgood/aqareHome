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
    <infoEdit :isShow="infoShow"></infoEdit>
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

  clickInfo () {
    this.infoShow = true
  }

  clickCollect () {
    console.log(1111)
  }

  clickFollow () {
    console.log(1111)
  }

  clickSurvey () {
    console.log(1111)
  }

  clickScheme () {
    console.log(1111)
  }

  clickSteet () {
    console.log(1111)
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
