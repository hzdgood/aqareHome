<template>
  <div class="cardDiv">
    <a-card :title="data.techName + '&nbsp;&nbsp;' + data.type + '&nbsp;&nbsp;' + data.status" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="workEdit(data.workId)">详情</a-button>
        <a-button :style="style" @click="uploadImg(data.workId)">往期图片</a-button>
      </div>
      <table class="cardTale">
        <tr>
          <td>项目姓名</td>
          <td>{{ data.projectName }}</td>
          <td>工单类型</td>
          <td>{{ data.type }}</td>
        </tr>
        <tr>
          <td>项目电话</td>
          <td>{{ data.telephone }}</td>
          <td>工单状态</td>
          <td>{{ data.status }}</td>
        </tr>
        <tr>
          <td>装修进度</td>
          <td>{{ data.schedule }}</td>
        </tr>
        <tr>
          <td>上门日期</td>
          <td colspan="3">{{ dateFilter(data.dateOfVisit,'yyyy-mm-dd hh:mm:ss') }}</td>
        </tr>
        <tr v-if="data.signTime !== null">
          <td>签到时间</td>
          <td colspan="3">{{ dateFilter(data.signTime,'yyyy-mm-dd hh:mm:ss') }}</td>
        </tr>
        <tr v-if="data.departureTime !== null">
          <td>离场时间</td>
          <td colspan="3">{{ dateFilter(data.departureTime,'yyyy-mm-dd hh:mm:ss') }}</td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button type="primary" 
          v-show="data.signTime === null"
          @click="sign(data.workId)">签到</a-button>

        <a-button type="primary"
          v-show="data.departureTime === null && data.signTime !== null"
          @click="depart(data.workId)">离开</a-button>

        <a-button type="primary" v-show="data.signTime !== null"
          @click="WriterInfo(data.workId)">核销</a-button>

        <a-button type="primary" v-show="data.status !== '已完成' 
            && data.signTime !== null 
            && data.departureTime !== null "
          @click="CompleteInfo()">完成</a-button>
          
      </div>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk(data.workId)">
      <p>工单完成后，锁定核销数量！</p>
    </a-modal>
  </div>
</template>
<script setup lang="ts">
// 工单卡片
import { dateFilter } from '../../util/time'
import { httpGet } from '../../config/interFace'
import { ref } from 'vue';

const techIds = localStorage.getItem('techId')
const emit = defineEmits(['toPage','pageReset'])
const open = ref<boolean>(false);

defineProps({
  data: {
    type: Object,
    default: null
  }
})

const sign = async (id: number) => {
  await httpGet('/workSheet/sign',{
    id: id,
    updateName: techIds
  })
  emit('pageReset')
}

const depart = async (id: any) => {
  await httpGet('/workSheet/sign',{
    id: id,
    updateName: techIds
  })
  emit('pageReset')
}

const CompleteInfo = async () => {
  showModal();
}

const WriterInfo = (id: any) => {
  emit('toPage','subWriter', id)
}

const workEdit = (id: any) => {
  emit('toPage','workEdit', id)
}

const uploadImg = (id: any) => {
  emit('toPage','uploadImg', id)
}

const showModal = () => {
  open.value = true;
};

const handleOk = async (id: number) => {
  open.value = false;
  await httpGet('/workSheet/complete',{
    id: id,
    updateName: techIds
  })
  emit('pageReset')
};

const style = {
  backgroundColor: '#0099DD',color: '#fff'
}
</script>
<style scoped>

</style>