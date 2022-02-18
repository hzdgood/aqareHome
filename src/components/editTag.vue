<template>
  <div>
    <button class="editTag" @click="showEdit()">编辑</button>
    <div v-show="editShow" id="customerDiv">
      <div class="tag" v-if="custominto.length != 0">
        <div>客户来源</div>
        <button
          v-for="item in custominto"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="houseInfo.length != 0">
        <div>房屋情况</div>
        <button
          v-for="item in houseInfo"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="hometype.length != 0">
        <div>房型</div>
        <button
          v-for="item in hometype"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="personInfo.length != 0">
        <div>人员属性</div>
        <button
          v-for="item in personInfo"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="cAttribute.length != 0">
        <div>分类属性</div>
        <button
          v-for="item in cAttribute"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="fitmentStage.length != 0">
        <div>装修阶段</div>
        <button
          v-for="item in fitmentStage"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="oneCustom.length != 0">
        <div>单品客户</div>
        <button
          v-for="item in oneCustom"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <div class="tag" v-if="lostStatus.length != 0">
        <div>流失状态</div>
        <button
          v-for="item in lostStatus"
          :key="item.id"
          :id="item.id"
          :name="item.field"
          @click="onchange(item)"
        >
          {{ item.value }}
        </button>
      </div>
      <button @click="saveInfo()">保存</button>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field, user } from '@/config/config'
import { SearchInfo, updateTable } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  editShow = false;
  taglist: any[] = [];// 标签list
  houseInfo: any[] = [];
  personInfo: any[] = [];
  cAttribute: any[] = [];
  fitmentStage: any[] = [];
  oneCustom: any[] = [];
  lostStatus: any[] = [];
  hometype: any[] = [];
  custominto: any[] = [];
  itemId: any = ''; // 行ID
  ticket = localStorage.getItem('ticket');
  tableID = table.tagInfo;
  customerInfo = table.customerInfo;
  async mounted () {
    // 获取全部的标签
    const data = {
      limit: 100,
      offset: 0,
      order_by: [
        {
          field: field.tagListId,
          sort: 'desc'
        }
      ]
    }
    const result = await SearchInfo(this.ticket, this.tableID, data)
    this.taglist = result
    // 使用循环进行赋值显示
    for (let i = 0; i < result.length; i++) {
      const name = result[i].fields[0].values[0].title
      const value = result[i].fields[3].values[0].value
      const item_id = result[i].item_id
      if (name === '客户来源') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.custominto
        }
        this.custominto.push(ob)
      }
      if (name === '房屋情况') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.houseInfo
        }
        this.houseInfo.push(ob)
      }
      if (name === '房型') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.hometype
        }
        this.hometype.push(ob)
      }
      if (name === '人员属性') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.personInfo
        }
        this.personInfo.push(ob)
      }
      if (name === '分类属性') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.cAttribute
        }
        this.cAttribute.push(ob)
      }
      if (name === '装修阶段') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.fitmentStage
        }
        this.fitmentStage.push(ob)
      }
      if (name === '单品客户') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.oneCustom
        }
        this.oneCustom.push(ob)
      }
      if (name === '流失状态') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.lostStatus
        }
        this.lostStatus.push(ob)
      }
    }
  }

  onchange = (items: any) => {
    const id: any = items.id
    const dom: any = document.getElementById(id)
    // 单选判断

    // 设置选中
    if (dom.className === 'selected') {
      dom.className = ''
    } else {
      dom.className = 'selected'
    }
  };

  async saveInfo () {
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
    // 发送伙伴云修改
    const res = await updateTable(this.ticket, this.itemId, data)
    console.log(res)
    this.$emit('reload')
    this.editShow = false
  }

  async showEdit () {
    // 获取当前用户信息
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [user.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: field.userTable, sort: 'desc' }]
    }
    const res = await SearchInfo(this.ticket, this.customerInfo, data)
    const itemId = res[0].item_id
    this.itemId = itemId
    // 注入Button上
    const fields = res[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.custominto) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.houseInfo) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.hometype) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.personInfo) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.cAttribute) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.fitmentStage) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.oneCustom) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.lostStatus) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.className = 'selected'
        }
      }
    }
    if (this.editShow === true) {
      this.editShow = false
    } else {
      this.editShow = true
    }
  }
}
</script>
<style scoped>
.editTag {
  position: fixed;
  right: 20px;
  top: 10px;
}
.tag button {
  margin: 0px 0px 5px 10px;
  border: 0px;
  border-radius: 5px;
}
.selected {
  background-color: aquamarine;
}
</style>
