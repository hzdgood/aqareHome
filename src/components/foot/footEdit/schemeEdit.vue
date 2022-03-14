<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">上传方案</div>
      <input type="file" name="file" placeholder="请选择文件" />
      <div>
        <input type="button" @click="saveClick()" value="提交" />
        <input type="button" @click="closeClick()" value="关闭" />
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import {
  uploadFile,
  SearchInfo,
  filterInfo,
  batchAddPlan
} from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  productTable = table.productTable;
  customerPlan = table.customerPlan;
  // 获取所有的产品信息
  async saveClick () {
    const formData = new FormData()
    let file: any = document.getElementsByName('file')[0]
    file = file.files[0]
    formData.append('file', file, file.name)
    const res = await uploadFile(formData)
    // 获取项目名称 获取产品名称
    let projectName = ''
    const productName = []
    let projectId = ''
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
    if (result.item_id) {
      projectId = result[0].item_id
    } else {
      alert('项目信息不存在！')
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
      limit: 100
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
        }
      }
    }
    batchAddPlan(this.customerPlan, json)
    this.$emit('closeScheme')
  }

  closeClick () {
    this.$emit('closeScheme')
  }
}
</script>
