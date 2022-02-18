<template>
  <div id="app">
    <user></user>
    <customInfo></customInfo>
    <stage></stage>
    <sidebar-nav></sidebar-nav>
    <router-view />
  </div>
</template>

<script lang="ts">
import Nav from "@/components/Nav.vue";
import user from "@/components/user.vue";
import customInfo from "@/components/customInfo.vue";
import stage from "@/components/stage.vue";
import { Component, Vue } from "vue-property-decorator";
@Component({
  name: "App",
  components: {
    "sidebar-nav": Nav,
    user: user,
    customInfo: customInfo,
    stage: stage,
  },
})
export default class Actions extends Vue {
  async mounted() {}
}
</script>

<style lang="less"></style>
