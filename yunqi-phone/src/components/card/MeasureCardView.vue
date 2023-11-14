<template>
  <div class="cardDiv">
    <a-card :title="'开合帘-' +  data.createName" :bordered="false" v-show="type === '开合帘'">
      <div class="buttonPos">
        <a-button>详情</a-button>
      </div>
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>{{data.projectName}}</td>
          <td width="80px">区域</td>
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
          <td>窗帘盒宽</td>
          <td>{{data.boxWidth}}</td>
          <td>安装面材质</td>
          <td>{{data.surfaceMaterial}}</td>
        </tr>
        <tr>
          <td>图片路径</td>
          <td colspan="3">{{data.imgUrl}}</td>
        </tr>
      </table>
      <div class="buttonPos" >
        <a-button :disabled="true" type="primary">--同步伙伴云--</a-button>
        <a-button @click="disabledOpen(data.id)" type="primary" v-show="status">无效</a-button>
      </div>
    </a-card>

    <a-card :title="'卷帘' + data.createName" :bordered="false" v-show="type === '卷帘'">
      <div class="buttonPos">
        <a-button>详情</a-button>
      </div>
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>{{data.projectName}}</td>
          <td width="80px">区域</td>
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
          <td>数量</td>
          <td>{{data.number}}</td>
          <td>安装面材质</td>
          <td>{{data.surfaceMaterial}}</td>
        </tr>
        <tr>
          <td>图片路径</td>
          <td colspan="3">{{data.imgUrl}}</td>
        </tr>
      </table>
      <div class="buttonPos" >
        <a-button :disabled="true" type="primary">--同步伙伴云--</a-button>
        <a-button  @click="disabledRoller(data.id)" type="primary" v-show="status">无效</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { httpGet } from '../../config/interFace'

const admins = localStorage.getItem("admins");
const techId = localStorage.getItem("techId");

const json: any = defineProps({
  data: {
    type: Object,
    default: null
  },
  type: {
    type: String,
    default: null
  }
})

const type = json.type
const data = json.data

const status = data.techId + '' === '' + techId || admins === 'true'

// 测量 开合 卷帘
const disabledOpen = async (id: any) => {
  await httpGet('/measure/disabledOpen',{
    id: id,
  })
}

const disabledRoller = async (id: any) => {
  await httpGet('/measure/disabledRoller',{
    id: id,
  })
}

</script>