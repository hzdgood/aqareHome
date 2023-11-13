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
            <td>工单名称</td>
            <td>{{ data.projectName + "-" + data.sheetName }}</td>
            <td>工单类型</td>
            <td>{{ data.type }}</td>
          </tr>
          <tr>
            <td>技术姓名</td>
            <td>{{ data.techName }}</td>
            <td>结算日期</td>
            <td>{{ dateFilter(data.writerTime,'yyyy-mm-dd') }}</td>
          </tr>
          <tr>
            <td>核销数量</td>
            <td>{{ data.number }}</td>
            <td>贡献度</td>
            <td>{{ data.contribution }}</td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button :disabled="true" type="primary">--报错--</a-button>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
// 核销卡片
import { dateFilter } from '../../util/time'
defineProps({
  data: {
    type: Object,
    default: null
  }
})
</script>

<style scoped lang="less">

</style>