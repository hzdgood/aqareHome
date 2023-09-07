<template>
  <div>
    <a-form
      :model="formState"
      name="writeView"
      class="writeView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>核销日期:</span>
      <a-date-picker format="YYYY-MM-DD" @change="onChange" style="width: 40%;"/>
      <span>
        <a-button type="primary" html-type="submit">查询</a-button>
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
span {
  padding: 5px;
  font-size: 1em;
}
.writeView{
  margin-top: 10px;
  margin-bottom: 5px;
}
</style>