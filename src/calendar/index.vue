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
      </div>
      <!-- <div class="rightDiv">
        <detail-info></detail-info>
      </div> -->
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import selectInfo from './componments/selectInfo.vue'
import personInfo from './componments/personInfo.vue'
import baiduMap from './componments/baiduMap.vue'
import detailInfo from './componments/detailInfo.vue'
import { table, field } from '@/config/config'
import { SearchInfo, streams } from '@/config/interFace'
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
export default class Actions extends Vue {
  // dataList: any[] = [];
  // async mounted () {
  //   const obj = {
  //     where: { and: [{ field: 2200000200319248, query: { in: [3] } }] },
  //     offset: 0,
  //     limit: 500
  //   }
  //   const result = await SearchInfo(table.customerPlan, obj)
  //   for (let i = 0; i < result.length; i++) {
  //     const fields = result[i].fields
  //     const itemId: any = result[i].item_id
  //     let projectName, schemeName
  //     for (let j = 0; j < fields.length; j++) {
  //       if (fields[j].field_id === 2200000143342285) {
  //         projectName = fields[j].values[0].title
  //       }
  //       if (fields[j].field_id === field.projectName) {
  //         schemeName = fields[j].values[0].title
  //       }
  //     }
  //     const obj = {
  //       itemId: itemId,
  //       projectName: projectName,
  //       schemeName: schemeName
  //     }
  //     this.dataList.push(obj)
  //   }
  //   for (let m = 0; m < this.dataList.length; m++) {
  //     const itemId = this.dataList[m].itemId
  //     const result1 = await streams(itemId, '0', '20')
  //     for (let i = 0; i < result1.length; i++) {
  //       const content = result1[i].content
  //       const updated_on = result1[i].updated_on
  //       if (updated_on.split(' ')[0] === '2022-06-27') {
  //         for (let j = 0; j < content.length; j++) {
  //           if (content[j].indexOf(' → ')) {
  //             const name = content[j].split(':')[0]
  //             if (name === '服务费') {
  //               console.log(
  //                 content[j] +
  //                   '----' +
  //                   this.dataList[m].projectName +
  //                   '----' +
  //                   this.dataList[m].schemeName
  //               )
  //             }
  //             if (name === '销售单价') {
  //               console.log(
  //                 content[j] +
  //                   '----' +
  //                   this.dataList[m].projectName +
  //                   '----' +
  //                   this.dataList[m].schemeName
  //               )
  //             }
  //             // if (name === '全款数量') {
  //             //   console.log(
  //             //     content[j] +
  //             //       '----' +
  //             //       this.dataList[m].projectName +
  //             //       '----' +
  //             //       this.dataList[m].schemeName
  //             //   )
  //             // }
  //           }
  //         }
  //       }
  //     }
  //   }
  // }
}
</script>
