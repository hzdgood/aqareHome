<template>
  <div>
    <div class="head">
      <div>
        <div style="font-size: 24px; line-height: 40px;">日程系统</div>
      </div>
    </div>
    <div class="body-div">
      <div class="personDiv">
        <person-info></person-info>
      </div>
      <div class="cententDiv">
        <select-info></select-info>
        <baidu-map></baidu-map>
        <!-- <tables-map></tables-map> -->
      </div>
    </div>
    <div class="right-div">
      <work-info></work-info>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import selectInfo from './componments/selectInfo.vue'
import personInfo from './componments/personInfo.vue'
import baiduMap from './componments/baiduMap.vue'
import tableMaps from './componments/tableMap.vue'
import workInfo from './componments/workInfo.vue'
import '@/calendar/css/index.css'
@Component({
  name: 'App',
  components: {
    'select-info': selectInfo,
    'person-info': personInfo,
    'baidu-map': baiduMap,
    'tables-map': tableMaps,
    'work-info': workInfo
  }
})
export default class Actions extends Vue {}
</script>
