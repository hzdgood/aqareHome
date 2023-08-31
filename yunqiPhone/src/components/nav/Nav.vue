<template>
  <a-menu v-model="current" mode="horizontal">
    <a-menu-item key="企业话术">
      <router-link to="/">
        企业话术
      </router-link>
    </a-menu-item>
    <a-menu-item key="个人话术">
      <router-link to="/person">
        个人话术
      </router-link>
    </a-menu-item>
    <a-menu-item key="团队话术">
      <router-link to="/team">
        团队话术
      </router-link>
    </a-menu-item>
  </a-menu>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Menu } from 'ant-design-vue'
@Component({
  name: 'Nav',
  components: {
    'a-menu': Menu,
    'a-menu-item': Menu.Item
  }
})
export default class Nav extends Vue {
  current: string[] = [this.$route.name as string];
}
</script>
<style scoped>
.ant-menu-item, .ant-menu-submenu-title {
  padding: 0px 20px !important;
}
</style>
