<template>
  <div>
    <div v-for="item in sendList" :key="item.index" class="sendDiv">
      <div>
        {{ item.issuance }}{{ item.orderType }}
        {{ item.batchingState }}
      </div>
      <div>
        <table class="sendTable">
          <tr>
            <td>产品名称</td>
            <td>产品数量</td>
          </tr>
          <tr v-for="data in item.orderdata" :key="data.index">
            <td>{{ data.orderPname }}</td>
            <td>{{ data.shipment }}</td>
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
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  workSheet = table.workSheet;
  sheetDetail = table.sheetDetail;
  sendList: any[] = [];
  userId = localStorage.getItem('userId');
  contactType = localStorage.getItem('contactType');
  chatId = localStorage.getItem('chatID');

  getData () {
    let data = {}
    if (this.contactType === 'single_chat_tools') {
      data = {
        where: {
          and: [
            {
              query: { or: [{ in: [this.userId] }] },
              query_option_mappings: [-1],
              field: field.projectUUid
            },
            { field: field.masterProject, query: { in: [1] } }
          ]
        },
        offset: 0,
        limit: 20
      }
    } else {
      data = {
        where: {
          and: [
            {
              query: { or: [{ in: [this.chatId] }] },
              query_option_mappings: [-1],
              field: 2200000172376106
            }
          ]
        },
        offset: 0,
        limit: 20
      }
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
          { field: 2200000146398516, query: { in: [11] } },
          { field: 2200000146473059, query: { in: [1] } }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000146398516, sort: 'desc' }]
    }
    const result1 = await SearchInfo(this.workSheet, data1)
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
                    or: [{ eqm: [orderId] }]
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
          const result = await SearchInfo(this.sheetDetail, date2)
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
