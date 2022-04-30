<template>
  <div>
    <projectEdit @reload="reloadFunction"></projectEdit>
    <div class="tag inline" v-if="currentTask.length != 0">
      目前任务
      <button v-for="item in currentTask" :key="'currentTask' + item.value">
        {{ item.name }}
      </button>
    </div>
    <div class="tag inline" v-if="projectType.length != 0">
      项目类型
      <button v-for="item in projectType" :key="'projectType' + item.value">
        {{ item.name }}
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Emit } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
import projectEdit from '@/components/infoEdit/projectEdit.vue'
@Component({
  components: {
    projectEdit: projectEdit
  }
})
export default class Home extends Vue {
  currentTask:any = []
  projectType:any = []
  async mounted () {
    this.loadFunction()
  }

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
  @Emit()
  async reloadFunction () {
    setTimeout(this.loadFunction, 1000)
  }
}
</script>
<style></style>
