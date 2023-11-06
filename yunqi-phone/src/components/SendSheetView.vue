<template>
<div>
    <main>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finishFailed="onFinishFailed"
    >
      <div v-show="formState.type0">
        <span>&nbsp;上门日期: &nbsp;</span>
        <a-date-picker style="width: 25%;" v-model:value="formState.time" format="YYYY-MM-DD" @change="onRangeChange"  />
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
      <SendCardView :data="item"  @toPage="toPage" @pageReset="pageReset" ></SendCardView>
    </div>
  </main>
</div>
</template>

<script setup lang="ts">
import SendCardView from './card/SendCardView.vue';
import { reactive, onMounted  } from 'vue';
import router from '@/router';
import { httpGet } from '../config/interFace'
// import { Dayjs } from 'dayjs';

const techId = localStorage.getItem("techId");

onMounted (async function () {
  const res = await httpGet('/view/work',{ // 技术
    headId: techId,
    status: 'true' // 去除 已完成工单
  })
  formState.dataList = res
})

const toPage = (str: any, obj: any) => {
  router.push({name: str, query: {
    id: obj.id,
    techId: obj.techId
  }})
}

const pageReset = async () => {
  const res = await httpGet('/view/work',{
    headId: techId,
    status: 'true'
  })
  formState.dataList = res
}

interface FormState {
  projectName: any
  dataList: any
  time: string
  type0: boolean
  type1: boolean
  select0: string
  select1: string
}

const formState = reactive<FormState>({
  projectName: '',
  dataList: [],
  time: '',
  type0: false,
  type1: true,
  select0: '',
  select1: 'select'
});

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

const onRangeChange = async () => {
  const res = await httpGet('/view/work',{
    dateOfVisit: formState.time,
    headId: techId,
    status: 'true'
  })
  formState.dataList = res
};

const allSeletct = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    dateOfVisit: formState.time,
    headId: techId,
  })
  formState.dataList = res
}

const projectChange = async () => {
  const res = await httpGet('/view/work',{
    projectName: formState.projectName,
    headId: techId,
    status: 'true'
  })
  formState.dataList = res
}

// const onFinish = async () => {
//   const res = await httpGet('/view/work',{
//     projectName: formState.projectName,
//     dateOfVisit: formState.time,
//     headId: techId,
//     status: 'true'
//   })
//   formState.dataList = res
// };

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>