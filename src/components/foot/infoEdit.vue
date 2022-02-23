<template>
  <div v-show="isShow">
    <div v-for="project in projectList" :key="project.id">
      <div class="lineDiv">
        <span>项目类型：</span>
        <span><input type="text" :value="project.projectType" /></span>
      </div>
      <div class="lineDiv">
        <span>客户名称：</span>
        <span><input type="text" :value="project.projectCustom" /></span>
        <span>联系方式：</span>
        <span><input type="text" :value="project.telephone" /></span>
      </div>
      <div class="lineDiv">
        <span>小区名称：</span>
        <span><input type="text" :value="project.projectVillage" /></span>
        <span>客户房型：</span>
        <span><input type="text" :value="project.projectHometype" /></span>
      </div>
      <div class="lineDiv">
        <span>所在区域：</span>
        <span><input type="text" :value="project.projectArea" /></span>
        <span>装修阶段：</span>
        <span><input type="text" :value="project.projectStage" /></span>
      </div>
      <div class="lineDivT">
        <span>客户地址：</span>
        <span><input type="text" :value="project.projectAddress" /></span>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field, user } from '@/config/config'
@Component({})
export default class Home extends Vue {
  @Prop({ default: false }) readonly isShow!: boolean;
  ticket = localStorage.getItem('ticket');
  projectInfo = table.projectInfo;
  projectList: any[] = [];
  async mounted () {
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
      let projectCustom = ''
      let telephone = ''
      let projectVillage = ''
      let projectHometype = ''
      let projectArea = ''
      let projectStage = ''
      let projectAddress = ''
      let projectType = ''
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
        projectType: projectType
      }
      this.projectList.push(obj)
    }
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
