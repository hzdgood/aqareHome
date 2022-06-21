<template>
  <div>
    <div class="floatDiv" v-show="upload"></div>
    <div :class="upload ? 'infoDiv' : ''">
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
        <input
          class="saveButton"
          v-show="saveStatus"
          type="button"
          @click="saveClick()"
          value="提交"
        />
        <input
          class="closeButton"
          type="button"
          @click="closeClick()"
          value="关闭"
        />
      </div>
      <div>
        <span v-for="item in erronProduct" :key="item.index">
          {{ item.name }}
        </span>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Prop, Vue } from 'vue-property-decorator'
import { SearchInfo, uploadFile, updateTable, batchAddPlan, getCoordinate, logInsert, uploadPdf } from '@/config/interFace'

import { table, field } from '@/config/config'
import { masterReq } from '@/config/common'
@Component({})
export default class Home extends Vue {
  erronProduct: any[] = [];
  saveStatus = true;

  @Prop({
    type: Boolean,
    required: true,
    default: ''
  })
  upload!: any;

  userId = localStorage.getItem('userId');
  itemId = '';
  fileList: any[] = [];
  upFiles: any[] = [];

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
      alert('请上传PDF文件!')
      return
    }
    console.log(file)
    file = file.files[0]
    this.$store.dispatch('Loading')
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
    await logInsert('上传PDF成功')
    this.$store.dispatch('Loading')
    this.$emit('close')
  }

  // 获取所有的产品信息
  async saveClick () {
    let schemeType: any = document.getElementById('schemeType')
    schemeType = schemeType.options[schemeType.selectedIndex].value
    if (schemeType === '2') {
      await this.pdfUpFile()
      return
    }
    let projectName = ''
    let projectId = ''
    let projectAddress = ''
    let projectArea = ''
    let uploadCode = ''
    const productCode = []
    const json: any = {
      items: []
    }
    const formData = new FormData()
    this.erronProduct = []
    // 拼接伙伴云JSON
    let file: any = document.getElementsByName('file')[0]
    if (typeof file.files[0] === 'undefined') {
      alert('请上传表格!')
      return
    }
    file = file.files[0]
    this.saveStatus = false
    formData.append('file', file, file.name)
    const res = await uploadFile(formData)
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
    const result1 = await SearchInfo(table.projectInfo, obj1)
    // 获取地址信息
    if (result1.length !== 0) {
      projectId = result1[0].item_id
      const fields = result1[0].fields
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectAddress) {
          const values = fields[i].values[0].value
          projectAddress = values
        }
        if (fields[i].field_id === field.projectArea) {
          const values = fields[i].values[0].value
          projectArea = values
        }
        if (fields[i].field_id === field.uploadCode) {
          const values = fields[i].values[0].value
          uploadCode = values
        }
      }
    } else {
      const obj = {
        index: 0,
        name: '项目信息不存在！'
      }
      this.erronProduct.push(obj)
      return
    }
    const code = uploadCode.split(',')
    let status = true
    for (let i = 0; i < code.length; i++) {
      if (res[0].orderNumber === code[i]) {
        alert('该方案已上传，请勿重复上传！')
        status = false
      }
    }
    if (!status) {
      return
    }
    // 检查地址信息，赋值经纬度
    if (projectAddress !== '') {
      projectAddress = '上海市' + projectArea + projectAddress
      const obj = await getCoordinate([projectAddress])
      if (obj.lng === 0 && obj.lat === 0) {
        const obj = {
          index: 0,
          name: '项目地址错误！'
        }
        this.erronProduct.push(obj)
        return
      } else {
        const data = {
          fields: {
            [field.X]: obj.lng,
            [field.Y]: obj.lat
          }
        }
        await updateTable(projectId, data)
      }
    } else {
      alert('请填写项目地址信息！')
      return
    }
    this.$store.dispatch('Loading')
    // 查询伙伴云是否存在产品
    const obj2 = {
      where: {
        and: [
          {
            query: {
              or: [
                {
                  eqm: productCode
                }
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
    await batchAddPlan(table.customerPlan, json)
    const data = {
      fields: {
        [field.uploadCode]: res[0].orderNumber + ',' + uploadCode
      }
    }
    await updateTable(projectId, data)
    await logInsert('上传方案成功')
    this.$store.dispatch('Loading')
    this.$emit('close')
  }

  diff (arr1: any[], arr2: any[]) {
    var newArr = []
    newArr = arr1.filter((cur) => arr2.indexOf(cur) === -1)
    return newArr.concat(arr2.filter((cur) => arr1.indexOf(cur) === -1))
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
