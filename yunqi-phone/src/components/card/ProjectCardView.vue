<template>
  <div style="background: #ececec; padding: 10px 10px 0px 10px;">
    <a-card title="缪丹霞 全屋" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="handoverInfo">交接信息</a-button>
      </div>
      <table class="cardTale">
        <tr>
          <td>项目姓名</td>
          <td>缪丹霞</td>
          <td>装修进度</td>
          <td>水电完成</td>
        </tr>
        <tr>
          <td>项目类型</td>
          <td>全屋</td>
          <td>目前任务</td>
          <td>跟踪装修进度</td>
        </tr>
        <tr>
          <td>销售</td>
          <td>郑海朋</td>
          <td>负责人</td>
          <td>娄德</td>
        </tr>
        <tr>
          <td>项目电话</td>
          <td colspan="3">18328430072</td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button @click="sendWork" type="primary">发单</a-button>
        <a-button @click="schemeInfo" type="primary">方案</a-button>
        <a-button @click="measureInfo" type="primary">测量</a-button>
      </div>
    </a-card>
  </div>
</template>
<script setup lang="ts">
const emit = defineEmits(['toPage'])
const style = {
  backgroundColor: '#0099DD',color: '#fff'
}
const sendWork = () => {
  emit('toPage','subWork')
}
const schemeInfo = () => {
  emit('toPage','subSchemem')
}
const measureInfo = () => {
  emit('toPage','subMeasure')
}
const handoverInfo = () => {
  emit('toPage','handoverInfo')
}
</script>
<style scoped>
.cardTale{
  width: 100%;
}
.cardTale tr td{
  padding: 3px;
  text-align: left;
}
</style>