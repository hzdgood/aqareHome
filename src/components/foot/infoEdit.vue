<template>
  <div v-show="isShow">
    <button @click="addClick()">新增</button>
    <div id="projectList" v-for="project in projectList" :key="project.id">
      <div class="lineDiv">
        <span>项目类型：</span>
        <span>
          <input id="projectType" type="text" :value="project.projectType" />
        </span>
        <button @click="bindClick(project)">绑定</button>
        <button @click="deleteClick(project)">删除</button>
      </div>
      <div class="lineDiv">
        <span>客户名称：</span>
        <span>
          <input id="projectCustom" type="text" :value="project.customer" />
        </span>
        <span>联系方式：</span>
        <span>
          <input id="telephone" type="text" :value="project.telephone" />
        </span>
      </div>
      <div class="lineDiv">
        <span>小区名称：</span>
        <span>
          <input id="projectVillage" type="text" :value="project.village" />
        </span>
        <span>客户房型：</span>
        <span>
          <input id="projectHometype" type="text" :value="project.hometype" />
        </span>
      </div>
      <div class="lineDiv">
        <span>销售人员：</span>
        <span>
          <input id="projectArea" type="text" :value="project.saleMan" />
        </span>
        <span>所属门店：</span>
        <span>
          <input id="projectStage" type="text" :value="project.department" />
        </span>
      </div>
      <div class="lineDiv">
        <span>所在区域：</span>
        <span>
          <input id="projectArea" type="text" :value="project.area" />
        </span>
        <span>装修阶段：</span>
        <span>
          <input id="projectStage" type="text" :value="project.stage" />
        </span>
      </div>
      <div class="lineDivT">
        <span>客户地址：</span>
        <span>
          <input id="projectAddress" type="text" :value="project.address" />
        </span>
      </div>
      <button @click="saveClick(project)">保存</button>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field, user } from '@/config/config'
@Component({})
export default class Home extends Vue {
  @Prop({ default: false }) isShow!: boolean;
  ticket = localStorage.getItem('ticket');
  projectInfo = table.projectInfo;
  customerInfo = table.customerInfo;
  projectList: any[] = [];
  itemList: any[] = [];
  async getInfoList () {
    // 查询当前客户的所有的项目-->userid
    this.projectList = []
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [user.userId] }] },
            query_option_mappings: [-1],
            field: field.projectUUid
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: field.projectUUid, sort: 'desc' }]
    }
    const result = await SearchInfo(this.ticket, this.projectInfo, data)
    for (let j = 0; j < result.length; j++) {
      const fields = result[j].fields
      const itemId: any = result[j].item_id
      this.itemList.push(itemId)
      let projectCustom = ''
      let telephone = ''
      let projectVillage = ''
      let projectHometype = ''
      let projectArea = ''
      let projectStage = ''
      let projectAddress = ''
      let projectType = ''
      let saleMan = ''
      let department = ''
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectCustom) {
          const values = fields[i].values[0].value
          projectCustom = values
        }
        if (fields[i].field_id === field.telephone) {
          const values = fields[i].values[0].value
          telephone = values
        }
        if (fields[i].field_id === field.projectVillage) {
          const values = fields[i].values[0].value
          projectVillage = values
        }
        if (fields[i].field_id === field.projectAddress) {
          const values = fields[i].values[0].value
          projectAddress = values
        }
        if (fields[i].field_id === field.projectHometype) {
          const values = fields[i].values[0].name
          projectHometype = values
        }
        if (fields[i].field_id === field.projectArea) {
          const values = fields[i].values[0].title
          projectArea = values
        }
        if (fields[i].field_id === field.projectStage) {
          const values = fields[i].values[0].name
          projectStage = values
        }
        if (fields[i].field_id === field.projectType) {
          const values = fields[i].values[0].name
          projectType = values
        }
        if (fields[i].field_id === field.saleMan) {
          const values = fields[i].values[0].title
          saleMan = values
        }
        if (fields[i].field_id === field.department) {
          const values = fields[i].values[0].value
          department = values
        }
      }
      const obj = {
        id: j,
        itemId: itemId,
        customer: projectCustom,
        telephone: telephone,
        village: projectVillage,
        hometype: projectHometype,
        area: projectArea,
        stage: projectStage,
        address: projectAddress,
        projectType: projectType,
        saleMan: saleMan,
        department: department
      }
      this.projectList.push(obj)
    }
  }

  async mounted () {
    this.getInfoList()
  }

  async bindClick (project: any) {
    for (let i = 0; i < this.itemList.length; i++) {
      const data = { fields: { [field.masterProject]: [2] } }
      await updateTable(this.ticket, this.itemList[i], data)
    }
    const data = { fields: { [field.masterProject]: [1] } }
    const res = await updateTable(this.ticket, project.itemId, data)
    console.log(res)
  }

  deleteClick (project: any) {
    console.log(project.itemId)
  }

  async addClick () {
    const data = {
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
    }
    const res = await SearchInfo(this.ticket, this.customerInfo, data)
    const itemId = res[0].item_id
    if (itemId) {
      const data = { fields: { 2200000184840062: [1] } }
      await updateTable(this.ticket, itemId, data)
      setTimeout(this.getInfoList, 1000)
    }
  }

  saveClick (project: any) {
    console.log(project.itemId)
    this.isShow = false
  }
}
</script>

<style scoped>
.lineDiv {
  margin: 5px;
}
.lineDiv span {
  font-size: 12px;
  margin: 5px;
}
.lineDiv input {
  width: auto;
  max-width: 80px;
  font-size: 12px;
}
.lineDivT {
  margin: 5px;
}
.lineDivT span {
  font-size: 12px;
  margin: 5px;
}
.lineDivT input {
  width: 220px;
  font-size: 12px;
}
</style>
