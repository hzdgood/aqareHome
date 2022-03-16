<template>
  <div>
    请选择客户名称进行绑定
    <input id="name" type="text" />
    <input type="button" value="查询"/>
    <div>
      <select>
        <option></option>
      </select>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-property-decorator'
export default class Home extends Vue {}
</script>

<style>
</style>
