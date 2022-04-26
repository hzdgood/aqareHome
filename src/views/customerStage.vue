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
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  tagInfo = table.tagInfo;
  customerInfo = table.customerInfo;
  customStage: any[] = [];
  taglist: any[] = [];
  itemId: any = '';
  userId = localStorage.getItem('userId');

  // 获取所有客户标签
  async mounted () {
    const data = {
      where: {
        and: [{ field: 2200000182035321, query: { in: [2300006607764731] } }]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000182035321, sort: 'desc' }]
    }
    const result = await SearchInfo(this.tagInfo, data)
    this.taglist = result
    for (let i = 0; i < result.length; i++) {
      const name = result[i].fields[0].values[0].title
      const value = result[i].fields[1].values[0].value
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

  // 获取当前客户阶段
  async getCustomerTag () {
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
      limit: 20,
      order_by: [{ field: field.userTable, sort: 'desc' }]
    }
    const res = await SearchInfo(this.customerInfo, data)
    if (res.length === 0) {
      return
    }
    this.itemId = res[0].item_id
    const fields = res[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.customerStage) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
    }
  }

  // 选择
  onclick = (items: any) => {
    // 获取选中对象 删除class
    const obj: any = document.getElementsByClassName('selected')
    for (let i = 0; i < obj.length; i++) {
      const id = obj[i].id
      const dom: any = document.getElementById(id)
      dom.className = ''
    }
    // 设置新的选中对象
    const dom: any = document.getElementById(items.id)
    dom.className = 'selected'
    // 发送请求
    this.updateData(items)
  };

  // 更新
  async updateData (items: any) {
    const objs: any = {
      fields: {
        [items.field]: [parseInt(items.id)]
      }
    }
    await updateTable(this.itemId, objs)
  }
}
</script>
<style scoped></style>
