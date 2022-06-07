<template>
    <div>
        {{ errorMsg }}
    </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, externalList } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  errorMsg = ''

  async mounted () {
    // 先要删除全部的人员关系数据
    // 后台提供delete方案
    // main提供入口

    // 第一步获取所有销售人员信息
    const data = {}
    const saleList = []
    const result = await SearchInfo(table.saleManInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.localName) {
          saleList.push(fields[j].values[0].value)
        }
      }
    }
    console.log(saleList)
    // 第二部查询销售人员对应的客户
    for (let i = 0; i < saleList.length; i++) {
      await externalList({
        userId: saleList[i]
      })
    }
  }
}
</script>

<style></style>
