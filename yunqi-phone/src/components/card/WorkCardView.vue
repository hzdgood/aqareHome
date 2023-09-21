<template>
  <div class="cardDiv">
    <a-card :title="data.techName + '&nbsp;&nbsp;' + data.type + '&nbsp;&nbsp;' + data.status" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="workEdit(data.workId)">详情</a-button>
        <a-button :style="style" @click="uploadImg(data.workId)">图片</a-button>
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
          <td>上门日期</td>
          <td>{{ dateFilter(data.dateOfVisit,'yyyy-mm-dd') }}</td>
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
        <a-button type="primary"
          @click="WriterInfo(data.workId)">核销</a-button>
        <a-button type="primary" @click="measureInfo(data.workId)">测量</a-button>
      </div>
    </a-card>
  </div>
</template>
<script setup lang="ts">
// 工单卡片
import { dateFilter } from '../../util/time'
import { httpGet } from '../../config/interFace'
const techIds = localStorage.getItem('techId')

defineProps({
  data: {
    type: Object,
    default: null
  }
})

const sign = async (id: number) => {
  const res = await httpGet('/workSheet/sign',{
    id: id,
    updateName: techIds
  })
}

const depart = async (id: number) => {
  const res = await httpGet('/workSheet/depart',{
    id: id,
    updateName: techIds
  })
}

const emit = defineEmits(['toPage'])
const WriterInfo = (id: any) => {
  emit('toPage','subWriter', id)
}

const measureInfo = (id: any) => {
  emit('toPage','subMeasure',id)
}

const workEdit = (id: any) => {
  emit('toPage','workEdit', id)
}

const uploadImg = (id: any) => {
  emit('toPage','uploadImg', id)
}

const style = {
  backgroundColor: '#0099DD',color: '#fff'
}
</script>
<style scoped>

</style>