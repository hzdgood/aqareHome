<template>
  <div class="cardDiv">
    <a-card :title="data.projectName + '&nbsp;&nbsp;' + data.type + '-' + data.techId" :bordered="false">
      <table class="cardTale" >  
        <tr>
          <td width="70px">上门技术</td>
          <td>{{ data.techNames }}</td>
          <td width="70px">工单类型</td>
          <td>{{ data.type }}</td>
        </tr>
        <tr>
          <td>项目电话</td>
          <td>{{ data.telephone }}</td>
          <td>项目负责</td>
          <td>{{ data.techName }}</td>
        </tr>
        <tr>
          <td>装修进度</td>
          <td>{{ data.schedule }}</td>
          <td>工单状态</td>
          <td>{{ data.status }}</td>
        </tr>
        <tr>
          <td>客户地址</td>
          <td colspan="3">{{ data.address }}</td>
        </tr>
        <tr>
          <td>预约时间</td>
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
    </a-card>
  </div>
</template>
<script setup lang="ts">
// 工单卡片
import { dateFilter } from '../../util/time'
defineProps({
  data: {
    type: Object,
    default: null
  }
})
</script>
<style scoped>

</style>