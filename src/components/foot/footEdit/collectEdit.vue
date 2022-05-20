<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增定金收款</div>
      <div v-if="errorStatus == true">
        <table class="EditTable">
          <tr>
            <td>项目名称</td>
            <td>
              <input
                id="projectName"
                type="text"
                readonly
                :value="projectCode"
              />
            </td>
          </tr>
          <tr>
            <td>收款类型</td>
            <td>
              <input id="projectType" type="text" readonly value="定金" />
            </td>
          </tr>
          <tr>
            <td>收款方式</td>
            <td>
              <select id="collectType">
                <option
                  v-for="item in collectType"
                  :value="item.value"
                  :key="item.value"
                >
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>收款金额</td>
            <td><input id="collectMoney" type="text" /></td>
          </tr>
          <tr>
            <td>上传图片</td>
            <td><input id="file" type="file" /></td>
          </tr>
        </table>
        <div class="buttonSite">
          <input
            class="saveButton"
            type="button"
            @click="saveClick()"
            value="保存"
          />
          <input
            class="closeButton"
            type="button"
            @click="closeClick()"
            value="关闭"
          />
        </div>
      </div>
      <div v-if="errorStatus == false">
        {{ errorMsg }}
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, collectType } from '@/config/config'
import { SearchInfo, addInfo, uploadImg } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  collectTable = table.collectTable;
  projectInfo = table.projectInfo;
  collectType = collectType;
  projectCode = '';
  errorMsg = '';
  errorStatus = true;
  itemId = '';
  userId = localStorage.getItem('userId');
  async mounted () {
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
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      this.itemId = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectCode) {
          this.projectCode = fields[j].values[0].value
        }
      }
    }
    if (this.projectCode === '') {
      this.errorMsg = '请先添加项目！'
      this.errorStatus = false
    }
  }

  async saveClick () {
    const collectType: any = document.getElementById('collectType')
    const collectMoney: any = document.getElementById('collectMoney')
    const cType = collectType.options[collectType.selectedIndex].value
    let file: any = document.getElementById('file')
    file = file.files[0]
    const formData = new FormData()
    formData.append('source', file)
    formData.append('name', file.name)
    formData.append('domain', 'app.huoban.com')
    formData.append('type', 'attachment')
    const res = await uploadImg(formData)
    const data = {
      fields: {
        [field.pName]: [this.itemId],
        [field.pType]: [1],
        [field.cType]: [cType],
        [field.cMoney]: collectMoney.value,
        [field.uploadFile]: [res.file_id]
      }
    }
    await addInfo(this.collectTable, data)
    this.$emit('close')
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
<style scoped></style>
