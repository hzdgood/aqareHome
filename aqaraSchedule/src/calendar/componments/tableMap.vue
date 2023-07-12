<template>
  <div>
    <div>

    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {
  // @Watch('$store.state.searchStatus')
  // async selectPage () {
  //   const list: any[] = this.$store.state.selectData
  //   const date = this.$store.state.CalendarDate
  //   // this.layerList = []
  //   const obj = {
  //     where: {
  //       and: [
  //         { field: 2200000145748100, query: { in: list } }, // 人员
  //         { field: 2200000146398516, query: { in: [10, 6, 3, 2, 5, 4, 12] } }, // 订单类型 除了发货
  //         {
  //           field: 2200000145748099, // 上门时间
  //           query: {
  //             range: [
  //               { model: 'static', datetime: date },
  //               { model: 'static', datetime: date }
  //             ]
  //           }
  //         }
  //       ]
  //     },
  //     offset: 0,
  //     limit: 20,
  //     order_by: [{ field: 2200000146199958, sort: 'desc' }]
  //   }
  //   const result = await SearchInfo('2100000015054992', obj)
  //   if (result.length === 0) {
  //     this.$store.state.layerList = []
  //   }
  // }
}
</script>
