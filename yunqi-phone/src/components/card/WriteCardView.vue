<template>
  <div>
    <div class="cardDiv">
      <a-card title="" :bordered="false">
        <table class="cardTale">
          <tr>
            <td>工单</td>
            <td>{{ data.projectName }}</td>
            <td>类型</td>
            <td>{{ data.type }}</td>
          </tr>
          <tr>
            <td>核销数量</td>
            <td>{{ data.number }}</td>
            <td>贡献度</td>
            <td></td>
          </tr>
          <tr>
            <td>结算日期</td>
            <td colspan="3">   
              {{ dateFilter(data.writerTime,'yyyy-mm-dd hh:mm:ss') }}
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary">报错</a-button>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { dateFilter } from '../../util/time'
// 核销卡片
defineProps({
  data: {
    type: Object,
    default: null
  }
})

</script>

<style scoped lang="less">

</style>