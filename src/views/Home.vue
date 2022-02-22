<template>
  <div>
    <div class="lineDiv">
      <span>名称</span>
      <span><input type="text"></span>
      <span>微信</span>
      <span><input type="text"></span>
    </div>
    <div class="lineDiv">
      <span>电话</span>
      <span><input type="text"></span>
      <span>性别</span>
      <span><input type="text"></span>
    </div>
    <div class="lineDivT">
      <span>地址</span>
      <span><input type="text"></span>
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
<style scoped>
.lineDiv{
  margin: 5px;
}
.lineDiv span{
  font-size: 12px;
  margin: 5px;
}
.lineDiv input{
  width: auto;
  max-width: 120px;
  font-size: 12px;
}
.lineDivT{
  margin: 5px;
}
.lineDivT span{
  font-size: 12px;
  margin: 5px;
}
.lineDivT input{
  width: auto;
  font-size: 12px;
}
</style>>
