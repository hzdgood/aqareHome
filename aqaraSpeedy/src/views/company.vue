<template>
  <div class="contentDiv">
    <div class="teamDiv" v-for="item in teamList" :key="item.id">
      <div @click="teamChange(item.id)">{{item.team}}</div>
      <div class="themeDiv" v-for="theme in themeList" :key="theme.id" v-show="item.id === theme.teamId && theme.show">
        <div @click="themeChange(theme.id)">{{theme.theme}}</div>
        <div class="contentDiv" v-for="content in contentList" :key="content.id" v-show="theme.id === content.themeId  && content.show">
          <div v-if="content.contentType === '文本'" class="text">
            <div>
              <span>{{item.contentText}}</span>
              <button @click="textClick(content)">发送</button>
            </div>
          </div>
          <div v-if="content.contentType === '文件'" class="text">
            <div>
              <span>{{content.contentFile.split("\\")[4]}}</span>
              <button @click="FileClick(content)">发送</button>
            </div>
          </div>
          <div v-if="content.contentType === '图片'" class="text">
            <div>
              <span>{{content.contentFile.split("\\")[4]}}</span>
              <button @click="pictureClick(content)">发送</button>
            </div>
          </div>
          <div v-if="content.contentType === '视频'" class="text">
            <div>
              <span>{{content.contentFile.split("\\")[4]}}</span>
              <button @click="videoClick(content)">发送</button>
            </div>
          </div>
          <div v-if="content.contentType === '组合'" class="team">
            <div>
              <div>{{item.contentText}}</div>
              <div>{{content.contentFile.split("\\")[4]}}</div>
              <button @click="teamClick(content)">发送</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { treeList, mediaUpload } from '@/config/interFace'
import { invoke } from 'wecom-sidebar-jssdk'
@Component({
  name: 'company'
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
    const req = {
      teamId: teamId
    }
    const data = await treeList('/speedy/theme/select', req)
    let status = true
    for (let i = 0; i < this.themeList.length; i++) {
      const theme = this.themeList[i]
      if (theme.teamId === teamId) {
        if (theme.show === true) {
          theme.show = false
        } else {
          theme.show = true
        }
        status = false
      }
    }
    if (status) {
      for (let i = 0; i < data.data.length; i++) {
        const theme = data.data[i]
        theme.show = true
        this.themeList.push(theme)
      }
    }
  }

  async themeChange (themeId: any) {
    const req = {
      themeId: themeId
    }
    let status = true
    for (let i = 0; i < this.contentList.length; i++) {
      const content = this.contentList[i]
      if (content.themeId === themeId) {
        if (content.show === true) {
          content.show = false
        } else {
          content.show = true
        }
        status = false
      }
    }
    if (status) {
      const data = await treeList('/speedy/content/select', req)
      for (let i = 0; i < data.data.length; i++) {
        const content = data.data[i]
        content.show = true
        this.contentList.push(content)
      }
    }
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
    await invoke('sendChatMessage', {
      msgtype: 'file',
      enterChat: true,
      file: {
        mediaid: data.media_id
      }
    })
  }

  async pictureClick (item: any) {
    const data: any = await mediaUpload(item.contentFile)
    await invoke('sendChatMessage', {
      msgtype: 'image',
      enterChat: true,
      image: {
        mediaid: data.media_id
      }
    })
  }

  async videoClick (item: any) {
    const data: any = await mediaUpload(item.contentFile)
    await invoke('sendChatMessage', {
      msgtype: 'video',
      enterChat: true,
      video: {
        mediaid: data.media_id
      }
    })
  }

  async teamClick (item: any) {
    const data: any = await mediaUpload(item.contentFile)
    await invoke('sendChatMessage', {
      msgtype: 'text',
      enterChat: true,
      text: {
        content: item.contentText
      }
    })
    await invoke('sendChatMessage', {
      msgtype: 'video',
      enterChat: true,
      video: {
        mediaid: data.media_id
      }
    })
  }
}
</script>

<style>
.contentDiv {
  margin: 10px;
  background-color: #ffffff;
}
.contentDiv *{
  font-size: 16px !important;
}
.teamDiv{
  margin: 5px 0px 0px 5px;
}
.themeDiv{
  margin: 5px 0px 0px 10px;
}
.contentDiv{
  margin: 5px 0px 0px 15px;
}

</style>
