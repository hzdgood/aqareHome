<template>
  <div class="pageConter">
    <div class="headTop">
      技术服务系统
    </div>

    <div class="logout">
      {{ loginName }} <span class="logout-span" @click="logout">登出</span>
    </div>

    <div class="wrapper">
      <div :class="formState.select1" @click="changeSelect(1)"><RouterLink to="/workSheet">我的工单</RouterLink></div>
      <div :class="formState.select2" @click="changeSelect(2)"><RouterLink to="/project">我的项目</RouterLink></div>
      <div :class="formState.select3" @click="changeSelect(3)"><RouterLink to="/write">核销记录</RouterLink></div>
      <div :class="formState.select4" @click="changeSelect(4)"><RouterLink to="/measure">测量单</RouterLink></div>
      <!-- <div><RouterLink to="/commission">提成</RouterLink></div> -->
    </div>
    <div class="bodyContent">
      <RouterView></RouterView>
    </div>
  </div>
</template>
<script setup lang="ts">
import { reactive, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter()
const techId = localStorage.getItem('techId')
const loginName = localStorage.getItem('loginName')

const changeSelect = (id: number) => {
  if(id === 1 ) {
    formState.select1 = 'selected'
    formState.select2 = ''
    formState.select3 = ''
    formState.select4 = ''
  } else if(id === 2 ) {
    formState.select1 = ''
    formState.select2 = 'selected'
    formState.select3 = ''
    formState.select4 = ''
  } else if(id === 3 ) {
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = 'selected'
    formState.select4 = ''
  } else if(id === 4 ) {
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = ''
    formState.select4 = 'selected'
  }
}

interface FormState {
  select1: string,
  select2: string,
  select3: string,
  select4: string,
}

const formState = reactive<FormState>({
  select1: 'selected',
  select2: '',
  select3: '',
  select4: '',
});

onMounted (function () {  
  const username = localStorage.getItem("username")
  const password = localStorage.getItem("password")
  if(typeof(techId) === 'undefined' ){
    router.push({ name: "login"})
  } else {
    if(username === null || password === null ) {
      router.push({ name: "login"})
    } else {
      router.push({ name: "workSheet"})
    }
  }
});

const logout = () => {
  localStorage.clear()
  router.push({ name: "login"})
}

</script>

<style src="../css/home.css" scoped>

</style>
