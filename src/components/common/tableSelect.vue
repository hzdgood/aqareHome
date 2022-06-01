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
import { StageList1, StageList2 } from '@/config/config'
@Component({})
export default class Home extends Vue {
  StageList1 = StageList1
  StageList2 = StageList2

  @Prop({
    type: String,
    required: true,
    default: ''
  })
  width!: String;

  @Prop({
    type: String,
    required: true,
    default: ''
  })
  height!: String;

  click (item: any) {
    console.log(item)
  }
}
</script>
