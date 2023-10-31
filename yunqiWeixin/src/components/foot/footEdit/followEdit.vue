<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增跟进</div>
      <table class="EditTable">
        <tr>
          <td>跟进人员</td>
          <td>
            <select id="followPerson">
              <option v-for="item in personList" :key="item.id" :value="item.id">
                {{item.name}}
              </option>
            </select>
          </td>
        </tr>
        <tr>
          <td>跟进信息</td>
          <td>
            <input id="followInfo" type="text"/>
          </td>
        </tr>
        <tr>
          <td>上传附件</td>
          <td>
            <input id="file" type="file" multiple accept="image/*" placeholder="请选择文件"/>
          </td>
        </tr>
      </table>
      <div class="buttonSite">
        <button class="saveButton" @click="saveClick()">保存</button>
        <button class="closeButton" @click="closeClick()">关闭</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table } from '@/config/config'
import { masterReq } from '@/config/common'
import { SearchInfo, huobanUser, addInfo, uploadImg } from '@/config/interFace'
import moment from 'moment'
@Component({})
export default class Home extends Vue {
  followInfo = ''
  itemId = ''
  salesId = ''
  personList: any[] = []
  userId = localStorage.getItem('userId');
  localName = localStorage.getItem('localName')
  async mounted () {
    let result: any = {}
    const data = masterReq(this.userId)
    result = await SearchInfo(table.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      this.itemId = result[i].item_id
    }
    const result1 = await huobanUser()
    for (let i = 0; i < result1.length; i++) {
      const obj = {
        id: result1[i].user_id,
        name: result1[i].name
      }
      this.personList.push(obj)
    }
  }

  async saveClick () {
    const followPerson: any = document.getElementById('followPerson')
    const followInfo: any = document.getElementById('followInfo')
    this.salesId = followPerson.options[followPerson.selectedIndex].value
    this.followInfo = followInfo.value
    const file: any = document.getElementById('file')
    let fileId = null
    if (typeof file.files[0] !== 'undefined') {
      fileId = await this.upfile(file)
    }
    const date = new Date()
    const d = date ? moment(date).format('YYYY-MM-DD') : ''
    const data = {
      fields: {
        2200000316783265: d,
        2200000316783266: [this.salesId], // 更新人
        2200000316783267: this.followInfo, // 内容
        2200000316783268: fileId, // 上传附件
        2200000316783324: [this.itemId] // 关联项目
      }
    }
    await addInfo('2100000039691275', data)
    this.$emit('close')
  }

  closeClick () {
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
}
</script>
