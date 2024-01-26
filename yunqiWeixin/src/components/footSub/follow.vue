<template>
  <div>
    <div class="buttonPos">
      <a-button type="primary">提交</a-button>
      <a-button type="primary" @click="closePage()">关闭</a-button>
    </div>
  </div>
</template>
<script lang="ts" setup>
const emit = defineEmits(['close'])

const closePage = () => {
  emit('close')
}
</script>