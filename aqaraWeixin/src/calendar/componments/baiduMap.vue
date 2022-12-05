<template>
  <div>
    <baidu-map
      class="bm-view"
      ak="agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi"
      :center="center"
      :zoom="zoom"
      :scroll-wheel-zoom="wheelZoom"
      @ready="handler"
    >
      <!-- <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation> -->
      <happy-layer
        v-for="item in layerList"
        :key="item.id"
        :date="item.date"
        :position="{ lng: item.lng, lat: item.lat }"
        :active="active"
        :technology="item.technology"
        @mouseover.native="active = true"
        @mouseleave.native="active = false"
      ></happy-layer>
    </baidu-map>
  </div>
</template>

<script lang="ts">
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import overlay from './overlay.vue'
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo, getCoordinate1 } from '@/config/interFace'
@Component({
  name: 'Bmap',
  components: {
    'baidu-map': BaiduMap,
    'happy-layer': overlay
  }
})
export default class Actions extends Vue {
  center = '上海';
  zoom = 15;
  wheelZoom = true;
  BMap: any = {};
  map: any = {};
  active = false;
  layerList: any[] = [];

  @Watch('$store.state.searchStatus')
  async selectPage () {
    const list: any[] = this.$store.state.selectData
    const date = this.$store.state.CalendarDate
    this.layerList = []
    const obj = {
      where: {
        and: [
          { field: 2200000145748100, query: { in: list } }, // 人员
          { field: 2200000146398516, query: { in: [10, 6, 3, 2, 5, 4, 12] } }, // 订单类型 除了发货
          {
            field: 2200000145748099, // 上门时间
            query: {
              range: [
                { model: 'static', datetime: date },
                { model: 'static', datetime: date }
              ]
            }
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000146199958, sort: 'desc' }]
    }
    const result = await SearchInfo('2100000015054992', obj)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const itemId = result[i].item_id
      let custom = ''; let workTime = ''; let proStatus = ''
      let technology = ''; let address = ''; let tech = 0
      let technologys = ''; let dlAddress = ''; let type = ''
      let workStatus = ''
      let coordinate: any = {
        lon: '',
        lat: ''
      }
      let StartTime = ''
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 1101001291000000) {
          // 客户名称
          custom = fields[j].values[0].value
        }
        if (fields[j].field_id === 1101001159000000) {
          // 客户地址
          coordinate = fields[j].values[0].value.coordinate
        }
        if (fields[j].field_id === 2200000149226229) {
          // 额定工时
          workTime = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000145748100) {
          // 上门技术
          technology = fields[j].values[0].title
          tech = fields[j].values.length
          for (let m = 0; m < tech; m++) {
            technologys = fields[j].values[m].title + ',' + technologys
          }
        }
        if (fields[j].field_id === 2200000145748099) {
          // 上门时间
          StartTime = fields[j].values[0].value
        }
        if (fields[j].field_id === 1101001102000000) {
          // 地址 |
          address = fields[j].values[0].value
        }
        if (fields[j].field_id === 1101001291000000) {
          // 订单类型
          type = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000146398516) {
          // 订单类型
          type = fields[j].values[0].name
        }
        if (fields[j].field_id === 2200000151806983) {
          // 导流地址
          dlAddress = fields[j].values[0].value
        }
        if (fields[j].field_id === 1101001195000000) {
          // 当前进度
          proStatus = fields[j].values[0].name
        }
        if (fields[j].field_id === 2200000148897469) {
          // 工单状态
          workStatus = fields[j].values[0].name
        }
      }
      let worktimes
      if (type !== '导流') {
        worktimes = Number(workTime) / Number(tech)
        if (coordinate.lon === '') {
          const rs = await getCoordinate1({
            address: address
          })
          coordinate.lon = rs.lng
          coordinate.lat = rs.lat
        }
      } else {
        worktimes = workTime
        if (coordinate.lon === '') {
          if (dlAddress !== '') {
            const rs = await getCoordinate1({
              address: dlAddress
            })
            coordinate.lon = rs.lng
            coordinate.lat = rs.lat
          }
        }
      }
      if (address === '') {
        address = dlAddress
      }
      const obj = {
        id: itemId,
        lng: coordinate.lon,
        lat: coordinate.lat,
        technology: technology,
        tech: tech,
        date: [
          {
            id: i,
            name: custom,
            time: StartTime.split(' ')[1],
            type: type,
            workStatus: workStatus,
            technologys: technologys.substring(0, technologys.length - 1),
            workTime: worktimes,
            address: address,
            proStatus: proStatus
          }
        ]
      }
      this.layerList.push(obj)
      this.$store.state.layerList = this.layerList
    }
  }

  handler ({ BMap, map }: any) {
    this.BMap = BMap
    this.map = map
  }
}
</script>
