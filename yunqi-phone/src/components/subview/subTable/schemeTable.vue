<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <table class="schemeTable">
        <tr>
          <td width="70px">产品名称</td>
          <td colspan="3">高精度人体传感器</td>
        </tr>
        <tr>
          <td>数量</td>
          <td>10</td>
          <td>未装</td>
          <td>10</td>
        </tr>
        <tr>
          <td>未试</td>
          <td>5</td>
          <td>未发</td>
          <td>5</td>
        </tr>
      </table>
    </a-card>
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped lang="less">
.schemeTable{
  width: 100%;
}
.schemeTable tr td{
  padding: 3px;
  text-align: left;
}
</style>