<template>
  <div>
    <div class="headerDiv">群绑定</div>
    <table class="EditTable">
      <tr>
        <td>项目信息</td>
        <td>
          <input id="name" type="text" />
          <button class="img" @click="search">查询</button>
        </td>
      </tr>
      <tr>
        <td>项目名称</td>
        <td>
          <select id="projectCustom" style="max-width: 300px">
            <option
              v-for="project in projectList"
              :key="project.itemId"
              :value="project.itemId"
            >
              {{ project.projectCustom }}
            </option>
          </select>
        </td>
      </tr>
    </table>
    <div class="buttonSite">
      <button class="saveButton" @click="save()">绑定</button>
      <button class="closeButton" @click="close()">关闭</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable, logInsert } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectList: any[] = [];
  chatID = localStorage.getItem('chatID');
  async mounted () {
    const data = {
      where: {
        and: [{ field: field.masterProject, query: { in: [1] } }]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, data)
    this.setProjectList(result)
  }

  // 保存
  async save () {
    const projectCustom: any = document.getElementById('projectCustom')
    const itemId = projectCustom.options[projectCustom.selectedIndex].value
    const data = {
      fields: {
        [field.ChatId]: this.chatID // 更新userID
      }
    }
    await updateTable(itemId, data)
    await logInsert('群绑定')
    this.$emit('close')
  }

  // 关闭
  close () {
    this.$emit('loadPage')
  }

  // 查询按钮
  async search () {
    const name: any = document.getElementById('name')
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [name.value] }] },
            query_option_mappings: [-1],
            field: field.projectCustom // 项目名称
          },
          { field: field.masterProject, query: { in: [1] } }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, data)
    this.setProjectList(result)
  }

  setProjectList (result: any) {
    this.projectList = []
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const itemId = result[i].item_id
      let projectCustom = ''
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectCustom) {
          projectCustom = fields[j].values[0].value
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

<style scoped>
.img {
  position: absolute;
  right: 45px;
  width: 45px;
  height: 31px;
  margin-top: 0px;
}
</style>
