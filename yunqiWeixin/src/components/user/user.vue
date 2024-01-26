<template>
  <div>
    <div class="userDiv">
      <table>
        <tr>
          <td>
            <img width="40px" height="40px;" :src="avatar" />
          </td>
          <td>
            <span class="username">{{ name }}</span>
          </td>
          <td>
            <button @click="editPage()">编辑</button>
          </td>
        </tr>
      </table>
    </div>
    <div v-if="formState.customerShow">
      <customer @close="close()"></customer>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
import customer from '../edit/customer.vue'
const avatar: any = localStorage.getItem('avatar')
const name = localStorage.getItem('userName')
onMounted(function(){
  // name 需要转
})

interface FormState {
  customerShow: boolean
}

const formState = reactive<FormState>({
  customerShow: false
})

const editPage = function(){
  formState.customerShow = true
}

const close = function () {
  formState.customerShow = false
}

</script>