<template>
  <div id="app">
    <customerEdit @reload="reloadFunction"></customerEdit>
    <div class="tag inline" v-if="custominto.length != 0">
      <div>客户来源</div>
      <button v-for="item in custominto" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="hometype.length != 0">
      <div>房型</div>
      <button v-for="item in hometype" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="houseInfo.length != 0">
      <div>房屋情况</div>
      <button v-for="item in houseInfo" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="houseNeed.length != 0">
      <div>全屋需求</div>
      <button v-for="item in houseNeed" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="personInfo.length != 0">
      <div>人员属性</div>
      <button v-for="item in personInfo" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="cAttribute.length != 0">
      <div>客户属性</div>
      <button v-for="item in cAttribute" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="lostStatus.length != 0">
      <div>流失状态</div>
      <button v-for="item in lostStatus" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="oneCustom.length != 0">
      <div>单品客户</div>
      <button v-for="item in oneCustom" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Emit } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field, user } from '@/config/config'
import customerEdit from '@/components/infoEdit/customerEdit.vue'

@Component({
  name: 'customInfo',
  components: {
    customerEdit: customerEdit
  }
})
export default class Actions extends Vue {
  houseInfo: any[] = [];
  personInfo: any[] = [];
  cAttribute: any[] = [];
  oneCustom: any[] = [];
  lostStatus: any[] = [];
  hometype: any[] = [];
  custominto: any[] = [];
  houseNeed: any[] = [];
  customerInfo = table.customerInfo;
  userId = localStorage.getItem('userId');
  async mounted () {
    this.loadFunction()
  }

  // 加载客户数据
  async loadFunction () {
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

  // 回调 tagEdit
  @Emit()
  async reloadFunction () {
    setTimeout(this.loadFunction, 1000)
  }
}
</script>
<style scoped></style>
