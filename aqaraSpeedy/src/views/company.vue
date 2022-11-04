<template>
  <div>
    <div class="">
      <span>快捷组名：</span>
      <a-select style="width: 250px;" @change="teamChange" placeholder="请选择">
        <a-select-option :value='item.id' v-for="item in teamList" :key="item.id">{{item.team}}</a-select-option>
      </a-select>
    </div>
    <div class="">
      <span>快捷主题：</span>
      <a-select style="width: 250px;" @change="themeChange" placeholder="请选择">
        <a-select-option :value='item.id' v-for="item in themeList" :key="item.id">{{item.theme}}</a-select-option>
      </a-select>
    </div>
    <div class="">快捷内容</div>
    <div class="" v-for="item in contentList" :key="item.id">
      <div v-if="item.contentType === '文件'" @click="FileClick(item)">文件：{{item.contentFile}}</div>
      <div v-if="item.contentType === '文本'" @click="textClick(item)">文本：{{item.contentText}}</div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { treeList, mediaUpload } from '@/config/interFace'
import { Tree, Select } from 'ant-design-vue'
import { invoke } from 'wecom-sidebar-jssdk'
@Component({
  name: 'company',
  components: {
    'a-tree': Tree,
    'a-select': Select,
    'a-select-option': Select.Option
  }
})

export default class Actions extends Vue {
  teamList: any[] = []
  themeList: any[] = []
  contentList: any[] = []
  form: any

  async mounted () {
    const req = {
      type: '企业话术'
    }
    const data = await treeList('/speedy/team/select', req)
    this.teamList = data.data
  }

  async teamChange (teamId: any) {
    this.cleanList()
    const req = {
      teamId: teamId
    }
    const data = await treeList('/speedy/theme/select', req)
    this.themeList = data.data
  }

  async themeChange (themeId: any) {
    this.cleanList()
    const req = {
      themeId: themeId
    }
    const data = await treeList('/speedy/content/select', req)
    this.contentList = data.data
  }

  cleanList () {
    this.contentList = []
    this.themeList = []
  }

  async textClick (item: any) {
    await invoke('sendChatMessage', {
      msgtype: 'text',
      enterChat: true,
      text: {
        content: item.contentText
      }
    })
  }

  async FileClick (item: any) {
    const data: any = await mediaUpload(item.contentFile)
    console.log(data)
    await invoke('sendChatMessage', {
      msgtype: 'file',
      enterChat: true,
      file: {
        mediaid: data.media_id
      }
    })
  }
}
</script>

<style>

</style>
