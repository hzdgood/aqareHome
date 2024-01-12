<template>
  <div class="appbody">
    <div style="background-color: #f2f3fb;">
      <div class="appdiv">
        <user></user>
        <div class="lineType"></div>
        <customer></customer>
      </div>
      <div class="appdiv">
        <stage></stage>
      </div>
      <div class="appdiv">
        <sidebar></sidebar>
        <router-view />
        <foot></foot>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
import user from './components/user/user.vue'
import sidebar from './components/sidebar/sidebar.vue'
import foot from './components/foot/foot.vue'
import customer from './components/customer/customer.vue'
import stage from './components/stage/stage.vue'

onMounted(async function() {
  
})
</script>


<!-- <template>
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
.filings {
  font-size: 14px;
  position: absolute;
  bottom: 5px;
  width: 170px;
  padding-left: 10px;
  background-color: beige;
}
</style> -->
