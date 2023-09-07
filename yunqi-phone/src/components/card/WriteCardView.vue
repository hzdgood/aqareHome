<template>
  <div>
    <div style="background: #ececec; padding: 10px;">
      <a-card title="" :bordered="false">
        <table class="cardTale">
          <tr>
            <td>工单</td>
            <td>缪丹霞</td>
            <td>类型</td>
            <td>安装</td>
          </tr>
          <tr>
            <td>产品名称</td>
            <td>iphone 12PS</td>
            <td>核销数量</td>
            <td>娄德</td>
          </tr>
          <tr>
            <td>贡献度</td>
            <td>100</td>
            <td>结算日期</td>
            <td>2023-09-10</td>
          </tr>
        </table>
        <div class="buttons">
          <a-button type="primary">详情</a-button>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped lang="less">
.cardTale{
  width: 100%;
}
.cardTale tr td{
  padding: 3px;
  text-align: left;
}
.buttons button{
  margin: 3px;
}
</style>