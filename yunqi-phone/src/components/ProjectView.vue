<template>
  <div>
    <div>
      <a-form
        :model="formState"
        name="pageView"
        class="pageView"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <span>&nbsp;&nbsp;项目信息: &nbsp;</span>
        <a-input style="width: 30%;" v-model:value="formState.projectName"></a-input>
        <span>
          &nbsp;<a-button type="primary" html-type="submit">未完结</a-button>
        </span>
        <span>
          &nbsp;<a-button type="primary" @click="allSeletct">已完结</a-button>
        </span>
      </a-form>
    </div>
    <div v-for="item in formState.dataList" :key="item">
      <ProjectCardView :data="item" @toPage="toPage"></ProjectCardView>
    </div>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import ProjectCardView from './card/ProjectCardView.vue';
import { reactive, onMounted } from 'vue';
import { httpGet } from '../config/interFace'

onMounted (async function () {
  const res = await httpGet('/view/project',{})
  formState.dataList = res
})

const toPage = (str: any, id: any) => {
  router.push({name: str, query: {id: id}})
}

interface FormState {
  techName: string;
  projectName: string;
  dataList: any
}

const formState = reactive<FormState>({
  techName: '',
  projectName: '',
  dataList: []
});

const allSeletct = async () => {
  const res = await httpGet('/view/projectCom',{
    projectName: formState.projectName,
  })
  formState.dataList = res
};

const onFinish = async () => {
  const res = await httpGet('/view/project',{
    projectName: formState.projectName
  })
  formState.dataList = res
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>