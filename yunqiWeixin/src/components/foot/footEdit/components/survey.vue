<template>
  <div>
    <table class='EditTable'>
      <!-- <tr>
        <td>项目编码</td>
        <td>
          <input id='projectCode' type='text' readonly :value='projectCode'/>
        </td>
      </tr> -->
      <tr>
        <td>预约时间</td>
        <td>
          <a-date-picker
            :defaultValue="moment(survey.appointmentTime, 'YYYY-MM-DD HH:mm:ss')"
            :show-time="{ defaultValue: moment('00:00:00', 'HH:mm:ss') }"
            style='width: 87%'
            format='YYYY-MM-DD HH:mm'
            @change='dateChange'
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
            :defaultValue="survey.surveyPersonnel"
            mode='multiple'
            style='width: 87%'
            placeholder='Please select'
            @change='personChange'
          >
            <a-select-option
              v-for='item in saleManList'
              :key='item.saleId'
              :value='item.saleId'
            >
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
            style='width: 87%'
            @change='typeChange'
          >
            <a-select-option value='1'>工勘</a-select-option>
            <a-select-option value='2'>交底</a-select-option>
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
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { Select, Input, DatePicker } from 'ant-design-vue'
import { table } from '@/config/config'
import { uploadImg, updateTable, deleteItem } from '@/config/interFace'
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
  surveyPersonnel = ''
  appointmentTime = ''
  surveyType: any = ''
  moment = moment

  @Prop({
    type: Object,
    required: true
  })
  survey!: any;

  @Prop({
    type: Array,
    required: true
  })
  saleManList!: any;

  @Prop({
    type: String,
    required: true
  })
  projectCode!: any;

  personChange (value: any) {
    this.survey.surveyPersonnel = value
  }

  dateChange (value: any, date: any) {
    this.survey.appointmentTime = date
  }

  typeChange (value: String) {
    this.survey.surveyType = value
  }

  // 保存
  async saveClick (survey: any) {
    const EDuration: any = document.getElementById('EDuration')
    const file: any = document.getElementById('doorPhoto')
    if (this.survey.surveyType === '工勘') {
      this.survey.surveyType = 1
    } else if (this.survey.surveyType === '交底') {
      this.survey.surveyType = 2
    }
    let data = {}
    if (typeof file.files[0] === 'undefined') {
      data = {
        fields: {
          2200000168338254: this.survey.appointmentTime,
          2200000168338022: EDuration.value,
          2200000169723711: this.survey.surveyPersonnel,
          2200000168613835: [this.survey.surveyType]
        }
      }
    } else {
      const list = await this.upfile(file)
      data = {
        fields: {
          2200000168338254: this.survey.appointmentTime,
          2200000168338022: EDuration.value,
          2200000169723711: this.survey.surveyPersonnel,
          2200000168338023: list,
          2200000168613835: [this.survey.surveyType]
        }
      }
    }
    await updateTable(survey.itemId, data)
    this.$emit('close')
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
    await deleteItem(table.survey, data)
    this.$emit('delete', survey)
  }
}
</script>
