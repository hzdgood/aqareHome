<template>
    <div style="background: #ececec; padding: 10px;">
      <a-card title="" :bordered="false">
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
            <td>销售</td>
            <td>娄德</td>
          </tr>
          <tr>
            <td>项目电话</td>
            <td colspan="3">18328430072</td>
          </tr>
        </table>
        <div class="buttons">
          <a-button type="primary">新增工单</a-button>
          <a-button type="primary">项目方案</a-button>
          <a-button type="primary">项目详情</a-button>
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