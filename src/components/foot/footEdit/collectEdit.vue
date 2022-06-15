<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增收款</div>
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
              <select id="projectType" @change="typeChange">
                <option value="1">定金</option>
                <option value="2">全款</option>
              </select>
            </td>
          </tr>
          <tr v-show="quotationStatus">
            <td>报价单</td>
            <td>
              <input id="quotation" type="text" :value="title" readonly />
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
            <td><input id="collectMoney" type="text" :value="receivable" /></td>
          </tr>
          <tr>
            <td>上传图片</td>
            <td><input id="file" type="file" accept="image/*" /></td>
          </tr>
        </table>
      </div>
      <div v-if="errorStatus == false">
        {{ errorMsg }}
      </div>
      <div class="buttonSite">
        <input
          v-show="errorStatus"
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
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table, field, collectType } from '@/config/config'
import { SearchInfo, addInfo, uploadImg, procedure, logInsert } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  collectType = collectType;
  projectCode = '';
  errorMsg = '';
  errorStatus = true;
  quotationStatus = false;
  itemId = '';
  projectId = '';
  title = '';
  quotationId = '';
  receivable = '';
  userId = localStorage.getItem('userId');
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
        if (fields[j].field_id === field.projectId) {
          const values = fields[j].values[0].value
          this.projectId = values
        }
      }
    }
    if (this.projectCode === '') {
      this.errorMsg = '请先添加项目！'
      this.errorStatus = false
    }
  }

  async saveClick () {
    const collectMoney: any = document.getElementById('collectMoney')
    let projectType: any = document.getElementById('projectType')
    projectType = projectType.options[projectType.selectedIndex].value
    let collectType: any = document.getElementById('collectType')
    collectType = collectType.options[collectType.selectedIndex].value
    let file: any = document.getElementById('file')
    file = file.files[0]
    if (collectMoney.value === '') {
      alert('请输入金额!')
      return
    }
    if (typeof file === 'undefined') {
      alert('请上传图片!')
      return
    }
    this.$store.dispatch('Loading')
    // 上传图片
    if (projectType === '1') {
      this.errorStatus = false
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
          [field.cType]: [collectType],
          [field.cMoney]: collectMoney.value,
          [field.uploadFile]: [res.file_id]
        }
      }
      const result = await addInfo(table.collectTable, data)
      await logInsert([localStorage.getItem('localName') + ',收款定金成功: ' + collectMoney.value])
      this.run(result)
    } else {
      if (this.title === '') {
        alert('请生成报价单！')
        this.$store.dispatch('Loading')
        return
      }
      this.errorStatus = false
      const formData = new FormData()
      formData.append('source', file)
      formData.append('name', file.name)
      formData.append('domain', 'app.huoban.com')
      formData.append('type', 'attachment')
      const res = await uploadImg(formData)
      const data = {
        fields: {
          [field.pName]: [this.itemId],
          [field.pType]: [2],
          2200000180591045: [this.quotationId],
          [field.cType]: [collectType],
          [field.cMoney]: collectMoney.value,
          [field.uploadFile]: [res.file_id]
        }
      }
      const result = await addInfo(table.collectTable, data)
      await logInsert([localStorage.getItem('localName') + ',收款全款成功: ' + collectMoney.value])
      this.run(result)
    }
  }

  async typeChange () {
    this.$store.dispatch('Loading')
    let projectType: any = document.getElementById('projectType')
    projectType = projectType.options[projectType.selectedIndex].value
    if (projectType === '2') {
      this.quotationStatus = true
      const obj1 = {
        where: {
          and: [
            {
              query: { or: [{ eqm: [this.projectId] }] },
              query_option_mappings: [-1],
              field: 1102001110000000 // 项目ID
            }
          ]
        },
        offset: 0,
        limit: 20
      }
      const result1 = await SearchInfo(table.proposal, obj1)
      if (result1.length === 0) {
        alert('请生成报价单！')
        this.$store.dispatch('Loading')
        return
      }
      this.title = result1[0].title
      this.quotationId = result1[0].item_id
      const fields = result1[0].fields
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === 2200000180589759) {
          const values = fields[i].values[0].value
          this.receivable = values
        }
      }
    } else if (projectType === '1') {
      this.quotationStatus = false
      this.receivable = ''
    }
    this.$store.dispatch('Loading')
  }

  async run (result: any) {
    var obj = {
      action: 'spec_item',
      data: { item: { item_id: result.item_id } }
    }
    await procedure('3000000000246006', obj)
    this.$store.dispatch('Loading')
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>
<style scoped></style>
