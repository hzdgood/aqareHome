<template>
  <div>
    <div>
      <span>项目名称</span>
      <input type="text" readonly />
    </div>
    <div>
      <span>收款状态</span>
      <input type="text" />
    </div>
    <div>
      <span>收款方式</span>
      <input type="text" />
    </div>
    <div>
      <span>收款金额</span>
      <input type="text" />
    </div>
    <div>
      <span>上传图片</span>
      <input type="text" />
    </div>
  </div>
</template>

<script>
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {}
</script>

<style>
</style>
