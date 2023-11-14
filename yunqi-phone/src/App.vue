<template>
  <a-config-provider :getPopupContainer="getPopupContainer" :locale="zhCN">
    <RouterView></RouterView>
  </a-config-provider>
  <div class="filings">
    <a href="https://beian.miit.gov.cn/">沪ICP备2022007443号</a>
  </div>
</template>

<script setup lang="ts">
import 'dayjs/locale/zh-cn';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
const getPopupContainer = (el:any, dialogContext:any) => {
  if (dialogContext) {
    return dialogContext.getDialogWrap();
  } else {
    return document.body;
  }
}
localStorage.setItem("version","1.1")
</script>

<style>
.ant-card .ant-card-head {
  padding: 0 10px !important;
}
.ant-card .ant-card-body {
  padding: 10px !important;
}
.filings {
  font-size: 14px;
  position: absolute;
  bottom: 5px;
  width: 170px;
  padding-left: 10px;
  background-color: beige;
}
</style>
