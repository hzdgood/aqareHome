<template>
  <div>
    <table>
      <tr>
        <td>日期</td>
        <td>技术</td>
        <td>类型</td>
        <td>状态</td>
        <td>完成情况</td>
        <td>下次安排</td>
      </tr>
    </table>
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
