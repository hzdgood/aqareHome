<template>
<div>
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
  projectName: any;
  dataList: any
}

const formState = reactive<FormState>({
  projectName: '',
  dataList: []
});

const allSeletct = async () => {
  const res = await httpGet('/view/work',{
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

const onFinish = async () => {
  const res = await httpGet('/view/work',{
    headId: techId,
    status: 'true'
  })
  formState.dataList = res
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>