<template>
  <div class="footDiv">
    <a-menu mode="horizontal">
      <a-menu-item key="Info" @click="clickInfo()">信息</a-menu-item>
      <a-menu-item key="money" @click="clickMoney()">收款</a-menu-item>
      <a-menu-item key="follow" @click="clickFollow()">跟进</a-menu-item>
      <a-menu-item key="EngineeringSurvey" @click="clickSurvey()"
        >工勘</a-menu-item
      >
      <a-menu-item key="scheme" @click="clickScheme()">方案</a-menu-item>
      <a-menu-item key="workSteet" @click="clickSteet()">工单</a-menu-item>
    </a-menu>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { Icon, Menu } from 'ant-design-vue'
@Component({
  name: 'foot',
  components: {
    'a-menu': Menu,
    'a-menu-item': Menu.Item,
    'a-icon': Icon
  }
})
export default class Home extends Vue {
  ticket = localStorage.getItem('ticket');
  async mounted () {
    console.log(this.ticket)
  }

  clickInfo () {
    console.log(1111)
  }

  clickMoney () {
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
