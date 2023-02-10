<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增收款</div>
      <div>
        <table class="EditTable">
          <tr>
            <td>项目名称</td>
            <td>
              <input id="projectName" type="text" :value="projectCode" readonly />
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
                <option v-for="item in collectType" :value="item.value" :key="item.value">
                  {{ item.name }}
                </option>
              </select>
            </td>
          </tr>
          <tr>
            <td>收款金额</td>
            <td><input id="collectMoney" type="text" v-model="collectMoney" /></td>
          </tr>
          <tr>
            <td>上传图片</td>
            <td>
              <input id="file" type="file" accept="image/*" placeholder="请选择文件" multiple />
            </td>
          </tr>
        </table>
      </div>
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
    <my-Modal :visible="visible" :modalText="errorMsg"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import { collectType, table, field } from '@/config/config'
import { getProposal, getLocalSale } from '@/config/common'
import { SearchInfo, logInsert, addInfo, procedure, uploadImg } from '@/config/interFace'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'
@Component({
  name: 'collectEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  quotationStatus = false;
  collectType = collectType;
  collectMoney = '1500';
  visible = false
  loadVisible = false
  title = ''
  quotationId = ''
  errorMsg = '';

  @Prop({
    type: Object,
    required: true
  })
  projectCode: any

  @Prop({
    type: Object,
    required: true
  })
  itemId: any

  @Prop({
    type: Object,
    required: true
  })
  userId: any

  @Prop({
    type: Object,
    required: true
  })
  localName: any

  @Prop({
    type: Object,
    required: true
  })
  projectId: any

  async typeChange () {
    let projectType: any = document.getElementById('projectType')
    projectType = projectType.options[projectType.selectedIndex].value
    if (projectType === '2') {
      this.quotationStatus = true
      const result1 = await SearchInfo(table.proposal, getProposal(this.projectId))
      this.title = result1[0].title
      this.quotationId = result1[0].item_id
      const fields = result1[0].fields
      for (let i = 0; i < fields.length; i++) {
        if (fields[i].field_id === 2200000180589759) {
          const values = fields[i].values[0].value
          this.collectMoney = values
        }
      }
    } else if (projectType === '1') {
      this.quotationStatus = false
      this.collectMoney = ''
    }
  }

  async saveClick () {
    this.loadVisible = true
    const req = getLocalSale(this.localName)
    const result = await SearchInfo(table.saleManInfo, req)
    let salesId = ''
    if (result.length === 0) {
      this.errorInfo('找不到当前销售人员信息！' + this.localName)
      return
    }
    for (let i = 0; i < result.length; i++) {
      salesId = result[0].item_id
    }
    let projectType: any = document.getElementById('projectType')
    projectType = projectType.options[projectType.selectedIndex].value
    let collectType: any = document.getElementById('collectType')
    collectType = collectType.options[collectType.selectedIndex].value
    const file: any = document.getElementById('file')
    if (this.collectMoney === '') {
      this.errorInfo('请输入金额!')
      return
    }
    if (typeof file.files[0] === 'undefined') {
      this.errorInfo('请上传图片!')
      return
    }
    // 上传图片
    if (projectType === '1') {
      const list = await this.upfile(file)
      const data = {
        fields: {
          [field.pName]: [this.itemId],
          [field.pType]: [1],
          [field.cType]: [collectType],
          [field.cMoney]: this.collectMoney,
          [field.uploadFile]: list,
          2200000181625297: [salesId]
        }
      }
      const result = await addInfo(table.collectTable, data)
      this.run(result)
    } else {
      const list = await this.upfile(file)
      const data = {
        fields: {
          [field.pName]: [this.itemId],
          [field.pType]: [2],
          2200000180591045: [this.quotationId],
          [field.cType]: [collectType],
          [field.cMoney]: this.collectMoney,
          [field.uploadFile]: list,
          2200000181625297: [salesId]
        }
      }
      const result = await addInfo(table.collectTable, data)
      this.run(result)
    }
    await logInsert('收款')
  }

  // 启动收款流程
  async run (result: any) {
    var obj = {
      action: 'spec_item',
      data: { item: { item_id: result.item_id } }
    }
    await procedure('3000000000246006', obj)
    this.loadVisible = false
  }

  // 上传文件 获取 file_id
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

  errorInfo (str: any) {
    this.loadVisible = false
    this.visible = true
    this.errorMsg = str
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>

<style>

</style>
