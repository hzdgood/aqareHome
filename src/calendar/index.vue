<template>
  <div>
    <div class="head">
      <div>
        <div style="font-size: 24px">日程系统</div>
      </div>
    </div>
    <div class="body-div">
      <div class="personDiv">
        <person-info></person-info>
      </div>
      <div class="cententDiv">
        <select-info></select-info>
        <baidu-map></baidu-map>
      </div>
      <div class="rightDiv">
        <detail-info></detail-info>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import selectInfo from './componments/selectInfo.vue'
import personInfo from './componments/personInfo.vue'
import baiduMap from './componments/baiduMap.vue'
import detailInfo from './componments/detailInfo.vue'

import '@/calendar/css/index.css'
@Component({
  name: 'App',
  components: {
    'select-info': selectInfo,
    'person-info': personInfo,
    'baidu-map': baiduMap,
    'detail-info': detailInfo
  }
})
export default class Actions extends Vue {}
</script>
