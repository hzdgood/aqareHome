<template>
  <div>
    请选择客户名称进行绑定
    <input id="name" type="text" />
    <input type="button" value="查询" @click="search()" />
    <div>
      <select id="customName">
        <option
          v-for="customList in customList"
          :key="customList.id"
          :value="customList.id"
        >
          {{ customList.name }}
        </option>
      </select>
      <input type="button" value="保存" @click="save()">
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field, user } from '@/config/config'
export default class Home extends Vue {
  customList: any[] = [];
  projectInfo = table.projectInfo;
  userId = user.userId;
  async search () {
    const name: any = document.getElementById('name')
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [name.value] }] },
            query_option_mappings: [-1],
            field: 2200000144967973 // 客户名称
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const id = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[i].field_id === field.projectCustom) {
          const name = fields[i].values[0].value
          const obj = {
            id: id,
            name: name
          }
          this.customList.push(obj)
        }
      }
    }
  }

  async save () {
    const customName: any = document.getElementById('customName')
    const itemId = customName.options[customName.selectedIndex].value
    const data = {
      [field.salesperson]: this.userId
    }
    await updateTable(itemId, data)
  }
}
</script>

<style>
</style>
