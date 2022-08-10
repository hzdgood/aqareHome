<template>
  <div>
    <input id="file" type="file" name="file" placeholder="请选择文件" />
    <input type="button" value="导入" @click="customerClick">
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { uploadFile } from '@/config/interFace'

@Component({})
export default class Home extends Vue {
  async customerClick () {
    const formData = new FormData()
    let file: any = document.getElementsByName('file')[0]
    if (typeof file.files[0] === 'undefined') {
      return
    }
    file = file.files[0]
    formData.append('file', file, file.name)
    await uploadFile(formData, '/customer/upload')
  }
}
</script>
