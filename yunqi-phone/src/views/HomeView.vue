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
      <div><RouterLink to="/commission">我的提成</RouterLink></div>
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
  if(typeof(username) === 'undefined' || typeof(password) === 'undefined') {
    router.push({ name: "login"})
  } else if(username === 'null' || password === 'null') {
    router.push({ name: "login"})
  } else {
    router.push({ name: "workSheet"})
  }
});
</script>
<style scoped lang="less">
.headTop{
  width: 100%;
  background-color: #000dff;
  font-size: 20px;
  color: rgb(255, 255, 255) !important;
  color-scheme: light;
  text-align: center;
  padding: 10px;
}
.wrapper {
  width: 100%;
  position: fixed;
  bottom: 0;
  left: 0;
  z-index: 10;
  display: inline-block;
  background-color: #1a3b5a;
  border: 1px solid #cecece;
}
.wrapper div{
  padding: 5px 10px 5px 10px;
  display: inline-block;
  line-height: 30px;
  border-right: 0.5px solid #cecece;
  font-size: 14px;
}
.wrapper div:last-child {
  border-right: 0px solid #cecece;
}

.wrapper div a{
  color: rgba(255, 255, 255, 0.65);
  text-decoration:none;
}
.selected a {
  color: rgb(255, 255, 255) !important;
  color-scheme: light;
}
.selected {
  background-color: rgb(22, 119, 255);
}
.bodyContent{
  background-color: rgb(236, 236, 236);
  position: absolute;
  width: 100%;
  bottom: 40px;
  top: 40px;
  overflow: auto;
  padding-bottom: 10px;
}
</style>
