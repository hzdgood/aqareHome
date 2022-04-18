<template>
  <div>
    <div class="headerDiv">群绑定</div>

    <span>群名称</span>
    <input id="name" type="text" />
    <input type="button" value="查询" @click="search()" />

    <div>
      <select id="projectCustom" style="max-width: 300px">
        <option
          v-for="project in projectList"
          :key="project.itemId"
          :value="project.itemId"
        >
          {{ project.projectCustom }}
        </option>
      </select>
    </div>
    <div>
      <input type="button" value="绑定" @click="save()" />
      <input type="button" value="关闭" @click="close()" />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field, user } from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectList: any[] = [];
  async mounted () {
    console.log(111)
    const result = await SearchInfo(table.projectInfo, {})
    this.setProjectList(result)
  }

  async save () {
    const projectCustom: any = document.getElementById('projectCustom')
    const itemId = projectCustom.options[projectCustom.selectedIndex].value
    const data = {
      fields: {
        [field.ChatId]: user.chatID // 更新userID
      }
    }
    await updateTable(itemId, data)
    this.$emit('close')
  }

  close () {
    this.$emit('close')
  }

  async search () {
    const name: any = document.getElementById('name')
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [name.value] }] },
            query_option_mappings: [-1],
            field: field.projectCustom // 项目名称
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, data)
    this.setProjectList(result)
  }

  setProjectList (result: any) {
    for (let j = 0; j < result.length; j++) {
      const fields = result[j].fields
      const itemId = result[j].item_id
      let projectCustom = ''
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectCustom) {
          projectCustom = fields[i].values[0].value
        }
      }
      const obj = {
        itemId: itemId,
        projectCustom: projectCustom
      }
      this.projectList.push(obj)
    }
  }
}
</script>

<style scoped></style>
