<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">新增定金收款</div>
      <div class="lineDiv">
        <span>项目名称：</span>
        <input id="projectName" type="text" readonly :value="projectCode" />
        <span>收款类型：</span>
        <input id="projectType" type="text" readonly value="定金" />
      </div>
      <div class="lineDiv">
        <span>收款方式：</span>
        <select id="collectType">
          <option
            v-for="collectType in collectType"
            :value="collectType.value"
            :key="collectType.value"
          >
            {{ collectType.name }}
          </option>
        </select>
        <span>收款金额：</span>
        <input id="collectMoney" type="text" />
      </div>
      <div class="lineDiv">
        <span>上传图片：</span>
        <input id="file" type="file" />
      </div>
      <div class="lineDiv">
        <input type="button" @click="saveClick()" value="保存" />
        <input type="button" @click="closeClick()" value="关闭" />
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { table, field, collectType } from '@/config/config'
import { SearchInfo, addInfo, uploadImg } from '@/config/interFace'
// addInfo
@Component({})
export default class Home extends Vue {
  collectTable = table.collectTable;
  projectInfo = table.projectInfo;
  collectType = collectType;
  projectCode = '';
  itemId = '';
  async mounted () {
    const data = {
      search: { fields: [], keywords: ['小云'] },
      where: {
        and: [
          {
            field: 2200000184791041,
            query: { in: [1] }
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [{ field: 2200000150460774, sort: 'desc' }]
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
  }

  async saveClick () {
    // const projectName: any = document.getElementById("projectName");
    // const projectType: any = document.getElementById("projectType");
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
      [field.pName]: [this.itemId],
      [field.pType]: [1],
      [field.cType]: [cType],
      [field.cMoney]: collectMoney.value,
      [field.uploadFile]: [res.file_id]
    }
    await addInfo(this.collectTable, data)
    // this.$emit("close");
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>

<style scoped>
</style>
