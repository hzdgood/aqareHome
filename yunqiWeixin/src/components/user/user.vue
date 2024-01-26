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
            <buttons></buttons>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
import buttons from '../button/button.vue'

const avatar: any = localStorage.getItem('avatar')
const name = localStorage.getItem('userName')
onMounted(function(){
  // name 需要转
})

interface FormState {
  customerShow: boolean
}
</script>