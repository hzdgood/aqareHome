<template>
  <div>
    <div v-for="item in personList" :key="item.id">{{ item.name }}</div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {
  personList: any[] = [{
    id: 1,
    name: ''
  }];

  // 获取所有技术人员信息

  // 点击方法

  // 全选方法
}
</script>

<style>
</style>
