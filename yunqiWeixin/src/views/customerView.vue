<template>
  <div>
    <table width="100%" class="projectTable">
      <tr>
        <td width="70px">客户来源</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
        <td width="70px">客户房型</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
      </tr>
      <tr>
        <td>使用情况</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
        <td>装修进度</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
      </tr>
      <tr>
        <td>人员属性</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
        <td>全屋需求</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { httpGet } from '../config/interFace'
const follow_user: any = localStorage.getItem('follow_user')
onMounted(async function() {

})

interface FormState {
  custominto: String
}

const formState = reactive<FormState>({
  custominto: '',
})

const handleChange = (value: string) => {
  console.log(`selected ${value}`);
};

</script>