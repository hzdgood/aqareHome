<template>
  <div>
    <a-card :title="'卷帘-' + data.createName" :bordered="false">
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
      </table>
      <div class="buttonPos" >
        <a-button @click="uploadHuoban(data)" type="primary" :disabled="disabled" v-show="status">同步伙伴云</a-button>
        <a-button @click="disabledRoller(data.id)" type="primary" v-show="status">无效</a-button>
      </div>
      <input type="file">
    </a-card>
  </div>
</template>
<script setup lang="ts">
import { httpGet, addInfo, uploadFile } from '../../../config/interFace'
import { getRoller } from '../../../util/time'

const admins = localStorage.getItem("admins");
const techId = localStorage.getItem("techId");
const emit = defineEmits(['pageReset'])
const json: any = defineProps({
  data: {
    type: Object,
    default: null
  }
})
const data = json.data
const status = admins === 'true'
let list: any[] = []
let disabled = false

// 测量 开合 卷帘
const disabledRoller = async (id: any) => {
  await httpGet('/measure/disabledRoller',{
    id: id,
  })
  emit('pageReset')
}

const uploadHuoban = async (data: any) => { //
  const file = await httpGet('/measure/uploadRoller',{
    id: data.id,
  })
  UploadFile(file)
}

const UploadFile = async (file: any) => { // 读取文件--上传
  list = []
  disabled = true
  for (let i = 0; i < file.length; i++) {
    const str: any[] = file[i].split("\\")
    if(str.length > 5) {
      const url = str[5] + "/" + str[6]
      console.log(url);
      chooseStaticImg("https://aqara.club/measure/" + url, i, file.length);
    }
  }
}

const chooseStaticImg = async (imageUrl: any, index: number, length: number) => {
  const date = new Date();
  const time = date.getFullYear() + "-" + 
      (date.getMonth()+1).toString().padStart(2,"0") + "-" + 
      date.getDate().toString().padStart(2,"0")
  const tech = await httpGet('/tech/selectId',{ id: techId })
  
  getImageFileFromUrl(imageUrl, "图片.jpg", async function (file: any) {
    const formData = new FormData()
    formData.append('source', file)
    formData.append('name', file.name)
    formData.append('domain', 'app.huoban.com')
    formData.append('type', 'attachment')
    const res = await uploadFile(formData)
    list.push(res.file_id)
    if(length - 1 === index){
      const obj = {
        fields: {
          2200000159475364: time,
          2200000147636558: [Number.parseInt(data.itemId)], // 客户
          2200000159453028: [Number.parseInt(tech[0].itemId)], // 技术
          2200000147636560: [5], // 电机型号  T1S电机
          2200000147636559: data.area, // 房间区域
          2200000298835023: [Number.parseInt(data.rollingScheme)], // 卷帘方案
          2200000148927883: list, // 图片
          2200000147636564: data.length, // 长度
          2200000147636565: data.high, // 高度
          2200000149873850: data.surfaceWidth, // 安装面宽度
          2200000152262935: getRoller(data.installMethod), // 安装方式
          2200000147636566: getRoller(data.deductionInfo), // 尺寸备注
          2200000149873851: getRoller(data.surfaceMaterial), // 安装面材质
          2200000147636568: data.number, // 数量
          2200000147636569: getRoller(data.powerPosition), // 电源位置
          2200000147636570: getRoller(data.cover), // 罩壳
          2200000147636571: data.remark, // 备注
          // 2200000151790382: [3748782], // 技术
          // 2200000151790383: [3347777] // 销售
        }
      }
      const res = await addInfo('2100000014956211', obj)
      if(typeof(res.message) !== "undefined") {
        alert(res.message)
      }
    }
  });
}
// 根据路径返回file
const getImageFileFromUrl = (url: string, imageName: string, callback: any) => {
  fetch(url)
    .then((res) => {
      return res.blob();
    })
    .then((blob) => {
      let imgFile = new File([blob], imageName, { type: "image/jpeg" });
      callback(imgFile);
    });
}
</script>