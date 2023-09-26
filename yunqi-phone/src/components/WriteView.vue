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
      <a-date-picker format="YYYY-MM-DD" v-model:value="formState.time" style="width: 40%;"/>
      <span>
        &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
      </span>
    </a-form>

    <div v-for="item in formState.dataList" :key="item">
      <WriteCardView :data="item"></WriteCardView>
    </div>
  </div>
</template>

<script setup lang="ts">
import WriteCardView from './card/WriteCardView.vue';
import { reactive, onMounted} from 'vue';
import { httpGet } from '../config/interFace'

const techId = localStorage.getItem('techId')

onMounted (async function () {
  const res = await httpGet('/view/detail',{
    techId: techId,
    headId: techId
  })
  formState.dataList = res
})

interface FormState {
  time: string,
  dataList: object
}

const formState = reactive<FormState>({
  time: '',
  dataList: []
});

const onFinish = async (values: any) => {
  console.log('Success:', values);
  const res = await httpGet('/view/work',{
    writerTime: formState.time,
    techId: techId,
    headId: techId
  })
  formState.dataList = res
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style scoped lang="less">

</style>