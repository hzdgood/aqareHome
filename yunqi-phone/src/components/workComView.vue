<template>
  <main>
    <a-form
      :model="formState"
      name="pageView"
      class="pageView"
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
      <WorkComCardView :data="item"  @toPage="toPage" @pageReset="pageReset" ></WorkComCardView>
    </div>
  </main>
</template>

<script setup lang="ts">
import WorkComCardView from './card/WorkComCardView.vue';
import { reactive, onMounted  } from 'vue';
import router from '@/router';
import { httpGet } from '../config/interFace'

onMounted (async function () {
  const res = await httpGet('/view/workCom',{})
  formState.dataList = res
})

const toPage = (str: any, obj: any) => {
  router.push({name: str, query: {
    id: obj.id,
    techId: obj.techId
  }})
}

const pageReset = async () => {
  const res = await httpGet('/view/workCom',{ // 技术
  })
  formState.dataList = res
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

const projectChange = async () => {
  const res = await httpGet('/view/workCom',{
    projectName: formState.projectName,
  })
  formState.dataList = res
}

const onRangeChange = async () => {
  const res = await httpGet('/view/workCom',{
    dateOfVisit: formState.time,
  })
  formState.dataList = res
};

</script>

<style lang="less" scoped>
.sheetType span {
  background-color: darkgray;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  cursor: pointer;
}
.select{
  background-color: deepskyblue !important;
  color: #fff;
}
</style>