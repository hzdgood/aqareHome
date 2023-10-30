<template>
  <div class="cardDiv"> <!--  -->
    <a-card :title="data.techNames + '&nbsp;&nbsp;' + data.type + '&nbsp;&nbsp;' + data.status" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" 
          v-show="data.techName === loginName || data.headName === loginName"
          @click="workEdit(data.workId)">详情</a-button>
        <a-button :style="style"
          v-show="data.techName === loginName"
          @click="personEdit(data.workId)">日志</a-button>
      </div>
      <table class="cardTale" >  
        <tr>
          <td>项目姓名</td>
          <td>{{ data.projectName }}</td>
          <td>工单类型</td>
          <td>{{ data.type }}</td>
        </tr>
        <tr>
          <td>项目电话</td>
          <td>{{ data.telephone }}</td>
          <td>项目负责</td>
          <td>{{ data.techName }}</td>
        </tr>
        <tr>
          <td>装修进度</td>
          <td>{{ data.schedule }}</td>
          <td v-show="loginName === data.techId">工单状态</td>
          <td v-show="loginName === data.techId">{{ data.status }}</td>
        </tr>
        <!-- <tr>
          <td>客户地址</td>
          <td colspan="3">{{ data.address }}</td>
        </tr> -->
        <tr>
          <td>上门日期</td>
          <td colspan="3">{{ dateFilter(data.dateOfVisit,'yyyy-mm-dd hh:mm:ss') }}</td>
        </tr>
        <tr v-if="data.signTime !== null">
          <td>签到时间</td>
          <td colspan="3">{{ dateFilter(data.signTime,'yyyy-mm-dd hh:mm:ss') }}</td>
        </tr>
        <tr v-if="data.departureTime !== null">
          <td>离场时间</td>
          <td colspan="3">{{ dateFilter(data.departureTime,'yyyy-mm-dd hh:mm:ss') }}</td>
        </tr>
      </table>
      <div class="buttonPos" v-show="loginName === data.techId">
        <a-button type="primary" 
          v-show="data.signTime === null && data.dateOfVisit !== null"
          @click="sign(data.timeId, data.workId, data.address)">签到</a-button>

        <a-button type="primary"
          v-show="data.departureTime === null && data.signTime !== null"
          @click="depart(data.timeId, data.workId, data.address)">离开</a-button>

        <a-button type="primary" v-show="data.signTime !== null 
            && ( data.type ==='安装' || data.type ==='调试' || data.type ==='检测' ) "
          @click="WriterInfo(data.workId, data.techId)">核销</a-button>

        <a-button type="primary" 
          v-show="data.status !== '已完成' && data.departureTime !== null" 
          @click="CompleteInfo()">完成</a-button>
        <!-- <a-button type="primary" v-show="data.status === '已完成'" >完结</a-button> -->
      </div>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk(data)">
      <p>{{ formState.modalInfo }}</p>
    </a-modal>
  </div>
</template>
<script setup lang="ts">
// 工单卡片
import { dateFilter } from '../../util/time'
import { httpGet } from '../../config/interFace'
import { reactive, onMounted ,ref } from 'vue';

const techId = localStorage.getItem('techId')
const loginName = localStorage.getItem('loginName')
const emit = defineEmits(['toPage','pageReset'])
const open = ref<boolean>(false);

defineProps({
  data: {
    type: Object,
    default: null
  }
})

onMounted (function () {
  getPosition()
})

const getPosition = () => {
  var options = {
    enableHighAccuracy: true, //布尔值，表示系统是否使用最高精度来表示结果，注意，这会导致较慢的响应时间或者增加电量消耗（比如对于支持gps的移动设备来说）。如果值为false ，设备会通过更快响应以及/或者使用更少的电量等方法来尽可能的节约资源。默认值fasle
    timeout: 5000, //它表明的是设备必须在多长时间（单位毫秒）内返回一个位置。默认直到获取到位置才会返回值。
    maximumAge: 0 //表明可以返回多长时间（即最长年龄，单位毫秒）内的可获取的缓存位置。如果设置为 0, 说明设备不能使用一个缓存位置，而且必须去获取一个真实的当前位置。默认0
  }
  function success(position: any) {
    formState.latitude = position.coords.latitude //当前位置的纬度
    formState.longitude = position.coords.longitude //当前位置经度
  }
  function error(err: { code: number; }) {
    var errorType = ['您拒绝共享位置信息,请去app设置一下！', '获取不到位置信息', '获取位置信息超时']
    console.log(errorType[err.code - 1])
  }
  navigator.geolocation.getCurrentPosition(success, error, options)
}

interface FormState {
  latitude: string
  longitude: string
  modalInfo: string
  status: boolean
}

const formState = reactive<FormState>({
  latitude: '',
  longitude: '',
  modalInfo: '',
  status: false
});

const sign = async (id: number, workId: number, address: string) => {
  formState.status = false
  const res = await httpGet('/position/getCoordinate',{
    address: address
  })
  if(res === 'null')  {
    formState.modalInfo = '客户地址的地理位置读取失败！'
    return
  }
  const distance = await httpGet('/position/getDistance',{
    longitude1: formState.longitude,
    latitude1: formState.latitude,
    longitude2: res.lng,
    latitude2: res.lat
  })
  if(distance >= 5000) {
    formState.modalInfo = '请在指定区域内签到！'
    return
  }
  await httpGet('/workTime/sign',{
    id: id,
    workId: workId,
    updateName: techId
  })
  formState.modalInfo = '签到成功'
  showModal();
  emit('pageReset')
}

const depart = async (id: any, workId: number, address: string) => {
  formState.status = false
  const res = await httpGet('/position/getCoordinate',{
    address: address
  })
  if(res === 'null')  {
    formState.modalInfo = '客户地址的地理位置读取失败！'
    showModal();
    return
  }
  const distance = await httpGet('/position/getDistance',{
    longitude1: formState.longitude,
    latitude1: formState.latitude,
    longitude2: res.lng,
    latitude2: res.lat
  })
  if(distance >= 3000) {
    formState.modalInfo = '请在指定区域内离开！'
    showModal();
    return
  }
  await httpGet('/workTime/depart',{
    id: id,
    workId: workId,
    updateName: techId
  })
  formState.modalInfo = '离开成功'
  showModal();
  emit('pageReset')
}

const CompleteInfo = async () => {
  formState.modalInfo = '工单完成后，锁定核销数量！'
  formState.status = true
  showModal(); // 开启
}

const WriterInfo = (id: any, techId:any) => {
  emit('toPage','subWriter', { id: id, techId: techId })
}

const workEdit = (id: any) => {
  emit('toPage','workEdit', { id: id })
}

const personEdit = (id: any) => {
  emit('toPage','personEdit', { id: id })
}

const showModal = () => {
  open.value = true;
};

const handleOk = async (data: any ) => {
  open.value = false;
  if(formState.status) {
    await httpGet('/workSheet/computer',{ // 计算核销
      timeId: data.timeId,
      workId: data.workId,
      projectId: data.projectId,
      headId: data.headId,
      type: data.type,
      updateName: techId
    })
    emit('pageReset')
    formState.modalInfo = '离开成功'
    formState.status = false
    showModal();
  }
};

const style = {
  backgroundColor: '#0099DD',color: '#fff'
}
</script>
<style scoped>

</style>