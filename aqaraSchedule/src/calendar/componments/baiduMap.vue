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
import { httpGet, getCoordinate } from '@/config/interFace'
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
    this.layerList = []
    console.log(this.$store.state.selectData)
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
      const coordinate: any = {
        constlon: '',
        lat: ''
      }
      const worktimes = workTime
      const rs = await getCoordinate({
        address: address
      })
      coordinate.lon = rs.lng
      coordinate.lat = rs.lat
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
