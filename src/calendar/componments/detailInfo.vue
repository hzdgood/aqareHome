<template>
  <div>
    <div>工单信息</div>
    <table>
      <tr>
        <td>客户名称</td>
        <td></td>
      </tr>
      <tr>
        <td>工单状态</td>
        <td></td>
      </tr>
      <tr>
        <td>额定工时</td>
        <td></td>
      </tr>
      <tr>
        <td>开始时间</td>
        <td></td>
      </tr>
      <tr>
        <td>结束时间</td>
        <td></td>
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
