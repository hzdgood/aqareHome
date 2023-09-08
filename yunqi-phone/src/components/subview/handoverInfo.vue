<template>
  <div style="background: #ececec; padding: 10px 10px 0px 10px;">
    <a-card title="" :bordered="false">
      <table class="projectTable">
        <tr>
          <td width="70px">项目姓名</td>
          <td>缪丹霞</td>
          <td width="70px">负责人</td>
          <td>娄德</td>
        </tr>
        <tr>
          <td>目前任务</td>
          <td></td>
          <td>装修进度</td>
          <td></td>
        </tr>
        <tr>
          <td>类型</td>
          <td></td>
          <td>房型</td>
          <td></td>
        </tr>
        <tr>
          <td>房屋情况</td>
          <td></td>
          <td>是否完结</td>
          <td></td>
        </tr>
        <tr>
          <td>项目地址</td>
          <td colspan="3">瑞虹路505弄5号1902</td>
        </tr>
        <tr>
          <td>交接信息</td>
          <td colspan="3"></td>
        </tr>
      </table>
    </a-card>
    <div class="buttonPos">
      <a-button>返回</a-button>
    </div>
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped lang="less">
.projectTable{
  width: 100%;
}
.projectTable tr td{
  padding: 3px;
  text-align: left;
}
</style>