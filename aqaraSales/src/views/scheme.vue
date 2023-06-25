<template>
  <div class="schemeDiv">
    <table class="schemeTable">
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
      <tr :key="item.id" v-for="item in projectList">
        <td>{{ item.projectName }}</td>
        <td>{{ item.AllNumber }}</td>
        <td>{{ item.planNuber }}</td>
        <td>{{ item.price }}</td>
        <td>{{ item.discount }}</td>
        <td>{{ item.servieFee }}</td>
        <td>{{ item.notIssued }}</td>
        <td>{{ item.notInstalled }}</td>
        <td>{{ item.notAdjusted }}</td>
      </tr>
    </table>
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
  customerPlan = table.customerPlan;
  projectId: any = '';
  projectList: any[] = [];
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
    // 查询当前人员的主项目
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
      limit: 100
    }
    // 查询客户方案表
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
        if (fields[j].field_id === field.number) {
          AllNumber = fields[j].values[0].value
        }
        if (fields[j].field_id === field.planNuber) {
          planNuber = fields[j].values[0].value
        }
        if (fields[j].field_id === field.money) {
          price = fields[j].values[0].value
        }
        if (fields[j].field_id === field.discount) {
          discount = fields[j].values[0].value
        }
        if (fields[j].field_id === field.serviceFee) {
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
        if (AllNumber !== 0 || planNuber !== 0) {
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
