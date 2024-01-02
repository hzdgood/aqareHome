<template>
  <div id="login">
    <div class="userDiv">
      <table>
        <tr>
          <td>
            <img width="40px" height="40px;" v-bind:src="avatar" />
          </td>
          <td>
            <span class="username">{{ userId }}</span>
          </td>
          <td>
            <button>编辑</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
const userId: any = localStorage.getItem('userId');
const avatar: any = localStorage.getItem('avatar');

onMounted(function(){

})

interface FormState {
  userId: any[]
}

const formState = reactive<FormState>({
  userId: []
})
</script>