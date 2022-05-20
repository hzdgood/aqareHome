<template>
  <div>
    <div class="headerDiv">客户绑定</div>
    <table class="EditTable">
      <tr>
        <td>客户信息</td>
        <td><input id="name" type="text" @change="change()" /></td>
        <td><input type="button" value="查询" @click="search()" /></td>
      </tr>
      <tr>
        <td>客户名称</td>
        <td colspan="2">
          <select id="customName" style="max-width: 300px">
            <option
              :key="customer.id"
              v-for="customer in customerList"
              :value="customer.id"
            >
              {{ customer.name }} {{ customer.phone }}
            </option>
          </select>
        </td>
      </tr>
    </table>
    <div>
      <div class="buttonSite">
        <button class="addButton" type="button" @click="add()" v-show="addStatus">新增用户</button>
        <button class="bindButton" type="button" @click="save()">绑定该用户</button>
        <button class="closeButton" type="button" @click="close()">关闭</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable, addInfo } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  customerInfo = table.customerInfo;
  saleManInfo = table.saleManInfo;
  userId = localStorage.getItem('userId');
  userName = localStorage.getItem('userName');
  localName = localStorage.getItem('localName');
  customerList: any[] = [];
  addStatus = true;

  // @Prop({
  //   type: Boolean,
  //   required: true,
  //   default: ''
  // })
  // status!: any;

  change () {
    const name: any = document.getElementById('name')
    if (name.value === '') {
      this.addStatus = false
    } else {
      this.addStatus = true
    }
  }

  async mounted () {
    const data = {
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.customerInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const id = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.customerName) {
          const name = fields[j].values[0].value
          const obj = {
            id: id,
            name: name
          }
          this.customerList.push(obj)
        }
      }
    }
  }

  async search () {
    this.customerList = []
    const name: any = document.getElementById('name')
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [name.value] }] },
            query_option_mappings: [-1],
            field: field.customerName // 客户名称
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.customerInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const id = result[i].item_id
      let customerName = ''
      let ctelephone = ''
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.customerName) {
          customerName = fields[j].values[0].value
        }
        if (fields[j].field_id === field.ctelephone) {
          ctelephone = fields[j].values[0].value
        }
      }
      const obj = {
        id: id,
        name: customerName,
        phone: ctelephone
      }
      this.customerList.push(obj)
    }
  }

  async save () {
    const customName: any = document.getElementById('customName')
    const itemId = customName.options[customName.selectedIndex].value
    const data = {
      fields: {
        [field.userTable]: this.userId // 更新userID
      }
    }
    await updateTable(itemId, data)
    this.$emit('close')
  }

  async add () {
    // 查询销售人员信息表
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.localName] }] },
            query_option_mappings: [-1],
            field: field.localName
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.saleManInfo, data)
    let itemId = ''
    if (result.length === 0) {
      alert('找不到当前销售人员信息！' + this.localName)
      return
    }
    for (let i = 0; i < result.length; i++) {
      itemId = result[0].item_id
    }
    // 新增一条客户信息
    const obj = {
      fields: {
        [field.userTable]: this.userId,
        [field.customerName]: this.userName,
        [field.gender]: [1],
        [field.salesperson]: [itemId]
      }
    }
    await addInfo(this.customerInfo, obj)
    this.$emit('close')
  }

  close () {
    this.$emit('close')
  }
}
</script>

<style scoped></style>
