<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>缪丹霞</td>
          <td width="70px">负责人</td>
          <td>娄德</td>
        </tr>
        <tr>
          <td>工单类型</td>
          <td>安装</td>
          <td>工单状态</td>
          <td>待上门</td>
        </tr>
        <tr>
          <td width="70px">项目地址</td>
          <td colspan="3">瑞虹路505弄5号1902</td>
        </tr>
        <tr>
          <td>上门技术</td>
          <td colspan="3"><a-input style="width: 90%;"></a-input></td>
        </tr>
        <tr>
          <td>上门时间</td>
          <td colspan="3"><a-input style="width: 90%;"></a-input></td>
        </tr>
        <tr>
          <td>无效说明</td>
          <td colspan="3"><a-input style="width: 90%;"></a-input></td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button type="primary">提交</a-button>
        <a-button type="primary" @click="resPage()">返回</a-button>
        <a-button type="primary">无效</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
/// 工单详情
import router from '@/router';

const resPage = () => {
  router.push({name: 'workSheet'})
}
</script>

<style lang="less" scoped>

</style>