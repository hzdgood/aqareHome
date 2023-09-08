<template>
  <a-form
    :model="formState"
    name="normal_login"
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  >
    <div>
      用户名：<a-input v-model:value="formState.username" style="width: 200px;">
        <template #prefix>
          <UserOutlined class="site-form-item-icon" />
        </template>
      </a-input>
    </div>
    <div>
      密 码：<a-input-password v-model:value="formState.password" style="width: 200px; margin-left: 10px;">
        <template #prefix>
          <LockOutlined class="site-form-item-icon" />
        </template>
      </a-input-password>
    </div>
    <div>
      <a-form-item name="remember" no-style>
        <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
      </a-form-item>
      <a class="login-form-forgot" @click="forgotWord()">Forgot password</a>
    </div>
    <div>
      <a-button :disabled="disabled" type="primary" html-type="submit" class="login-form-button">
        登入
      </a-button>
      <a-button type="primary">
        注册
      </a-button>
    </div>
  </a-form>
</template>
<script lang="ts" setup>
import { reactive, computed } from 'vue';
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue';
import { useRouter } from "vue-router";
import { onMounted } from "vue";
const router = useRouter()

onMounted (function () {
  const username = localStorage.getItem("username")
  const password = localStorage.getItem("password")
  if(typeof(username) !== 'undefined' && typeof(password) !== 'undefined' ) {
    router.push({ name: "page"})
  }
});

interface FormState {
  username: string;
  password: string;
  remember: boolean;
}
const formState = reactive<FormState>({
  username: '',
  password: '',
  remember: true,
});

const forgotWord = () => {
  router.push({ name: "forgot"})
}

localStorage.clear();
const onFinish = (values: any) => {
  router.push({ name: "page"})
  localStorage.setItem("username",values.username)
  localStorage.setItem("password",values.password)
};
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
  margin: 0px 10px;
}
</style>
