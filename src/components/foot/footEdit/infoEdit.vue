<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">项目信息</div>
      <button @click="addClick()">新增</button>
      <div id="projectList" v-for="project in projectList" :key="project.id">
        <div class="lineDiv">
          <span>项目类型</span>
          <span>
            <select :id="project.id + 'projectType'">
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
          <span>项目名称</span>
          <span>
            <input
              :id="project.id + 'projectCustom'"
              type="text"
              :value="project.customer"
            />
          </span>
          <span>联系方式</span>
          <span>
            <input
              :id="project.id + 'telephone'"
              type="text"
              :value="project.telephone"
            />
          </span>
        </div>
        <div class="lineDiv">
          <span>小区名称</span>
          <span>
            <input
              :id="project.id + 'projectVillage'"
              type="text"
              :value="project.village"
            />
          </span>
          <span>客户房型</span>
          <span>
            <select :id="project.id + 'projectHometype'">
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
          <span>销售人员</span>
          <span>
            <select :id="project.id + 'saleMan'">
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
          <span>所属门店</span>
          <span>
            <select :id="project.id + 'department'">
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
          <span>所在区域</span>
          <span>
            <select :id="project.id + 'projectArea'">
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
          <span>装修阶段</span>
          <span>
            <select :id="project.id + 'projectStage'">
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
        <div class="lineDiv">
          <span>客户地址：</span>
          <span>
            <input
              :id="project.id + 'projectAddress'"
              class="projectAddress"
              type="text"
              :value="project.address"
            />
          </span>
        </div>
        <button @click="saveClick(project)">保存</button>
        <button @click="close()">关闭</button>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import {
  SearchInfo, updateTable, deleteItem, filterInfo
} from '@/config/interFace'
import {
  table, field, houseType, projectType
} from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  customerInfo = table.customerInfo;
  saleManInfo = table.saleManInfo;
  areaInfo = table.areaInfo;
  houseType: any = houseType;
  projectType: any = projectType;
  projectList: any[] = [];
  itemList: any[] = [];
  saleManList: any[] = [];
  areaList: any[] = [];
  departmentList: any[] = [];
  userId = localStorage.getItem('userId');

  // 查询所有的销售员
  async getSaleManList () {
    const data = {
      offset: 0,
      limit: 50,
      order_by: [{ field: 2200000160826904, sort: 'desc' }]
    }
    const result = await SearchInfo(this.saleManInfo, data)
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
    const result = await SearchInfo(this.areaInfo, data)
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
    const data = {
      search: { fields: [], keywords: ['门店'] },
      offset: 0,
      limit: 20
    }
    const tableId: any = 2100000016791383
    const result = await filterInfo(tableId, data)
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
    const result = await SearchInfo(this.projectInfo, data)
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

  // 主项目绑定
  async bindClick (project: any) {
    for (let i = 0; i < this.itemList.length; i++) {
      const old = this.itemList[i]
      if (old !== project.itemId) {
        const data = { fields: { [field.masterProject]: [2] } }
        await updateTable(this.itemList[i], data)
      }
    }
    const data = { fields: { [field.masterProject]: [1] } }
    await updateTable(project.itemId, data)
    setTimeout(this.getInfoList, 1000)
  }

  // 新增一条数据 主要就是修改伙伴云记录
  async addClick () {
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
    const itemId = res[0].item_id
    if (itemId) {
      const data = { fields: { 2200000184840062: [1] } }
      await updateTable(itemId, data)
      setTimeout(this.getInfoList, 1000)
    }
  }

  // 删除一条数据
  async deleteClick (project: any) {
    if (project.masterProject === '是') {
    } else {
      const data = { item_ids: [project.itemId] }
      await deleteItem(table.projectInfo, data)
      setTimeout(this.getInfoList, 1000)
    }
  }

  // 关闭
  close () {
    this.$emit('reload')
  }

  // 保存按钮 获取当前项目全部数据
  async saveClick (project: any) {
    const telephone: any = document.getElementById(project.id + 'telephone')
    const projectCustom: any = document.getElementById(
      project.id + 'projectCustom'
    )
    const address: any = document.getElementById(project.id + 'projectAddress')
    const village: any = document.getElementById(project.id + 'projectVillage')
    const stage: any = document.getElementById(project.id + 'projectStage')
    const stageValue = stage.options[stage.selectedIndex].value
    const hometype: any = document.getElementById(
      project.id + 'projectHometype'
    )
    const typeValue = hometype.options[hometype.selectedIndex].value
    const Type: any = document.getElementById(project.id + 'projectType')
    const TypeValue = Type.options[Type.selectedIndex].value
    const area: any = document.getElementById(project.id + 'projectArea')
    const areaValue = area.options[area.selectedIndex].value
    const saleMan: any = document.getElementById(project.id + 'saleMan')
    const saleManValue = saleMan.options[saleMan.selectedIndex].value
    const department: any = document.getElementById(project.id + 'department')
    const departmentValue = department.options[department.selectedIndex].value
    // 加入校验
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
    const res: any = await updateTable(project.itemId, data)
    if (res.message) {
      alert(res.message)
    } else {
      this.$emit('reload')
    }
  }
  // 全部有数据才能够提交
}
</script>
<style scoped></style>
