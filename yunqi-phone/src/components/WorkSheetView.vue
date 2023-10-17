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

const techId = localStorage.getItem('techId')
const loginName = localStorage.getItem('loginName')

onMounted (async function () {
  const res = await httpGet('/view/work',{ // 技术
    techId: loginName,
    status: 'true' // 去除 已完成工单
  })
  formState.dataList = res

  const res1 = await httpGet('/view/work',{ // 负责人
    headId: techId,
    status: 'true' // 去除 已完成工单
  })
  if(res1.length > 0) {
    formState.dataList.push(res1[0])
  }
})

const toPage = (str: any, obj: any) => {
  router.push({name: str, query: {
    id: obj.id,
    techId: obj.techId
  }})
}

const pageReset = async () => {
  const res = await httpGet('/view/work',{
    techId: loginName,
    status: 'true'
  })
  formState.dataList = res

  const res1 = await httpGet('/view/work',{
    headId: techId,
    status: 'true'
  })
  if(res1.length > 0) {
    formState.dataList.push(res1[0])
  }
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
    techId: loginName
  })
  formState.dataList = res

  const res1 = await httpGet('/view/work',{
    headId: techId,
  })
  if(res1.length > 0) {
    formState.dataList.push(res1[0])
  }
}

const projectChange = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    techId: loginName
  })
  formState.dataList = res

  const res1 = await httpGet('/view/work',{
    projectName: formState.projectName,
    headId: techId,
  })
  if(res1.length > 0) {
    formState.dataList.push(res1[0])
  }
}

const onFinish = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    techId: loginName,
    status: 'true'
  })
  formState.dataList = res

  const res1 = await httpGet('/view/work',{
    projectName: formState.projectName,
    headId: techId,
    status: 'true'
  })
  if(res1.length > 0) {
    formState.dataList.push(res1[0])
  }
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>