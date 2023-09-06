<template>
  <main>
    <a-form
      :model="formState"
      name="workView"
      class="workView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>技术人员：</span>
      <a-input style="width: 13%;" v-model:value="formState.techName"></a-input>
      <span>上门日期：</span>
      <a-date-picker show-time placeholder="Select Time" format="YYYY-MM-DD HH:mm" @change="onChange" @ok="onOk" style="width: 20%;"/>
      <span>
        <a-button type="primary" html-type="submit">查询</a-button>
      </span>
    </a-form>
    <WorkCardView></WorkCardView>
  </main>
</template>

<script setup lang="ts">
import WorkCardView from './card/WorkCardView.vue';
import { reactive } from 'vue';
import { Dayjs } from 'dayjs'

interface FormState {
  techName: string;
}
const formState = reactive<FormState>({
  techName: ''
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const onChange = (value: Dayjs, dateString: string) => {
  console.log('Selected Time: ', value);
  console.log('Formatted Selected Time: ', dateString);
};

const onOk = (value: Dayjs) => {
  console.log('onOk: ', value);
};

</script>

<style lang="less" scoped>
span {
  padding: 5px;
}
.workView{
  margin-top: 40px;
  margin-bottom: 5px;
}
</style>