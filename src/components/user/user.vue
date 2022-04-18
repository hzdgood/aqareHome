<template>
  <div id="app">
    <span class="headDiv">ID: {{ userName }}</span>
    <span class="headDiv">客户姓名:{{ name }}</span>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  userName = localStorage.getItem('userName')
  userId = localStorage.getItem('userId')
  name = ''
  async mounted () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.customerInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.customerName) {
          const values = fields[j].values[0].value
          this.name = values
        }
      }
    }
  }
}
</script>
<style scoped></style>
