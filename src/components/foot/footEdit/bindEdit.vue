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
        <input type="button" value="保存" @click="save()" />
        <input type="button" value="关闭" @click="close()" />
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field, user } from '@/config/config'

@Component({})
export default class Home extends Vue {
  customerInfo = table.customerInfo;
  userId = user.userId;
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
            field: 2200000144967973 // 客户名称
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

  async save () {
    if (this.status) {
      this.clearUser()
    } else {
      this.updateUser()
    }
  }

  close () {
    this.$emit('close')
  }

  async clearUser () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: 2200000166530102
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.customerInfo, data)
    // 可以获取用户信息
    for (let i = 0; i < result.length; i++) {
      const itemId = result[0].item_id
      const data1 = {
        fields: {
          2200000166530102: '' // 更新userID
        }
      }
      const res = await updateTable(itemId, data1)
      setTimeout(this.updateUser, 3000)
    }
  }

  async updateUser () {
    const customName: any = document.getElementById('customName')
    const itemId = customName.options[customName.selectedIndex].value
    const data = {
      fields: {
        2200000166530102: this.userId // 更新userID
      }
    }
    await updateTable(itemId, data)
    this.$emit('close')
  }
}
</script>

<style>
</style>
