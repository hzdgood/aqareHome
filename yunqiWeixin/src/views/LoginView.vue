<template>
  <div id="login">userId: {{ formState.userId }}</div>
</template>
<script lang="ts" setup>
import * as ww from '@wecom/jssdk'
import { onMounted, reactive } from 'vue';
const userId: any = [];

interface FormState {
  userId: any[]
}

const formState = reactive<FormState>({
  userId: []
})
</script>
