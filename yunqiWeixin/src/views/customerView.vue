<template>
  <div>
    <table width="100%" class="projectTable">
      <tr>
        <td width="70px">客户来源</td>
        <td><a-input style="width: 100%"></a-input></td>
        <td width="70px">客户房型</td>
        <td><a-input style="width: 100%"></a-input></td>
      </tr>
      <tr>
        <td>使用情况</td>
        <td><a-input style="width: 100%"></a-input></td>
        <td>装修进度</td>
        <td><a-input style="width: 100%"></a-input></td>
      </tr>
      <tr>
        <td>人员属性</td>
        <td><a-input style="width: 100%"></a-input></td>
        <td>全屋需求</td>
        <td><a-input style="width: 100%"></a-input></td>
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
  hometype: String
  houseInfo: String
  personInfo: String
  houseNeed: String
  cAttribute: String
  oneCustom: String
  lostStatus: String
}

const formState = reactive<FormState>({
  custominto: '',
  hometype: '',
  houseInfo: '',
  personInfo: '',
  houseNeed: '',
  cAttribute: '',
  oneCustom: '',
  lostStatus: ''
})

const handleChange = (value: string) => {
  console.log(`selected ${value}`);
};

</script>