<template>
  <div class="cardDiv">
    <a-card :title="data.projectName" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="handoverInfo(data.projectId)">详情</a-button>
        <a-button :style="style" @click="measureInfo(data.projectId)">新增测量单</a-button>
      </div>
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>{{data.projectName}}</td>
          <td width="70px">装修进度</td>
          <td>{{data.schedule}}</td>
        </tr>
        <tr>
          <td>销售</td>
          <td>{{data.salesName}}</td>
          <td>负责人</td>
          <td>{{data.techName}}</td>
        </tr>
        <tr>
          <td>项目类型</td>
          <td>{{data.type}}</td>
          <td>项目电话</td>
          <td colspan="3">{{data.telephone}}</td>
        </tr>
      </table>
      <div class="buttonPos" >
        <a-button @click="sendWork(data.projectId)" type="primary" 
          v-show="status"
          >发单</a-button>
        <a-button 
          v-show="data.schemeId !== null" 
          @click="schemeInfo(data.projectId)" 
          type="primary">方案</a-button>
      </div>
    </a-card>
  </div>
</template>
<script setup lang="ts">
// 项目卡片
const techId = localStorage.getItem("techId");
const admins = localStorage.getItem("admins");

const json: any = defineProps({
  data: {
    type: Object,
    default: null
  }
})

const data = json.data

const status = data.techId + '' === '' + techId || admins === 'true'

const emit = defineEmits(['toPage'])

const handoverInfo = (id: any) => {
  emit('toPage','handoverInfo', id)
}

const measureInfo = (id: any) => {
  emit('toPage','measureInfo', id)
}

const sendWork = (id: any) => {
  emit('toPage','subWork', id)
}

const schemeInfo = (id: any) => {
  emit('toPage','subSchemem', id)
}

const style = {
  backgroundColor: '#0099DD',color: '#fff'
}
</script>
<style scoped>

</style>