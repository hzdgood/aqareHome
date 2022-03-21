<template>
  <div class="centerDiv">
    <table class="infoTable">
      <thead>
        <tr>
          <td>产品</td>
          <td>全款</td>
          <td>方案</td>
          <td>单价</td>
          <td>折扣</td>
          <td>费率</td>
          <td>未发</td>
          <td>未装</td>
          <td>未调</td>
        </tr>
      </thead>
      <tr :key="projectList.id" v-for="projectList in projectList">
        <td>{{ projectList.projectName }}</td>
        <td>{{ projectList.AllNumber }}</td>
        <td>{{ projectList.planNuber }}</td>
        <td>{{ projectList.price }}</td>
        <td>{{ projectList.discount }}</td>
        <td>{{ projectList.servieFee }}</td>
        <td>{{ projectList.notIssued }}</td>
        <td>{{ projectList.notInstalled }}</td>
        <td>{{ projectList.notAdjusted }}</td>
      </tr>
    </table>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, user } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  customerPlan = table.customerPlan;
  projectId: any = '';
  projectList: any[] = [];
  async mounted () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [user.userId] }] },
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
    if (projectCode === '') {
      return
    }
    const data1 = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [projectCode] }] },
            query_option_mappings: [-1],
            field: 1127011226000000
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result1 = await SearchInfo(this.customerPlan, data1)
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      let projectName: any = ''
      let AllNumber: any = ''
      let planNuber: any = ''
      let price: any = ''
      let discount: any = ''
      let servieFee: any = ''
      let notIssued: any = ''
      let notInstalled: any = ''
      let notAdjusted: any = ''
      let status = true
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000176622409) {
          const value = fields[j].values[0].id
          if (value === 1) {
            status = false
          }
        }
        if (fields[j].field_id === field.projectName) {
          projectName = fields[j].values[0].title
        }
        if (fields[j].field_id === field.AllNumber) {
          AllNumber = fields[j].values[0].value
        }
        if (fields[j].field_id === field.planNuber) {
          planNuber = fields[j].values[0].value
        }
        if (fields[j].field_id === field.price) {
          price = fields[j].values[0].value
        }
        if (fields[j].field_id === field.discount) {
          discount = fields[j].values[0].value
        }
        if (fields[j].field_id === field.servieFee) {
          servieFee = fields[j].values[0].value
        }
        if (fields[j].field_id === field.notIssued) {
          notIssued = fields[j].values[0].value
        }
        if (fields[j].field_id === field.notInstalled) {
          notInstalled = fields[j].values[0].value
        }
        if (fields[j].field_id === field.notAdjusted) {
          notAdjusted = fields[j].values[0].value
        }
      }
      if (status) {
        if (AllNumber !== 0 && planNuber !== 0) {
          const obj = {
            id: i,
            projectName: projectName,
            AllNumber: AllNumber,
            planNuber: planNuber,
            price: price,
            discount: discount,
            servieFee: servieFee,
            notIssued: notIssued,
            notInstalled: notInstalled,
            notAdjusted: notAdjusted
          }
          this.projectList.push(obj)
        }
      }
    }
  }
}
// 折扣 费率 %
</script>
