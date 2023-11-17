<template>
  <div>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finish="onFinish"
    >
      <span>&nbsp;&nbsp;测量类型：&nbsp;</span>
      <a-select :value="formState.type" style="width: 30%;">
        <a-select-option value="开合帘">开合帘</a-select-option>
        <a-select-option value="卷帘">卷帘</a-select-option>
      </a-select>
      &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
    </a-form>
    <div v-for="item in formState.dataList" :key="item">
      <MeasureCardView :data="item" :type="formState.type" @pageReset="pageReset" @toPage="toPage"></MeasureCardView>
    </div>
  </div>
</template>

<script setup lang="ts">
// 测量 开合 卷帘  内容
import router from '@/router';
import MeasureCardView from './card/MeasureCardView.vue'
import { reactive, onMounted } from 'vue';
import { httpGet } from '../config/interFace'

onMounted (async function () {
  const res = await httpGet('/measure/selectOpen',{})
  if(res.length > 0)  {
    formState.type = res[0].type
    formState.dataList = res
  }
})

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

const pageReset = async () => {
  const res = await httpGet('/measure/selectOpen',{})
  if(res.length > 0)  {
    formState.type = res[0].type
    formState.dataList = res
  }
}

const toPage = (str: any, id: any) => {
  router.push({name: str, query: {id: id}})
}

const onFinish = async () => {
  if(formState.type === '开合帘') {
    const res = await httpGet('/measure/selectOpen',{})
    formState.dataList = res
  } else {
    const res = await httpGet('/measure/selectRoller',{})
    formState.dataList = res
  }
};
</script>