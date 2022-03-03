<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div>
        <span>项目名称</span>
        <input type="text" readonly />
      </div>
      <div>
        <span>收款类型</span>
        <span>定金</span>
      </div>
      <div>
        <span>收款方式</span>
        <input type="text" />
      </div>
      <div>
        <span>收款金额</span>
        <input type="text" />
      </div>
      <div>
        <span>上传图片</span>
        <input type="file" />
      </div>
    </div>
  </div>
</template>

<script>
import { Component, Vue } from 'vue-property-decorator'
import { table, field } from '@/config/config'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  ticket = localStorage.getItem('ticket');
  collectTable = table.collectTable;
  projectInfo = table.projectInfo;
  async mounted () {
    // const data = {
    //   search: { fields: [], keywords: ["小云"] },
    //   where: {
    //     and: [
    //       {
    //         field: 2200000184791041,
    //         query: { in: [1] },
    //       },
    //     ],
    //   },
    //   offset: 0,
    //   limit: 20,
    //   order_by: [{ field: 2200000150460774, sort: "desc" }],
    // };
    // const result = await SearchInfo(this.ticket, this.projectInfo, data);
    // let projectCode: any = "";
    // for (let i = 0; i < result.length; i++) {
    //   const fields = result[i].fields;
    //   for (let j = 0; j < fields.length; j++) {
    //     if (fields[j].field_id === field.projectCode) {
    //       projectCode = fields[j].values[0].value;
    //     }
    //   }
    // }
  }
}
</script>

<style scoped>
</style>
