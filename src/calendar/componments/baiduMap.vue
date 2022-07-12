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
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>

      <happy-layer
        v-for="item in layerList"
        :key="item.id"
        :date="item.date"
        :position="{ lng: item.lng, lat: item.lat }"
        @click="overLayClick(item)"
      ></happy-layer>
    </baidu-map>
  </div>
</template>

<script lang="ts">
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import overlay from './overlay.vue'
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
@Component({
  name: 'Bmap',
  components: {
    'baidu-map': BaiduMap,
    'happy-layer': overlay
  }
})
export default class Actions extends Vue {
  center = '上海';
  zoom = 10;
  wheelZoom = true;
  BMap: any = {};
  map: any = {};
  layerList: any[] = [];

  @Watch('$store.state.searchStatus')
  async selectPage () {
    const list: any[] = this.$store.state.selectData
    const date = this.$store.state.CalendarDate
    const obj = {
      where: {
        and: [
          { field: 2200000145748100, query: { in: list } }, // 人员
          { field: 2200000146398516, query: { in: [10, 6, 3, 2, 5, 4] } }, // 订单类型 除了发货
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
    console.log(result)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      for (let j = 0; j < fields.length; j++) {}
    }
  }

  overLayClick (obj: any) {
    console.log(obj)
  }

  handler ({ BMap, map }: any) {
    this.BMap = BMap
    this.map = map
  }
}
</script>
