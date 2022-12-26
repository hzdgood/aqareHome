<template>
  <div>
    <div class="addUser">
      <div class="text1">Ta添加的</div>
      <div class="text2">
        员工
        <span v-for="item in userList" :key="item.userid">
          <img :src="item.avatar" width="25px" height="25px"/>
          {{ item.name }}
        </span>
      </div>
    </div>
    <div v-for="project in projectList" :key="project.id">
      <table width="100%" class="projectTable">
        <tr>
          <td colspan="4">
            <span>{{ project.projectCustom }}</span>
            <span>{{ project.masterProject }}</span>
            <span>{{ project.currentTask }}</span>
          </td>
        </tr>
        <tr>
          <td>项目类型</td>
          <td>{{ project.projectType }}</td>
          <td>联系方式</td>
          <td>{{ project.telephone }}</td>
        </tr>
        <tr>
          <td>最终价格</td>
          <td>{{ project.endMoney }}</td>
          <td>收款总额</td>
          <td>{{ project.collectMoney }}</td>
        </tr>
        <tr>
          <td>小区名称</td>
          <td>{{ project.projectVillage }}</td>
          <td>客户房型</td>
          <td>{{ project.projectHometype }}</td>
        </tr>
        <tr>
          <td>所在区域</td>
          <td>{{ project.projectArea }}</td>
          <td>装修阶段</td>
          <td>{{ project.projectStage }}</td>
        </tr>
        <tr>
          <td>客户地址</td>
          <td colspan="3">{{ project.projectAddress }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo, compUser } from '@/config/interFace'
import { table, field } from '@/config/config'
import { masterReq, chatReq } from '@/config/common'
@Component({})
export default class Home extends Vue {
  projectList: any[] = [];
  userId = localStorage.getItem('userId');
  contactType = localStorage.getItem('contactType');
  chatId = localStorage.getItem('chatID');
  follow_user: any = localStorage.getItem('follow_user')
  userList: any[] = []

  @Watch('$store.state.reloadStatus')
  reloadPage () {
    setTimeout(this.onloadFunction, 2000)
  }

  getData () {
    let data = {}
    if (this.contactType === 'single_chat_tools') {
      data = masterReq(this.userId)
    } else {
      data = chatReq(this.chatId)
    }
    return data
  }

  async getUserList () {
    const users: any[] = JSON.parse(this.follow_user)
    for (let i = 0; i < users.length; i++) {
      if (typeof (users[i].oper_userid) !== 'undefined') {
        const res: any = await compUser(users[i].oper_userid)
        if (res.errmsg === 'ok') {
          const obj = {
            userid: res.userid,
            avatar: res.avatar,
            name: res.name
          }
          this.userList.push(obj)
        }
      }
    }
  }

  async onloadFunction () {
    this.projectList = []
    const data = this.getData()
    const result = await SearchInfo(table.projectInfo, data)
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
      let endMoney = ''
      let collectMoney = ''
      let currentTask = ''
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === field.projectCustom) {
          const values = fields[i].values[0].value
          projectCustom = values
        }
        if (fields[i].field_id === 2200000154568276) {
          const values = fields[i].values[0].name
          currentTask = values
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
        if (fields[i].field_id === 2200000151011276) {
          const values = fields[i].values[0].value
          collectMoney = values
        }
        if (fields[i].field_id === 2200000150080670) {
          const values = fields[i].values[0].value
          endMoney = values
        }
        if (fields[i].field_id === field.masterProject) {
          const values = fields[i].values[0].name
          if (values === '是') {
            masterProject = '主项目'
          } else {
            masterProject = '非主项目'
          }
        }
      }
      const obj = {
        id: j,
        currentTask: currentTask,
        projectCustom: projectCustom,
        telephone: telephone,
        projectVillage: projectVillage,
        projectHometype: projectHometype,
        projectArea: projectArea,
        projectStage: projectStage,
        projectAddress: projectAddress,
        projectType: projectType,
        masterProject: masterProject,
        endMoney: endMoney,
        collectMoney: collectMoney

      }
      this.projectList.push(obj)
    }
  }

  async mounted () {
    this.onloadFunction()
    this.getUserList()
  }
}
</script>
<style scoped>
.addUser div{
  padding: 5px 0px 5px 10px;
}
.text1 {
  font-size: 18px;
  font-weight: bold;
}
.text2 img{
  margin: 3px;
  border-radius: 100%;
  font-size: 14px;
}
</style>
