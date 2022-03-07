<template>
  <div>
    方案上传
    <div>
      <input type="button" @click="saveClick()" value="保存" />
      <input type="button" @click="closeClick()" value="关闭" />
    </div>
  </div>
</template>

<script>
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {
  saveClick () {
    this.$emit('closeScheme')
  }

  closeClick () {
    this.$emit('closeScheme')
  }
}
</script>

<style>
</style>
