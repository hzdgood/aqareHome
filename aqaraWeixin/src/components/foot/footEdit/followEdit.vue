<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增跟进</div>
      <table class="EditTable">
        <tr>
          <td>跟进信息</td>
          <td>
            <input
              id="followInfo"
              type="text"
              :value="followInfo"
            />
          </td>
        </tr>
        <tr>
          <td>上传附件</td>
          <td>
            <input
                id="file"
                type="file"
                multiple
                accept="image/*"
                placeholder="请选择文件"
              />
          </td>
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
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { table } from '@/config/config'
import { masterReq } from '@/config/common'
import { SearchInfo, addInfo, uploadImg } from '@/config/interFace'
import moment from 'moment'
@Component({})
export default class Home extends Vue {
  followInfo = ''
  itemId = ''
  salesId = ''
  userId = localStorage.getItem('userId');
  async mounted () {
    let result: any = {}
    const data = masterReq(this.userId)
    result = await SearchInfo(table.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      // const fields = result[i].fields
      this.itemId = result[i].item_id
    }
    // const req = huobanUser()
    // result = await SearchInfo(table.saleManInfo, req)
    // for (let i = 0; i < result.length; i++) {
    //   this.salesId = result[0].user_id
    // }
  }

  async saveClick () {
    const file: any = document.getElementById('file')
    let fileId = null
    if (typeof file.files[0] !== 'undefined') {
      fileId = await this.upfile(file)
    }
    const date = new Date()
    const d = date ? moment(date).format('YYYY-MM-DD') : ''
    console.log(d)
    this.$emit('close')
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
