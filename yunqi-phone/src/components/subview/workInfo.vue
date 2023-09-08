<template>
  <div>
    <a-card title="" :bordered="false">
      <a-form
        :model="formState"
        name="workInfo"
        class="workInfo"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <table class="workTable">
          <tr>
            <td width="70px">项目姓名:</td>
            <td>
              <a-input :disabled="true" style="width: 95%;" v-model:value="formState.projectName"></a-input>
            </td>
          </tr>
          <tr>
            <td>技术人员:</td>
            <td>
              <a-input style="width: 95%;" v-model:value="formState.techName"></a-input>
            </td>
          </tr>
          <tr>
            <td>工单类型:</td>
            <td>
              <a-input style="width: 95%;" v-model:value="formState.workType"></a-input>
            </td>
          </tr>
          <tr>
            <td>上门时间:</td>
            <td>
              <a-input style="width: 95%;" v-model:value="formState.time"></a-input>
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">发单</a-button>
          <a-button type="primary">取消</a-button>
        </div>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
interface FormState {
  techName: string;
  projectName: string;
  workType: string;
  time: string;
}
const formState = reactive<FormState>({
  techName: '',
  projectName: '',
  workType: '',
  time: ''
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style lang="less" scoped>
.workTable{
  width: 100%;
  margin: 5px;
}
.workTable tr td{
  padding: 5px;
}
</style>