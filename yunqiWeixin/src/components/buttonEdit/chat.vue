<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">客户动态</div>
      <div class="buttonPos">
        <a-button type="primary">提交</a-button>
        <a-button type="primary" @click="closePage()">关闭</a-button>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
const emit = defineEmits(['close'])

const closePage = () => {
  emit('close')
}

</script>