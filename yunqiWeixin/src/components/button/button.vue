<template>
  <div>
    <div>
      <button @click="editPage()">客户编辑</button>
      <button>客户动态</button>
    </div>
    <div v-if="formState.customerShow">
      <customer @close="close()"></customer>
    </div>
    <div v-if="formState.chatShow">
      <chat @close="close()"></chat>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive } from 'vue';
import customer from '../buttonEdit/customer.vue'
import chat from '../buttonEdit/chat.vue'

interface FormState {
  customerShow: boolean
  chatShow: boolean
}

const formState = reactive<FormState>({
  customerShow: false,
  chatShow: false,
})

const editPage = function(){
  formState.customerShow = true
  formState.chatShow = false
}

const close = function () {
  formState.customerShow = false
  formState.chatShow = false
}

</script>