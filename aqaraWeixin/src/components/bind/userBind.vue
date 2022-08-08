<template>
  <div>
    <custom v-if="bindStatus" @close="close" @loadPage="loadPage"></custom>
    <div v-if="bindStatus == false">
      <div class="bindHome">
        <img src="../../img/home.png" width="100%" />
        <div class="bindSite">
          <button class="bindButton" type="button" @click="add()">
            新增用户
          </button>
        </div>
        <div class="bindSite">
          <button class="bindButton1" type="button" @click="bind()">
            绑定用户
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo, addInfo } from '@/config/interFace'
import { table, field } from '@/config/config'
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
  bindStatus = false;

  bind () {
    this.bindStatus = true
  }

  async add () {
    // 查询销售人员信息表
    const data = {"where":{"and":[{"field":2200000257456339,"query":{"in":[2,3]}}]},"offset":0,"limit":20}
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
    this.bindStatus = false
    this.$emit('close')
  }

  loadPage () {
    this.bindStatus = false
  }

  close () {
    this.$emit('close')
  }
}
</script>

<style scoped>
.bindHome {
  background-color: rgba(242, 243, 251, 1);
}
</style>
