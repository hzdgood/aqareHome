<template>
  <div class="centerDiv">
    <div v-for="sendList in sendList" :key="sendList.index">
      <div>
        <span>{{ sendList.issuance }} </span>
        <span>{{ sendList.orderType }} </span>
        <span>{{ sendList.batchingState }}</span>
      </div>
      <div>
        <table class="infoTable">
          <thead>
            <tr>
              <td>产品名称</td>
              <td>产品数量</td>
            </tr>
          </thead>
          <tr v-for="data in sendList.orderdata" :key="data.index">
            <td>{{ data.orderPname }}</td>
            <td>{{ data.shipment }}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  ticket = localStorage.getItem('ticket');
  projectInfo = table.projectInfo;
  workSheet = table.workSheet;
  sheetDetail = table.sheetDetail;
  sendList: any[] = [];
  async mounted () {
    const data1 = {
      where: {
        and: [
          {
            query: { or: [{ in: ['马女士'] }] },
            query_option_mappings: [-1],
            field: 1101001226000000
          },
          { field: 2200000146398516, query: { in: [11] } },
          { field: 2200000146473059, query: { in: [1] } }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000146398516, sort: 'desc' }]
    }
    const result1 = await SearchInfo(this.ticket, this.workSheet, data1)
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      let issuance = ''
      let orderType = ''
      let batchingState = ''
      let orderId = ''
      const resultData: any[] = []
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.issuance) {
          issuance = fields[j].values[0].value
        }
        if (fields[j].field_id === field.orderType) {
          orderType = fields[j].values[0].name
        }
        if (fields[j].field_id === field.batchingState) {
          batchingState = fields[j].values[0].name
        }
        if (fields[j].field_id === field.orderId) {
          orderId = fields[j].values[0].value
          const date2 = {
            where: {
              and: [
                {
                  query: {
                    or: [{ in: [orderId] }]
                  },
                  query_option_mappings: [-1],
                  field: 1115001102000000
                }
              ]
            },
            offset: 0,
            limit: 20,
            order_by: [{ field: 2200000145750844, sort: 'desc' }]
          }
          const result = await SearchInfo(this.ticket, this.sheetDetail, date2)
          for (let m = 0; m < result.length; m++) {
            const fields = result[m].fields
            let orderPname = ''
            let shipment = ''
            for (let n = 0; n < fields.length; n++) {
              if (fields[n].field_id === field.orderPname) {
                orderPname = fields[n].values[0].title
              }
              if (fields[n].field_id === field.shipment) {
                shipment = fields[n].values[0].value
              }
            }
            const obj = {
              index: m,
              orderPname: orderPname,
              shipment: shipment
            }
            resultData.push(obj)
          }
        }
      }
      const obj: any = {
        index: i,
        issuance: issuance,
        orderType: orderType,
        batchingState: batchingState,
        orderdata: resultData
      }
      this.sendList.push(obj)
    }
  }
}
</script>
