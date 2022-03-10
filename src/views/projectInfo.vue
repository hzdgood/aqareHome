<template>
  <div class="centerDiv">
    <div v-for="project in projectList" :key="project.id">
      <div class="lineDiv">
        <span>项目类型：</span>
        <input type="text" readonly :value="project.projectType" />
        <span>主&nbsp;&nbsp;项&nbsp;&nbsp;目：</span>
        <input type="text" readonly :value="project.masterProject"/>
      </div>
      <div class="lineDiv">
        <span>客户名称：</span>
        <input type="text" readonly :value="project.projectCustom"
        />
        <span>联系方式：</span>
        <input type="text" readonly :value="project.telephone" />
      </div>
      <div class="lineDiv">
        <span>小区名称：</span>
        <input type="text" readonly :value="project.projectVillage"/>
        <span>客户房型：</span>
        <input type="text" readonly :value="project.projectHometype"/>
      </div>
      <div class="lineDiv">
        <span>所在区域：</span>
        <input type="text" readonly :value="project.projectArea" />
        <span>装修阶段：</span>
        <input type="text" readonly :value="project.projectStage"/>
      </div>
      <div class="lineDiv">
        <span>客户地址：</span>
        <input class="projectAddress" type="text" readonly :value="project.projectAddress"/>
      </div>
    </div>
  </div>
</template>
<script lang='ts'>
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field, user } from '@/config/config'
@Component({})
export default class Home extends Vue {
  projectInfo = table.projectInfo;
  projectList: any[] = [];

  @Watch('$store.state.reloadStatus')
  reloadPage () {
    setTimeout(this.onloadFunction, 2000)
  }

  async onloadFunction () {
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
    const result = await SearchInfo(this.projectInfo, data)
    for (let j = 0; j < result.length; j++) {
      const fields = result[j].fields
      let projectCustom = ''
      let telephone = ''
      let projectVillage = ''
      let projectHometype = ''
      let projectArea = ''
      let projectStage = ''
      let projectAddress = ''
      let projectType = ''
      let masterProject = ''
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
        if (fields[i].field_id === field.masterProject) {
          const values = fields[i].values[0].name
          masterProject = values
        }
      }
      const obj = {
        id: j,
        projectCustom: projectCustom,
        telephone: telephone,
        projectVillage: projectVillage,
        projectHometype: projectHometype,
        projectArea: projectArea,
        projectStage: projectStage,
        projectAddress: projectAddress,
        projectType: projectType,
        masterProject: masterProject
      }
      this.projectList.push(obj)
    }
  }

  async mounted () {
    this.onloadFunction()
  }
}
</script>
<style scoped>

</style>
