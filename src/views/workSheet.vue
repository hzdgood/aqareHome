<template>
  <div>
    <div>
      <span>日期</span>
      <span>类型</span>
      <span>状态</span>
    </div>
    <div>
      <span>技术</span>
    </div>
    <div>
      <span>完成情况</span>
      <input type="text"/>
    </div>
    <div>
      <span>下次安排</span>
      <input type="text"/>
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
