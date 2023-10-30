<template>
  <div></div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, addInfo } from '@/config/interFace'
import { table, field } from '@/config/config'
import { getLocalSale } from '@/config/common'
import custom from '@/components/bindInfo/custom.vue'
@Component({
  components: {
    custom: custom
  }
})
export default class Home extends Vue {
  userId = localStorage.getItem('userId');
  userName = localStorage.getItem('userName');
  localName = localStorage.getItem('localName');
  async mounted () {
    // 查询销售人员信息表
    const data = getLocalSale(this.localName)
    const result = await SearchInfo(table.saleManInfo, data)
    let itemId = ''
    if (result.length === 0) {
      alert('找不到当前销售人员信息！' + this.localName)
      return
    }
    for (let i = 0; i < result.length; i++) {
      itemId = result[0].item_id
    }
    // 新增一条客户信息
    const obj = {
      fields: {
        [field.userTable]: this.userId,
        [field.customerName]: this.userName,
        [field.gender]: [1],
        [field.salesperson]: [itemId],
        [field.customerStage]: [2300006607764778]
      }
    }
    await addInfo(table.customerInfo, obj)
    this.$emit('close')
  }
}
</script>

<style scoped>
.bindHome {
  background-color: rgba(242, 243, 251, 1);
}
</style>
