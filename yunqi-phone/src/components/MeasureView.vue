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
      &nbsp;<a-select :value="formState.type">
        <a-select-option value="开合帘">开合帘</a-select-option>
        <a-select-option value="卷帘">卷帘</a-select-option>
      </a-select>
      <a-date-picker format="YYYY-MM-DD" @change="onChange" style="width: 20%;"/>
      &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
    </a-form>
    <div v-for="item in formState.dataList" :key="item">
      <MeasureCardView :data="item" :type="formState.type" @toPage="toPage"></MeasureCardView>
    </div>
  </div>
</template>

<script setup lang="ts">
// 测量 开合 卷帘  内容
import router from '@/router';
import MeasureCardView from './card/MeasureCardView.vue'
import { reactive } from 'vue';
import { Dayjs } from 'dayjs'

interface FormState {
  dataList: any
  time: string
  type: string
}

const formState = reactive<FormState>({
  dataList: [],
  time: '',
  type: ''
});

const toPage = (str: any, id: any) => {
  router.push({name: str, query: {id: id}})
}

const onFinish = async () => {
  
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const onChange = (value: Dayjs, dateString: string) => {
  formState.time = dateString
};

</script>