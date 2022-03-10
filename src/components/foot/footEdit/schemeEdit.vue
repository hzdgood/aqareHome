<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">上传方案</div>
      <input type="file" name="file" placeholder="请选择文件"/>
      <div>
        <input type="button" @click="saveClick()" value="提交" />
        <input type="button" @click="closeClick()" value="关闭" />
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { uploadFile } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  async saveClick () {
    const formData = new FormData()
    let file: any = document.getElementsByName('file')[0]
    file = file.files[0]
    formData.append('file', file, file.name)
    uploadFile(formData)
    this.$emit('closeScheme')
  }

  closeClick () {
    this.$emit('closeScheme')
  }
}
</script>
