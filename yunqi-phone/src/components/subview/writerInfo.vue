<template>
  <div class="cardDiv">
    <a-card :title="formState.projectName + '-' + formState.type" :bordered="false">
      <a-form
        :model="formState"
        name="writerInfo"
        class="writerInfo"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <div v-for="item in formState.dataList" :key="item">
          <writerTable :data="item" @change="getChange"></writerTable>
        </div>
        <div>
          今日工作总结: <a-input style="width: 90%;" :value="formState.workSummary"></a-input>
        </div>
        <div>
          下次上门节点: <a-input style="width: 90%;" :value="formState.visitNode"></a-input>
        </div>
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
// 工单核销
import { reactive, onMounted } from 'vue';
import router from '@/router';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import writerTable from './tables/writerTable.vue'

const route = useRoute()

let formObj: any[] = []

onMounted (async function () {
  formObj = [];
  const workId = route.query.id
  const res = await httpGet('/view/writer',{
    workId: workId
  })
  if(res.length === 0) {
    router.push({name: 'workSheet'})
  } else {
    formState.dataList = res
  }

  const res1 = await httpGet('/view/work',{
    workId: workId
  })
  formState.projectName = res1[0].projectName;
  formState.type = res1[0].type;
})

const resPage = () => {
  router.push({name: 'workSheet'})
}

const getChange = (obj: any) => {
  if(formObj.length === 0 ) {
    formObj.push(obj)
  } else {
    for(let i=0; i < formObj.length; i++) {
      const projectId = formObj[i].projectId;
      if(projectId !== obj.projectId) {
        formObj.push(obj)
      } else {
        formObj[i].install = obj.install
        formObj[i].debug = obj.debug
      }
    }
  }
}

interface FormState {
  projectName: string;
  type: string
  workSummary: string
  visitNode: string
  dataList: object
}

const formState = reactive<FormState>({
  projectName: '',
  type: '',
  workSummary: '',
  visitNode: '',
  dataList: []
});

const onFinish = async () => {
  console.log(formObj);
  for(let i=0; i<formObj.length;i++){
    const res = await httpGet('/writer/insert', formObj[i]) // 核销新增
    console.log(res);
  }
  const res = await httpGet('/workSheet/update', { //工单修改
    id: route.query.id,
    workSummary: formState.workSummary,
    visitNode: formState.visitNode
  })
  console.log(res);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style lang="less" scoped>

</style>