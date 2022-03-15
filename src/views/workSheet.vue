<template>
  <div class="centerDiv">
    <div v-for="sheetList in sheetList" :key="sheetList.index">
      <div>
        <span>{{ sheetList.visitDate }} </span>
        <span>{{ sheetList.orderType }} </span>
        <span>{{ sheetList.workOrderStatus }}</span>
      </div>
      <div>
        <span>{{ sheetList.technology }}</span>
      </div>
      <div>
        <span>完成情况: </span>
        <span>{{ sheetList.todayCompletion }}</span>
      </div>
      <div>
        <span>下次安排: </span>
        <span>{{ sheetList.nextDoor }}</span>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field, user } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  workSheet = table.workSheet;
  sheetList: any[] = [];
  async mounted () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [user.userId] }] },
            query_option_mappings: [-1],
            field: field.projectUUid
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [
        { field: field.projectUUid, sort: 'desc' },
        { field: field.masterProject, sort: 'asc' }
      ]
    }
    const result = await SearchInfo(this.projectInfo, data)
    let projectCode: any = ''
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectCode) {
          projectCode = fields[j].values[0].value
        }
      }
    }
    const data1 = {
      where: {
        and: [
          {
            query: { or: [{ in: [projectCode] }] },
            query_option_mappings: [-1],
            field: 1101001226000000
          },
          { field: 2200000146398516, query: { in: [4, 5, 2, 3, 6, 10] } },
          { field: 2200000146473059, query: { in: [1] } }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 1101001226000000, sort: 'desc' }]
    }
    const result1 = await SearchInfo(this.workSheet, data1)

    for (let i = 0; i < result1.length; i++) {
      let visitDate = ''
      let orderType = ''
      let technology = ''
      let workOrderStatus = ''
      let nextDoor = ''
      let todayCompletion = ''
      const fields = result1[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.visitDate) {
          visitDate = fields[j].values[0].value
        }
        if (fields[j].field_id === field.orderType) {
          orderType = fields[j].values[0].name
        }
        if (fields[j].field_id === field.technology) {
          for (let k = 0; k < fields[j].values.length; k++) {
            technology += fields[j].values[k].title
          }
        }
        if (fields[j].field_id === field.workOrderStatus) {
          workOrderStatus = fields[j].values[0].name
        }
        if (fields[j].field_id === field.nextDoor) {
          nextDoor = fields[j].values[0].value
        }
        if (fields[j].field_id === field.todayCompletion) {
          todayCompletion = fields[j].values[0].value
        }
      }
      const obj = {
        index: i,
        visitDate: visitDate,
        orderType: orderType,
        technology: technology,
        workOrderStatus: workOrderStatus,
        nextDoor: nextDoor,
        todayCompletion: todayCompletion
      }
      this.sheetList.push(obj)
    }
  }
}
</script>
<style scoped>
</style>
