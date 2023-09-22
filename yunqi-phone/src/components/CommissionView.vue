<template>
  <div>
    <div v-for="item in formState.dataList" :key="item">
      <CommissionCardView :data="item"></CommissionCardView>
    </div>
  </div>
</template>

<script setup lang="ts">
// 提成
import CommissionCardView from './card/CommissionCardView.vue'
import { reactive, onMounted } from 'vue';
import { httpGet } from '../config/interFace'

onMounted (async function () {
  const res = await httpGet('/view/commission',{})
  formState.dataList = res
})

interface FormState {
  dataList: any
}

const formState = reactive<FormState>({
  dataList: []
});

</script>