<template>
  <div>
    <div class='floatDiv'></div>
    <div class='infoDiv'>
      <div class='headerDiv'>报价单</div>
      <button class="closeButton" @click="closeClick()">关闭</button>
      <div v-for='item in dataList' :key='item.id'>
        <table class='EditTable'>
          <tr>
            <td>订单类型</td>
            <td><input type='text' :value='item.type' readonly /></td>
          </tr>
          <tr>
            <td>方案金额</td>
            <td><input type='text' :value='item.schemeMoney' readonly /></td>
          </tr>
          <tr>
            <td>优惠金额</td>
            <td><input id='discount' type='text' :value='item.discount' /></td>
          </tr>
          <tr>
            <td>应收金额</td>
            <td><input type='text' :value='item.receivable' readonly /></td>
          </tr>
          <tr>
            <td>已收金额</td>
            <td><input type='text' :value='item.Received' readonly /></td>
          </tr>
          <tr>
            <td>上传文件</td>
            <td>
              <input id='file' type='file' accept='image/*' placeholder='请选择文件' multiple/>
            </td>
          </tr>
        </table>
        <div class='buttonSite'>
          <button class='saveButton' @click="uploadFile()">上传合同</button>
          <button class='saveButton' @click='synchroClick(item)'>同步</button>
          <button class='saveButton' @click='saveClick(item)'>保存</button>
        </div>
      </div>
    </div>
    <my-Modal :visible="visible" :modalText="errorMsg" @close="closeModal()"></my-Modal>
    <my-load :loadVisible="loadVisible"></my-load>
  </div>
</template>
<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { updateTable, uploadImg, logInsert } from '@/config/interFace'
import myModal from '@/components/common/myModal.vue'
import loading from '@/components/common/loading.vue'
@Component({
  name: 'ProposalEdit',
  components: {
    'my-Modal': myModal,
    'my-load': loading
  }
})
export default class Home extends Vue {
  proposalId = ''
  type = ''
  schemeMoney = ''
  discount = ''
  receivable = ''
  Received = ''
  fileList: any = []
  visible = false
  loadVisible = false
  errorMsg = ''

  @Prop({
    type: Array,
    required: true
  })
  dataList: any

  // 保存
  async saveClick (item: any) {
    const discount: any = document.getElementById('discount')
    const data = {
      fields: {
        2200000180589758: discount.value,
        2200000203196675: this.fileList,
        2200000180589754: [1],
        2200000180591563: [1]
      }
    }
    await updateTable(item.proposalId, data)
    await logInsert('报价单修改')
    this.errorInfo('提交报价单成功！')
  }

  // 同步
  async synchroClick (item: any) {
    const data = {
      fields: {
        2200000180589754: [1],
        2200000180591563: [1]
      }
    }
    await updateTable(item.proposalId, data)
    await logInsert('报价单同步')
    this.errorInfo('同步报价单完成！')
  }

  async uploadFile () {
    const file: any = document.getElementById('file')
    if (typeof file.files !== 'undefined') {
      for (let i = 0; i < file.files.length; i++) {
        const files = file.files[i]
        const formData = new FormData()
        formData.append('source', files)
        formData.append('name', files.name)
        formData.append('domain', 'app.huoban.com')
        formData.append('type', 'attachment')
        const res = await uploadImg(formData)
        this.fileList.push(res.file_id)
      }
    }
  }

  errorInfo (str: any) {
    this.loadVisible = false
    this.visible = true
    this.errorMsg = str
  }

  closeClick () {
    this.$emit('close')
  }

  closeModal () {
    this.visible = false
  }
}
</script>

<style scoped>
.floatDiv {
  position: fixed;
  height: 100%;
  width: 100%;
  background: #000000;
  opacity: 0.5;
  z-index: 25;
  top: 0;
  overflow: hidden;
}

.infoDiv {
  position: fixed;
  width: 100%;
  height: 70%;
  bottom: 0px;
  overflow: auto;
  z-index: 25;
  background-color: #fff;
  border-radius: 8px;
}
</style>
