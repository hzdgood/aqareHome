<template>
  <div class="cardDiv">
    <a-card title="开合帘" :bordered="false" v-show="type === '开合帘'">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>{{data.projectName}}</td>
          <td width="70px">区域</td>
          <td>{{data.area}}</td>
        </tr>
        <tr>
          <td>电机型号</td>
          <td>{{data.motorModel}}</td>
          <td>轨道类型</td>
          <td>{{data.trackType}}</td>
        </tr>
        <tr>
          <td>安装方式</td>
          <td>{{data.installMethod}}</td>
          <td>数量</td>
          <td>{{data.number}}</td>
        </tr>
        <tr>
          <td>开合方式</td>
          <td>{{data.openMethod}}</td>
          <td>电源位置</td>
          <td>{{data.powerPosition}}</td>
        </tr>
        <tr>
          <td>窗帘盒宽</td>
          <td>{{data.boxWidth}}</td>
          <td>安装面材质</td>
          <td>{{data.surfaceMaterial}}</td>
        </tr>
        <tr>
          <td>预留位置</td>
          <td>{{data.placeholder}}</td>
          <td>扣减说明</td>
          <td>{{data.deductionInfo}}</td>
        </tr>
      </table>
      <div class="buttonPos" >
        <a-button 
          v-show="data.schemeId !== null" 
          @click="disabledOpen(data.id)" 
          type="primary">无效</a-button>
      </div>
    </a-card>

    <a-card title="卷帘" :bordered="false" v-show="type === '卷帘'">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>{{data.projectName}}</td>
          <td width="70px">区域</td>
          <td>{{data.area}}</td>
        </tr>
        <tr>
          <td>卷帘方案</td>
          <td>{{data.rollingScheme}}</td>
          <td>长度</td>
          <td>{{data.length}}</td>
        </tr>
        <tr>
          <td>高度</td>
          <td>{{data.high}}</td>
          <td>安装面宽度</td>
          <td>{{data.surfaceWidth}}</td>
        </tr>
        <tr>
          <td>安装方式</td>
          <td>{{data.installMethod}}</td>
          <td>扣减说明</td>
          <td>{{data.deductionInfo}}</td>
        </tr>
        <tr>
          <td>数量</td>
          <td>{{data.number}}</td>
          <td>安装面材质</td>
          <td>{{data.surfaceMaterial}}</td>
        </tr>
        <tr>
          <td>电源位置</td>
          <td>{{data.powerPosition}}</td>
          <td>需要罩壳</td>
          <td>{{data.cover}}</td>
        </tr>
      </table>
      <div class="buttonPos" >
        <a-button 
          v-show="data.schemeId !== null" 
          @click="disabledRoller(data.id)" 
          type="primary">无效</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { httpGet } from '../../config/interFace'
// 测量 开合 卷帘
defineProps({
  data: {
    type: Object,
    default: null
  },
  type: {
    type: String,
    default: null
  }
})

const disabledOpen = async (id: any) => {
  const res = await httpGet('/measure/disabledOpen',{
    id: id,
  })
  console.log(res);
}

const disabledRoller = async (id: any) => {
  const res = await httpGet('/measure/disabledRoller',{
    id: id,
  })
  console.log(res);
}

</script>