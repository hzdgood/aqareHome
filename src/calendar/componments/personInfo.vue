<template>
  <div>
    <div v-for="item in personList" :key="item.id">{{ item.name }}</div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Actions extends Vue {
  personList: any[] = [];
  async mounted () {
    // 获取所有技术人员信息
    const obj = {
      where: { and: [{ field: 2200000145711106, query: { em: false } }] },
      offset: 0,
      limit: 30
    }
    const result = await SearchInfo('2100000015050396', obj)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const item_id = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000149382960) { // 名称
          const data = {
            id: i,
            name: fields[j].values[0].value
          }
          this.personList.push(data)
        }
      }
    }
  }

  click () {
    // 点击方法
    console.log(111)
  }

  // 全选方法
  allClick () {
    console.log(111)
  }
}
</script>

<style>
</style>
