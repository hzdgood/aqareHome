<template>
  <a-form
    :model="formState"
    name="normal_login"
    class="login-form"
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  >
    <div>
    用 户 名: <a-input v-model:value="formState.username" style="width: 200px;">
        <template #prefix>
          <UserOutlined class="site-form-item-icon" />
        </template>
      </a-input>
    </div>
    <div>
    新 密 码: <a-input-password v-model:value="formState.password" style="width: 200px;">
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </div>
    <div>
    确认密码: <a-input-password v-model:value="formState.surePassword" style="width: 200px;">
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </div>
    <div>
      <a-button :disabled="disabled" type="primary" html-type="submit" class="login-form-button">
        确认
      </a-button>
      <a-button type="primary" class="login-form-button" @click="toLogin()">
        返回
      </a-button>
    </div>
  </a-form>
</template>
<script lang="ts" setup>
  import { reactive, computed } from 'vue';
  import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
  import router from '@/router';
  interface FormState {
    username: string;
    password: string;
    surePassword: string
  }
  const formState = reactive<FormState>({
    username: '',
    password: '',
    surePassword: ''
  });
  const onFinish = (values: any) => {
    console.log('Success:', values);
    router.push({ path: "/"})
  };
  const toLogin = () => {
    router.push({ name: "login"})
  }
  const onFinishFailed = (errorInfo: any) => {
    console.log('Failed:', errorInfo);
  };
  const disabled = computed(() => {
    return !(formState.username && formState.password);
  });
</script>
<style lang="less" scoped>
div{
  margin: 15px;
}
button {
  margin: 5px;
}
</style>
  