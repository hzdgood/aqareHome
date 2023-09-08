<template>
  <div>
    <div style="background: #ececec; padding: 10px 10px 0px 10px;">
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
button{
  margin: 3px;
}
.buttonPos {
  position: absolute;
  top: 10px;
  right: 10px
}
</style>