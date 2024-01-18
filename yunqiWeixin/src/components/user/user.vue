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
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
const userId: any = localStorage.getItem('userId')
const avatar: any = localStorage.getItem('avatar')
const name = localStorage.getItem('userName')

onMounted(function(){

})

interface FormState {
  userId: String
}

const editPage = function(){

}

const formState = reactive<FormState>({
  userId: ''
})
</script>