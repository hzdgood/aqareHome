<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div>
        <span>项目名称</span>
        <input type="text" readonly />
      </div>
      <div>
        <span>收款类型</span>
        <span>定金</span>
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
        <input type="file" />
      </div>
    </div>
  </div>
</template>

<script>
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {}
</script>

<style scoped>

</style>>
