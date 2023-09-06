<template>
    <div style="background: #ececec; padding: 10px;">
      <a-card title="郭佳伟，张恒 安装" :bordered="false" style="width: 98%;">
        <table class="cardTale">
          <tr>
            <td>项目姓名</td>
            <td>王鹏宇</td>
            <td>工单类型</td>
            <td>安装</td>
          </tr>
          <tr>
            <td>项目电话</td>
            <td colspan="3">18328430072</td>
          </tr>
          <tr>
            <td>上门日期</td>
            <td colspan="3">2023-09-09 10:12</td>
          </tr>
        </table>
        <div class="buttons">
          <a-button type="primary">签到</a-button>
          <a-button type="primary">核销</a-button>
          <a-button type="primary">上传</a-button>
          <a-button type="primary">详情</a-button>
        </div>
      </a-card>
    </div>
</template>

<style scoped>
.cardTale{
  width: 100%;
}
.cardTale tr td{
  padding: 3px;
  text-align: left;
}
.buttons button{
  margin: 3px;
}

</style>