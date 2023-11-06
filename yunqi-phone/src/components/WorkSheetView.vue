<template>
  <main>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finishFailed="onFinishFailed"
    >
      <div v-show="formState.type0">
        <span>&nbsp;上门日期: &nbsp;</span>
        <a-date-picker style="width: 25%;" v-model:value="formState.time" format="YYYY-MM-DD" @change="onRangeChange"/>
      </div>

      <div v-show="formState.type1">
        <span>&nbsp;项目信息: &nbsp;</span>
        <a-input style="width: 25%;" v-model:value="formState.projectName" @change="projectChange"></a-input>
      </div>

      <div class="sheetType">
        <span :class="formState.select0" @click="changeType(0)">日期</span>
        <span :class="formState.select1" @click="changeType(1)">项目</span>
      </div>

      <span>
        <!-- &nbsp;<a-button type="primary" html-type="submit">查询</a-button> -->
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

const changeType = (id: number) => {
  if(id === 0){
    formState.type0 = true
    formState.type1 = false
    formState.select0 = 'select'
    formState.select1 = ''
  } else {
    formState.type0 = false
    formState.type1 = true
    formState.select0 = ''
    formState.select1 = 'select'
  }
}

interface FormState {
  projectName: any;
  dataList: any
  time: string
  type0: boolean
  type1: boolean
  select0: string
  select1: string
}

const formState = reactive<FormState>({
  projectName: null,
  dataList: [],
  time: '',
  type0: false,
  type1: true,
  select0: '',
  select1: 'select'
});

const allSeletct = async () => {
  if(techId === '342') {
    const res = await httpGet('/view/work',{
      projectName: formState.projectName,
      dateOfVisit: formState.time,
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{ // 技术
      projectName: formState.projectName,
      dateOfVisit: formState.time,
      techId: loginName,
    })
    formState.dataList = res
  }
}

const projectChange = async () => {
  const res = await httpGet('/view/work',{ // 技术
    projectName: formState.projectName,
    techId: loginName,
    status: 'true'
  })
  formState.dataList = res
}

const onRangeChange = async () => {
  const res = await httpGet('/view/work',{
    dateOfVisit: formState.time,
    techId: loginName,
    status: 'true'
  })
  formState.dataList = res
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>