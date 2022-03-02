<template>
  <div id="app">
    <user></user>
    <customerTag></customerTag>
    <stage></stage>
    <sidebar-nav></sidebar-nav>
    <router-view />
    <footContent></footContent>
  </div>
</template>

<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import customerTag from '@/components/infoEdit/customerTag.vue'
import stage from '@/components/infoEdit/stage.vue'
import footContent from '@/components/foot/footContent.vue'
import { Component, Vue } from 'vue-property-decorator'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    user: user,
    customerTag: customerTag,
    stage: stage,
    footContent: footContent
  }
})
export default class Actions extends Vue {
  // async mounted () {}
}
</script>

<style>
.centerDiv{
  padding-bottom: 50px;
}
.floatDiv {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: #eee;
  opacity: 0.92;
  z-index: 10;
  top: 0;
}
input {
  font-size: 12px;
  max-width: 85px;
}
span {
  font-size: 12px;
  margin: 5px;
}
.lineDiv {
  margin: 5px;
}
.infoTable {
  width: 100%;
  overflow: auto;
}
.infoTable tr td {
  border: 1px solid #eee;
  text-align: center;
  padding: 1px 5px 1px 5px;
  font-size: 10px;
}
.infoTable thead {
  font-weight: bold;
  background-color: #ddd;
}
</style>
