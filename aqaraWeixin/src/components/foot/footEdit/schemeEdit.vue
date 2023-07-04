<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">上传方案</div>
      <table class="EditTable">
        <tr>
          <td>方案类型</td>
          <td>
            <select id="schemeType">
              <option value="1">表格文件</option>
              <option value="2">pdf文件</option>
            </select>
          </td>
        </tr>
      </table>
      <table class="EditTable">
        <tr v-for="item in upFiles" :key="item.file_id">
          <td>方案名称</td>
          <td>
            {{ item.name }}
          </td>
        </tr>
      </table>
      <div class="uploadFile">
        <input id="file" type="file" name="file" placeholder="请选择文件" />
      </div>
      <div class="buttonSite">
        <button class="saveButton" @click="saveClick()" v-show="saveStatus">提交</button>
        <button class="closeButton" @click="closeClick()">关闭</button>
      </div>
    </div>
    <my-Modal :visible="visible" :modalText="modalText" @close="closeModal()"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, uploadFile, updateTable, batchAddPlan, getCoordinate, uploadPdf, logInsert } from '@/config/interFace'
import { table, field } from '@/config/config'
import { masterReq } from '@/config/common'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'
@Component({
  name: 'schemeEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  saveStatus = true;
  userId = localStorage.getItem('userId');
  itemId = '';
  fileList: any[] = [];
  upFiles: any[] = [];
  visible = false
  modalText = ''
  loadVisible = false

  async mounted () {
    const data = masterReq(this.userId)
    const result = await SearchInfo(table.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      this.itemId = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000146039443) {
          const values = fields[j].values
          this.upFiles = values
          for (let k = 0; k < values.length; k++) {
            this.fileList.push(values[k].file_id)
          }
        }
      }
    }
  }

  async pdfUpFile () {
    let file: any = document.getElementById('file')
    if (typeof file.files[0] === 'undefined') {
      this.errorInfo('请选择PDF文件！')
      return
    }
    this.uploadStart()
    file = file.files[0]
    const formData = new FormData()
    formData.append('source', file)
    formData.append('name', file.name)
    formData.append('domain', 'app.huoban.com')
    formData.append('type', 'attachment')
    const res = await uploadPdf(formData)
    this.fileList.push(res.file_id)
    const data1 = {
      fields: {
        2200000146039443: this.fileList
      }
    }
    await updateTable(this.itemId, data1)
    this.errorInfo('上传成功！')
  }

  // 获取所有的产品信息
  async saveClick () {
    let schemeType: any = document.getElementById('schemeType')
    schemeType = schemeType.options[schemeType.selectedIndex].value
    // 判断是否pdf上传
    if (schemeType === '2') {
      await this.pdfUpFile()
      return
    }
    let projectName = ''
    let projectId = ''
    let projectAddress = ''
    // let uploadCode = ''
    const productCode = []
    const json: any = {
      items: []
    }
    const formData = new FormData()
    // 拼接伙伴云JSON
    let file: any = document.getElementsByName('file')[0]
    if (typeof file.files[0] === 'undefined') {
      this.errorInfo('请选择EXCEL文件！')
      return
    }
    this.uploadStart()
    file = file.files[0]
    formData.append('file', file, file.name)
    const res = await uploadFile(formData, '/file/upload') // 1
    for (let i = 0; i < res.length; i++) {
      if (i === 0) {
        projectName = res[i].projectName
      }
      productCode.push(res[i].productCode)
    }
    // 查询伙伴云是否有该项目
    const obj1 = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [projectName] }] },
            query_option_mappings: [-1],
            field: 2200000150460774
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result1 = await SearchInfo(table.projectInfo, obj1) // 2
    // 获取地址信息
    if (result1.length !== 0) {
      projectId = result1[0].item_id
      const fields = result1[0].fields
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectAddress) {
          const values = fields[i].values[0].value
          projectAddress = values
        }
        // if (fields[i].field_id === field.uploadCode) {
        //   const values = fields[i].values[0].value
        //   uploadCode = values
        // }
      }
    } else {
      this.errorInfo('项目信息不存在！')
      return
    }
    if (projectAddress === '') {
      this.errorInfo('项目地址不存在！')
      return
    }
    // 查询伙伴云是否存在产品
    const obj2 = {
      where: {
        and: [
          {
            query: {
              or: [
                { eqm: productCode }
              ]
            },
            query_option_mappings: [-1],
            field: field.productCode
          }
        ]
      },
      offset: 0,
      limit: 1000
    }
    const result2 = await SearchInfo(table.productTable, obj2)
    // 检查产品数量
    if (productCode.length !== res.length) {
      this.errorInfo('请检查上传文件中的产品信息！')
      return
    }
    // 导入伙伴云数据
    for (let i = 0; i < res.length; i++) {
      for (let j = 0; j < result2.length; j++) {
        const itemId = result2[j].item_id
        const code = result2[j].fields[2].values[0].value // 产品条码
        if (res[i].productCode === code) {
          if (res[i].money === '0.00') {
            const obj = {
              fields: {
                [field.orderNumber]: res[i].orderNumber,
                [field.productItemId]: [itemId],
                [field.projectItemId]: [projectId],
                [field.primaryNumer]: res[i].number,
                [field.number]: res[i].number,
                [field.money]: res[i].money,
                [field.serviceFee]: res[i].serviceFee,
                [field.present]: [1]
              }
            }
            json.items.push(obj)
          } else {
            const obj = {
              fields: {
                [field.orderNumber]: res[i].orderNumber,
                [field.productItemId]: [itemId],
                [field.projectItemId]: [projectId],
                [field.primaryNumer]: res[i].number,
                [field.money]: res[i].money,
                [field.number]: res[i].number,
                [field.serviceFee]: res[i].serviceFee
              }
            }
            json.items.push(obj)
          }
        }
      }
      if (json.items.length % 5 === 0) {
        await batchAddPlan(table.customerPlan, json)
        json.items = []
      }
    }
    this.errorInfo('上传成功！')
    if (json.items.length !== 0) {
      await batchAddPlan(table.customerPlan, json) // 新增
    }
    await logInsert('上传方案')
  }

  uploadStart () {
    this.visible = false
    this.saveStatus = false
    this.loadVisible = true
  }

  errorInfo (str: any) {
    this.visible = true
    this.loadVisible = false
    this.modalText = str
  }

  closeClick () {
    this.$emit('close')
  }

  closeModal () {
    this.loadVisible = false
    this.visible = false
  }
}

</script>
