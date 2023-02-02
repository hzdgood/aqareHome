<template>
  <div>
    <div class='floatDiv'></div>
    <div class='infoDiv'>
      <div class='headerDiv'>报价单</div>
      <div class='addSite' v-show='errorStatus'>
        <table class='EditTable'>
          <tr>
            <td>
              <input id='file' type='file' multiple accept='image/*' placeholder='请选择文件'/>
            </td>
            <button class='saveButton' @click="uploadFile()">上传合同</button>
          </tr>
        </table>
        <button class='saveButton' @click='create()' v-show='createStatus'>生成报价</button>
      </div>
      <div v-for='item in dataList' :key='item.id'>
        <table class='EditTable' v-show='errorStatus'>
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
        </table>
        <div class='buttonSite'>
          <button class='saveButton' @click='synchroClick(item)'>同步</button>
          <button class='saveButton' @click='saveClick(item)'>保存</button>
          <button class='closeButton' @click='closeClick()'>关闭</button>
        </div>
      </div>
      <div v-if='errorStatus == false'>
        <div class='buttonSite'>
          <button class='closeButton' @click='closeClick()'>关闭</button>
        </div>
      </div>
    </div>
    <my-Modal :visible='visible' :modalText='errorMsg'></my-Modal>
    <my-load :loadVisible='loadVisible'></my-load>
  </div>
</template>
<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field, collectType } from '@/config/config'
import { SearchInfo, addInfo, updateTable, uploadImg, logInsert } from '@/config/interFace'
import { masterReq } from '@/config/common'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'

@Component({
  name: 'ProposalEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  collectType = collectType
  projectId = ''
  errorMsg = ''
  errorStatus = false
  createStatus = true
  itemId = ''
  proposalId = ''
  userId = localStorage.getItem('userId')
  dataList: any[] = []
  type = ''
  schemeMoney = ''
  discount = ''
  receivable = ''
  Received = ''
  fileList: any = []
  loadVisible = false
  visible = false

  async mounted () {
    let money: any
    const obj1 = masterReq(this.userId)
    const result1 = await SearchInfo(table.projectInfo, obj1)
    if (result1.length === 0) {
      this.errorInfo('请先添加项目！')
      return
    }
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      this.itemId = result1[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000151011510) {
          // 需补款
          const values = fields[j].values[0].value
          money = values
        }
        if (fields[j].field_id === field.projectId) {
          const values = fields[j].values[0].value
          this.projectId = values
        }
      }
    }
    if (money === '0' || money === 0) {
      this.errorInfo('请先上传方案！')
      return
    }
    this.errorStatus = true
    this.search()
  }

  // 新增报价单
  async create () {
    if (this.fileList.length === 0) {
      this.errorInfo('请上传合同！')
      return
    }
    this.createStatus = false
    const obj = {
      fields: {
        2200000180589754: [1],
        2200000180589755: [this.itemId],
        2200000180591563: [1],
        2200000203196675: this.fileList
      }
    }
    await addInfo(table.proposal, obj)
    this.update('新增成功！')
    await logInsert('新增报价单')
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

  // 查询数据
  async search () {
    // 查询报价单
    this.dataList = []
    const obj1 = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [this.projectId] }] },
            query_option_mappings: [-1],
            field: 1102001110000000 // 项目ID
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result1 = await SearchInfo(table.proposal, obj1)
    if (result1.length !== 0) {
      this.createStatus = false
    }
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
    this.loadVisible = true
    const discount: any = document.getElementById('discount')
    const data = {
      fields: {
        2200000180589758: discount.value
      }
    }
    await updateTable(item.proposalId, data)
    await logInsert('报价单修改')
    this.errorInfo('提交成功！')
  }

  // 同步
  async synchroClick (item: any) {
    this.loadVisible = true
    const data = {
      fields: {
        2200000180589754: [1],
        2200000180591563: [1]
      }
    }
    await updateTable(item.proposalId, data)
    this.update('同步完成！')
  }

  errorInfo (str: any) {
    this.errorMsg = str
    this.loadVisible = false
    this.visible = true
  }

  // 更新
  update (str: String) {
    setTimeout(() => {
      this.search()
      this.errorInfo(str)
    }, 1000)
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
