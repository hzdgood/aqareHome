<template>
  <div>
    <div class="footDiv" @click="openOption()">+</div>
    <div class="footContent" v-show="formState.optionStatus">
      <div @click="projectCheck">项目</div>
      <div @click="followCheck">跟进</div>
      <div @click="schemeCheck">方案</div>
      <div @click="collentCheck">收款</div>
    </div>
    <div v-if="formState.projectShow">
      <project @close="close()"></project>
    </div>
    <div v-if="formState.followShow">
      <follow @close="close()"></follow>
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
import follow from '../footSub/follow.vue'
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
  followShow: boolean
}

const formState = reactive<FormState>({
  optionStatus: false,
  projectShow: false,
  schemeShow: false,
  collentShow: false,
  followShow: false
})

const projectCheck = function () {
  formState.projectShow = true
  formState.collentShow = false
  formState.schemeShow = false
  formState.followShow = false
}

const followCheck = function(){
  formState.projectShow = false
  formState.collentShow = false
  formState.schemeShow = false
  formState.followShow = true
}

const schemeCheck = function () {
  formState.projectShow = false
  formState.collentShow = false
  formState.schemeShow = true
  formState.followShow = false
}

const collentCheck = function(){
  formState.projectShow = false
  formState.collentShow = true
  formState.schemeShow = false
  formState.followShow = false
}

const close = function () {
  formState.schemeShow = false
  formState.projectShow = false
  formState.collentShow = false
  formState.followShow = false
}

</script>

<style>

</style>