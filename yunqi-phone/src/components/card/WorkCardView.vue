<template>
  <div style="background: #ececec; padding: 10px 10px 0px 10px;">
    <a-card title="郭佳伟，张恒 安装" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="WriterEdit">详情</a-button>
        <a-button :style="style" @click="uploadImg">图片</a-button>
      </div>
      <table class="cardTale">
        <tr>
          <td>项目姓名</td>
          <td>王鹏宇</td>
          <td>工单类型</td>
          <td>安装</td>
        </tr>
        <tr>
          <td>项目电话</td>
          <td>18328430072</td>
          <td>工单状态</td>
          <td>待上门</td>
        </tr>
        <tr>
          <td>上门日期</td>
          <td colspan="3">2023-09-09 10:12</td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button type="primary">签到</a-button>
        <a-button type="primary">离开</a-button>
        <a-button type="primary" @click="WriterInfo">核销</a-button>
        <a-button type="primary" @click="measureInfo">测量</a-button>
      </div>
    </a-card>
  </div>
</template>
<script setup lang="ts">
const emit = defineEmits(['toPage'])
const WriterInfo = () => {
  emit('toPage','subWriter')
}
const measureInfo = () => {
  emit('toPage','subMeasure')
}
const WriterEdit = () => {
  emit('toPage','WriterEdit')
}
const uploadImg = () => {
  emit('toPage','uploadImg')
}
const style = {
  backgroundColor: '#0099DD',color: '#fff'
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
.buttonPos {
  width: 100%;
  text-align: right;
}
button{
  margin: 10px;
}
</style>