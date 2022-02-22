<template>
  <div></div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'

@Component({})
export default class Home extends Vue {
  async mounted () {
    const ticket = localStorage.getItem('ticket')
    console.log(ticket)
  }
}
</script>
