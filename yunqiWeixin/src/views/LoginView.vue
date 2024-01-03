<template>
  <div class="appbody">
    <div style="background-color: #f2f3fb;">

      <a-form :model="formState" :label-col="labelCol">
        <a-form-item label="用户名称：">
          <a-input v-model:value="formState.name" />
        </a-form-item>
      </a-form>

      <a-space direction="vertical">
        <table width="100%">
          <tr>
            <td>用户名称</td>
            <td><a-input v-model:value="formState.name"></a-input></td>
            <td>电话</td>
            <td><a-input v-model:value="formState.telephone"></a-input></td>
          </tr>
          <tr>
            <td>客户来源</td>
            <td><a-input v-model:value="formState.source"></a-input></td>
            <td>销售</td>
            <td><a-input v-model:value="formState.sales"></a-input></td>
          </tr>
          <tr>
            <td>房屋情况</td>
            <td>
              <a-select v-model:value="formState.houseState">
                <a-select-option key="" value=""></a-select-option>
              </a-select>
            </td>
            <td>房型</td>
            <td>
              <a-select v-model:value="formState.houseType">
                <a-select-option key="" value=""></a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>客户阶段</td>
            <td>
              <a-select v-model:value="formState.customerStage">
                <a-select-option key="" value=""></a-select-option>
              </a-select>
            </td>
            <td>需求</td>
            <td>
              <a-select v-model:value="formState.houseDemand">
                <a-select-option key="" value=""></a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>人员属性</td>
            <td>
              <a-select v-model:value="formState.personnelAttr">
                <a-select-option key="" value=""></a-select-option>
              </a-select>
            </td>
            <td>状态</td>
            <td>
              <a-select v-model:value="formState.lossStatus">
                <a-select-option key="" value=""></a-select-option>
              </a-select>
            </td>
          </tr>
        </table>
      </a-space>
      <div>
        <a-button type="primary" @click="addCustomer">提交</a-button>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
const userId = localStorage.getItem("userId")

const labelCol = { style: { width: '60px' } };
const wrapperCol = { span: 14 };

onMounted(function() {
  // 请求 userId, 如果存在，跳转， 不存在 新增
  
})

const addCustomer = () => {

}

interface FormState {
  name: string
  telephone: string
  source: string
  sales: string
  houseState: string
  houseType: string
  customerStage: string
  houseDemand: string
  personnelAttr: string
  lossStatus: string
}

const formState = reactive<FormState>({
  name: '',
  telephone: '',
  source: '',
  sales: '',
  houseState: '',
  houseType: '',
  customerStage: '',
  houseDemand: '',
  personnelAttr: '',
  lossStatus: ''
})
</script>
