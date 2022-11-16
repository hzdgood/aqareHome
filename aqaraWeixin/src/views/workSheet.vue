<template>
  <div class="centerDiv">
    <div class="workDiv" v-for="item in sheetList" :key="item.index">
      <div v-if="item.orderType !== '发货'">
        <span></span>
        <table class="workTable">
          <tr>
            <td>{{ item.orderType }}</td>
            <td>{{ item.technology }}</td>
            <td><span>{{ item.workOrderStatus }}</span></td>
          </tr>
          <tr>
            <td>上门时间</td>
            <td colspan="2">{{ item.visitDate }}</td>
          </tr>
          <tr>
            <td>完成情况</td>
            <td colspan="2">{{ item.todayCompletion }}</td>
          </tr>
          <tr>
            <td>下次安排</td>
            <td colspan="2">{{ item.nextDoor }}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
import { masterReq, chatReq } from '@/config/common'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  workSheet = table.workSheet;
  sheetList: any[] = [];
  userId = localStorage.getItem('userId');
  contactType = localStorage.getItem('contactType');
  chatId = localStorage.getItem('chatID');

  getData () {
    let data = {}
    if (this.contactType === 'single_chat_tools') {
      data = masterReq(this.userId)
    } else {
      data = chatReq(this.chatId)
    }
    return data
  }

  async mounted () {
    const data = this.getData()
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
    if (projectCode === '') {
      return
    }
    const data1 = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [projectCode] }] },
            query_option_mappings: [-1],
            field: 1101001226000000
          },
          { field: 2200000146398516, query: { eqm: [4, 5, 2, 3, 6, 10] } },
          { field: 2200000146473059, query: { eqm: [1] } }
        ]
      },
      offset: 0,
      limit: 100
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
<style scoped></style>
