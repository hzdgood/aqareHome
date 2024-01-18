<template>
  <div>
    <div class="footDiv" @click="selectOption()">+</div>
    <div class="footContent" v-show="optionStatus">
      <div>信息</div>
      <div>方案</div>
      <div>收款</div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive } from 'vue';

let optionStatus = false
const selectOption = function () {
  if (optionStatus) {
    optionStatus = false
  } else {
    optionStatus = true
  }
}

</script>

<style>

</style>