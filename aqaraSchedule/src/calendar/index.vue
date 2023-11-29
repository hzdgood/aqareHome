<template>
  <div>
    <div class="head">
      <div>
        <div style="font-size: 24px; line-height: 40px;">汇社日程系统</div>
      </div>
    </div>
    <div class="body-div">
      <div class="personDiv">
        <person-info></person-info>
      </div>
      <div class="cententDiv">
        <select-info @setModel="setModel"></select-info>
        <baidu-map v-show="mapStatus"></baidu-map>
        <tables-map v-show="tabStatus"></tables-map>
      </div>
    </div>
    <div class="right-div">
      <work-info></work-info>
    </div>
    <div class="filings">
      <a href="https://beian.miit.gov.cn/">沪ICP备2022007443号</a>
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
import { SearchInfo, httpGet, userInfo } from '../config/interFace'

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
export default class Actions extends Vue {
  mapStatus = true
  tabStatus = false

  setModel (model: boolean) {
    if (model) {
      this.mapStatus = true
      this.tabStatus = false
    } else {
      this.mapStatus = false
      this.tabStatus = true
    }
  }

  async mounted () {
    await userInfo().then(function (response) {
      localStorage.setItem('ticket', response.data.ticket)
    })
  //   const obj = {
  //     offset: 2500,
  //     limit: 3000,
  //     order_by: [
  //       {
  //         field: 'created_on',
  //         sort: 'desc'
  //       }
  //     ]
  //   }
  //   const res = await SearchInfo('2100000054696521', obj)
  //   for (let i = 0; i < res.length; i++) {
  //     var field = res[i].fields
  //     const itemId = res[i].item_id
  //     let value: any
  //     let type: any
  //     for (let j = 0; j < field.length; j++) {
  //       if (field[j].field_id === 2200000450555011) {
  //         type = field[j].values[0].name
  //       }
  //       if (type === '云起') {
  //         if (field[j].field_id === 1106001118000000) {
  //           value = field[j].values[0].value
  //         }
  //       } else if (type === '绿米' || type === '创米') {
  //         if (field[j].field_id === 1105001159000000) {
  //           value = field[j].values[0].value
  //         }
  //       }
  //     }
  //     if (typeof value !== 'undefined') {
  //       const result = {
  //         projectId: itemId + '',
  //         latitude: value.coordinate.lat + '',
  //         longitude: value.coordinate.lon + '',
  //         type: type
  //       }
  //       await httpGet('/position/insert', result)
  //     }
  //   }
  }
}
</script>
