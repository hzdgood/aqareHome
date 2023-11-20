<template>
  <div>
    <a-card :title="'开合帘-' +  data.createName" :bordered="false">
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
      </table>
      <div class="buttonPos" >
        <a-button @click="uploadHuoban(data)" type="primary" :disabled="disabled" v-show="status">同步伙伴云</a-button>
        <a-button @click="disabledOpen(data.id)" type="primary" v-show="status">无效</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { httpGet, addInfo, uploadFile } from '../../../config/interFace'
import { getValue } from '../../../util/time'

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

const disabledOpen = async (id: any) => {
  await httpGet('/measure/disabledOpen',{
    id: id,
  })
  emit('pageReset')
}

const uploadHuoban = async (data: any) => {
  const file = await httpGet('/measure/uploadOpen',{
    id: data.id,
  })
  UploadFile(file)
}

const UploadFile = async (file: any) => { // 读取文件上传
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
  
  getImageFileFromUrl(imageUrl, "图片.png", async function (file: any) {
    const formData = new FormData()
    formData.append('source', file)
    formData.append('name', file.name)
    formData.append('domain', 'app.huoban.com')
    formData.append('type', 'attachment')
    const res = await uploadFile(formData)
    list.push(res.file_id)
    let obj = {}
    if(list.length === length){
      obj = {
        fields: {
          2200000159703324: time, // 下单日期
          2200000148927095: [Number.parseInt(data.itemId)], // 客户
          2200000159452329: [Number.parseInt(tech[0].itemId)], // 程    // 技术
          2200000148927645: [1], // 客户确认
          2200000148927631: data.area + "", // 房间区域
          2200000159840961: getValue(data.motorModel), // 电机
          2200000148927639: getValue(data.trackType), // 轨道类型
          2200000198760074: [Number.parseInt(data.custerScheme)], // 方案
          2200000148927632: data.number + "", //数量
          2200000326533031: getValue(data.placeholder), //预留位置 // 靠窗 1 居中 2 靠屋 3
          2200000148927633: getValue(data.installMethod), //安装方式  1，2
          2200000152018959: getValue(data.openMethod), //开合方式  1，2
          2200000148927635: getValue(data.powerPosition), //电机位置 1，2
          2200000148927636: data.boxWidth + "", //窗帘盒宽度
          2200000148927638: getValue(data.surfaceMaterial),// 墙体材质-木 1 混 2 金 3 石 4
          2200000148927640: data.l1 + "", //L1(cm)
          2200000170067122: data.extendedTrack + "", //延长轨个数
          2200000148927642: getValue(data.deductionInfo), //扣减要求 不扣 1 扣3-4 2 自定义 3
          2200000148927644: list, //图片
          2200000148927643: data.remark // 测量备注
        }
      }
      if(data.trackType === 'L型左直角' || data.trackType === 'L型右直角') {
        obj = {
          fields: {
            2200000159703324: time, // 下单日期
            2200000148927095: [Number.parseInt(data.itemId)], // 客户
            2200000159452329: [Number.parseInt(tech[0].itemId)], // 程    // 技术
            2200000148927645: [1], // 客户确认
            2200000148927631: data.area + "", // 房间区域
            2200000159840961: getValue(data.motorModel), // 电机
            2200000148927639: getValue(data.trackType), // 轨道类型
            2200000198760074: [Number.parseInt(data.custerScheme)], // 方案
            2200000148927632: data.number + "", //数量
            2200000326533031: getValue(data.placeholder), //预留位置 // 靠窗 1 居中 2 靠屋 3
            2200000148927633: getValue(data.installMethod), //安装方式  1，2
            2200000152018959: getValue(data.openMethod), //开合方式  1，2
            2200000148927635: getValue(data.powerPosition), //电机位置 1，2
            2200000148927636: data.boxWidth + "", //窗帘盒宽度
            2200000148927638: getValue(data.surfaceMaterial),// 墙体材质-木 1 混 2 金 3 石 4
            2200000148927640: data.l1 + "", //L1(cm)
            2200000170067122: data.extendedTrack + "", //延长轨个数
            2200000148927642: getValue(data.deductionInfo), //扣减要求 不扣 1 扣3-4 2 自定义 3
            2200000148927644: list, //图片
            2200000148927641: data.l2 + "", // L2
            2200000148927643: data.remark // 测量备注
          }
        }
      }
      if(data.trackType === '异型轨') {
        obj = {
          fields: {
            2200000159703324: time, // 下单日期
            2200000148927095: [Number.parseInt(data.itemId)], // 客户
            2200000159452329: [Number.parseInt(tech[0].itemId)], // 程    // 技术
            2200000148927645: [1], // 客户确认
            2200000148927631: data.area + "", // 房间区域
            2200000159840961: getValue(data.motorModel), // 电机
            2200000148927639: getValue(data.trackType), // 轨道类型
            2200000198760074: [Number.parseInt(data.custerScheme)], // 方案
            2200000148927632: data.number + "", //数量
            2200000326533031: getValue(data.placeholder), //预留位置 // 靠窗 1 居中 2 靠屋 3
            2200000148927633: getValue(data.installMethod), //安装方式  1，2
            2200000152018959: getValue(data.openMethod), //开合方式  1，2
            2200000148927635: getValue(data.powerPosition), //电机位置 1，2
            2200000148927636: data.boxWidth + "", //窗帘盒宽度
            2200000148927638: getValue(data.surfaceMaterial),// 墙体材质-木 1 混 2 金 3 石 4
            2200000148927640: data.l1 + "", //L1(cm)
            2200000170067122: data.extendedTrack + "", //延长轨个数
            2200000148927642: getValue(data.deductionInfo), //扣减要求 不扣 1 扣3-4 2 自定义 3
            2200000148927644: list, //图片
            2200000148927641: data.l2 + "", // L2
            2200000200111372: data.l3 + "", // L3
            2200000148927643: data.remark // 测量备注
          }
        }
      }
      const res = await addInfo('2100000015445679', obj)
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
      let imgFile = new File([blob], imageName, { type: "image/png" });
      callback(imgFile);
    });
}

</script>
