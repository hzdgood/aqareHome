<template>
  <div class="pageConter">
    <div class="headTop">
      技术服务系统
    </div>
    <div class="wrapper">
      <div class="selected"><RouterLink to="/workSheet">我的工单</RouterLink></div>
      <div><RouterLink to="/project">我的项目</RouterLink></div>
      <div><RouterLink to="/write">核销记录</RouterLink></div>
      <div><RouterLink to="/measure">测量单</RouterLink></div>
      <!-- <div><RouterLink to="/commission">提成</RouterLink></div> -->
    </div>
    <div class="bodyContent">
      <RouterView></RouterView>
    </div>
  </div>
</template>
<script setup lang="ts">
import { onMounted } from "vue";
import { useRouter } from "vue-router";

onMounted (function () {
  const router = useRouter()
  const username = localStorage.getItem("username")
  const password = localStorage.getItem("password")
  const techId = localStorage.getItem('techId')
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
</script>

<style src="../css/home.css" scoped></style>
