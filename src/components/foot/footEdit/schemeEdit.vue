<template>
  <div>
    <div class="floatDiv" v-show="upload"></div>
    <div :class="upload ? 'infoDiv' : ''">
      <div class="headerDiv">上传方案</div>
      <div class="uploadFile">
        <input type="file" name="file" placeholder="请选择文件" />
      </div>
      <div class="buttonSite">
        <input
          class="saveButton"
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
import {
  SearchInfo,
  uploadFile,
  updateTable,
  batchAddPlan,
  getCoordinate
} from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  erronProduct: any[] = [];

  @Prop({
    type: Boolean,
    required: true,
    default: ''
  })
  upload!: any;

  // 获取所有的产品信息
  async saveClick () {
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
    file = file.files[0]
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
            query: { or: [{ in: [projectName] }] },
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
        updateTable(projectId, data)
      }
    }

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
        const fields = result2[j].fields
        const itemId = result2[j].item_id
        const code = fields[2].values[0].value // 产品条码
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
    batchAddPlan(table.customerPlan, json)
    const data = {
      fields: {
        [field.uploadCode]: res[0].orderNumber + ',' + uploadCode
      }
    }
    updateTable(projectId, data)
    this.$emit('close')
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
