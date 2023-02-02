<template>
  <div>
    <div class='floatDiv'></div>
    <div class='infoDiv'>
      <div class='headerDiv'>工勘/交底</div>
      <div class='addSite'>
        <button class='addButton' @click='addClick()' >新增 +</button>
        <button class='closeButton' @click='closeClick()'>关闭</button>
      </div>
      <div>
        <table class='EditTable'>
          <tr>
            <td>工勘文件</td>
            <td><input id='signUpload' type='file' /></td>
          </tr>
          <tr>
            <td colspan="2">
              <input class='saveButton' type="button" value="提交" @click="signUpload" />
            </td>
          </tr>
        </table>
      </div>
      <div id="surveyList" v-for="survey in surveyList" :key="survey.id">
        <my-survey
          :survey="survey"
          :projectCode="projectCode"
          :saleManList="saleManList"
          @close="closeClick"
          @delete="deleteClick"
        ></my-survey>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { Select, Input, DatePicker } from 'ant-design-vue'
import { SearchInfo, addInfo, uploadImg, SearchUser, updateTable, logInsert } from '@/config/interFace'
import survey from './components/survey.vue'
import 'moment/locale/zh-cn'
@Component({
  name: 'surveyEdit',
  components: {
    'a-select': Select,
    'a-select-option': Select.Option,
    'ant-Input': Input,
    'a-date-picker': DatePicker,
    'my-survey': survey
  }
})
export default class Home extends Vue {
  userId = localStorage.getItem('userId')
  itemId = ''
  projectCode = ''
  saleManList: any[] = []
  surveyList: any[] = []
  index = 0

  async mounted () {
    this.getInfoList()
    this.getSaleManList()
  }

  // 获取工勘单
  async getSurveyList () {
    this.surveyList = []
    const req = {
      where: { and: [{ field: 2200000146063366, query: { in: [this.itemId] } }] },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.survey, req)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const itemId = result[i].item_id
      let time = null
      const personList = []
      let duration, person, type
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000168338254) { // 时间
          time = fields[j].values[0].value
        } else if (fields[j].field_id === 2200000168338022) {
          duration = fields[j].values[0].value // 时长
        } else if (fields[j].field_id === 2200000169723711) {
          person = fields[j].values // 参与人
          for (let m = 0; m < person.length; m++) {
            personList.push(person[m].user_id)
          }
        } else if (fields[j].field_id === 2200000168613835) {
          type = fields[j].values[0].id // 类型
        }
      }
      const obj = {
        key: this.index + 1,
        itemId: itemId,
        appointmentTime: time,
        EDuration: duration,
        surveyPersonnel: personList,
        surveyType: type
      }
      this.surveyList.unshift(obj)
    }
  }

  // 获取项目code，名称
  async getInfoList () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [this.userId] }] },
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
    const result = await SearchInfo(table.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      this.itemId = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectCode) {
          this.projectCode = fields[j].values[0].value
        }
      }
    }
    this.getSurveyList()
  }

  // 获取参与人
  async getSaleManList () {
    const result = await SearchUser()
    for (let i = 0; i < result.length; i++) {
      const saleId = result[i].user_id
      const saleName = result[i].name
      const obj = {
        saleName: saleName,
        saleId: saleId
      }
      this.saleManList.push(obj)
    }
  }

  // 新增点击
  async addClick () {
    const data = {
      fields: {
        2200000146063366: [this.itemId], // 项目
        2200000168613835: ['1']
      }
    }
    const res = await addInfo(table.survey, data)
    const obj = {
      key: this.index + 1,
      itemId: res.item_id,
      appointmentTime: null,
      EDuration: '2',
      surveyType: '工勘'
    }
    this.surveyList.push(obj)
  }

  async signUpload () {
    const file: any = document.getElementById('signUpload')
    if (typeof file.files[0] !== 'undefined') {
      const list = await this.upfile(file)
      const data = {
        fields: {
          2200000262572772: list
        }
      }
      await updateTable(this.itemId, data)
      await logInsert('新增工勘')
      this.$emit('close')
    }
  }

  async upfile (file: any) {
    const list = []
    for (let i = 0; i < file.files.length; i++) {
      const files = file.files[i]
      const formData = new FormData()
      formData.append('source', files)
      formData.append('name', files.name)
      formData.append('domain', 'app.huoban.com')
      formData.append('type', 'attachment')
      const res = await uploadImg(formData)
      list.push(res.file_id)
    }
    return list
  }

  closeClick () {
    this.$emit('close')
  }

  deleteClick (survey: any) {
    this.surveyList.forEach((value, index, array) => {
      if (value.itemId === survey.itemId) {
        array.splice(index, 1)
      }
    })
  }
}
</script>

<style>
</style>
