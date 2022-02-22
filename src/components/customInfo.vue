<template>
  <div id="app">
    <editTag @reload="reloadFunction"></editTag>
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
    <div class="tag inline" v-if="fitmentStage.length != 0" style="display:none">
      <div>装修阶段</div>
      <button v-for="item in fitmentStage" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag inline" v-if="lostStatus.length != 0">
      <div>流失状态</div>
      <button v-for="item in lostStatus" :key="item.item_id">
        {{ item.title }}
      </button>
    </div>
    <div class="tag" v-if="oneCustom.length != 0">
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
import editTag from '@/components/editTag.vue'

@Component({
  name: 'customInfo',
  components: {
    editTag: editTag
  }
})
export default class Actions extends Vue {
  houseInfo: any[] = [];
  personInfo: any[] = [];
  cAttribute: any[] = [];
  fitmentStage: any[] = [];
  oneCustom: any[] = [];
  lostStatus: any[] = [];
  hometype: any[] = [];
  custominto: any[] = [];
  houseNeed: any[] = [];
  tableID = table.customerInfo;
  ticket = localStorage.getItem('ticket');
  data = {
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
  };

  async mounted () {
    this.loadFunction()
  }

  async loadFunction () {
    const res = await SearchInfo(this.ticket, this.tableID, this.data)
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
      // if (fields[i].field_id === field.fitmentStage) {
      //   const values = fields[i].values
      //   this.fitmentStage = values
      // }
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
    }
  }

  @Emit()
  async reloadFunction () {
    setTimeout(this.loadFunction, 1000)
  }
}
</script>
<style scoped>
.tag button {
  text-align: center;
  border: 0px solid #bbb;
  margin: 0px 5px 5px 5px;
  background-color: rgb(230, 227, 227);
  font-size: 10px;
  color: cornflowerblue;
  padding: 0px 5px 0px 5px;
  border-radius: 5px;
}
.tag div {
  text-align: left;
  font-size: 10px;
  margin-left: 10px;
  font-weight: bold;
}
.inline {
  width: auto;
  display: inline-block;
}
</style>
