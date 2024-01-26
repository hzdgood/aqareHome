<template>
  <div>
    <table width="100%" class="projectTable">
      <tr>
        <td width="70px">项目类型</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
        <td width="70px">项目状态</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
      </tr>
      <tr>
        <td>房屋情况</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
        <td>联系方式</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
      </tr>
      <tr>
        <td>小区名称</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
        <td>所在区域</td>
        <td><a-input style="width: 100%" :value="formState.custominto"></a-input></td>
      </tr>
      <tr>
        <td>客户地址</td>
        <td colspan="3"><a-input style="width: 100%"></a-input></td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { httpGet } from '../config/interFace'
onMounted(function() {
  
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
