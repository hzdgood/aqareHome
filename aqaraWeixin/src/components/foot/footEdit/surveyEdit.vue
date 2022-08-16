<template>
  <div>
    <div class='floatDiv'></div>
    <div class='infoDiv'>
      <div class='headerDiv'>工勘</div>
      <div class='addSite'>
        <button class='addButton' @click='addClick()' v-show='addShow'>
          新增 +
        </button>
      </div>
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
            <input id='appointmentTime' type='text' readonly />
          </td>
        </tr>
        <tr>
          <td>预计时长</td>
          <td>
            <input id='EDuration' type='text' />
          </td>
        </tr>
        <tr>
          <td>工勘人员</td>
          <td>
            <select id='surveyPerson' multiple>
              <option
                v-for='item in saleManList'
                :value='item.saleId'
                :key='item.saleId'
              >
                {{ item.saleName }}
              </option>
            </select>
          </td>
        </tr>
        <tr>
          <td>工勘类型</td>
          <td>
            <select id='surveyType'>
               <option value="1">工勘</option>
               <option value="2">交底</option>
            </select>
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
        <input
          class='saveButton'
          type='button'
          @click='saveClick()'
          value='保存'
        />
        <input
          class='closeButton'
          type='button'
          @click='closeClick()'
          value='关闭'
        />
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { SearchInfo, addInfo, uploadImg } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  addShow = true;
  userId = localStorage.getItem('userId')
  itemId = ''
  projectCode = ''
  saleManList: any[] = [];
  async mounted () {
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
  }

  async getSaleManList () {
    const data = {
      where: { and: [{ field: 2200000257456339, query: { in: [2, 3] } }] },
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

  addClick () {
    console.log()
  }

  saveClick () {
    console.log()
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>

<style>
</style>
