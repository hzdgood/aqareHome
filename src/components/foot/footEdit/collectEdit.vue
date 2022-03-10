<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="lineDiv">
        <span>项目名称：</span>
        <input id="projectName" type="text" readonly :value="projectCode" />
        <span>收款类型：</span>
        <input id="projectType" type="text" readonly value="定金" />
      </div>
      <div class="lineDiv">
        <span>收款方式：</span>
        <select id="collectType">
          <option v-for="collectType in collectType" :key="collectType.value">
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
import { SearchInfo, addInfo } from '@/config/interFace'
// addInfo
@Component({})
export default class Home extends Vue {
  ticket = localStorage.getItem('ticket');
  collectTable = table.collectTable;
  projectInfo = table.projectInfo;
  collectType = collectType;
  projectCode = '';
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
    const result = await SearchInfo(this.ticket, this.projectInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectCode) {
          this.projectCode = fields[j].values[0].value
        }
      }
    }
  }

  async saveClick () {
    const projectName: any = document.getElementById('projectName')
    const projectType: any = document.getElementById('projectType')
    const collectType: any = document.getElementById('collectType')
    const collectMoney: any = document.getElementById('collectMoney')
    // const file: any = document.getElementById("file");

    const data = {
      [field.pName]: projectName.value,
      [field.pType]: projectType.value,
      [field.cType]: collectType.options[collectType.selectedIndex].value,
      [field.cMoney]: collectMoney.value
    }
    await addInfo(this.ticket, this.collectTable, data)

    this.$emit('close')
  }

  closeClick () {
    this.$emit('close')
  }
}
</script>

<style scoped>
</style>
