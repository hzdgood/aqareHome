<template>
  <div class="tableMapDiv">
    <div v-for="item in layerList" :key="item.id" class="tableMap">
      <table>
        <tr>
          <td>工单类型</td>
          <td>{{ item.date[0].type }}</td>
        </tr>
        <tr>
          <td>客户名称</td>
          <td>{{ item.date[0].name }}</td>
        </tr>
        <tr>
          <td>客户地址</td>
          <td :title="item.date[0].address">{{ item.date[0].address }}</td>
        </tr>
        <tr>
          <td>开始时间</td>
          <td>{{ item.date[0].time }}</td>
        </tr>
        <tr>
          <td>额定工时</td>
          <td>{{ item.date[0].workTime }}</td>
        </tr>
        <tr>
          <td>项目状态</td>
          <td>{{ item.date[0].proStatus }}</td>
        </tr>
        <tr>
          <td>上门技术</td>
          <td>{{ item.date[0].technologys }}</td>
        </tr>
        <tr>
          <td>工单状态</td>
          <td>{{ item.date[0].workStatus }}</td>
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
    // const list: any[] = this.$store.state.selectData
    // const date = this.$store.state.CalendarDate
    // this.layerList = []
    // const obj = {
    //   where: {
    //     and: [
    //       { field: 2200000145748100, query: { in: list } }, // 人员
    //       { field: 2200000146398516, query: { in: [10, 6, 3, 2, 5, 4, 12] } }, // 订单类型 除了发货
    //       {
    //         field: 2200000145748099, // 上门时间
    //         query: {
    //           range: [
    //             { model: 'static', datetime: date },
    //             { model: 'static', datetime: date }
    //           ]
    //         }
    //       },
    //       { field: 2200000146473059, query: { in: [1] } }
    //     ]
    //   },
    //   offset: 0,
    //   limit: 20,
    //   order_by: [{ field: 2200000146199958, sort: 'desc' }]
    // }
    // const result = await SearchInfo('2100000015054992', obj)
    // if (result.length === 0) {
    //   this.$store.state.layerList = []
    // }
    this.layerList = []
    const result = await httpGet('/measure/selectOpen', {
      person: this.$store.state.selectData,
      time: this.$store.state.CalendarDate
    })

    for (let i = 0; i < result.length; i++) {
      const custom = '' //  客户名称
      const workTime = '' // 额定工时
      const StartTime = '' // 上门时间
      const proStatus = '' // 当前进度
      const address = '' // 客户地址
      const tech = 0
      const technologys = '' // 上门技术
      const type = '' // 订单类型
      const workStatus = '' // 工单状态
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
