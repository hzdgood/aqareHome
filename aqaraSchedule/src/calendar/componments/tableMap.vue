<template>
  <div class="tableMapDiv">
    <div v-for="item in layerList" :key="item.id" class="tableMap">
      <table>
        <tr>
          <td>上门技术</td>
          <td>{{ item.date[0].technologys }}</td>
        </tr>
        <tr>
          <td>客户名称</td>
          <td>{{ item.date[0].name }}</td>
        </tr>
        <tr>
          <td>工单类型</td>
          <td>{{ item.date[0].type }}</td>
        </tr>
        <tr>
          <td>客户地址</td>
          <td :title="item.date[0].address">{{ item.date[0].address }}</td>
        </tr>
        <tr>
          <td>额定工时</td>
          <td>{{ item.date[0].workTime }}</td>
        </tr>
        <tr>
          <td>预约时间</td>
          <td>{{ item.date[0].time }}</td>
        </tr>
        <tr>
          <td>预计离开</td>
          <td>{{ item.date[0].Leave }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Watch } from 'vue-property-decorator'
import { httpGet } from '@/config/interFace'
@Component({})
export default class Actions extends Vue {
  layerList: any[] = [];

  @Watch('$store.state.searchStatus')
  async selectPage () {
    this.layerList = []
    const result = await httpGet('/schedule/work', {
      // person: this.$store.state.selectData,
      dateOfVisit: this.$store.state.CalendarDate
    })
    for (let i = 0; i < result.length; i++) {
      const custom = result[i].p_name //  客户名称
      const workTime = result[i].st // 额定工时
      const StartTime = result[i].dateOfVisit // 上门时间
      const proStatus = '' // 当前进度
      const address = result[i].address // 客户地址
      const tech = result[i].leader
      const technologys = result[i].allname // 上门技术
      const type = result[i].type // 订单类型
      const workStatus = '' // 工单状态
      const dateOfLeave = result[i].dateOfLeave
      const coordinate: any = {
        lon: '',
        lat: ''
      }
      const obj = {
        id: i,
        lng: coordinate.lon,
        lat: coordinate.lat,
        technology: technologys,
        tech: tech,
        date: [
          {
            id: i,
            name: custom,
            time: StartTime.split(' ')[1],
            Leave: dateOfLeave.split(' ')[1],
            type: type,
            workStatus: workStatus,
            technologys: technologys,
            workTime: workTime,
            address: address,
            proStatus: proStatus
          }
        ]
      }
      this.layerList.push(obj)
      this.$store.state.layerList = this.layerList
    }
  }
}
</script>
