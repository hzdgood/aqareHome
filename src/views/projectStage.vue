<template>
  <div>
    <div>装修进度</div>
    <div class="stageButton">
      <button v-for="item in decorationStage" :key="item.value" :id="item.value">
        {{ item.name }}
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, decorationStage } from '@/config/config'
import { SearchInfo } from '@/config/interFace'

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

          // projectStage = fields[j].values[0].value
          // console.log(projectStage)
        }
      }
    }
  }
}
</script>
<style></style>
