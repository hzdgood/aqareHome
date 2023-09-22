<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <table class="cardTale">
        <tr>
          <td>技术姓名</td>
          <td></td>
          <td>贡献度</td>
          <td></td>
        </tr>
        <tr>
          <td>产品名称</td>
          <td>高精度人体传感器</td>
          <td>核销数量</td>
          <td>10</td>
        </tr>
        <tr>
          <td>贡献度</td>
          <td>100</td>
          <td>结算日期</td>
          <td>2023-09-10</td>
        </tr>
      </table>
    </a-card>
  </div>
</template>

<script setup lang="ts">
// 提成卡片

defineProps({
  data: {
    type: Object,
    default: null
  }
})

</script>