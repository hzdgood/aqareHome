<template>
  <div class="appbody">
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import '@/css/aqara.css'
@Component({})
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
