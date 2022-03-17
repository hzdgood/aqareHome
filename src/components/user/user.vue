<template>
  <div id="app">
    <div class="headDiv"> ID {{ userName }}</div>
  </div>
</template>
<script lang="ts">
import { Vue } from 'vue-property-decorator'
export default class Home extends Vue {
  userName = localStorage.getItem('userName')
}
</script>
<style scoped>
.headDiv{
  line-height: 50px;
  vertical-align: middle;
  padding-left: 10px;
}
</style>
