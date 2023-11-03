<template>
  <main>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <span>&nbsp;&nbsp;项目信息: &nbsp;</span>
      <a-input style="width: 30%;" v-model:value="formState.projectName" @change="projectChange"></a-input>
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

const techId = localStorage.getItem("techId");
const loginName = localStorage.getItem('loginName')

onMounted (async function () {
  if(techId === '342') {
    const res = await httpGet('/view/work',{ // 技术
      status: 'true' // 去除 已完成工单
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{ // 技术
      techId: loginName,
      status: 'true' // 去除 已完成工单
    })
    formState.dataList = res
  }
})

const toPage = (str: any, obj: any) => {
  router.push({name: str, query: {
    id: obj.id,
    techId: obj.techId
  }})
}

const pageReset = async () => {
  if(techId === '342') {
    const res = await httpGet('/view/work',{ // 技术
      status: 'true' // 去除 已完成工单
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{ // 技术
      techId: loginName,
      status: 'true' // 去除 已完成工单
    })
    formState.dataList = res
  }
}

interface FormState {
  projectName: any;
  dataList: any
}

const formState = reactive<FormState>({
  projectName: null,
  dataList: []
});

const allSeletct = async () => {
  if(techId === '342') {
    const res = await httpGet('/view/work',{})
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{ // 技术
      techId: loginName,
    })
    formState.dataList = res
  }
}

const projectChange = async () => {
  if(techId === '342') {
    const res = await httpGet('/view/work',{
      projectName: formState.projectName,
      status: 'true'
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{ // 技术
      projectName: formState.projectName,
      techId: loginName,
      status: 'true'
    })
    formState.dataList = res
  }
}

const onFinish = async () => {
  if(techId === '342') {
    const res = await httpGet('/view/work',{
      projectName: formState.projectName,
      status: 'true'
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{ // 技术
      projectName: formState.projectName,
      techId: loginName,
      status: 'true'
    })
    formState.dataList = res
  }
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>