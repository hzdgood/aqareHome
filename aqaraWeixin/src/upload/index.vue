<template>
  <div>
    <input id="file" type="file" name="file" placeholder="请选择文件" />
    <input type="button" value="导入" @click="saveClick">
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { customerFile } from '@/config/interFace'

@Component({})
export default class Home extends Vue {
  async saveClick () {
    const formData = new FormData()
    let file: any = document.getElementsByName('file')[0]
    if (typeof file.files[0] === 'undefined') {
      return
    }
    file = file.files[0]
    formData.append('file', file, file.name)
    await customerFile(formData)
  }
}
</script>
