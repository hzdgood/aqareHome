<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">群编辑</div>
      <table class="EditTable">
        <tr>
          <td>客户名称</td>
          <td><input id="customerName" type="text" /></td>
        </tr>
        <tr>
          <td>客户电话</td>
          <td><input id="telephone" type="text" /></td>
        </tr>
        <tr>
          <td>项目类型</td>
          <td>
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
          </td>
        </tr>
      </table>
      <div class="buttonSite">
        <button class="saveButton" @click="saveInfo()">保存</button>
        <button class="closeButton" @click="close()">关闭</button>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, projectType } from '@/config/config'
import { SearchInfo, updateTable } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  projectType = projectType;
  userId = localStorage.getItem('userId');
  itemId: any = '';
  async mounted () {
    this.showEdit()
  }

  async showEdit () {
    const chatId = localStorage.getItem('chatID')
    const obj = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [chatId] }] },
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
  }

  close () {
    this.$emit('close')
  }

  async saveInfo () {
    const customerName: any = document.getElementById('customerName')
    const telephone: any = document.getElementById('telephone')
    const projectType: any = document.getElementById('projectType')
    const data: any = {
      fields: {
        [field.pname]: customerName.value,
        [field.ptelephone]: telephone.value,
        [field.projectType]: [
          projectType.options[projectType.selectedIndex].value
        ]
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
