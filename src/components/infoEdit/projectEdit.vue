<template>
  <div>
    <button class="editTag" @click="showEdit()">编辑</button>
    <div class="floatDiv" v-show="editShow"></div>
    <div id="infoDiv" class="infoDiv" v-show="editShow">
      <div class="headerDiv">群编辑</div>
      <div class="inline">
        <span>客户名称</span>
        <input id="customerName" type="text" />
      </div>
      <div class="inline">
        <span>客户电话</span>
        <input id="telephone" type="text" />
      </div>

      <div>目前任务</div>
      <button
        v-for="item in projectProgress"
        :key="item.value"
        :id="item.value"
        name="目前任务"
        @click="onchange(item)"
      >
        {{ item.name }}
      </button>

      <div>项目类型</div>
      <button
        v-for="item in projectType"
        :key="item.value"
        :id="item.value"
        name="项目类型"
        @click="onchange(item)"
      >
        {{ item.name }}
      </button>
    </div>
    <button @click="saveInfo()">保存</button>
    <button @click="close()">关闭</button>
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
  editShow = false;
  itemId: any = '';
  // async mounted() {
  //   console.log(111);
  // }
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
    console.log(result)
    if (result.length === 0) {
      return
    }
    this.itemId = result[0].item_id
    const fields = result[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.projectType) {
        console.log(fields[i])
      }
      if (fields[i].field_id === field.currentTask) {
        console.log(fields[i])
      }
    }
    this.close()
  }

  close () {
    if (this.editShow === true) {
      this.editShow = false
    } else {
      this.editShow = true
    }
  }

  async saveInfo () {
    const customerName: any = document.getElementById('customerName')
    const telephone: any = document.getElementById('telephone')
    const data: any = {
      fields: {}
    }
    const obj: any = document.getElementsByClassName('selected')
    let index = 0
    // 迭代循环DOM对象合成伙伴云数据
    for (let i = 0; i < obj.length; i++) {
      let id = obj[i].id
      const name = obj[i].name
      id = parseInt(id)
      if (i === 0) {
        const d: any[] = []
        d.push(id)
        data.fields[name] = d
      } else {
        const d1 = Object.keys(data.fields)[index]
        if (name === d1) {
          data.fields[name].push(id)
        } else {
          index++
          const d: any[] = []
          d.push(id)
          data.fields[name] = d
        }
      }
    }
    // 拼接用户名称和电话
    data.fields[field.customerName] = customerName.value
    data.fields[field.ctelephone] = telephone.value
    // 发送伙伴云修改
    await updateTable(this.itemId, data)
    this.$emit('reload')
    this.editShow = false
  }
}
</script>
<style></style>
