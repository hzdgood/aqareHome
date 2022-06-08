<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">报价单</div>
      <div class="addSite" v-show="errorStatus">
        <input
          v-show="createStatus"
          id="createButton"
          class="saveButton"
          type="button"
          value="生成报价"
          @click="create()"
        />
      </div>
      <div v-for="item in dataList" :key="item.id">
        <table class="EditTable" v-show="errorStatus">
          <tr>
            <td>订单类型</td>
            <td><input type="text" :value="item.type" readonly /></td>
          </tr>
          <tr>
            <td>方案金额</td>
            <td><input type="text" :value="item.schemeMoney" readonly /></td>
          </tr>
          <tr>
            <td>优惠金额</td>
            <td><input id="discount" type="text" :value="item.discount" /></td>
          </tr>
          <tr>
            <td>应收金额</td>
            <td><input type="text" :value="item.receivable" readonly /></td>
          </tr>
          <tr>
            <td>已收金额</td>
            <td><input type="text" :value="item.Received" readonly /></td>
          </tr>
        </table>
        <div class="buttonSite">
          <input
            class="saveButton"
            type="button"
            value="同步"
            @click="synchroClick(item)"
          />
          <input
            class="saveButton"
            type="button"
            value="保存"
            @click="saveClick(item)"
          />
          <input
            class="closeButton"
            type="button"
            value="关闭"
            @click="closeClick()"
          />
        </div>
      </div>

      <div v-if="errorStatus == false">
        {{ errorMsg }}
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, collectType } from '@/config/config'
import { SearchInfo, addInfo, updateTable } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  collectType = collectType;
  projectId = '';
  errorMsg = '';
  errorStatus = false;
  createStatus = true;
  itemId = '';
  proposalId = '';
  userId = localStorage.getItem('userId');
  dataList: any[] = [];
  type = '';
  schemeMoney = '';
  discount = '';
  receivable = '';
  Received = '';

  async mounted () {
    let money = '0.00'
    const obj1 = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.projectUUid
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [
        { field: field.projectUUid, sort: 'desc' },
        { field: field.masterProject, sort: 'asc' }
      ]
    }

    const result1 = await SearchInfo(table.projectInfo, obj1)
    if (result1.length === 0) {
      this.errorMsg = '请先添加项目！'
      return
    }

    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      this.itemId = result1[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000151011510) {
          // 需补款
          const values = fields[j].values[0].value
          money = values
        }
        if (fields[j].field_id === field.projectId) {
          const values = fields[j].values[0].value
          this.projectId = values
        }
      }
    }
    if (money === '0.00') {
      this.errorMsg = '请先上传方案！'
      return
    }
    this.errorStatus = true
    this.search()
  }

  // 新增报价单
  async create () {
    const obj = {
      fields: {
        2200000180589754: [1],
        2200000180589755: [this.itemId],
        2200000180591563: [1]
      }
    }
    await addInfo(table.proposal, obj)
    this.createStatus = false
    this.update()
  }

  // 查询数据
  async search () {
    // 查询报价单
    this.dataList = []
    const obj1 = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.projectId] }] },
            query_option_mappings: [-1],
            field: 1102001110000000 // 项目ID
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result1 = await SearchInfo(table.proposal, obj1)
    if (result1.length !== 0) {
      this.createStatus = false
    }
    for (let i = 0; i < result1.length; i++) {
      const fields = result1[i].fields
      this.proposalId = result1[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000180589754) {
          const values = fields[j].values[0].name
          this.type = values
        }
        if (fields[j].field_id === 2200000180589757) {
          const values = fields[j].values[0].value
          this.schemeMoney = values
        }
        if (fields[j].field_id === 2200000180591044) {
          const values = fields[j].values[0].value
          this.Received = values
        }
        if (fields[j].field_id === 2200000180589759) {
          const values = fields[j].values[0].value
          this.receivable = values
        }
        if (fields[j].field_id === 2200000180589758) {
          const values = fields[j].values[0].value
          this.discount = values
        }
        // 2200000180589754 类型
        // 2200000180589757 方案金额
        // 2200000180591044 已收
        // 2200000180589759 未收
        // 2200000180589758 优惠金额
      }
      const obj = {
        id: i,
        proposalId: this.proposalId,
        type: this.type,
        schemeMoney: this.schemeMoney,
        Received: this.Received,
        receivable: this.receivable,
        discount: this.discount
      }
      this.dataList.push(obj)
    }
  }

  // 保存
  saveClick (item: any) {
    const discount: any = document.getElementById('discount')
    const data = {
      fields: {
        2200000180589758: discount.value
      }
    }
    updateTable(item.proposalId, data)
    this.update()
  }

  // 同步
  synchroClick (item: any) {
    const data = {
      fields: {
        2200000180589754: [1],
        2200000180591563: [1]
      }
    }
    updateTable(item.proposalId, data)
    this.update()
  }

  // 更新
  update () {
    setTimeout(this.search, 2000)
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
