<template>
  <div>
    <div>装修进度</div>
    <div class="stageButton">
      <button v-for="item in decorationStage" :key="item.value" :id="item.value" @click="onclick(item)">
        {{ item.name }}
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, decorationStage } from '@/config/config'
import { SearchInfo, updateTable } from '@/config/interFace'

@Component({})
export default class Home extends Vue {
  itemId: any = '';
  userId = localStorage.getItem('userId');
  decorationStage = decorationStage;

  async mounted () {
    // 获取当前用户客户装修阶段
    const chatId = localStorage.getItem('chatID')
    const obj = {
      where: {
        and: [
          {
            query: { or: [{ in: [chatId] }] },
            query_option_mappings: [-1],
            field: field.ChatId
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, obj)
    for (let i = 0; i < result.length; i++) {
      this.itemId = result[0].item_id
      const fields = result[0].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectStage) {
          console.log(fields[j].values[0])
        }
      }
    }
  }

  // 选中
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

  // 更新关系
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
<style></style>
