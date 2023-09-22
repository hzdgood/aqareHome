<template>
  <main>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>&nbsp;&nbsp;项目名称: &nbsp;</span>
      <a-input style="width: 30%;" v-model:value="formState.projectName" @change="projectChange"></a-input>
      <!-- <span>&nbsp;&nbsp;类型: &nbsp;</span>
      <a-select style="width: 16%;" v-model:value="formState.type">
        <a-select-option value="安装">安装</a-select-option>
        <a-select-option value="调试">调试</a-select-option>
        <a-select-option value="交底">交底</a-select-option>
        <a-select-option value="检测">检测</a-select-option>
        <a-select-option value="售后">售后</a-select-option>
      </a-select>
      <span>&nbsp;&nbsp;日期: &nbsp;</span>
      <a-date-picker format="YYYY-MM-DD" v-model:value="formState.dateOfVisit" style="width: 20%;"/> -->
      <span>
        &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
        &nbsp;<a-button type="primary" @click="allSeletct">全部查询</a-button>
      </span>
    </a-form>
    <div v-for="item in formState.dataList" :key="item">
      <WorkCardView :data="item"  @toPage="toPage" @pageReset="pageReset" ></WorkCardView>
    </div>
  </main>
</template>

<script setup lang="ts">
import WorkCardView from './card/WorkCardView.vue';
import { reactive, onMounted  } from 'vue';
import router from '@/router';
import { httpGet } from '../config/interFace'

const techIds = localStorage.getItem('techId')

onMounted (async function () {
  const res = await httpGet('/view/work',{
    status: 'true',
    techIds: techIds,
    headId: techIds
  })
  formState.dataList = res
})

const toPage = (str: any, id: any) => {
  router.push({name: str, query: {id: id}})
}

const pageReset = async () => {
  const res = await httpGet('/view/work',{
    techIds: techIds,
    headId: techIds
  })
  formState.dataList = res
}

interface FormState {
  projectName: string;
  dataList: any
}

const formState = reactive<FormState>({
  projectName: '',
  dataList: []
});

const allSeletct = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    techIds: techIds,
    headId: techIds
  })
  formState.dataList = res
}

const projectChange = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    status: 'true',
    techIds: techIds,
    headId: techIds
  })
  formState.dataList = res
}

const onFinish = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    status: 'true',
    techIds: techIds,
    headId: techIds
  })
  formState.dataList = res
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>