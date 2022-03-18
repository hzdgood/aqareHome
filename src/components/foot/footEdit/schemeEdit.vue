<template>
  <div>
    <div class="floatDiv" v-show="upload"></div>
    <div :class="upload ? 'infoDiv' : ''">
      <div class="headerDiv">上传方案</div>
      <input type="file" name="file" placeholder="请选择文件" style="max-width: 200px;" />
      <div>
        <input type="button" @click="saveClick()" value="提交" />
        <input type="button" @click="closeClick()" value="关闭" />
      </div>
      <div>
        <span v-for="erronProduct in erronProduct" :key="erronProduct.index">
          {{ erronProduct.name }}
        </span>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Prop, Vue } from 'vue-property-decorator'
import {
  uploadFile,
  SearchInfo,
  filterInfo,
  batchAddPlan,
  updateTable,
  getCoordinate
} from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  productTable = table.productTable;
  customerPlan = table.customerPlan;
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
    const productName = []
    let projectAddress = ''
    let projectId = ''
    const formData = new FormData()
    this.erronProduct = []

    let file: any = document.getElementsByName('file')[0]
    file = file.files[0]
    formData.append('file', file, file.name)
    const res = await uploadFile(formData)
    // 获取项目名称 获取产品名称

    for (let i = 0; i < res.length; i++) {
      if (i === 0) {
        projectName = res[i].projectName
        productName.push(res[i].productName)
      } else {
        productName.push(res[i].productName)
      }
    }
    // 查询伙伴云是否存在产品
    const data = {
      search: {
        fields: [],
        keywords: [projectName.substring(4, projectName.length - 1)]
      },
      offset: 0,
      limit: 20
    }
    const result = await filterInfo(this.projectInfo, data)
    if (result.length !== 0) {
      projectId = result[0].item_id
      const fields = result[0].fields
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectAddress) {
          const values = fields[i].values[0].value
          projectAddress = values
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
    // 获取产品信息
    const data1 = {
      where: {
        and: [
          {
            query: {
              or: [
                {
                  eqm: productName
                }
              ]
            },
            query_option_mappings: [-1],
            field: field.productName
          }
        ]
      },
      offset: 0,
      limit: 1000
    }
    const result1 = await SearchInfo(this.productTable, data1)
    // 拼接伙伴云JSON
    const json: any = {
      items: []
    }
    for (let i = 0; i < res.length; i++) {
      const number = res[i].number
      const money = res[i].money
      const serviceFee = res[i].serviceFee
      for (let j = 0; j < result1.length; j++) {
        const fields = result1[j].fields
        const itemId = result1[j].item_id
        const name = fields[0].values[0].value
        if (res[i].productName === name) {
          const obj = {
            fields: {
              [field.productItemId]: [itemId],
              [field.projectItemId]: [projectId],
              [field.money]: money,
              [field.number]: number,
              [field.serviceFee]: serviceFee
            }
          }
          json.items.push(obj)
          j = result1.length
        } else {
          if (j === result1.length - 1) {
            if (res[i].productName !== name) {
              const obj = {
                index: i,
                name: res[i].productName
              }
              this.erronProduct.push(obj)
            }
          }
        }
      }
    }
    if (this.erronProduct.length > 0) {

    } else {
      if (projectAddress !== '') {
        const obj = await getCoordinate([projectAddress])
        if (obj.lng === 0 && obj.lat === 0) {
          const obj = {
            index: 0,
            name: '项目地址错误！'
          }
          this.erronProduct.push(obj)
        } else {
          const data = {
            fields: {
              2200000150942406: obj.lng,
              2200000150942407: obj.lat
            }
          }
          updateTable(projectId, data)
          batchAddPlan(this.customerPlan, json)
          this.$emit('close')
        }
      }
    }
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
