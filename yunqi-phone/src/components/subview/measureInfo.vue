<template>
  <div>
    测量
    <div class="buttonPos">
      <a-button @click="resPage()">返回</a-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
const resPage = () => {
  router.push({name: 'project'})
}
</script>

<style lang="less" scoped>

</style>