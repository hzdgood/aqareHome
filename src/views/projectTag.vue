<template>
  <div>
    <table class="tagTable">
      <tr>
        <td>目前任务</td>
        <td>
          <button v-for="item in currentTask" :key="'currentTask' + item.value">
            {{ item.name }}
          </button>
        </td>
        <td>项目类型</td>
        <td>
          <button v-for="item in projectType" :key="'projectType' + item.value">
            {{ item.name }}
          </button>
        </td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  currentTask: any = [];
  projectType: any = [];
  async mounted () {
    this.loadFunction()
  }

  // 加载群标签
  async loadFunction () {
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
    if (result.length === 0) {
      return
    }
    const fields = result[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.projectType) {
        const obj = {
          name: fields[i].values[0].name,
          value: fields[i].values[0].id
        }
        this.projectType.push(obj)
      }
      if (fields[i].field_id === field.currentTask) {
        const obj = {
          name: fields[i].values[0].name,
          value: fields[i].values[0].id
        }
        this.currentTask.push(obj)
      }
    }
  }

  // 回调 tagEdit
  // @Emit()
  // async reloadFunction() {
  //   setTimeout(this.loadFunction, 1000);
  // }
}
</script>
<style></style>
