<template>
  <div>
    <table width='100%' class='projectTable'>
      <tr>
        <td colspan="2">系统跟踪日志</td>
      </tr>
      <tr v-for = 'item in logList' :key='item.id'>
        <td>{{ item.date }}</td>
        <td>{{ item.info }}</td>
      </tr>
    </table>
    <table width='100%' class='projectTable'>
      <tr>
        <td colspan="2">项目跟踪日志</td>
      </tr>
      <tr v-for = 'item in logList1' :key='item.id'>
        <td>{{ item.date }}</td>
        <td>{{ item.info }}</td>
      </tr>
    </table>
  </div>
</template>
<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { masterReq } from '@/config/common'
import { table } from '@/config/config'
import { logSelect, SearchInfo } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  logList: any[] = []
  logList1: any[] = []
  itemId = ''
  userId = localStorage.getItem('userId')
  async mounted () {
    const res = await logSelect()
    for (let i = 0; i < res.length; i++) {
      const obj = {
        id: res[i].id,
        date: res[i].date.substring(0, 10),
        info: res[i].info
      }
      this.logList.push(obj)
    }
    const data = masterReq(this.userId)
    const result = await SearchInfo(table.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      this.itemId = result[i].item_id
    }
    if (this.itemId === '') {
      return
    }
    const obj = {
      where: {
        and: [{ field: 2200000316783324, query: { in: [this.itemId] } }]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000316783265, sort: 'desc' }]
    }
    const result1 = await SearchInfo('2100000039691275', obj)
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      let date = ''
      let info = ''
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000316783265) {
          date = fields[j].values[0].value
        } else if (fields[j].field_id === 2200000316783267) {
          info = fields[j].values[0].value
        }
      }
      const obj = {
        id: i,
        date: date,
        info: info
      }
      this.logList1.push(obj)
    }
  }
}
</script>
<style scoped></style>
