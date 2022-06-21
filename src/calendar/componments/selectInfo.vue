<template>
  <div>
    周
    <select>
      <option>本周</option>
      <option>下周周</option>
      <option>下下周</option>
    </select>
    日期
    <select>
      <option>周一</option>
      <option>周二</option>
      <option>周三</option>
      <option>周四</option>
      <option>周五</option>
      <option>周六</option>
      <option>周日</option>
    </select>
    <button>查询</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {

}
</script>

<style>
</style>
