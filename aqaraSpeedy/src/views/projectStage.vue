<template>
  <div class="stageDiv">
    <chat-select
      :width="comWidth"
      :height="comHeight"
      :itemId="itemId"
      @updateStage="updateStage"
    ></chat-select>
    <div class="stageButton">
      <span>
        <img src="../img/x1.png" width="22%" />
      </span>
      <span>
        <img src="../img/chat/state-9.png" width="73%" v-show="stageImg9" />
        <img src="../img/chat/state-8.png" width="73%" v-show="stageImg8" />
        <img src="../img/chat/state-1.png" width="73%" v-show="stageImg1" />
        <img src="../img/chat/state-2.png" width="73%" v-show="stageImg2" />
        <img src="../img/chat/state-3.png" width="73%" v-show="stageImg3" />
        <img src="../img/chat/state-4.png" width="73%" v-show="stageImg4" />
        <img src="../img/chat/state-10.png" width="73%" v-show="stageImg10" />
      </span>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { table, field, decorationStage } from '@/config/config'
import { SearchInfo, updateTable } from '@/config/interFace'
import chatSelect from '@/components/common/chatSelect.vue'
@Component({
  components: {
    'chat-select': chatSelect
  }
})
export default class Home extends Vue {
  itemId: any = '';
  decorationStage = decorationStage;
  stageImg1 = false;
  stageImg2 = false;
  stageImg3 = false;
  stageImg4 = false;
  stageImg8 = false;
  stageImg9 = false;
  stageImg10 = false;
  screenWidth = document.body.clientWidth;
  comWidth: any = '100px';
  comHeight: any = '20px';

  // @Watch('this.screenWidth')
  // reloadTable () {
  // }

  async mounted () {
    window.onresize = () => {
      this.screenWidth = document.body.clientWidth
      this.comWidth = this.screenWidth - 40 + 'px'
      this.comHeight = this.screenWidth / 3 - 10 + 'px'
    }

    // 获取当前用户客户装修阶段
    const chatId = localStorage.getItem('chatID')
    const obj = {
      where: {
        and: [
          {
            query: { or: [{ eqm: [chatId] }] },
            query_option_mappings: [-1],
            field: field.ChatId
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.projectInfo, obj)
    let status = false
    for (let i = 0; i < result.length; i++) {
      this.itemId = result[0].item_id + ''
      const fields = result[0].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.projectStage) {
          var id = fields[j].values[0].id
          status = true
          this.StageImg(id)
        }
      }
    }
    if (!status) {
      const objs: any = {
        fields: {
          [field.projectStage]: [9]
        }
      }
      await updateTable(this.itemId, objs)
      this.stageImg9 = true
    }
    this.comWidth = this.screenWidth - 40 + 'px'
    this.comHeight = this.screenWidth / 3 - 10 + 'px'
  }

  // 选中图片
  StageImg (itemid: any) {
    for (let i = 0; i < this.decorationStage.length; i++) {
      const value = this.decorationStage[i].value
      if (itemid === value) {
        this.updateImg(value)
      }
    }
  }

  updateImg (value: any) {
    this.stageImg1 = false
    this.stageImg2 = false
    this.stageImg3 = false
    this.stageImg4 = false
    this.stageImg8 = false
    this.stageImg9 = false
    this.stageImg10 = false
    if (value === 1) {
      this.stageImg1 = true
    } else if (value === 2) {
      this.stageImg2 = true
    } else if (value === 3) {
      this.stageImg3 = true
    } else if (value === 4) {
      this.stageImg4 = true
    } else if (value === 8) {
      this.stageImg8 = true
    } else if (value === 9) {
      this.stageImg9 = true
    } else if (value === 10) {
      this.stageImg10 = true
    }
  }

  updateStage (item: any) {
    this.updateImg(item.value)
  }
}
</script>
<style></style>
