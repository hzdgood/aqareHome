<template>
  <div>
    <div class='floatDiv'></div>
    <div class='infoDiv'>
      <div class='headerDiv'>工勘</div>
      <div class='addSite' v-show='addShow'>
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
        <table class='EditTable'>
          <tr>
            <td>项目编码</td>
            <td>
              <input id='projectCode' type='text' readonly :value='projectCode' />
            </td>
          </tr>
          <tr>
            <td>预约时间</td>
            <td>
              <a-date-picker
                style="width: 87%"
                :defaultValue="moment(survey.appointmentTime, 'YYYY-MM-DD HH:mm:ss')"
                format="YYYY-MM-DD HH:mm:ss"
                @change="dateChange"
                :disabled-date="disabledDate"
                :disabled-time="disabledDateTime"
                :show-time="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
              />
            </td>
          </tr>
          <tr>
            <td>预计时长</td>
            <td>
              <input id='EDuration' type='text' :value="survey.EDuration"/>
            </td>
          </tr>
          <tr>
            <td>工勘人员</td>
            <td>
              <a-select
                :defaultValue="survey.surveyPerson"
                mode="multiple"
                style="width: 87%"
                placeholder="Please select"
                @change="personChange"
              >
                <a-select-option v-for='item in saleManList' :key='item.saleId' :value='item.saleId'>
                  {{ item.saleName }}
                </a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>工勘类型</td>
            <td>
              <a-select
                :defaultValue="survey.surveyType"
                style="width: 87%"
                @change="typeChange"
              >
                <a-select-option value="1">工勘</a-select-option>
                <a-select-option value="2">交底</a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>上门照片</td>
            <td>
              <input id='doorPhoto' type='file' />
            </td>
          </tr>
        </table>
        <div class='buttonSite'>
          <button class='saveButton' @click='saveClick(survey)'>保存</button>
          <button class='saveButton' @click='deleteClick(survey)'>删除</button>
          <button class='closeButton' @click='closeClick()'>关闭</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { Select, Input, DatePicker } from 'ant-design-vue'
import { SearchInfo, addInfo, uploadImg, SearchUser, updateTable, deleteItem } from '@/config/interFace'
import moment from 'moment'
import 'moment/locale/zh-cn'
@Component({
  name: 'survey',
  components: {
    'a-select': Select,
    'a-select-option': Select.Option,
    'ant-Input': Input,
    'a-date-picker': DatePicker
  }
})
export default class Home extends Vue {
  moment = moment
  addShow = true;
  userId = localStorage.getItem('userId')
  itemId = ''
  projectCode = ''
  saleManList: any[] = []
  surveyList: any[] = []
  index = 0
  appointmentTime = ''
  surveyPersonnel: any = []
  surveyType: any = ''
  async mounted () {
    this.getInfoList()
    this.getSaleManList()
  }

  range (start:any, end: any) {
    const result = []
    for (let i = start; i < end; i++) {
      result.push(i)
    }
    return result
  }

  personChange (value: any) {
    this.surveyPersonnel = value
  }

  dateChange (value: any, date: any) {
    this.appointmentTime = date
  }

  typeChange (value: String) {
    this.surveyType = value
  }

  // 获取工勘单
  async getSurveyList () {
    const req = {
      where: { and: [{ field: 2200000146063366, query: { in: [this.itemId] } }] },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.survey, req)
    const personList = []
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const itemId = result[i].item_id
      let time, duration, person, type
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000168338254) { // 时间
          time = fields[j].values[0].value
          this.appointmentTime = time
        } else if (fields[j].field_id === 2200000168338022) {
          duration = fields[j].values[0].value // 时长
        } else if (fields[j].field_id === 2200000169723711) {
          person = fields[j].values // 参与人
          for (let m = 0; m < person.length; m++) {
            personList.push(person[m].user_id)
          }
          this.surveyPersonnel = personList
        } else if (fields[j].field_id === 2200000168613835) {
          type = fields[j].values[0].name // 类型
          this.surveyType = fields[j].values[0].id
        }
      }
      const obj = {
        key: this.index + 1,
        itemId: itemId,
        appointmentTime: time,
        EDuration: duration,
        surveyPerson: personList,
        surveyType: type
      }
      this.surveyList.push(obj)
    }
    if (this.surveyList.length !== 0) {
      this.addShow = false
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
    this.addShow = false
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
      appointmentTime: '',
      EDuration: '2',
      surveyType: [1]
    }
    this.surveyType = 1
    this.surveyList.push(obj)
  }

  // 保存
  async saveClick (survey: any) {
    const EDuration: any = document.getElementById('EDuration')
    const file: any = document.getElementById('doorPhoto')
    let data = {}
    if (typeof file.files[0] === 'undefined') {
      data = {
        fields: {
          2200000168338254: this.appointmentTime,
          2200000168338022: EDuration.value,
          2200000169723711: this.surveyPersonnel,
          2200000168613835: [this.surveyType]
        }
      }
    } else {
      const list = await this.upfile(file)
      data = {
        fields: {
          2200000168338254: this.appointmentTime,
          2200000168338022: EDuration.value,
          2200000169723711: this.surveyPersonnel,
          2200000168338023: list,
          2200000168613835: [this.surveyType]
        }
      }
    }
    await updateTable(survey.itemId, data)
    this.$emit('close')
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

  async deleteClick (survey: any) {
    const data = { item_ids: [survey.itemId] }
    this.surveyList = []
    await deleteItem(table.survey, data)
    this.addShow = true
  }

  closeClick () {
    this.$emit('close')
  }

  disabledDate (current: any) {
    return current && current < moment().endOf('day')
  }

  disabledDateTime () {
    return {
      disabledHours: () => this.range(0, 24).splice(4, 20),
      disabledMinutes: () => this.range(30, 60),
      disabledSeconds: () => [55, 56]
    }
  }
}
</script>

<style>
</style>
