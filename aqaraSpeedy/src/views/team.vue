<template>
  <div class="bodyDiv">
    <div class="teamDiv" v-for="item in teamList" :key="item.id">
      <div @click="teamChange(item.id)">{{item.team}}</div>
      <div class="themeDiv" v-for="theme in themeList" :key="theme.id" v-show="item.id === theme.teamId && theme.show">
        <div @click="themeChange(theme.id)">{{theme.theme}}</div>
        <div class="contentDiv" v-for="content in contentList" :key="content.id" v-show="theme.id === content.themeId  && content.show">
          <div v-if="content.contentType === '文本'" class="content">
            <div class="title">{{content.contentTitle}}</div>
            <div>
              <span>{{content.contentText}}</span>
            </div>
            <button @click="textClick(content)" :disabled="content.disabled">发送</button>
          </div>
          <div v-if="content.contentType === '文件'" class="content">
            <div class="title">{{content.contentTitle}}</div>
            <div>
              <table>
                <tr>
                  <td v-show="content.contentFile.split('.pdf').length > 1"><img src="../img/pdf.png"/></td>
                  <td><span>{{content.contentFile.split("\\")[4]}}</span></td>
                </tr>
              </table>
            </div>
            <button @click="FileClick(content)" :disabled="content.disabled">发送</button>
          </div>
          <div v-if="content.contentType === '图片'" class="content">
            <div class="title">{{content.contentTitle}}</div>
            <div>
              <span>{{content.contentFile.split("\\")[4]}}</span>
            </div>
            <button @click="pictureClick(content)" :disabled="content.disabled">发送</button>
          </div>
          <div v-if="content.contentType === '视频'" class="content">
            <div class="title">{{content.contentTitle}}</div>
            <div>
              <span>{{content.contentFile.split("\\")[4]}}</span>
            </div>
            <button @click="FileClick(content)" :disabled="content.disabled">发送</button>
          </div>
          <div v-if="content.contentType === '组合'" class="content">
            <div class="title">{{content.contentTitle}}</div>
            <div>
              <div>{{content.contentText}}</div>
              <div>{{content.contentFile.split("\\")[4]}}</div>
            </div>
            <button @click="teamClick(content)" :disabled="content.disabled">发送</button>
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

  async mounted () {
    const req = {
      type: '团队话术',
      name: localStorage.getItem('localName')
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
        if (content.show) {
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
        content.disabled = false
        this.contentList.push(content)
      }
    }
  }

  sendButton (content: any) {
    for (let i = 0; i < this.contentList.length; i++) {
      const obj = this.contentList[i]
      if (obj.id === content.id) {
        if (obj.disabled) {
          obj.disabled = false
        } else {
          obj.disabled = true
        }
      }
    }
  }

  async textClick (content: any) {
    await invoke('sendChatMessage', {
      msgtype: 'text',
      enterChat: true,
      text: {
        content: content.contentText
      }
    })
  }

  async FileClick (content: any) {
    this.sendButton(content)
    if (typeof (content.mediaId) !== 'undefined' && content.mediaId !== null && content.mediaId !== '') {
      await invoke('sendChatMessage', {
        msgtype: 'file',
        enterChat: true,
        file: {
          mediaid: content.mediaId
        }
      })
    } else {
      const data: any = await mediaUpload(content.contentFile)
      await invoke('sendChatMessage', {
        msgtype: 'file',
        enterChat: true,
        file: {
          mediaid: data.media_id
        }
      })
    }
    this.sendButton(content)
  }

  async pictureClick (content: any) {
    const data: any = await mediaUpload(content.contentFile)
    await invoke('sendChatMessage', {
      msgtype: 'image',
      enterChat: true,
      image: {
        mediaid: data.media_id
      }
    })
  }

  async teamClick (content: any) {
    this.sendButton(content)
    await this.textClick(content)
    if (content.contentFile.split('.jpg').length > 1 || content.contentFile.split('.png').length > 1) {
      await this.pictureClick(content)
    } else {
      await this.FileClick(content)
    }
    this.sendButton(content)
  }
}
</script>
