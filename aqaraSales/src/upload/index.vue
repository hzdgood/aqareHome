<template>
  <div>
    <div class="headerDiv">上传方案</div>
    <div class="uploadScheme">
      <input id="file" type="file" name="file" placeholder="请选择文件" />
      <button class="saveButton" @click="saveClick">方案导入</button>
    </div>
    <my-Modal :visible="visible" :modalText="modalText" @close="closeModal()"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, uploadFile, updateTable, batchAddPlan, logInsert, getCoordinate } from '@/config/interFace'
import { table, field } from '@/config/config'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'

@Component({
  name: 'upload',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  visible = false
  modalText = ''
  loadVisible = false

  async uploadFile (req: any) {
    const formData = new FormData()
    let file: any = document.getElementsByName('file')[0]
    if (typeof file.files[0] === 'undefined') {
      return
    }
    file = file.files[0]
    formData.append('file', file, file.name)
    await uploadFile(formData, req)
  }

  async saveClick () {
    let projectName = ''
    let projectId = ''
    let projectAddress = ''
    let uploadCode = ''
    const productCode = []
    const formData = new FormData()
    const json: any = {
      items: []
    }

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
        if (fields[i].field_id === field.uploadCode) {
          const values = fields[i].values[0].value
          uploadCode = values
        }
      }
    } else {
      this.errorInfo('项目信息不存在！')
      return
    }
    const code = uploadCode.split(',')
    let status = true
    for (let i = 0; i < code.length; i++) {
      if (res[0].orderNumber === code[i]) {
        this.errorInfo('该方案已上传，请勿重复上传！')
        status = false
      }
    }
    if (!status) {
      return
    }
    // 检查地址信息，赋值经纬度
    if (projectAddress !== '') {
      const obj = await getCoordinate([projectAddress]) // 3
      if (obj.lng === 0 && obj.lat === 0) {
        this.errorInfo('项目地址错误！')
        return
      } else {
        const data = {
          fields: {
            [field.X]: obj.lng,
            [field.Y]: obj.lat
          }
        }
        await updateTable(projectId, data) // 4
      }
    } else {
      this.errorInfo('请填写项目地址信息！')
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
    }
    const data = {
      fields: {
        [field.uploadCode]: res[0].orderNumber + ',' + uploadCode
      }
    }
    await updateTable(projectId, data) // 修改
    await batchAddPlan(table.customerPlan, json) // 新增
    await logInsert('上传方案')
    this.errorInfo('上传成功！')
  }

  errorInfo (str: any) {
    this.visible = true
    this.loadVisible = false
    this.modalText = str
  }

  uploadStart () {
    this.visible = false
    this.loadVisible = true
  }

  closeModal () {
    this.visible = false
  }
}
</script>
<style scoped>
.uploadScheme {
  width: 100%;
  text-align: center;
  margin-top: 30px;
}
.headerDiv {
  height: 70px;
  background-color: aquamarine;
}
</style>
