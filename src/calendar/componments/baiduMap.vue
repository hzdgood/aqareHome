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
      <bm-point-collection
        :points="points"
        shape="BMAP_POINT_SHAPE_STAR"
        color="red"
        size="BMAP_POINT_SIZE_SMALL"
        @click="clickHandler"
      ></bm-point-collection>

      <!-- <bm-overlay
        pane="labelPane"
        :class="{ sample: true, active }"
        @draw="draw"
        @mouseover.native="active = true"
        @mouseleave.native="active = false"
      >
        <div>
          <div>李晓龙</div>
          <table class="point">
            <tr>
              <td class="busy"></td>
              <td class="busy"></td>
              <td class="fee"></td>
              <td class="fee"></td>
            </tr>
          </table>
        </div>
      </bm-overlay> -->
    </baidu-map>
  </div>
</template>

<script lang="ts">
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
@Component({
  name: 'Bmap',
  components: {
    'baidu-map': BaiduMap
  }
})
export default class Actions extends Vue {
  center = '上海';
  zoom = 3;
  points: any[] = [];
  active = false;
  wheelZoom = true;
  keyword = '上海市黄浦区露香园万竹街45弄10号';
  location = '上海';
  BMap: any = {}
  map: any = {}

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

    }
  }

  handler ({ BMap, map }: any) {
    this.BMap = BMap
    this.map = map
  }

  draw ({ el, BMap, map }: any) {
    const pixel = map.pointToOverlayPixel(new BMap.Point(116.404, 39.915))
    el.style.left = pixel.x - 60 + 'px'
    el.style.top = pixel.y - 20 + 'px'
  }

  clickHandler (e: any) {
    alert(`单击点的坐标为：${e.point.lng}, ${e.point.lat}`)
  }
}
</script>
