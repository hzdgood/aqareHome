<template>
  <div>
    <div>
      <a-form
        :model="formState"
        name="pageView"
        class="pageView"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <span>&nbsp;&nbsp;负责人: &nbsp;</span>
        <a-select style="width: 16%;" v-model:value="formState.techName">
          <a-select-option value="328">葛栋梁</a-select-option>
          <a-select-option value="329">娄德</a-select-option>
          <a-select-option value="327">汪克祥</a-select-option>
          <a-select-option value="332">严熠</a-select-option>
        </a-select>
        <span>&nbsp;&nbsp;项目名称: &nbsp;</span>
        <a-input style="width: 20%;" v-model:value="formState.projectName"></a-input>
        <span>
          &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
        </span>
      </a-form>
    </div>
    <div v-for="item in formState.dataList" :key="item">
      <ProjectCardView :data="item" @toPage="toPage"></ProjectCardView>
    </div>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import ProjectCardView from './card/ProjectCardView.vue';
import { reactive, onMounted } from 'vue';
import { httpGet } from '../config/interFace'

onMounted (async function () {
  const res = await httpGet('/view/project',{})
  formState.dataList = res
})

const toPage = (str: any, id: any) => {
  router.push({name: str, query: {id: id}})
}

interface FormState {
  techName: string;
  projectName: string;
  dataList: any
}

const formState = reactive<FormState>({
  techName: '',
  projectName: '',
  dataList: []
});

const onFinish = async () => {
  const res = await httpGet('/view/project',{
    techID: formState.techName,
    projectName: formState.projectName
  })
  formState.dataList = res
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>