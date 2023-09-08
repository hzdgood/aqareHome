<template>
  <div style="background: #ececec; padding: 10px 10px 0px 10px;">
    <a-card title="" :bordered="false">
      <table class="schemeTable">
        <tr>
          <td width="70px">项目姓名</td>
          <td>缪丹霞</td>
          <td width="70px">负责人</td>
          <td>娄德</td>
        </tr>
        <tr>
          <td width="70px">项目地址</td>
          <td colspan="3">瑞虹路505弄5号1902</td>
        </tr>
        <tr>
          <td>上门技术</td>
          <td></td>
          <td>上门时间</td>
          <td></td>
        </tr>
        <tr>
          <td>工单类型</td>
          <td></td>
          <td>工单状态</td>
          <td></td>
        </tr>
      </table>
    </a-card>
    <div class="buttonPos">
      <a-button type="primary">提交</a-button>
      <a-button type="primary">取消</a-button>
      <a-button type="primary">有效性</a-button>
    </div>
  </div>
</template>

<script setup lang="ts">

</script>

<style lang="less" scoped>

</style>