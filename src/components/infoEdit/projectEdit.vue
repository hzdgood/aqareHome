<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv" >
      <div class="headerDiv">群编辑</div>
      <div class="inline">
        <span>客户名称</span>
        <input id="customerName" type="text" />
      </div>
      <div class="inline">
        <span>客户电话</span>
        <input id="telephone" type="text" />
      </div>
      <div class="inline">
        <span>目前任务</span>
        <select id="projectProgress">
          <option
            v-for="item in projectProgress"
            :key="item.value"
            :value="item.value"
            name="目前任务"
          >
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="inline">
        <span>项目类型</span>
        <select id="projectType">
          <option
            v-for="item in projectType"
            :key="item.value"
            :value="item.value"
            name="项目类型"
          >
            {{ item.name }}
          </option>
        </select>
      </div>
      <div>
        <button @click="saveInfo()">保存</button>
        <button @click="close()">关闭</button>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, projectType, projectProgress } from '@/config/config'
import { SearchInfo, updateTable } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  projectType = projectType;
  projectProgress = projectProgress;
  userId = localStorage.getItem('userId');
  itemId: any = '';
  async showEdit () {
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
    this.itemId = result[0].item_id
    const fields = result[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.projectType) {
        const id = parseInt(fields[i].values[0].id)
        const projectType: any = document.getElementById('projectType')
        for (let i = 0; i < projectType.options.length; i++) {
          if (parseInt(projectType.options[i].value) === id) {
            projectType.options[i].selected = true
          }
        }
      }
      if (fields[i].field_id === field.currentTask) {
        const id = parseInt(fields[i].values[0].id)
        const currentTask: any = document.getElementById('projectProgress')
        for (let i = 0; i < currentTask.options.length; i++) {
          if (parseInt(currentTask.options[i].value) === id) {
            console.log(currentTask.options[i].value + '---' + id)
            currentTask.options[i].selected = true
          }
        }
      }
      if (fields[i].field_id === field.pname) {
        const values = fields[i].values
        const customerName: any = document.getElementById('customerName')
        customerName.value = values[0].value
      }
      if (fields[i].field_id === field.ptelephone) {
        const values = fields[i].values
        const telephone: any = document.getElementById('telephone')
        telephone.value = values[0].value
      }
    }
    this.close()
  }

  close () {
    console.log(111)
  }

  async saveInfo () {
    const customerName: any = document.getElementById('customerName')
    const telephone: any = document.getElementById('telephone')
    const currentTask: any = document.getElementById('currentTask')
    const projectType: any = document.getElementById('projectType')
    const data: any = {
      fields: {
        [field.pname]: customerName.value,
        [field.ptelephone]: telephone.value,
        [field.currentTask]: [currentTask.options[currentTask.selectedIndex].value],
        [field.projectType]: [projectType.options[projectType.selectedIndex].value]
      }
    }
    // 发送伙伴云修改
    await updateTable(this.itemId, data)
    this.$emit('reload')
    this.close()
  }
}
</script>
<style></style>
