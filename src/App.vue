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

</style>
