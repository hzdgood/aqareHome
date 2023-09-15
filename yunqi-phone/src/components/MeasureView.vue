<template>
  <div>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>&nbsp;&nbsp;测量日期：&nbsp;</span>
      <a-date-picker format="YYYY-MM-DD" @change="onChange" style="width: 20%;"/>
      &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
    </a-form>
    <MeasureCardView></MeasureCardView>
  </div>
</template>

<script setup lang="ts">
// 测量 开合 卷帘  内容
import MeasureCardView from './card/MeasureCardView.vue'
import { reactive } from 'vue';
import { Dayjs } from 'dayjs'

interface FormState {
  time: string;
}

const formState = reactive<FormState>({
   time: ''
});

const onFinish = async () => {
  
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const onChange = (value: Dayjs, dateString: string) => {
  formState.time = dateString
};

</script>