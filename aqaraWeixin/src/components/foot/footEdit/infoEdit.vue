<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">项目信息</div>
      <div class="addSite">
        <button class="addButton" @click="addClick()" v-show="addShow">
          新增 +
        </button>
        <button
          v-if="projectList.length == 0"
          class="closeButton"
          @click="close()"
        >
          关闭
        </button>
      </div>
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
            <td>主项目</td>
            <td>
              {{ project.masterProject }}
            </td>
          </tr>
          <tr>
            <td>*项目类型</td>
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
            <td>*联系方式</td>
            <td>
              <input
                :id="project.id + 'telephone'"
                type="text"
                :value="project.telephone"
              />
            </td>
          </tr>
          <tr>
            <td>*小区名称</td>
            <td>
              <input
                :id="project.id + 'projectVillage'"
                type="text"
                :value="project.village"
              />
            </td>
          </tr>
          <tr>
            <td>*客户房型</td>
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
            <td>*销售人员</td>
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
            <td>*装修进度</td>
            <td>
              <select :id="project.id + 'stage'">
                <option :value="project.stage">
                  {{ project.stageName }}
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
            <td>*所属门店</td>
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
            <td>*所在区域</td>
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
            <td>*客户地址</td>
            <td>
              <input :id="project.id + 'projectAddress'" type="text" :value="project.address"/>
            </td>
          </tr>
          <tr>
            <td>跟踪备注</td>
            <td>
              <input :id="project.id + 'remarks'" type="text" :value="project.remarks" />
            </td>
          </tr>
        </table>
        <div class="buttonSite">
          <button class="saveButton" @click="saveClick(project)">保存</button>
          <button class="closeButton" @click="close()">关闭</button>
        </div>
      </div>
    </div>
    <my-Modal :visible="visible" :modalText="modalText" @close="closeModal()"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, updateTable, deleteItem, logInsert } from '@/config/interFace'
