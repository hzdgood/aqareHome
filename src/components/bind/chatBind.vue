<template>
  <div>
    <chat v-if="bindStatus" @close="close" @loadPage="loadPage"></chat>
    <div v-if="bindStatus == false">
      <div class="bindHome">
        <img src="../../img/home.png" width="100%" />
        <div class="buttonSite">
          <input class="bindButton" type="button" value="项目绑定" />
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import chat from '@/components/bindInfo/chat.vue'
@Component({
  components: {
    chat: chat
  }
})
export default class Home extends Vue {
  bindStatus = false;
  // 保存
  async save () {
    this.bindStatus = false
    this.$emit('close')
  }

  // 关闭
  close () {
    this.$emit('close')
  }

  loadPage () {
    this.bindStatus = false
  }
}
</script>
