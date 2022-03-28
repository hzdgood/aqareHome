<template>
  <a-menu v-model="current" mode="horizontal">
    <a-menu-item key="项目信息">
      <router-link to="/">
        信息
      </router-link>
    </a-menu-item>
    <a-menu-item key="跟进">
      <router-link to="/followUp">
        跟进
      </router-link>
    </a-menu-item>
    <a-menu-item key="发货">
      <router-link to="/sendOut">
        发货
      </router-link>
    </a-menu-item>
    <a-menu-item key="方案">
      <router-link to="/scheme">
        方案
      </router-link>
    </a-menu-item>
    <a-menu-item key="工单">
      <router-link to="/workSheet">
        工单
      </router-link>
    </a-menu-item>
  </a-menu>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Icon, Menu } from 'ant-design-vue'
@Component({
  name: 'Nav',
  components: {
    'a-menu': Menu,
    'a-menu-item': Menu.Item,
    'a-icon': Icon
  }
})
export default class Nav extends Vue {
  current: string[] = [this.$route.name as string];
}
</script>
<style scoped></style>