import { table, field, houseType, projectType, decorationStage } from '@/config/config'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'
@Component({
  name: 'infoEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  houseType: any = houseType;
  projectType: any = projectType;
  projectList: any[] = [];
  itemList: any[] = [];
  saleManList: any[] = [];
  areaList: any[] = [];
  departmentList: any[] = [];
  userId = localStorage.getItem('userId');
  decorationStage = decorationStage;
  remarks = '';
  addShow = true;

  visible = false
  modalText = ''
  loadVisible = false

  // 查询所有的销售员
  async getSaleManList () {
    const data = {
      where: { and: [{ field: 2200000257456339, query: { in: [2, 3] } }] }, offset: 0, limit: 20
    }
    const result = await SearchInfo(table.saleManInfo, data)
    for (let i = 0; i < result.length; i++) {
      const field = result[i].fields
      const saleId = result[i].item_id
      const saleName = field[0].values[0].name
      // const department = field[5].values[0].value
      const obj = {
        saleName: saleName,
        saleId: saleId
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
      where: { and: [{ field: 2200000169987088, query: { in: [5] } }] }, offset: 0, limit: 20
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
    // 查询当前客户的所有的项目-->userid
    this.projectList = []
    const data = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.projectUUid
          }
        ]
      }
    }
    const result = await SearchInfo(table.projectInfo, data)
    for (let j = 0; j < result.length; j++) {
      const fields = result[j].fields
      const itemId: any = result[j].item_id
      this.itemList.push(itemId)
      let projectCustom = ''; let telephone = ''; let projectVillage = ''; let projectAddress = ''
      let masterProject = ''; let department = ''; let departmentId = ''; let projectHometype = ''
      let projectHometypeId = ''; let projectArea = ''; let projectAreaId = ''; let projectType = ''
      let projectTypeId = ''; let saleMan = ''; let saleManId = ''; let masterStatus = true
      let stage = ''; let stageName = ''; let remarks = ''
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectStage) {
          stage = fields[i].values[0].id
          stageName = fields[i].values[0].name
        }
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
          if (masterProject === '是') {
            masterStatus = false
          }
        }
        if (fields[i].field_id === field.projectHometype) {
          projectHometype = fields[i].values[0].name
          projectHometypeId = fields[i].values[0].id
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
        if (fields[i].field_id === field.remarks) { //
          remarks = fields[i].values[0].value
          this.remarks = fields[i].values[0].value
        }
      }
      const obj = {
        id: j,
        itemId: itemId,
        stage: stage,
        stageName: stageName,
        customer: projectCustom,
        telephone: telephone,
        village: projectVillage,
        address: projectAddress,
        masterProject: masterProject,
        masterStatus: masterStatus,
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
        remarks: remarks
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
    this.uploadStart()
    this.addShow = false
    if (this.projectList.length >= 1) {
      if (!confirm('该客户已有项目，请确认是否继续创建？')) {
        return
      }
    }
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
    const res = await SearchInfo(table.customerInfo, data)
    const itemId = res[0].item_id
    if (itemId) {
      const data = { fields: { 2200000184840062: [1] } }
      await updateTable(itemId, data)
      setTimeout(this.getInfoList, 1000)
      this.errorInfo('新增成功')
    }
  }

  // 删除一条数据
  async deleteClick (project: any) {
    const data = { item_ids: [project.itemId] }
    await deleteItem(table.projectInfo, data)
    setTimeout(this.getInfoList, 1000)
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
    let hometype: any = document.getElementById(project.id + 'projectHometype')
    hometype = hometype.options[hometype.selectedIndex].value

    let Type: any = document.getElementById(project.id + 'projectType')
    Type = Type.options[Type.selectedIndex].value

    let area: any = document.getElementById(project.id + 'projectArea')
    area = area.options[area.selectedIndex].value

    let saleMan: any = document.getElementById(project.id + 'saleMan')
    saleMan = saleMan.options[saleMan.selectedIndex].value

    let department: any = document.getElementById(project.id + 'department')
    department = department.options[department.selectedIndex].value

    let stage: any = document.getElementById(project.id + 'stage')
    stage = stage.options[stage.selectedIndex].value

    const remarks: any = document.getElementById(project.id + 'remarks')

    // 加入校验
    if (telephone.value === '') {
      this.errorInfo('请输入联系电话！')
      return
    } else if (projectCustom.value === '') {
      this.errorInfo('请输入客户名称！')
      return
    } else if (address.value === '') {
      this.errorInfo('请输入客户地址！')
      return
    } else if (village.value === '') {
      this.errorInfo('请输入小区名称！')
      return
    } else if (hometype === '') {
      this.errorInfo('请选择房屋类型！')
      return
    } else if (area === '') {
      this.errorInfo('请选择客户所在区域！')
      return
    } else if (saleMan === '') {
      this.errorInfo('请选择销售人员！')
      return
    } else if (department === '') {
      this.errorInfo('请选择所属门店！')
      return
    } else if (stage === '') {
      this.errorInfo('请选择装修进度！')
      return
    }
    this.uploadStart()
    const data = {
      fields: {
        [field.projectCustom]: projectCustom.value,
        [field.telephone]: telephone.value,
        [field.projectAddress]: address.value,
        [field.projectVillage]: village.value,
        [field.remarks]: remarks.value,
        [field.projectHometype]: [hometype],
        [field.projectType]: [Type],
        [field.projectArea]: [area],
        [field.saleMan]: [saleMan],
        [field.department]: [department],
        [field.projectStage]: [stage]
      }
    }
    await logInsert('项目编辑:' + remarks.value)
    const res: any = await updateTable(project.itemId, data)
    if (res.message) {
      this.errorInfo(res.message)
    } else {
      this.errorInfo('保存成功')
    }
  }

  errorInfo (str: any) {
    this.visible = true
    this.loadVisible = false
    this.modalText = str
  }

  uploadStart () {
    this.visible = false
    this.loadVisible = true
  }

  closeModal () {
    this.visible = false
  }
}
</script>
