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
        <table class="cardTale">
          <tr>
            <td>今日工作总结: </td>
            <td>
              <a-input style="width: 90%;" v-model:value="formState.workSummary"></a-input>
            </td>
          </tr>
          <tr>
            <td>下次上门节点: </td>
            <td>
              <a-input style="width: 90%;" v-model:value="formState.visitNode"></a-input>
            </td>
          </tr>
        </table>
        <div v-for="item in formState.dataList" :key="item">
          <writerTable :data="item" @change="getChange"></writerTable>
        </div>
        
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>该工单已核销完成！</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
// 工单核销
import { reactive, onMounted, ref } from 'vue';
import router from '@/router';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import writerTable from './tables/writerTable.vue'

const open = ref<boolean>(false);
const techIds = localStorage.getItem('techId')
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
    workId: workId,
    techIds: techIds,
  })
  formState.projectName = res1[0].projectName;
  formState.type = res1[0].type;
})

const resPage = () => {
  router.push({name: 'workSheet'})
}

const getChange = (obj: any) => {
  let status = false;

  if(formObj.length === 0 ) {
    formObj.push(obj)
  } else {
    for(let i=0; i < formObj.length; i++) {
      const productId = formObj[i].productId + '';
      if(productId === obj.productId + '') {
        status = true
      }
      formObj[i].install = obj.install
      formObj[i].debug = obj.debug
    }
    if(status === false) {
      formObj.push(obj)
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
  for(let i=0; i < formObj.length; i++){
    await httpGet('/writer/insert', formObj[i]) // 核销新增
  }
  await httpGet('/workSheet/update', { //工单修改 -- 核销完成
    id: route.query.id,
    workSummary: formState.workSummary,
    visitNode: formState.visitNode
  })
  showModal();
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  router.push({name: 'workSheet'})
};

</script>

<style lang="less" scoped>

</style>