<template>
  <div class="cardDiv">
    <a-card title="开合帘" :bordered="false" v-show="type === '开合帘'">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td></td>
          <td width="70px">区域</td>
          <td></td>
        </tr>
        <tr>
          <td>电机型号</td>
          <td></td>
          <td>轨道类型</td>
          <td></td>
        </tr>
        <tr>
          <td>安装方式</td>
          <td></td>
          <td>数量</td>
          <td></td>
        </tr>
        <tr>
          <td>开合方式</td>
          <td></td>
          <td>电源位置</td>
          <td></td>
        </tr>
        <tr>
          <td>窗帘盒宽</td>
          <td></td>
          <td>安装面材质</td>
          <td></td>
        </tr>
        <tr>
          <td>预留位置</td>
          <td></td>
          <td>扣减说明</td>
          <td></td>
        </tr>
      </table>
    </a-card>

    <a-card title="卷帘" :bordered="false" v-show="type === '卷帘'">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td></td>
          <td width="70px">区域</td>
          <td></td>
        </tr>
        <tr>
          <td>卷帘方案</td>
          <td></td>
          <td>长度</td>
          <td></td>
        </tr>
        <tr>
          <td>高度</td>
          <td></td>
          <td>安装面宽度</td>
          <td></td>
        </tr>
        <tr>
          <td>安装方式</td>
          <td></td>
          <td>扣减说明</td>
          <td></td>
        </tr>
        <tr>
          <td>数量</td>
          <td></td>
          <td>安装面材质</td>
          <td></td>
        </tr>
        <tr>
          <td>电源位置</td>
          <td></td>
          <td>需要罩壳</td>
          <td></td>
        </tr>
      </table>
    </a-card>
  </div>
</template>

<script setup lang="ts">
// 测量 开合 卷帘
defineProps({
  data: {
    type: Object,
    default: null
  },
  type: {
    type: String,
    default: null
  }
})

</script>