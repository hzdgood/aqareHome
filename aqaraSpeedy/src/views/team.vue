<template>
  <div></div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { invoke } from 'wecom-sidebar-jssdk'
@Component({
  name: 'person'
})
export default class Actions extends Vue {
  async mounted () {
    // await invoke('sendChatMessage', {
    //   msgtype: 'text',
    //   enterChat: true,
    //   text: {
    //     content: '111111'
    //   }
    // })
  }
}
</script>

<style>

</style>
