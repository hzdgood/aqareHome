<template>
  <main>
    <a-form
      :model="formState"
      name="workView"
      class="workView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>技术人员:</span>
      <a-input style="width: 16%;" v-model:value="formState.techName"></a-input>
      <span>上门日期:</span>
      <a-date-picker format="YYYY-MM-DD" @change="onChange" style="width: 20%;"/>
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
</script>

<style lang="less" scoped>
span {
  padding: 5px;
  font-size: 1em;
}
.workView{
  margin-top: 10px;
  margin-bottom: 5px;
}
</style>