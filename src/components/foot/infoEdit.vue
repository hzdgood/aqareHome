<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
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
                v-for="projectType in projectType"
                :value="projectType.value"
                :key="projectType.value"
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
                v-for="houseType in houseType"
                :value="houseType.value"
                :key="houseType.value"
              >
                {{ houseType.name }}
              </option>
            </select>
          </span>
        </div>
        <div class="lineDiv">
          <span>销售人员：</span>
          <span>
            <select id="saleMan">
              <option :value="project.saleManId">
                {{ project.saleMan }}
              </option>
              <option
                v-for="saleManList in saleManList"
                :value="saleManList.saleId"
                :key="saleManList.saleId"
              >
                {{ saleManList.saleName }}
              </option>
            </select>
          </span>
          <span>所属门店：</span>
          <span>
            <select id="department">
              <option :value="project.departmentId">
                {{ project.department }}
              </option>
              <option
                v-for="departmentList in departmentList"
                :value="departmentList.value"
                :key="departmentList.value"
              >
                {{ departmentList.name }}
              </option>
            </select>
          </span>
        </div>
        <div class="lineDiv">
          <span>所在区域：</span>
          <span>
            <select id="projectArea">
              <option :value="project.areaId">
                {{ project.area }}
              </option>
              <option
                v-for="areaList in areaList"
                :value="areaList.value"
                :key="areaList.value"
              >
                {{ areaList.name }}
              </option>
            </select>
          </span>
          <span>装修阶段：</span>
          <span>
            <select id="projectStage">
              <option :value="project.stageId">
                {{ project.stage }}
              </option>
              <option
                v-for="fitmentStage in fitmentStage"
                :value="fitmentStage.value"
                :key="fitmentStage.value"
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
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import {
  SearchInfo,
  updateTable,
  deleteItem,
  filterInfo
} from '@/config/interFace'
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
  saleManInfo = table.saleManInfo;
  areaInfo = table.areaInfo;
  houseType: any = houseType;
  fitmentStage: any = fitmentStage;
  projectType: any = projectType;
  projectList: any[] = [];
  itemList: any[] = [];
  saleManList: any[] = [];
  areaList: any[] = [];
  departmentList: any[] = [];
  // 查询所有的销售员
  async getSaleManList () {
    const data = {
      offset: 0,
      limit: 50,
      order_by: [{ field: 2200000160826904, sort: 'desc' }]
    }
    const result = await SearchInfo(this.ticket, this.saleManInfo, data)
    for (let i = 0; i < result.length; i++) {
      const field = result[i].fields
      const saleId = result[i].item_id
      const saleName = field[1].values[0].value
      const department = field[4].values[0].title
      const obj = {
        saleName: saleName,
        saleId: saleId,
        department: department
      }
      this.saleManList.push(obj)
    }
  }

  // 查询上海市区域信息
  async getAreaList () {
    const data = {
      where: {
        and: [{ field: 2200000153719584, query: { in: [2300002681549825] } }]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(this.ticket, this.areaInfo, data)
    for (let i = 0; i < result.length; i++) {
      const field = result[i].fields
      const name = field[1].values[0].value
      const value = result[i].item_id
      const obj = {
        name: name,
        value: value
      }
      this.areaList.push(obj)
    }
  }

  // 查询所有门店数据
  async getDepartmentList () {
    const result = await filterInfo(this.ticket)
    for (let i = 0; i < result.length; i++) {
      const obj = {
        name: result[i].title,
        value: result[i].item_id
      }
      this.departmentList.push(obj)
    }
  }

  // 查询当前人员项目信息
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
      let department = ''
      let departmentId = ''
      let projectHometype = ''
      let projectHometypeId = ''
      let projectArea = ''
      let projectAreaId = ''
      let projectStage = ''
      let projectStageId = ''
      let projectType = ''
      let projectTypeId = ''
      let saleMan = ''
      let saleManId = ''
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
          projectAreaId = fields[i].values[0].item_id
        }
        if (fields[i].field_id === field.saleMan) {
          saleMan = fields[i].values[0].title
          saleManId = fields[i].values[0].item_id
        }
        if (fields[i].field_id === field.department) {
          department = fields[i].values[0].title
          departmentId = fields[i].values[0].item_id
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
        areaId: projectAreaId,
        saleMan: saleMan,
        saleManId: saleManId,
        department: department,
        departmentId: departmentId,
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
    this.getSaleManList()
    this.getAreaList()
    this.getDepartmentList()
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
    const area: any = document.getElementById('projectArea')
    const areaValue = area.options[area.selectedIndex].value
    const saleMan: any = document.getElementById('saleMan')
    const saleManValue = saleMan.options[saleMan.selectedIndex].value

    const department: any = document.getElementById('department')
    const departmentValue = department.options[department.selectedIndex].value
    const data = {
      fields: {
        [field.projectCustom]: projectCustom.value,
        [field.telephone]: telephone.value,
        [field.projectAddress]: address.value,
        [field.projectVillage]: village.value,
        [field.projectStage]: [stageValue],
        [field.projectHometype]: [typeValue],
        [field.projectType]: [TypeValue],
        [field.projectArea]: [areaValue],
        [field.saleMan]: [saleManValue],
        [field.department]: [departmentValue]
      }
    }
    await updateTable(this.ticket, project.itemId, data)
    this.$emit('reload')
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
.floatDiv {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: #eee;
  opacity: 0.92;
  z-index: 10;
  top: 0;
}
.infoDiv {
  position: fixed;
  width: 100%;
  height: 40%;
  overflow: auto;
  bottom: 50px;
  z-index: 14;
  background-color: #fff;
}
</style>
