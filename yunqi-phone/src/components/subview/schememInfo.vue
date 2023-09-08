<template>
  <div>
    <schemeTable></schemeTable>
    <div class="buttonPos">
      <a-button type="primary">返回</a-button>
    </div>
  </div>
</template>

<script setup lang="ts">
  import schemeTable from './subTable/schemeTable.vue'
</script>

<style lang="less" scoped>
.buttonPos {
  width: 100%;
  text-align: right;
}
button{
  margin: 10px;
}
</style>