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
      <bm-overlay
        pane="labelPane"
        :class="{ sample: true, active }"
        @draw="draw"
        @mouseover.native="active = true"
        @mouseleave.native="active = false"
      >
        <div>我爱北京天安门</div>
      </bm-overlay>
    </baidu-map>
  </div>
</template>

<script lang="ts">
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import { Component, Vue } from 'vue-property-decorator'

@Component({
  name: 'Bmap',
  components: {
    'baidu-map': BaiduMap
  }
})
export default class Actions extends Vue {
  center = { lng: 0, lat: 0 };
  zoom = 3;
  points: any[] = [];
  active = false;
  wheelZoom = true;

  mounted () {
    // this.center.lng = 116.404;
    // this.center.lat = 39.915;
    // this.zoom = 15;
  }

  handler ({ BMap, map }: any) {
    console.log(BMap, map)
    this.center.lng = 116.404
    this.center.lat = 39.915
    this.zoom = 15
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

<style scoped>

</style>
