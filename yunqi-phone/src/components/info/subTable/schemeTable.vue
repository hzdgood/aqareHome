<template>
  <div class="cardDiv">
    <a-card :title="formState.title" :bordered="false">
      <table class="schemeTable">
        <thead>
          <tr>
            <td>产品名称</td>
            <td>数量</td>
            <td>未装</td>
            <td>未试</td>
          </tr>
        </thead>
        <tr v-for="item in data" :key="item.schemeId">
          <td>{{ item.productName }}</td>
          <td>{{ item.number }}</td>
          <td>{{ item.notInstalled }}</td>
          <td>{{ item.unregulated }}</td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button type="primary" @click="resPage()">返回</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
// 项目方案
import { reactive, watch } from 'vue';

const emit = defineEmits(['toPage'])

const props = defineProps({
  data: {
    type: Object,
    default: null
  }
})

watch(props, (newValue, oldValue) => {
  if(newValue.data.length > 0) {
    const str = newValue.data[0];
    formState.title = str.projectName + " " + str.salesName + " " + str.techName; 
  }
})

interface FormState {
  title: String
}

const formState = reactive<FormState>({
  title: ''
});

const resPage = () => {
  emit('toPage')
}
</script>

<style scoped lang="less">
.schemeTable{
  width: 100%;
}
.schemeTable tr td{
  padding: 3px;
  text-align: left;
  border: 0.5px solid #cecece;
}

.schemeTable thead td {
  text-align: center;
  font-weight: 550;
  background-color: #f7f8fa;
}
</style>