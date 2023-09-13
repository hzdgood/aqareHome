<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <a-form
        :model="formState"
        name="workInfo"
        class="workInfo"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <table class="cardTale">
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
              <a-select style="width: 95%;" v-model:value="formState.workType">
                <a-select-option value="安装">安装</a-select-option>
                <a-select-option value="调试">调试</a-select-option>
                <a-select-option value="交底">交底</a-select-option>
                <a-select-option value="检测">检测</a-select-option>
                <a-select-option value="售后">售后</a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>上门时间:</td>
            <td>
              <a-date-picker format="YYYY-MM-DD" @change="onChange" style="width: 95%;"/>
            </td>
          </tr>
          <tr>
            <td>工单备注:</td>
            <td>
              <a-input style="width: 95%;" v-model:value="formState.remark"></a-input>
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">发单</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
//发单
import { reactive } from 'vue';
import router from '@/router';
import { Dayjs } from 'dayjs'

const resPage = () => {
  router.push({name: 'workSheet'})
}
interface FormState {
  techName: string;
  projectName: string;
  workType: string;
  time: string;
  remark: String;
}
const formState = reactive<FormState>({
  techName: '',
  projectName: '',
  workType: '',
  time: '',
  remark: ''
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const onChange = (value: Dayjs, dateString: string) => {
  formState.time = dateString
};

</script>

<style lang="less" scoped>

</style>