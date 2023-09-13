<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <table class="schemeTable">
        <tr>
          <td>产品名称</td>
          <td>类型</td>
          <td>数量</td>
          <td>未装</td>
          <td>未试</td>
          <td>未发</td>
        </tr>
        <tr>
          <td>高精度人体传感器</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
        </tr>
        <tr>
          <td>高精度人体传感器</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
        </tr>
        <tr>
          <td>高精度人体传感器</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
          <td>10</td>
        </tr>
      </table>
    </a-card>
  </div>
</template>

<script setup lang="ts">
  // 项目方案
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