<template>
  <div>
    <table class="tagTable">
      <tr>
        <td>客户来源</td>
        <td>
          <button v-for="item in custominto" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
        <td>房型</td>
        <td>
          <button v-for="item in hometype" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr>
        <td>房屋情况</td>
        <td>
          <button v-for="item in houseInfo" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
        <td>人员属性</td>
        <td>
          <button v-for="item in personInfo" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="houseNeed.length !== 0">
        <td>全屋需求</td>
        <td colspan="3">
          <button v-for="item in houseNeed" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="cAttribute.length !== 0">
        <td>客户属性</td>
        <td colspan="3">
          <button v-for="item in cAttribute" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="oneCustom.length !==0">
        <td>单品客户</td>
        <td colspan="3">
          <button v-for="item in oneCustom" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="lostStatus.length !== 0">
        <td>流失状态</td>
        <td colspan="3">
          <button v-for="item in lostStatus" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field } from '@/config/config'

@Component({})
export default class Actions extends Vue {
  houseInfo: any[] = [];
  personInfo: any[] = [];
  cAttribute: any[] = [];
  oneCustom: any[] = [];
  lostStatus: any[] = [];
  hometype: any[] = [];
  custominto: any[] = [];
  houseNeed: any[] = [];
  telephone: any = ''
  customerInfo = table.customerInfo;
  userId = localStorage.getItem('userId');

  async mounted () {
    this.loadFunction()
  }

  @Watch('$store.state.customTagStatus')
  reloadPage () {
    setTimeout(this.loadFunction, 2000)
  }

  // 加载客户数据
  async loadFunction () {
    this.oneCustom = []
    this.cAttribute = []
    this.houseNeed = []
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
    const fields = res[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.ctelephone) {
        const values = fields[i].values[0].value
        this.telephone = values
      }
      if (fields[i].field_id === field.houseInfo) {
        const values = fields[i].values
        this.houseInfo = values
      }
      if (fields[i].field_id === field.personInfo) {
        const values = fields[i].values
        this.personInfo = values
      }
      if (fields[i].field_id === field.cAttribute) {
        const values = fields[i].values
        this.cAttribute = values
      }
      if (fields[i].field_id === field.oneCustom) {
        const values = fields[i].values
        this.oneCustom = values
      }
      if (fields[i].field_id === field.lostStatus) {
        const values = fields[i].values
        this.lostStatus = values
      }
      if (fields[i].field_id === field.custominto) {
        const values = fields[i].values
        this.custominto = values
      }
      if (fields[i].field_id === field.hometype) {
        const values = fields[i].values
        this.hometype = values
      }
      if (fields[i].field_id === field.houseNeed) {
        const values = fields[i].values
        this.houseNeed = values
      }
    }
  }
}
</script>
<style scoped></style>
