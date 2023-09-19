<template>
  <div class="cardDiv">
    <a-card title="王优 安装" :bordered="false">
      <a-form
        :model="formState"
        name="writerInfo"
        class="writerInfo"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        
        <writerTable></writerTable>

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

onMounted (async function () {
  const res = await httpGet('/view/writer',{
    workId: route.query.id
  })
  console.log(res);
  
})

const resPage = () => {
  router.push({name: 'workSheet'})
}

interface FormState {
  projectName: string;
  workSummary: string
  visitNode: string
  data: object
}

const formState = reactive<FormState>({
  projectName: '',
  workSummary: '11',
  visitNode: '11',
  data: []
});

const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style lang="less" scoped>

</style>