<template>
  <div>
    <div class="rightInfo">工单信息</div>
    <table class="detailTable">
      <tr>
        <td>客户名称</td>
        <td>马骏杰</td>
      </tr>
      <tr>
        <td>工单状态</td>
        <td>忙碌</td>
      </tr>
      <tr>
        <td>额定工时</td>
        <td>8</td>
      </tr>
      <tr>
        <td>开始时间</td>
        <td>10:00</td>
      </tr>
      <tr>
        <td>结束时间</td>
        <td>18:00</td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {
  sheetList: any[] = []
}
</script>
