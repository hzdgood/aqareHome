<template>
  <div>
    <div>
      <a-card title="" :bordered="false" >
        <div class="buttonPos">
          <a-button type="primary" @click="check1()">开合帘</a-button>
          <a-button type="primary" @click="check2()">卷帘</a-button>
        </div>
        <div v-show="formState.formDiv1">
          <openTab :id="route.query" :data="formState.datalist" @resPage="resPage"></openTab>
        </div>
        <div v-show="formState.formDiv2">
          <rollerTab :id="route.query" :data="formState.datalist" @resPage="resPage"></rollerTab>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
//  新增测量
import router from '@/router';
import { reactive, onMounted } from 'vue';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import openTab from './subTab/openTab.vue'
import rollerTab from './subTab/rollerTab.vue';
const route = useRoute()

onMounted (async function () {
  const res = await httpGet('/project/selectId',{
    id: route.query.id
  })
  formState.datalist = res
  console.log(res);
});

const formState = reactive<FormState>({
  datalist: [],
  formDiv1: true,
  formDiv2: false
})

interface FormState {
  datalist: object[]
  formDiv1: boolean
  formDiv2: boolean
}

const resPage = () => {
  router.push({name: 'project'})
}

const check1 = () => {
  formState.formDiv1 = true
  formState.formDiv2 = false
}

const check2 = () => {
  formState.formDiv1 = false
  formState.formDiv2 = true
}
</script>