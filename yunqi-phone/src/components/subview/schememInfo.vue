<template>
  <div>
    <schemeTable></schemeTable>
    <div class="buttonPos">
      <a-button type="primary" @click="resPage()">返回</a-button>
    </div>
  </div>
</template>

<script setup lang="ts">
// 方案


import schemeTable from './subTable/schemeTable.vue'
import router from '@/router';
const resPage = () => {
  router.push({name: 'project'})
}
</script>

<style lang="less" scoped>

</style>