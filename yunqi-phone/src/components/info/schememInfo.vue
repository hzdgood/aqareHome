<template>
  <div>
    <schemeTable :data="formState.dataList" @toPage="resPage"></schemeTable>
  </div>
</template>

<script setup lang="ts">
// 方案
import schemeTable from './subTable/schemeTable.vue'
import router from '@/router';
import { useRoute } from "vue-router";
import { reactive, onMounted } from 'vue';
import { httpGet } from '../../config/interFace'

onMounted (async function () {
  const route = useRoute()  
  const res = await httpGet('/view/scheme',{
    projectId: route.query.id
  })
  if(res.length === 0) {
    router.push({name: 'project'})
  }
  formState.dataList = res
})

interface FormState {
  dataList: any
}

const formState = reactive<FormState>({
  dataList: []
});

const resPage = () => {
  router.push({name: 'project'})
}
</script>

<style lang="less" scoped>

</style>