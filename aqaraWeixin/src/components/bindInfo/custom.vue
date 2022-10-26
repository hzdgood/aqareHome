<template>
  <div>
    <div class="headerDiv">客户绑定</div>
    <table class="EditTable">
      <tr>
        <td>客户信息</td>
        <td>
          <input id="name" type="text" />
          <button class="img" @click="search">查询</button>
        </td>
      </tr>
      <tr>
        <td>客户名称</td>
        <td>
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
        <button class="saveButton" type="button" @click="save()">
          绑定用户
        </button>
        <button class="closeButton" type="button" @click="close()">
          返回
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable, logInsert } from '@/config/interFace'
import { table, field } from '@/config/config'
@Component({})
export default class Home extends Vue {
  userId = localStorage.getItem('userId');
  userName = localStorage.getItem('userName');
  localName = localStorage.getItem('localName');
  customerList: any[] = [];

  async mounted () {
    const data = {
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.customerInfo, data)
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
    const result = await SearchInfo(table.customerInfo, data)
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

  close () {
    this.$emit('loadPage')
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
    await logInsert('个人绑定')
    this.$emit('close')
  }
}
</script>

<style scoped>
.img{
  position: absolute;
  right: 45px;
  width: 50px;
  height: 30px;
  margin-top: 1px;
  background: #2b72e8;
  color: #fff;
  border: 2px solid #4285f4;
  border-radius: 8px;
}
</style>
