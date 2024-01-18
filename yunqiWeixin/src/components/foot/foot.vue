<template>
  <div>
    <div class="footDiv" @click="openOption()">+</div>
    <div class="footContent" v-show="formState.optionStatus">
      <div>信息</div>
      <div>方案</div>
      <div>收款</div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive } from 'vue';

const openOption = function () {
  if (formState.optionStatus) {
    formState.optionStatus = false
  } else {
    formState.optionStatus = true
  }
}

interface FormState {
  optionStatus: boolean
}

const formState = reactive<FormState>({
  optionStatus: false
})

</script>

<style>

</style>