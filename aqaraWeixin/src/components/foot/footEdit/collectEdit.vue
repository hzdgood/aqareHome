<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增收款</div>
      <button class='saveButton' @click='proposalCreate()'>生成报价</button>
      <button class='saveButton'>新增收款</button>
      <button class="closeButton" @click="closeClick()">关闭</button>
      <div>
        <table class='EditTable' v-for='item in collectList' :key='item.id'>
          <tr>
            <td>项目名称</td>
            <td></td>
          </tr>
          <tr>
            <td>收款类型</td>
            <td></td>
          </tr>
          <tr>
            <td>报价单</td>
            <td></td>
          </tr>
          <tr>
            <td>收款方式</td>
            <td></td>
          </tr>
          <tr>
            <td>收款金额</td>
            <td></td>
          </tr>
        </table>
      </div>

      <div>
        <table class='EditTable' v-for='item in proposalList' :key='item.id'>
          <tr>
            <td>订单类型</td>
            <td></td>
          </tr>
          <tr>
            <td>方案金额</td>
            <td></td>
          </tr>
          <tr>
            <td>优惠金额</td>
            <td></td>
          </tr>
          <tr>
            <td>应收金额</td>
            <td></td>
          </tr>
          <tr>
            <td>已收金额</td>
            <td></td>
          </tr>
        </table>
      </div>
    </div>
    <my-Modal :visible="visible" :modalText="errorMsg"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { masterReq, getProposal, getCollect } from '@/config/common'
