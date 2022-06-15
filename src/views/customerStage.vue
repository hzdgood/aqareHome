<template>
  <div class="stageDiv">
    <table-select
      :width="comWidth"
      :height="comHeight"
      :itemId="itemId"
      @updateStage="updateStage"
    ></table-select>
    <div class="stageButton">
      <span>
        <img src="../img/x1.png" width="22%" />
      </span>
      <span>
        <img src="../img/person/state-1.png" width="73%" v-show="stageImg1" />
        <img src="../img/person/state-2.png" width="73%" v-show="stageImg2" />
        <img src="../img/person/state-3.png" width="73%" v-show="stageImg3" />
        <img src="../img/person/state-4.png" width="73%" v-show="stageImg4" />
        <img src="../img/person/state-5.png" width="73%" v-show="stageImg5" />
        <img src="../img/person/state-6.png" width="73%" v-show="stageImg6" />
        <img src="../img/person/state-7.png" width="73%" v-show="stageImg7" />
        <img src="../img/person/state-8.png" width="73%" v-show="stageImg8" />
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
  comWidth: any = '100px';
  comHeight: any = '20px';

  @Watch('this.screenWidth')
  reloadTable () {
    console.log(11)
  }

  // 获取所有客户标签
  async mounted () {
    window.onresize = () => {
      this.screenWidth = document.body.clientWidth
      this.comWidth = this.screenWidth - 40 + 'px'
      this.comHeight = this.screenWidth / 3 - 10 + 'px'
    }
    const data = {
      where: {
        and: [
          {
            field: 2200000182035321,
            query: { em: false, in: [2300006607764731] }
          }
        ]
      },
      offset: 0,
      limit: 20
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
            query: { or: [{ eqm: [this.userId] }] },
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
    this.itemId = res[0].item_id + ''
    const fields = res[0].fields
    let status = false
    for (let i = 0; i < fields.length; i++) {
      if (fields[i].field_id === field.customerStage) {
        const values = fields[i].values
        status = true
        for (let j = 0; j < values.length; j++) {
          const itemid = values[j].item_id
          this.StageImg(itemid)
        }
      }
    }
    // 取不到值
    if (!status) {
      const objs: any = {
        fields: {
          [field.customerStage]: [2300006607764785]
        }
      }
      await updateTable(this.itemId, objs)
      this.stageImg8 = true
    }
    this.comWidth = this.screenWidth - 40 + 'px'
    this.comHeight = this.screenWidth / 3 + 'px'
  }

  // 选中图片
  StageImg (itemid: any) {
    for (let i = 0; i < this.customStage.length; i++) {
      const obj = this.customStage[i]
      if (obj.id === itemid) {
        this.updateImg(obj.img)
      }
    }
    for (let i = 0; i < this.customStage.length; i++) {
      const obj = this.customStage[i]
      if (obj.id === itemid) {
        this.updateImg(obj.img)
      }
    }
  }

  updateImg (value: any) {
    this.stageImg1 = false
    this.stageImg2 = false
    this.stageImg3 = false
    this.stageImg4 = false
    this.stageImg5 = false
    this.stageImg6 = false
    this.stageImg7 = false
    this.stageImg8 = false
    if (value === 1) {
      this.stageImg1 = true
    } else if (value === 2) {
      this.stageImg2 = true
    } else if (value === 3) {
      this.stageImg3 = true
    } else if (value === 4) {
      this.stageImg4 = true
    } else if (value === 5) {
      this.stageImg5 = true
    } else if (value === 6) {
      this.stageImg6 = true
    } else if (value === 7) {
      this.stageImg7 = true
    } else if (value === 8) {
      this.stageImg8 = true
    }
  }

  updateStage (item: any) {
    console.log(item)
    this.updateImg(item.value)
  }

  // // 选择
  // onclick = (items: any) => {
  //   // 获取选中对象 删除class
  //   const obj: any = document.getElementsByClassName('selected')
  //   for (let i = 0; i < obj.length; i++) {
  //     const id = obj[i].id
  //     const dom: any = document.getElementById(id)
  //     dom.className = ''
  //   }
  //   // 设置新的选中对象
  //   const dom: any = document.getElementById(items.id)
  //   dom.className = 'selected'
  //   // 发送请求
  //   this.updateData(items)
  // };

  // // 更新
  // async updateData (items: any) {
  //   const objs: any = {
  //     fields: {
  //       [items.field]: [parseInt(items.id)]
  //     }
  //   }
  //   await updateTable(this.itemId, objs)
  // }
}
</script>
<style scoped></style>
