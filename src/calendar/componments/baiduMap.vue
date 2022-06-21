<template>
  <div>
    <!-- <baidu-map
      :center="center"
      :zoom="zoom"
      :scroll-wheel-zoom="true"
      style="width: 100%; height: 100%"
      @ready="handler"
      @click="getClickInfo"
      @moving="syncCenterAndZoom"
      @moveend="syncCenterAndZoom"
      @zoomend="syncCenterAndZoom"
    >
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
      <bm-geolocation
        anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
        :showAddressBar="true"
        :autoLocation="true"
      ></bm-geolocation>
      <bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT"></bm-city-list>
    </baidu-map> -->
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Baidu from 'vue-baidu-map'
Vue.use(Baidu, {
  ak: 'agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi'
})

// declare const BMap: any

@Component({
  name: 'baidu-map',
  components: {
    'baidu-map': Baidu
  }
})
export default class Actions extends Vue {
  center = { lng: 116.404, lat: 39.915 };
  address = '';
  zoom = '';

  handler () {
    // this.center.lng = 116.404
    // this.center.lat =
    // this.zoom = this.zoom
  }

  getClickInfo (e: any) {
    // // 创建地理编码实例
    // const myGeo = new BMap.Geocoder()
    // // 根据坐标逆解析地址
    // myGeo.getLocation(new BMap.Point(e.point.lng, e.point.lat), (result:any) => {
    //   // console.log(result, 'result-->>>>')
    //   if (result) {
    //     this.address = result.address
    //   }
    // })
    // this.center.lng = e.point.lng
    // this.center.lat = e.point.lat
  }

  syncCenterAndZoom (e: any) {
    // console.log(e.target, 'e.target-->>>>')
    // const { lng, lat } = e.target.getCenter()
    // this.zoom = e.target.getZoom()
  }
}
</script>

<style scoped>
</style>
