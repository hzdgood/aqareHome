<template>
  <div>
    <div>
      <table>
        <tr>产品名称</tr>
        <tr>全款数量</tr>
        <tr>方案数量</tr>
        <tr>销售单价</tr>
        <tr>单品折扣</tr>
        <tr>服务费</tr>
        <tr>未发</tr>
        <tr>未装</tr>
        <tr>未调</tr>
      </table>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {
  async mounted () {
    const ticket = localStorage.getItem('ticket')
    console.log(ticket)
  }
}
</script>
