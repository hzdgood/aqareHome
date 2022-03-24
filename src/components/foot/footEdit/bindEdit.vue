<template>
  <div>
    <div class="floatDiv" v-show="status"></div>
    <div :class="status ? 'infoDiv' : ''">
      <div class="headerDiv">客户绑定</div>
      <input id="name" type="text" />
      <input type="button" value="查询" @click="search()" />
      <div>
        <select id="customName">
          <option
            :key="customer.id"
            v-for="customer in customerList"
            :value="customer.id"
          >
            {{ customer.name }}
          </option>
        </select>
        <input type="button" value="新增" @click="add()" />
        <input type="button" value="保存" @click="save()" />
        <input type="button" value="关闭" @click="close()" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import { SearchInfo, updateTable, addInfo } from '@/config/interFace'
import { table, field, user } from '@/config/config'
@Component({})
export default class Home extends Vue {
  customerInfo = table.customerInfo;
  saleManInfo = table.saleManInfo;
  userId = user.userId;
  userName = localStorage.getItem('userName');
  localName = localStorage.getItem('localName');
  customerList: any[] = [];
  @Prop({
    type: Boolean,
    required: true,
    default: ''
  })
  status!: any;

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

  // async clearUser () {
  //   const data = {
  //     where: {
  //       and: [
  //         {
  //           query: { or: [{ in: [this.userId] }] },
  //           query_option_mappings: [-1],
  //           field: [field.userTable]
  //         }
  //       ]
  //     },
  //     offset: 0,
  //     limit: 20
  //   }
  //   const result = await SearchInfo(this.customerInfo, data)
  //   // 可以获取用户信息
  //   for (let i = 0; i < result.length; i++) {
  //     const itemId = result[0].item_id
  //     const data1 = {
  //       fields: {
  //         [field.userTable]: '' // 更新userID
  //       }
  //     }
  //     const res = await updateTable(itemId, data1)
  //     setTimeout(this.updateUser, 3000)
  //   }
  // }

  async updateUser () {
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

  async save () {
    if (this.status) {
      // this.clearUser()
    } else {
      this.updateUser()
    }
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

<style></style>
