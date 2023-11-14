<template>
  <div v-show="dataList.length !== 0" class="right-info">
    <div>工单详情</div>
    <table v-for="item in dataList" :key="item.id">
      <tr>
        <td>工单类型</td>
        <td>{{ item.type }}</td>
      </tr>
      <tr>
        <td>客户名称</td>
        <td>{{ item.name }}</td>
      </tr>
      <tr>
        <td>客户地址</td>
        <td>{{ item.address }}</td>
      </tr>
      <tr>
        <td>额定工时</td>
        <td>{{ item.workTime }}</td>
      </tr>
      <tr>
        <td>上门技术</td>
        <td>{{ item.technologys }}</td>
      </tr>
      <tr>
        <td>开始时间</td>
        <td>{{ item.time }}</td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {
  dataList: any[] = [];
  @Watch('$store.state.dataList')
  pageList () {
    this.dataList = this.$store.state.dataList
  }
}
</script>
<style>
</style>
