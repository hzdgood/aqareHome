<template>
  <div class="tableSelect">
    <table :width="width" :height="height">
      <tr>
        <td rowspan="2"></td>
        <td v-for="item in chatStageList1" :key="item.value" @click="click(item)"></td>
      </tr>
      <tr>
        <td v-for="item in chatStageList2" :key="item.value" @click="click(item)"></td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import { field, chatStageList1, chatStageList2 } from '@/config/config'
import { updateTable } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  chatStageList1 = chatStageList1
  chatStageList2 = chatStageList2

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

  // 更新关系
  async click (item: any) {
    const objs: any = {
      fields: {
        [field.projectStage]: [parseInt(item.value)]
      }
    }
    await updateTable(this.itemId, objs)
    this.$emit('updateStage', item)
  }
}
</script>
