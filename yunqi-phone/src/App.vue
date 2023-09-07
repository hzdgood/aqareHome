<template>
  <a-config-provider :getPopupContainer="getPopupContainer" :locale="zhCN">
    <RouterView></RouterView>
  </a-config-provider>
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
</script>

<style>
.ant-card .ant-card-head {
  padding: 0 10px !important;
}
.ant-card .ant-card-body {
  padding: 10px !important;
}
</style>
