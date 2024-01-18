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
const avatar: any = localStorage.getItem('avatar')
const name = localStorage.getItem('userName')
onMounted(function(){

})

interface FormState {
  userId: any
}

const formState = reactive<FormState>({
  userId: localStorage.getItem('userId')
})

const editPage = function(){

}
</script>