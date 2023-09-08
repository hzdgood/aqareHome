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
        <span>&nbsp;&nbsp;负责人: &nbsp;</span><a-input style="width: 16%;" v-model:value="formState.techName"></a-input>
        <span>&nbsp;&nbsp;项目名称: &nbsp;</span><a-input style="width: 20%;" v-model:value="formState.projectName"></a-input>
        <span>
          &nbsp;<a-button type="primary" html-type="submit">查询</a-button>
        </span>
      </a-form>
    </div>
    <ProjectCardView @toPage="toPage"></ProjectCardView>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import ProjectCardView from './card/ProjectCardView.vue';
import { reactive } from 'vue';
const toPage = (str: any) => {
  router.push({name: str})
}
interface FormState {
  techName: string;
  projectName: string;
}
const formState = reactive<FormState>({
  techName: '',
  projectName: '',
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