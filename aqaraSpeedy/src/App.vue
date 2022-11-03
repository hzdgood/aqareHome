<template>
  <div class="appbody">
    <sidebar-nav></sidebar-nav>
    <router-view />
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Nav from '@/components/nav/Nav.vue'
import { invoke } from 'wecom-sidebar-jssdk'
import '@/css/aqara.css'

@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav
  }
})
export default class Actions extends Vue {
  userId = localStorage.getItem('userId')

  async mounted () {
    console.log(111)
    // await invoke('sendChatMessage', {
    //   msgtype: 'text',
    //   enterChat: false,
    //   text: {
    //     content: '111111'
    //   }
    // })
  }
}
</script>
