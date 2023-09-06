<template>
  <div>
    <div class="wrapper">
      <nav>
        <RouterLink to="/workSheet">我的工单</RouterLink>
        <RouterLink to="/project">我的项目</RouterLink>
        <RouterLink to="/measure">测量单</RouterLink>
        <RouterLink to="/write">核销</RouterLink>
        <RouterLink to="/commission">提成</RouterLink>
      </nav>
    </div>
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { useRouter } from "vue-router";

onMounted (function () {
  const router = useRouter()
  const username = localStorage.getItem("username")
  const password = localStorage.getItem("password")
  if(typeof(username) === 'undefined' || typeof(password) === 'undefined') {
    router.push({ name: "login"})
  } else if(username === 'null' || password === 'null') {
    router.push({ name: "login"})
  } else {
    router.push({ name: "workSheet"})
  }
});
</script>