import { SearchInfo, addInfo, logInsert } from '@/config/interFace'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'
@Component({
  name: 'collectEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  collectList: any[] = []
  proposalList: any[] = []
  projectCode = ''
  projectId = ''
  errorMsg = ''
  itemId = ''
  proposalMoney = ''
  visible = false
  loadVisible = false
  userId = localStorage.getItem('userId');
  fileList: any = []

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
          const values = fields[j].values[0].value
          this.proposalMoney = values
        }
      }
    }
  }

  async searchCollect () {
    const result1 = await SearchInfo(table.collectTable, getCollect(this.projectId))
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      for (let j = 0; j < fields.length; j++) {

      }
      const obj = {
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
    const result1 = await SearchInfo(table.proposal, getProposal(this.projectId))
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      proposalId = result1[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000180589754) {
          const values = fields[j].values[0].name
          type = values
        }
        if (fields[j].field_id === 2200000180589757) {
          const values = fields[j].values[0].value
          schemeMoney = values
        }
        if (fields[j].field_id === 2200000180591044) {
          const values = fields[j].values[0].value
          Received = values
        }
        if (fields[j].field_id === 2200000180589759) {
          const values = fields[j].values[0].value
          receivable = values
        }
        if (fields[j].field_id === 2200000180589758) {
          const values = fields[j].values[0].value
          discount = values
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
    const obj = {
      fields: {
        2200000180589754: [1],
        2200000180589755: [this.itemId],
        2200000180591563: [1],
        2200000203196675: this.fileList
      }
    }
    await addInfo(table.proposal, obj)
    await logInsert('新增报价单')
    this.errorInfo('新增报价单完成！')
  }

  errorInfo (str: any) {
    this.loadVisible = false
    this.visible = true
    this.errorMsg = str
  }

  closeClick () {
    this.$emit('close')
  }

  // 收款
  // async saveClick () {
  //   this.loadVisible = true
  //   const req = getLocalSale(this.localName)
  //   const result = await SearchInfo(table.saleManInfo, req)
  //   let salesId = ''
  //   if (result.length === 0) {
  //     this.errorInfo('找不到当前销售人员信息！' + this.localName)
  //     return
  //   }
  //   for (let i = 0; i < result.length; i++) {
  //     salesId = result[0].item_id
  //   }
  //   let projectType: any = document.getElementById('projectType')
  //   projectType = projectType.options[projectType.selectedIndex].value
  //   let collectType: any = document.getElementById('collectType')
  //   collectType = collectType.options[collectType.selectedIndex].value
  //   const file: any = document.getElementById('file')
  //   if (this.collectMoney === '') {
  //     this.errorInfo('请输入金额!')
  //     return
  //   }
  //   if (typeof file.files[0] === 'undefined') {
  //     this.errorInfo('请上传图片!')
  //     return
  //   }
  //   // 上传图片
  //   if (projectType === '1') {
  //     const list = await this.upfile(file)
  //     const data = {
  //       fields: {
  //         [field.pName]: [this.itemId],
  //         [field.pType]: [1],
  //         [field.cType]: [collectType],
  //         [field.cMoney]: this.collectMoney,
  //         [field.uploadFile]: list,
  //         2200000181625297: [salesId]
  //       }
  //     }
  //     const result = await addInfo(table.collectTable, data)
  //     this.run(result)
  //   } else {
  //     const list = await this.upfile(file)
  //     const data = {
  //       fields: {
  //         [field.pName]: [this.itemId],
  //         [field.pType]: [2],
  //         2200000180591045: [this.quotationId],
  //         [field.cType]: [collectType],
  //         [field.cMoney]: this.collectMoney,
  //         [field.uploadFile]: list,
  //         2200000181625297: [salesId]
  //       }
  //     }
  //     const result = await addInfo(table.collectTable, data)
  //     this.run(result)
  //   }
  //   await logInsert('收款')
  // }

  // 收款类型更改
  // async typeChange () {
  //   let projectType: any = document.getElementById('projectType')
  //   projectType = projectType.options[projectType.selectedIndex].value
  //   if (projectType === '2') {
  //     this.quotationStatus = true
  //     const result1 = await SearchInfo(table.proposal, getProposal(this.projectId))
  //     this.title = result1[0].title
  //     this.quotationId = result1[0].item_id
  //     const fields = result1[0].fields
  //     for (let i = 0; i < fields.length; i++) {
  //       if (fields[i].field_id === 2200000180589759) {
  //         const values = fields[i].values[0].value
  //         this.collectMoney = values
  //       }
  //     }
  //   } else if (projectType === '1') {
  //     this.quotationStatus = false
  //     this.collectMoney = ''
  //   }
  // }

  // 启动收款流程
  // async run (result: any) {
  //   var obj = {
  //     action: 'spec_item',
  //     data: { item: { item_id: result.item_id } }
  //   }
  //   await procedure('3000000000246006', obj)
  //   this.loadVisible = false
  // }
  // 报价单同步
  // async synchroClick (item: any) {
  //   this.loadVisible = true
  //   const data = {
  //     fields: {
  //       2200000180589754: [1],
  //       2200000180591563: [1]
  //     }
  //   }
  //   await updateTable(item.proposalId, data)
  //   await logInsert('报价单同步')
  //   this.errorInfo('同步报价单完成！')
  // }
  // 报价单保存
  // async saveProposal (item: any) {
  //   this.loadVisible = true
  //   const discount: any = document.getElementById('discount')
  //   const data = {
  //     fields: {
  //       2200000180589758: discount.value,
  //       2200000203196675: this.fileList
  //     }
  //   }
  //   await updateTable(item.proposalId, data)
  //   await logInsert('报价单修改')
  //   this.errorInfo('提交报价单成功！')
  // }
  // 报价单合同上传
  // async uploadFile () {
  //   const file: any = document.getElementById('fileUpload')
  //   if (typeof file.files !== 'undefined') {
  //     for (let i = 0; i < file.files.length; i++) {
  //       const files = file.files[i]
  //       const formData = new FormData()
  //       formData.append('source', files)
  //       formData.append('name', files.name)
  //       formData.append('domain', 'app.huoban.com')
  //       formData.append('type', 'attachment')
  //       const res = await uploadImg(formData)
  //       this.fileList.push(res.file_id)
  //     }
  //   }
  // }
  // 上传文件 获取 file_id
  // async upfile (file: any) {
  //   const list = []
  //   for (let i = 0; i < file.files.length; i++) {
  //     const files = file.files[i]
  //     const formData = new FormData()
  //     formData.append('source', files)
  //     formData.append('name', files.name)
  //     formData.append('domain', 'app.huoban.com')
  //     formData.append('type', 'attachment')
  //     const res = await uploadImg(formData)
  //     list.push(res.file_id)
  //   }
  //   return list
  // }

  // 检查数据
  // checkData (status: number) {
  //   if (status === 0) {
  //     if (this.projectCode === '') {
  //       this.errorInfo('请先添加项目！')
  //       // this.errorStatus = false
  //     }
  //   } else if (status === 1) {
  //     if (this.proposalMoney === '0' || this.proposalMoney === 0) {
  //       this.errorInfo('请先上传方案！')
  //       // this.errorStatus = false
  //     }
  //   }
  // }
}
</script>
<style scoped></style>
