<template>
  <div class="appbody">
    <div style="background-color: #f2f3fb;">
      <div class="appdiv">
        <user></user>
        <!-- <div class="lineType"></div>
        <customer></customer> -->
      </div>
      <div class="appdiv">
        <stage></stage>
      </div>
      <div class="appdiv">
        <sidebar></sidebar>
        <a-config-provider :getPopupContainer="getPopupContainer" :locale="zhCN">
          <RouterView></RouterView>
        </a-config-provider>
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
import stage from './components/stage/stage.vue'
import 'dayjs/locale/zh-cn';
import zhCN from 'ant-design-vue/es/locale/zh_CN';

onMounted(async function() {
  
})

const getPopupContainer = (el:any, dialogContext:any) => {
  if (dialogContext) {
    return dialogContext.getDialogWrap();
  } else {
    return document.body;
  }
}
</script>