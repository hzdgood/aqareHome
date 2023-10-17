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
        <table class="cardTale">
          <tr>
            <td>今日工作总结: </td>
            <td>
              <a-textarea v-model:value="formState.workSummary" :rows="4" />
            </td>
          </tr>
          <tr>
            <td>下次上门节点: </td>
            <td>
              <a-textarea v-model:value="formState.visitNode" :rows="4" />
            </td>
          </tr>
          <tr>
            <td>工作交接: </td>
            <td>
              <a-textarea v-model:value="formState.visitNode" :rows="4" />
            </td>
          </tr>
        </table>

        <a-button type="primary">物料核对</a-button>
        <a-button type="primary">汇报凭证</a-button>
        <a-button type="primary">核销照片</a-button>
        <a-button type="primary">签字单</a-button>
        <a-button type="primary">其他照片</a-button>

        <div class="buttonPos">
          <a-button type="primary" html-type="submit">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>核销完成！</p>
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
const techId = localStorage.getItem('techId')
const route = useRoute()

let formObj: any[] = []

onMounted (async function () {
  formObj = [];

  const res1 = await httpGet('/view/work',{ // 查询工单
    workId: route.query.id
  })

  formState.projectName = res1[0].projectName;
  formState.type = res1[0].type;
  formState.workSummary = res1[0].workSummary
  formState.visitNode = res1[0].visitNode

  let res: any
  if(formState.type === '检测') {
      res = await httpGet('/view/writer',{ // 查询可核销数据
      workId: route.query.id,
      techId: route.query.techId,
      type: '检测' // 只能核销上门类型
    })
  } else {
      res = await httpGet('/view/writer',{ // 查询可核销数据
      workId: route.query.id,
      techId: route.query.techId
    })
  }
  if(res.length === 0) {
    router.push({name: 'workSheet'})
  } else {
    formState.dataList = res
  }
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
        formObj[i].install = obj.install
        formObj[i].debug = obj.debug
        status = true
      }
    }
    if(status === false) {
      formObj.push(obj)
    }
  }
  console.log(formObj);
}

interface FormState {
  projectName: string;
  type: string
  workSummary: string
  visitNode: string
  dataList: object
  modalInfo: String
}

const formState = reactive<FormState>({
  projectName: '',
  type: '',
  workSummary: '',
  visitNode: '',
  dataList: [],
  modalInfo: ''
});

const onFinish = async () => {
  formState.modalInfo = '请确认核销内容'
  showModal();
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  open.value = false;
  if(formState.modalInfo === '核销完成') {
    router.push({name: 'workSheet'})
  } else {
    for(let i=0; i < formObj.length; i++){
      await httpGet('/writer/insert', formObj[i]) // 核销新增
    }
    const res = await httpGet('/workSheet/update', { //工单修改
      id: route.query.id,
      workSummary: formState.workSummary, //今日工作总结
      visitNode: formState.visitNode, //下次上门节点
      updateName: techId // 核销人
    })
    formState.modalInfo = res
    showModal()
  }
};

</script>

<style lang="less" scoped>

</style>