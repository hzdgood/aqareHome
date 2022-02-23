<template>
  <div>
    <div v-if="customStage.length != 0">客户阶段</div>
    <div class="stageButton">
      <button
        v-for="item in customStage"
        :key="item.id"
        :id="item.id"
        :name="item.field"
        @click="onclick(item)"
      >
        {{ item.value }}
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field, user } from '@/config/config'
@Component({})
export default class Home extends Vue {
  tagInfo = table.tagInfo;
  customerInfo = table.customerInfo;
  ticket = localStorage.getItem('ticket');
  customStage: any[] = [];
  taglist: any[] = [];
  itemId: any = '';
  async mounted () {
    const data = {
      where: {
        and: [{ field: 2200000182035321, query: { in: [2300006479346393] } }]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.ticket, this.tagInfo, data)
    this.taglist = result
    for (let i = 0; i < result.length; i++) {
      const name = result[i].fields[0].values[0].title
      const value = result[i].fields[2].values[0].value
      const item_id = result[i].item_id
      const ob = {
        id: item_id,
        name: name,
        value: value,
        field: field.customerStage
      }
      this.customStage.push(ob)
    }
    this.getCustomerTag()
  }

  async getCustomerTag () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [user.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: field.userTable, sort: 'desc' }]
    }
    const res = await SearchInfo(this.ticket, this.customerInfo, data)
    this.itemId = res[0].item_id
    const fields = res[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.customerStage) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'select'
        }
      }
    }
  }

  onclick = (items: any) => {
    // 获取选中对象 删除class
    const obj: any = document.getElementsByClassName('select')
    for (let i = 0; i < obj.length; i++) {
      const id = obj[i].id
      const dom: any = document.getElementById(id)
      dom.className = ''
    }
    // 设置新的选中对象
    const dom: any = document.getElementById(items.id)
    dom.className = 'select'
    // 发送请求
    this.updateData(items)
  };

  // fields: {2200000184312981: [2300006479350198]}
  async updateData (items: any) {
    const objs: any = {
      fields: {}
    }
    var val = items.id
    objs.fields[items.field] = [parseInt(val)]
    const res = await updateTable(this.ticket, this.itemId, objs)
    console.log(res)
  }
}
</script>
<style scoped>
.stageButton button {
  text-align: center;
  border: 0px solid #bbb;
  margin: 0px 5px 5px 5px;
  background-color: rgb(230, 227, 227);
  font-size: 10px;
  color: cornflowerblue;
  padding: 0px 5px 0px 5px;
  border-radius: 5px;
}
.select {
  background-color: aquamarine !important;
}
</style>>
