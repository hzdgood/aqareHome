<template>
  <div>
    <div class="footDiv" @click="openOption()">+</div>
    <div class="footContent" v-show="formState.optionStatus">
      <div @click="projectCheck">项目</div>
      <div @click="schemeCheck">方案</div>
      <div @click="collentCheck">收款</div>
    </div>
    <div v-if="formState.projectShow">
      <project @close="close()"></project>
    </div>
    <div v-if="formState.schemeShow">
      <scheme @close="close()"></scheme>
    </div>
    <div v-if="formState.collentShow">
      <collent @close="close()"></collent>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue';
import project from '../footSub/project.vue'
import scheme from '../footSub/scheme.vue'
import collent from '../footSub/collent.vue'

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
  collentShow: boolean
}

const formState = reactive<FormState>({
  optionStatus: false,
  projectShow: false,
  schemeShow: false,
  collentShow: false
})

const projectCheck = function () {
  formState.projectShow = true
  formState.collentShow = false
  formState.schemeShow = false
}

const schemeCheck = function () {

}

const collentCheck = function(){

}

const close = function () {
  formState.schemeShow = false
  formState.projectShow = false
  formState.collentShow = false
}

</script>

<style>

</style>