<template>
  <div>
    <input id="file" type="file" name="file" placeholder="请选择文件" />
    <button @click="uploadFile('/customer/upload')">客户导入</button>
    <button @click="uploadFile('/survey/upload')">工勘导入</button>
    <button @click="uploadFile('/quotation/upload')">报价单导入</button>
    <button @click="uploadFile('/project/upload')">项目导入</button>
    <button @click="uploadFile('/collent/upload')">收款导入</button>
    <!-- <iframe class="myiframe" src="https://ss3153003.huoban.com/account/login" frameborder="0" scrolling="auto"></iframe> -->
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { uploadFile } from '@/config/interFace'

@Component({})
export default class Home extends Vue {
  async uploadFile (req: any) {
    const formData = new FormData()
    let file: any = document.getElementsByName('file')[0]
    if (typeof file.files[0] === 'undefined') {
      return
    }
    file = file.files[0]
    formData.append('file', file, file.name)
    await uploadFile(formData, req)
  }
}
</script>
<style scoped>
.myiframe{
  width: 100%;
  height: 100%;
  position: absolute;
}
</style>
