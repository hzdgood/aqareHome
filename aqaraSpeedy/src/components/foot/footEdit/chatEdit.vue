<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">项目信息</div>
      <div id="projectList" v-for="project in projectList" :key="project.id">
        <table class="EditTable">
          <tr>
            <td>项目名称</td>
            <td>
              <input
                :id="project.id + 'projectCustom'"
                type="text"
                :value="project.customer"
              />
            </td>
          </tr>
          <tr>
            <td>联系方式</td>
            <td>
              <input
                :id="project.id + 'telephone'"
                type="text"
                :value="project.telephone"
              />
            </td>
          </tr>
          <tr>
            <td>项目类型</td>
            <td>
              <select :id="project.id + 'projectType'">
                <option :value="project.projectTypeId">
                  {{ project.projectType }}
                </option>
                <option
                  v-for="item in projectType"
                  :value="item.value"
                  :key="item.value"
                >
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>小区名称</td>
            <td>
              <input
                :id="project.id + 'projectVillage'"
                type="text"
                :value="project.village"
              />
            </td>
          </tr>
          <tr>
            <td>客户房型</td>
            <td>
              <select :id="project.id + 'projectHometype'">
                <option :value="project.hometypeId">
                  {{ project.hometype }}
                </option>
                <option
                  v-for="item in houseType"
                  :value="item.value"
                  :key="item.value"
                >
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>销售人员</td>
            <td>
              <select :id="project.id + 'saleMan'">
                <option :value="project.saleManId">
                  {{ project.saleMan }}
                </option>
                <option
                  v-for="item in saleManList"
                  :value="item.saleId"
                  :key="item.saleId"
                >
                  {{ item.saleName }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>装修阶段</td>
            <td>
              <select :id="project.id + 'projectStage'">
                <option :value="project.stageId">
                  {{ project.stage }}
                </option>
                <option
                  v-for="item in decorationStage"
                  :value="item.value"
                  :key="item.value"
                >
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>所属门店</td>
            <td>
              <select :id="project.id + 'department'">
                <option :value="project.departmentId">
                  {{ project.department }}
                </option>
                <option
                  v-for="item in departmentList"
                  :value="item.value"
                  :key="item.value"
                >
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>所在区域</td>
            <td>
              <select :id="project.id + 'projectArea'">
                <option :value="project.areaId">
                  {{ project.area }}
                </option>
                <option
                  v-for="item in areaList"
                  :value="item.value"
                  :key="item.value"
                >
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>客户地址</td>
            <td>
              <input
                :id="project.id + 'projectAddress'"
                class="projectAddress"
                type="text"
                :value="project.address"
              />
            </td>
          </tr>
        </table>
        <div class="buttonSite">
          <button class="saveButton" @click="saveClick(project)">保存</button>
          <button class="closeButton" @click="close()">关闭</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable } from '@/config/interFace'
import { table, field, houseType, projectType, decorationStage } from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectList: any[] = [];
  itemList: any[] = [];
  saleManList: any[] = [];
  areaList: any[] = [];
  houseType: any = houseType;
  projectType: any = projectType;
  decorationStage: any = decorationStage;
  departmentList: any[] = [];
  chatId = localStorage.getItem('chatID');

  // 查询所有的销售员
  async getSaleManList () {
    const data = {
      where: {
        and: [{
          field: 2200000257456339,
          query: { in: [2, 3] }
        }]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.saleManInfo, data)
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
        and: [{ field: 2200000153719584, query: { eqm: [2300002681549825] } }]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.areaInfo, data)
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
      where: {
        and: [{
          field: 2200000169987088,
          query: { in: [5] }
        }]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.departmentInfo, data)
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
    // 查询当前客户的所有的项目-->chatId
    this.projectList = []
    const data = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [this.chatId] }] },
            query_option_mappings: [-1],
            field: 2200000172376106
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, data)
    for (let j = 0; j < result.length; j++) {
      const fields = result[j].fields
      const itemId: any = result[j].item_id
      this.itemList.push(itemId)
      let projectCustom = ''
      let telephone = ''
      let projectVillage = ''
      let projectAddress = ''
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
