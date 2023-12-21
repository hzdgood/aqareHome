<template>
  <div class="appbody">
    {{ userId }}
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import '@/css/aqara.css'
@Component({})
export default class Actions extends Vue {
  userId = localStorage.getItem('userId')
}
</script>
