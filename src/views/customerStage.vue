<template>
  <div class="stageDiv">
    <div class="stageButton">
      <table-select :width="comWidth" :height="comHeright"></table-select>
      <span>
        <img src="../img/x1.png" width="25%" />
      </span>
      <span>
        <img id="img" src="../img/state-1.png" width="73%" v-show="stageImg1" />
        <img src="../img/state-2.png" width="73%" v-show="stageImg2" />
        <img src="../img/state-3.png" width="73%" v-show="stageImg3" />
        <img src="../img/state-4.png" width="73%" v-show="stageImg4" />
        <img src="../img/state-5.png" width="73%" v-show="stageImg5" />
        <img src="../img/state-6.png" width="73%" v-show="stageImg6" />
        <img src="../img/state-7.png" width="73%" v-show="stageImg7" />
        <img src="../img/state-8.png" width="73%" v-show="stageImg8" />
      </span>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field } from '@/config/config'
import tableSelect from '@/components/common/tableSelect.vue'
@Component({
  components: {
    'table-select': tableSelect
  }
})
export default class Home extends Vue {
  tagInfo = table.tagInfo;
  customerInfo = table.customerInfo;
  customStage: any[] = [];
  taglist: any[] = [];
  itemId: any = '';
  userId = localStorage.getItem('userId');
  stageImg1 = false;
  stageImg2 = false;
  stageImg3 = false;
  stageImg4 = false;
  stageImg5 = false;
  stageImg6 = false;
  stageImg7 = false;
  stageImg8 = false;
  screenWidth = document.body.clientWidth;

  comWidth = ''
  comHeright = ''

  @Watch('this.screenWidth')
  reloadTable () {
    const dom: any = document.getElementById('img')
    console.log(dom.width)
  }

  // 获取所有客户标签
  async mounted () {
    window.onresize = () => {
      this.screenWidth = document.body.clientWidth
    }
    const data = {
      where: {
        and: [{ field: 2200000182035321, query: { in: [2300006607764731] } }]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000182035325, sort: 'asc' }]
    }
    const result = await SearchInfo(this.tagInfo, data)
    this.taglist = result
    for (let i = 0; i < result.length; i++) {
      const name = result[i].fields[0].values[0].title
      const value = result[i].fields[1].values[0].value
      const item_id = result[i].item_id
      const img = result[i].fields[3].values[0].value
      const ob = {
        id: item_id,
        name: name,
        value: value,
        img: img,
        field: field.customerStage
      }
      this.customStage.push(ob)
    }
    this.getCustomerTag()
  }

  // 获取当前客户阶段
  async getCustomerTag () {
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
    this.itemId = res[0].item_id
    const fields = res[0].fields
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.customerStage) {
        const values = fields[i].values
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          this.StageImg(itemid)
        }
      }
    }
  }

  // 选中图片
  StageImg (itemid: any) {
    for (let i = 0; i < this.customStage.length; i++) {
      const obj = this.customStage[i]
      if (obj.id === itemid) {
        if (obj.img === 1) {
          this.stageImg1 = true
        } else if (obj.img === 2) {
          this.stageImg2 = true
        } else if (obj.img === 3) {
          this.stageImg3 = true
        } else if (obj.img === 4) {
          this.stageImg4 = true
        } else if (obj.img === 5) {
          this.stageImg5 = true
        } else if (obj.img === 6) {
          this.stageImg6 = true
        } else if (obj.img === 7) {
          this.stageImg7 = true
        } else if (obj.img === 8) {
          this.stageImg8 = true
        }
      }
    }
  }

  // 选择
  onclick = (items: any) => {
    // 获取选中对象 删除class
    const obj: any = document.getElementsByClassName('selected')
    for (let i = 0; i < obj.length; i++) {
      const id = obj[i].id
      const dom: any = document.getElementById(id)
      dom.className = ''
    }
    // 设置新的选中对象
    const dom: any = document.getElementById(items.id)
    dom.className = 'selected'
    // 发送请求
    this.updateData(items)
  };

  // 更新
  async updateData (items: any) {
    const objs: any = {
      fields: {
        [items.field]: [parseInt(items.id)]
      }
    }
    await updateTable(this.itemId, objs)
  }
}
</script>
<style scoped></style>
