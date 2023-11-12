<template>
<div>
    <main>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
      @finishFailed="onFinishFailed"
    >
      <span v-show="formState.type0">&nbsp;预约时间: &nbsp;</span>
      <a-date-picker v-show="formState.type0" style="width: 35%;" v-model:value="formState.time" format="YYYY-MM-DD" @change="onRangeChange"  />
      <span v-show="formState.type1">&nbsp;项目信息: &nbsp;</span>
      <a-input v-show="formState.type1" style="width: 35%;" v-model:value="formState.projectName" @change="projectChange"></a-input>
      &nbsp;<span class="sheetType">
        <span :class="formState.select0" @click="changeType(0)">日期</span>
        <span :class="formState.select1" @click="changeType(1)">项目</span>
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

const techId = localStorage.getItem("techId");

onMounted (async function () {
  if(techId === '342' || techId === '339') {
    const res = await httpGet('/view/work',{})
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{
      headId: techId,
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
  if(techId === '342' || techId === '339') {
    const res = await httpGet('/view/work',{
      dateOfVisit: formState.time
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{
      headId: techId,
    })
    formState.dataList = res
  }
};

const projectChange = async () => {
  if(techId === '342' || techId === '339') {
    const res = await httpGet('/view/work',{
      projectName: formState.projectName,
    })
    formState.dataList = res
  } else {
    const res = await httpGet('/view/work',{
      projectName: formState.projectName,
      headId: techId,
    })
    formState.dataList = res
  }
}

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style lang="less" scoped>
.sheetType span {
  background-color: darkgray;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px
}
.select{
  background-color: deepskyblue !important;
  color: #fff;
}
</style>