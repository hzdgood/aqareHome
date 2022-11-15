<template>
  <div class="tableSelect">
    <table :width="width" :height="height">
      <tr>
        <td rowspan="2"></td>
        <td v-for="item in StageList1" :key="item.id" @click="click(item)"></td>
      </tr>
      <tr>
        <td v-for="item in StageList2" :key="item.id" @click="click(item)"></td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import { field, StageList1, StageList2 } from '@/config/config'
import { updateTable, logInsert } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  StageList1 = StageList1
  StageList2 = StageList2

  @Prop({
    type: String,
    required: false
  })
  width!: String;

  @Prop({
    type: String,
    required: false
  })
  height!: String;

  @Prop({
    type: String,
    required: false
  })
  itemId!: any;

  async click (item: any) {
    const objs: any = {
      fields: {
        [field.customerStage]: [parseInt(item.id)]
      }
    }
    await updateTable(this.itemId, objs)
    await logInsert(item.name)
    this.$emit('updateStage', item)
  }
}
</script>
