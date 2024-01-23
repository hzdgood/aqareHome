<template>
  <div>
    <div class="footDiv" @click="openOption()">+</div>
    <div class="footContent" v-show="formState.optionStatus">
      <div>项目</div>
      <div>方案</div>
      <div>收款</div>
    </div>
    <div v-if="formState.projectShow">
      <project @close="close()"></project>
    </div>
    <div v-if="formState.schemeShow">
      <scheme @close="close()"></scheme>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue';
import scheme from '../footSub/scheme.vue'
import project from '../footSub/project.vue'

const openOption = function () {
  if (formState.optionStatus) {
    formState.optionStatus = false
  } else {
    formState.optionStatus = true
  }
}

interface FormState {
  optionStatus: boolean
  projectShow: boolean
  schemeShow: boolean
}

const formState = reactive<FormState>({
  optionStatus: false,
  projectShow: false,
  schemeShow: false
})

const close = function () {
  formState.schemeShow = false
  formState.projectShow = false
}

</script>

<style>

</style>