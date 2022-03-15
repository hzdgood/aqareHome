<template>
  <div id="app">
    <div v-if="uploadStaus == true">
      <user></user>
      <customerTag></customerTag>
      <stage></stage>
      <sidebar-nav></sidebar-nav>
      <router-view />
      <footContent></footContent>
    </div>
    <div v-if="uploadStaus == false">
      <schemeEdit :upload="false" @closeScheme="close"></schemeEdit>
    </div>
  </div>
</template>

<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import customerTag from '@/components/infoEdit/customerTag.vue'
import stage from '@/components/infoEdit/stage.vue'
import footContent from '@/components/foot/footContent.vue'
import schemeEdit from '@/components/foot/footEdit/schemeEdit.vue'
import { Component, Vue } from 'vue-property-decorator'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    user: user,
    customerTag: customerTag,
    stage: stage,
    footContent: footContent,
    schemeEdit: schemeEdit
  }
})
export default class Actions extends Vue {
  uploadStaus = true;
  async mounted () {
    const url = window.location.href
    if (url.split('#')[1] === '/upload') {
      this.uploadStaus = false
    }
  }

  close () {
    console.log(111)
  }
}
</script>
