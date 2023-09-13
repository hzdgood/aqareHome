<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <a-form
        :model="formState"
        name="writerInfo"
        class="writerInfo"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <table class="cardTale">
          <tr>
            <td width="70px">项目姓名:</td>
            <td>
              <a-input :disabled="true" style="width: 95%;"></a-input>
            </td>
            <td width="70px">工单类型:</td>
            <td>
              <a-input :disabled="true" style="width: 95%;"></a-input>
            </td>
          </tr>
          <tr>
            <td width="70px">产品名称:</td>
            <td colspan="3">
              <a-input :disabled="true" style="width: 95%;" v-model:value="formState.projectName"></a-input>
            </td>
          </tr>
          <tr>
            <td>未安装:</td>
            <td>
              <a-input :disabled="true" style="width: 90%;"></a-input>
            </td>
            <td width="70px">未调试:</td>
            <td>
              <a-input :disabled="true" style="width: 90%;"></a-input>
            </td>
          </tr>
          <tr>
            <td>本次安装:</td>
            <td>
              <a-input style="width: 90%;"></a-input>
            </td>
            <td>本次调试:</td>
            <td>
              <a-input style="width: 90%;"></a-input>
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
// 工单核销
import { reactive } from 'vue';
import router from '@/router';

const resPage = () => {
  router.push({name: 'workSheet'})
}

interface FormState {
  projectName: string;

}
const formState = reactive<FormState>({
  projectName: '高精度人体传感器'
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style lang="less" scoped>

</style>