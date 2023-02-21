<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">收款/报价</div>
      <div>
        <button class='saveButton' v-show="collectStatus" @click='collectCreate()'>新增收款</button>
        <button class='saveButton' v-show="proposalStatus" @click='proposalCreate()'>新增报价</button>
        <button class="closeButton" @click="closeClick()">关闭</button>
      </div>
      <div class="contentDiv">收款单</div>
      <div v-show='collectList.length != 0'>
        <table class='schemeTable'>
          <thead>
          <tr>
            <td>名称</td>
            <td>类型</td>
            <td>方式</td>
            <td>金额</td>
          </tr>
          </thead>
          <tr v-for='item in collectList' :key='item.id'>
            <td>{{ item.pName }}</td>
            <td>{{ item.pType }}</td>
            <td>{{ item.cType }}</td>
            <td>{{ item.cMoney }}</td>
          </tr>
        </table>
      </div>
      <div class="contentDiv">报价单</div>
      <div v-show='proposalList.length != 0'>
        <table class='schemeTable'>
          <thead>
          <tr>
            <td>方案</td>
            <td>优惠</td>
            <td>应收</td>
            <td>已收</td>
          </tr>
          </thead>
          <tr v-for='item in proposalList' :key='item.id'>
            <td>{{ item.schemeMoney }}</td>
            <td>{{ item.discount }}</td>
            <td>{{ item.receivable }}</td>
            <td>{{ item.Received }}</td>
          </tr>
        </table>
      </div>
    </div>
    <my-Modal :visible="visible" :modalText="errorMsg" @close="closeModal()"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
    <collect-from :itemId="itemId" :localName="localName" :projectCode="projectCode"
      :projectId="projectId" :proposalMoney="proposalMoney" v-show="collectFromShow" @close="closeCollectFrom()"></collect-from>
    <proposal-form :dataList="dataList" v-show="proposalFromShow" @close="closeProposalFrom()"></proposal-form>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { masterReq, getProposal, getCollect } from '@/config/common'
import { SearchInfo, addInfo, logInsert } from '@/config/interFace'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'
import collectFrom from './components/collectForm.vue'
import proposalForm from './components/proposalForm.vue'
@Component({
  name: 'collectEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading,
    'collect-from': collectFrom,
    'proposal-form': proposalForm
  }
})
export default class Home extends Vue {
  collectList: any[] = []
  proposalList: any[] = []
  projectCode = ''
  projectId = ''
  errorMsg = ''
  itemId = 0
  proposalMoney = 0
  visible = false
  loadVisible = false
  userId = localStorage.getItem('userId');
  localName = localStorage.getItem('localName');
  fileList: any = []
  collectStatus = false
  proposalStatus = false
  proposalFromShow = false
  collectFromShow = false
  dataList: any[] = []

  // 初始化
  async mounted () {
    const data = masterReq(this.userId)
    const result = await SearchInfo(table.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      this.itemId = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectCode) {
          this.projectCode = fields[j].values[0].value // 项目Code
        }
        if (fields[j].field_id === field.projectId) { // 项目ID
          this.projectId = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000151011510) { // 需补款
          this.proposalMoney = fields[j].values[0].value
        }
      }
    }
    if (this.proposalMoney !== 0) {
      this.proposalStatus = true
    }
    if (this.projectId !== '' || this.projectCode !== '') {
      this.collectStatus = true
    }
    if (this.collectStatus === true) {
      this.searchCollect() // 查询收款表
      this.searchProposal() // 查询报价单
    }
  }

  // 查询收款表
  async searchCollect () {
    this.collectList = []
    let pName: any = ''
    let pType: any = ''
    let cType: any = ''
    let cMoney: any = ''
    const result1 = await SearchInfo(table.collectTable, getCollect(this.projectId))
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.pName) {
          pName = fields[j].values[0].title
        }
        if (fields[j].field_id === field.pType) {
          pType = fields[j].values[0].name
        }
        if (fields[j].field_id === field.cType) {
          cType = fields[j].values[0].name
        }
        if (fields[j].field_id === field.cMoney) {
          cMoney = fields[j].values[0].value
        }
      }
      const obj = {
        id: i,
        pName: pName,
        pType: pType,
        cType: cType,
        cMoney: cMoney
      }
      this.collectList.push(obj)
    }
  }

  // 查询数据 报价单
  async searchProposal () {
    let proposalId: any = ''
    let type: any = ''
    let schemeMoney: any = ''
    let Received: any = ''
    let receivable: any = ''
    let discount: any = ''
    this.proposalList = []
    const result1 = await SearchInfo(table.proposal, getProposal(this.projectId))
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      proposalId = result1[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000180589754) {
          type = fields[j].values[0].name
        }
        if (fields[j].field_id === 2200000180589756) {
          schemeMoney = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000180591044) {
          Received = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000180589757) {
          receivable = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000180589758) {
          discount = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000197781040) {
          const values = fields[j].values
          for (let k = 0; k < values.length; k++) {
            this.fileList.push(values[k].file_id)
          }
        }
      }
      const obj = {
        id: i,
        proposalId: proposalId,
        type: type,
        schemeMoney: schemeMoney,
        Received: Received,
        receivable: receivable,
        discount: discount
      }
      this.proposalList.push(obj)
    }
  }

  // 新增报价单
  async proposalCreate () {
    this.dataList = []
    const req = {
      fields: {
        2200000180589754: [1],
        2200000180589755: [this.itemId],
        2200000180591563: [1],
        2200000203196675: this.fileList
      }
    }
    const result = await addInfo(table.proposal, req)
    await logInsert('新增报价单')
    let type: any = ''
    let schemeMoney: any = ''
    let Received: any = ''
    let receivable: any = ''
    let discount: any = ''
    const fields = result.fields
    const proposalId = result.item_id
    for (let j = 0; j < fields.length; j++) {
      if (fields[j].field_id === 2200000180589754) {
        type = fields[j].values[0].name
      }
      if (fields[j].field_id === 2200000180589756) {
        schemeMoney = fields[j].values[0].value
      }
      if (fields[j].field_id === 2200000180591044) {
        Received = fields[j].values[0].value
      }
      if (fields[j].field_id === 2200000180589757) {
        receivable = fields[j].values[0].value
      }
      if (fields[j].field_id === 2200000180589758) {
        discount = fields[j].values[0].value
      }
      if (fields[j].field_id === 2200000197781040) {
        const values = fields[j].values
        for (let k = 0; k < values.length; k++) {
          this.fileList.push(values[k].file_id)
        }
      }
    }
    const obj = {
      proposalId: proposalId,
      type: type,
      schemeMoney: schemeMoney,
      Received: Received,
      receivable: receivable,
      discount: discount
    }
    this.dataList.push(obj)
    this.proposalFromShow = true
  }

  collectCreate () {
    this.collectFromShow = true
  }

  errorInfo (str: any) {
    this.loadVisible = false
    this.visible = true
    this.errorMsg = str
  }

  closeClick () {
    this.$emit('close')
  }

  closeCollectFrom () {
    this.searchCollect()
    this.collectFromShow = false
  }

  closeProposalFrom () {
    this.searchProposal()
    this.proposalFromShow = false
  }

  closeModal () {
    this.visible = false
  }
}
</script>
<style scoped>
.schemeTable tr td {
  padding-right: 10px;
}
</style>
