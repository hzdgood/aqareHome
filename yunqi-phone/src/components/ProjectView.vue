<template>
  <div>
    <div>
      <a-form
        :model="formState"
        name="projectView"
        class="projectView"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <span>技术人员:</span><a-input style="width: 16%;" v-model:value="formState.techName"></a-input>
        <span>项目名称:</span><a-input style="width: 20%;" v-model:value="formState.projectName"></a-input>
        <span>
          <a-button type="primary" html-type="submit">查询</a-button>
        </span>
      </a-form>
    </div>
    <ProjectCardView></ProjectCardView>
  </div>
</template>

<script setup lang="ts">
import ProjectCardView from './card/ProjectCardView.vue';
import { reactive } from 'vue';
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
span {
  padding: 5px;
  font-size: 1em;
}
.projectView{
  margin-top: 10px;
  margin-bottom: 5px;
}
</style>