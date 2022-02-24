<template>
  <div>
    <button @click="addClick()">新增</button>
    <div id="projectList" v-for="project in projectList" :key="project.id">
      <div class="lineDiv">
        <span>项目类型：</span>
        <span>
          <select id="projectType">
            <option :value="project.projectTypeId">
              {{ project.projectType }}
            </option>
            <option
              :key="projectType.value"
              v-for="projectType in projectType"
              :value="projectType.value"
            >
              {{ projectType.name }}
            </option>
          </select>
        </span>
        <button @click="bindClick(project)">绑定</button>
        <button @click="deleteClick(project)">删除</button>
        <span>{{ project.masterProject }}</span>
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
          <select id="projectHometype">
            <option :value="project.hometypeId">
              {{ project.hometype }}
            </option>
            <option
              :key="houseType.value"
              v-for="houseType in houseType"
              :value="houseType.value"
            >
              {{ houseType.name }}
            </option>
          </select>
        </span>
      </div>
      <div class="lineDiv">
        <span>销售人员：</span>
        <span>
          <input id="projectArea" type="text" :value="project.saleMan" />
        </span>
        <span>所属门店：</span>
        <span>
          <input id="department" readonly type="text" :value="project.department" />
        </span>
      </div>
      <div class="lineDiv">
        <span>所在区域：</span>
        <span>
          <input id="projectArea" type="text" :value="project.area" />
        </span>
        <span>装修阶段：</span>
        <span>
          <select id="projectStage">
            <option :value="project.stageId">
              {{ project.stage }}
            </option>
            <option
              :key="fitmentStage.value"
              v-for="fitmentStage in fitmentStage"
              :value="fitmentStage.value"
            >
              {{ fitmentStage.name }}
            </option>
          </select>
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
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable, deleteItem } from '@/config/interFace'
import {
  table,
  field,
  user,
  houseType,
  fitmentStage,
  projectType
} from '@/config/config'
@Component({})
export default class Home extends Vue {
  ticket = localStorage.getItem('ticket');
  projectInfo = table.projectInfo;
  customerInfo = table.customerInfo;
  projectList: any[] = [];
  itemList: any[] = [];
  houseType: any = houseType;
  fitmentStage: any = fitmentStage;
  projectType: any = projectType;
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
      order_by: [
        { field: field.projectUUid, sort: 'desc' },
        { field: field.masterProject, sort: 'asc' }
      ]
    }
    const result = await SearchInfo(this.ticket, this.projectInfo, data)
    for (let j = 0; j < result.length; j++) {
      const fields = result[j].fields
      const itemId: any = result[j].item_id
      this.itemList.push(itemId)
      let projectCustom = ''
      let telephone = ''
      let projectVillage = ''
      let projectAddress = ''
      let masterProject = ''
      let projectHometype = ''; let projectHometypeId = ''
      let projectArea = ''
      let projectStage = ''; let projectStageId = ''
      let projectType = ''; let projectTypeId = ''
      let saleMan = ''
      let department = ''
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectCustom) {
          projectCustom = fields[i].values[0].value
        }
        if (fields[i].field_id === field.telephone) {
          telephone = fields[i].values[0].value
        }
        if (fields[i].field_id === field.projectVillage) {
          projectVillage = fields[i].values[0].value
        }
        if (fields[i].field_id === field.projectAddress) {
          projectAddress = fields[i].values[0].value
        }
        if (fields[i].field_id === field.masterProject) {
          masterProject = fields[i].values[0].name
        }
        if (fields[i].field_id === field.projectHometype) {
          projectHometype = fields[i].values[0].name
          projectHometypeId = fields[i].values[0].id
        }
        if (fields[i].field_id === field.projectStage) {
          projectStage = fields[i].values[0].name
          projectStageId = fields[i].values[0].id
        }
        if (fields[i].field_id === field.projectType) {
          projectType = fields[i].values[0].name
          projectTypeId = fields[i].values[0].id
        }
        if (fields[i].field_id === field.projectArea) {
          projectArea = fields[i].values[0].title
        }
        if (fields[i].field_id === field.saleMan) {
          saleMan = fields[i].values[0].title
        }
        if (fields[i].field_id === field.department) {
          department = fields[i].values[0].value
        }
      }
      const obj = {
        id: j,
        itemId: itemId,
        customer: projectCustom,
        telephone: telephone,
        village: projectVillage,
        address: projectAddress,
        masterProject: masterProject,
        area: projectArea,

        saleMan: saleMan,
        department: department,

        hometype: projectHometype,
        hometypeId: projectHometypeId,

        projectType: projectType,
        projectTypeId: projectTypeId,

        stage: projectStage,
        stageId: projectStageId
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
    await updateTable(this.ticket, project.itemId, data)
    setTimeout(this.getInfoList, 1000)
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

  async deleteClick (project: any) {
    if (project.masterProject === '是') {
      console.log(11111)
    } else {
      const data = { item_ids: [project.itemId] }
      await deleteItem(this.ticket, table.projectInfo, data)
      setTimeout(this.getInfoList, 1000)
    }
  }

  async saveClick (project: any) {
    const telephone: any = document.getElementById('telephone')
    const projectCustom: any = document.getElementById('projectCustom')
    const address: any = document.getElementById('projectAddress')
    const village: any = document.getElementById('projectVillage')
    const stage: any = document.getElementById('projectStage')
    const stageValue = stage.options[stage.selectedIndex].value
    const hometype: any = document.getElementById('projectHometype')
    const typeValue = hometype.options[hometype.selectedIndex].value
    const Type: any = document.getElementById('projectType')
    const TypeValue = Type.options[Type.selectedIndex].value

    const data = {
      fields: {
        [field.projectCustom]: projectCustom.value,
        [field.telephone]: telephone.value,
        [field.projectAddress]: address.value,
        [field.projectVillage]: village.value,
        [field.projectStage]: [stageValue],
        [field.projectHometype]: [typeValue],
        [field.projectType]: [TypeValue]
      }
    }
    await updateTable(this.ticket, project.itemId, data)
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
