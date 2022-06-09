<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">客户编辑</div>
      <table class="EditTable">
        <tr>
          <td>客户名称</td>
          <td><input id="customerName" type="text" /></td>
        </tr>
        <tr>
          <td>联系电话</td>
          <td><input id="telephone" type="text" /></td>
        </tr>
      </table>
      <table class="EditTable">
        <tr>
          <td width="100px">客户来源</td>
          <td>
            <span v-for="item in custominto" :key="item.id">
              <input
                type="radio"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>房屋情况</td>
          <td>
            <span v-for="item in houseInfo" :key="item.id">
              <input
                type="radio"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>全屋需求</td>
          <td>
            <span v-for="item in houseNeed" :key="item.id">
              <input
                type="checkbox"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>房型</td>
          <td>
            <span v-for="item in hometype" :key="item.id">
              <input
                type="radio"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>人员属性</td>
          <td>
            <span v-for="item in personInfo" :key="item.id">
              <input
                type="radio"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>单品客户</td>
          <td>
            <span v-for="item in oneCustom" :key="item.id">
              <input
                type="checkbox"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>客户属性</td>
          <td>
            <span v-for="item in cAttribute" :key="item.id">
              <input
                type="checkbox"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
          </td>
        </tr>
        <tr>
          <td>流失状态</td>
          <td>
            <span v-for="item in lostStatus" :key="item.id">
              <input
                type="radio"
                :id="item.id"
                :name="item.field"
                @click="onchange(item)"
              />
              {{ item.value }}
            </span>
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
import { table, field } from '@/config/config'
import { SearchInfo, updateTable } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  taglist: any[] = []; // 标签list
  houseInfo: any[] = [];
  personInfo: any[] = [];
  cAttribute: any[] = [];
  fitmentStage: any[] = [];
  oneCustom: any[] = [];
  lostStatus: any[] = [];
  hometype: any[] = [];
  custominto: any[] = [];
  houseNeed: any[] = [];
  itemId: any = ''; // 行ID
  tagInfo = table.tagInfo;
  customerInfo = table.customerInfo;
  userId = localStorage.getItem('userId');

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
    const result = await SearchInfo(this.tagInfo, data)
    this.taglist = result
    // 使用循环进行赋值显示
    for (let i = 0; i < result.length; i++) {
      const name = result[i].fields[0].values[0].title
      const value = result[i].fields[1].values[0].value
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
      if (name === '客户属性') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.cAttribute
        }
        this.cAttribute.push(ob)
      }
      if (name === '全屋需求') {
        const ob = {
          id: item_id,
          name: name,
          value: value,
          field: field.houseNeed
        }
        this.houseNeed.push(ob)
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
    this.showEdit()
  }

  close () {
    this.$emit('close')
  }

  // 标签更新
  onchange = (items: any) => {
    // 这块有些小bug
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

  // 保存客户数据
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
      if (obj[i].checked === true) {
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
    }
    // 拼接用户名称和电话
    data.fields[field.customerName] = customerName.value
    data.fields[field.ctelephone] = telephone.value
    if (typeof (data.fields[field.oneCustom]) === 'undefined') {
      data.fields[field.oneCustom] = []
    }
    if (typeof (data.fields[field.houseNeed]) === 'undefined') {
      data.fields[field.houseNeed] = []
    }
    if (typeof (data.fields[field.cAttribute]) === 'undefined') {
      data.fields[field.cAttribute] = []
    }
    // 发送伙伴云修改
    await updateTable(this.itemId, data)
    this.$emit('reload')
    this.close()
  }

  // 编辑按钮触发
  async showEdit () {
    // 获取当前用户信息
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: field.userTable, sort: 'desc' }]
    }
    const res = await SearchInfo(this.customerInfo, data)
    if (res.length === 0) {
      return
    }
    // const itemId = res[0].item_id
    this.itemId = res[0].item_id
    // 注入Button上
    const fields = res[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.customerName) {
        const values = fields[i].values
        const customerName: any = document.getElementById('customerName')
        customerName.value = values[0].value
      }
      if (fields[i].field_id === field.ctelephone) {
        const values = fields[i].values
        const telephone: any = document.getElementById('telephone')
        telephone.value = values[0].value
      }
      if (fields[i].field_id === field.custominto) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.houseInfo) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.hometype) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.personInfo) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.cAttribute) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.oneCustom) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.lostStatus) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
      if (fields[i].field_id === field.houseNeed) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          const dom: any = document.getElementById(itemid)
          dom.checked = 'true'
          dom.className = 'selected'
        }
      }
    }
  }
}
</script>
<style scoped>
.EditTable tr td:nth-child(1) {
  vertical-align: top;
  top: 3px;
  position: relative;
}
</style>
