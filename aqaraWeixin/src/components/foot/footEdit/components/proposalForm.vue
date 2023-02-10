<template>
  <div>
    <div class='floatDiv'></div>
    <div class='infoDiv'>
      <div class='headerDiv'>报价单</div>
      <div v-for='item in dataList' :key='item.id'>
        <table class='EditTable'>
          <tr>
            <td>订单类型</td>
            <td><input type='text' :value='item.type' readonly /></td>
          </tr>
          <tr>
            <td>方案金额</td>
            <td><input type='text' :value='item.schemeMoney' readonly /></td>
          </tr>
          <tr>
            <td>优惠金额</td>
            <td><input id='discount' type='text' :value='item.discount' /></td>
          </tr>
          <tr>
            <td>应收金额</td>
            <td><input type='text' :value='item.receivable' readonly /></td>
          </tr>
          <tr>
            <td>已收金额</td>
            <td><input type='text' :value='item.Received' readonly /></td>
          </tr>
          <tr>
            <td>上传文件</td>
            <td>
              <input id='file' type='file' accept='image/*' placeholder='请选择文件' multiple/>
            </td>
          </tr>
        </table>
        <div class='buttonSite'>
          <button class='saveButton' @click="uploadFile()">上传合同</button>
          <button class='saveButton' @click='synchroClick(item)'>同步</button>
          <button class='saveButton' @click='saveClick(item)'>保存</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { table } from '@/config/config'
import { SearchInfo, updateTable, uploadImg, logInsert } from '@/config/interFace'
import { getProposal } from '@/config/common'

@Component({
  name: 'ProposalEdit'
})
export default class Home extends Vue {
  proposalId = ''
  dataList: any[] = []
  type = ''
  schemeMoney = ''
  discount = ''
  receivable = ''
  Received = ''
  fileList: any = []
  visible = false
  loadVisible = false
  errorMsg = ''

  @Prop({
    type: Object,
    required: true
  })
  projectId: any

  @Prop({
    type: Object,
    required: true
  })
  userId: any

  @Prop({
    type: Object,
    required: true
  })
  itemId: any

  // 查询数据
  async search () {
    // 查询报价单
    this.dataList = []
    const result1 = await SearchInfo(table.proposal, getProposal(this.projectId))
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      this.proposalId = result1[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000180589754) {
          const values = fields[j].values[0].name
          this.type = values
        }
        if (fields[j].field_id === 2200000180589757) {
          const values = fields[j].values[0].value
          this.schemeMoney = values
        }
        if (fields[j].field_id === 2200000180591044) {
          const values = fields[j].values[0].value
          this.Received = values
        }
        if (fields[j].field_id === 2200000180589759) {
          const values = fields[j].values[0].value
          this.receivable = values
        }
        if (fields[j].field_id === 2200000180589758) {
          const values = fields[j].values[0].value
          this.discount = values
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
        proposalId: this.proposalId,
        type: this.type,
        schemeMoney: this.schemeMoney,
        Received: this.Received,
        receivable: this.receivable,
        discount: this.discount
      }
      this.dataList.push(obj)
    }
  }

  // 保存
  async saveClick (item: any) {
    const discount: any = document.getElementById('discount')
    const data = {
      fields: {
        2200000180589758: discount.value,
        2200000203196675: this.fileList
      }
    }
    await updateTable(item.proposalId, data)
    await logInsert('报价单修改')
    this.errorInfo('提交报价单成功！')
  }

  // 同步
  async synchroClick (item: any) {
    const data = {
      fields: {
        2200000180589754: [1],
        2200000180591563: [1]
      }
    }
    await updateTable(item.proposalId, data)
    await logInsert('报价单同步')
    this.errorInfo('同步报价单完成！')
  }

  async uploadFile () {
    const file: any = document.getElementById('file')
    if (typeof file.files !== 'undefined') {
      for (let i = 0; i < file.files.length; i++) {
        const files = file.files[i]
        const formData = new FormData()
        formData.append('source', files)
        formData.append('name', files.name)
        formData.append('domain', 'app.huoban.com')
        formData.append('type', 'attachment')
        const res = await uploadImg(formData)
        this.fileList.push(res.file_id)
      }
    }
  }

  errorInfo (str: any) {
    this.loadVisible = false
    this.visible = true
    this.errorMsg = str
  }

  closeClick () {
    this.$emit('close')
  }

  // // 新增报价单
  // async create () {
  //   const obj = {
  //     fields: {
  //       2200000180589754: [1],
  //       2200000180589755: [this.itemId],
  //       2200000180591563: [1],
  //       2200000203196675: this.fileList
  //     }
  //   }
  //   await addInfo(table.proposal, obj)
  //   this.errorInfo('新增成功！')
  //   await logInsert('新增报价单')
  // }
}
</script>
