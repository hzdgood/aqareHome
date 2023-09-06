<template>
    <a-form
      :model="formState"
      name="normal_login"
      class="login-form"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <a-form-item
        label="Username"
        name="username"
        :rules="[{ required: true, message: 'Please input your username!' }]"
      >
        <a-input v-model:value="formState.username"></a-input>
      </a-form-item>
    </a-form>


    <!-- <WorkCardView></WorkCardView>
    <WorkCardView></WorkCardView>
    <WorkCardView></WorkCardView>
  </div> -->
</template>

<script setup lang="ts">

const labelCol = { style: { width: '100px' } };
const wrapperCol = { span: 20 };

import WorkCardView from './card/WorkCardView.vue';
import { reactive, computed } from 'vue';
interface FormState {
  username: string;
  password: string;
}
const formState = reactive<FormState>({
  username: '',
  password: '',
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
const disabled = computed(() => {
  return !(formState.username && formState.password);
});
</script>