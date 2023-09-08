<template>
  <div>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>&nbsp;&nbsp;结算日期: &nbsp;</span>
      <a-date-picker format="YYYY-MM-DD" @change="onChange" style="width: 40%;"/>
      <span>
        &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
      </span>
    </a-form>
    <WriteCardView></WriteCardView>
  </div>
</template>

<script setup lang="ts">
import WriteCardView from './card/WriteCardView.vue';
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

<style scoped lang="less">

</style>