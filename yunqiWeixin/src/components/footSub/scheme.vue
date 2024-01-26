<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">方案系统</div>
      <div class="buttonPos">
        <a-button type="primary">提交</a-button>
        <a-button type="primary" @click="closePage()">关闭</a-button>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
const emit = defineEmits(['close'])

const closePage = () => {
  emit('close')
}
</script>