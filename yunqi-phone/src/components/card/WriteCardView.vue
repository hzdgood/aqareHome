<template>
  <div>
    <div class="cardDiv">
      <a-card title="" :bordered="false">
        <table class="cardTale">
          <tr v-show="data.productName !== null">
            <td>产品名称</td>
            <td colspan="3">{{ data.productName }}</td>
          </tr>
          <tr>
            <td>工单</td>
            <td>{{ data.sheetName }}</td>
            <td>类型</td>
            <td>{{ data.type }}</td>
          </tr>
          <tr>
            <td>核销数量</td>
            <td>{{ data.number }}</td>
            <td>贡献度</td>
            <td>{{ data.contribution }}</td>
          </tr>
          <tr>
            <td>技术姓名</td>
            <td>{{ data.techName }}</td>
            <td>结算日期</td>
            <td>{{ dateFilter(data.writerTime,'yyyy-mm-dd') }}</td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" @click="error(data.writerId)">报错</a-button>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
// 核销卡片
import { dateFilter } from '../../util/time'

const emit = defineEmits(['toPage','pageReset'])

defineProps({
  data: {
    type: Object,
    default: null
  }
})

const error = async (writerId: any) => { //核销ID
  emit('toPage','writerError', { id: writerId })
}

</script>

<style scoped lang="less">

</style>