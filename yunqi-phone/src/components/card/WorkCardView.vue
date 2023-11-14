<template>
  <div class="cardDiv">
    <a-card :title="data.projectName + '&nbsp;&nbsp;' + data.type + '-' + data.techId" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="workEdit(data.workId)">详情</a-button>
        <a-button :disabled="true" :style="style" >--交通费--</a-button>
        <a-button :disabled="true" :style="style" >--退货--</a-button>
      </div>
      <table class="cardTale" >  
        <tr>
          <td width="70px">上门技术</td>
          <td>{{ data.techNames }}</td>
          <td width="70px">工单类型</td>
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
          <td>工单状态</td>
          <td>{{ data.status }}</td>
        </tr>
        <tr>
          <td>客户地址</td>
          <td colspan="3">{{ data.address }}</td>
        </tr>
        <tr>
          <td>预约时间</td>
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
          @click="sign(data.timeId, data.workId)">签到</a-button>
        <a-button type="primary"
          v-show="data.departureTime === null && data.signTime !== null"
          @click="depart(data.timeId, data.workId)">离开</a-button>
        <a-button type="primary" v-show="data.signTime !== null"
          @click="WriterInfo(data.workId, data.techId)">核销</a-button>
        <a-button type="primary" 
          v-show="data.status !== '已完成' && data.departureTime !== null" 
          @click="CompleteInfo()">完成</a-button>
        <a-button type="primary" :disabled="formState.deleteStatus" 
          v-show="status"
          @click="deleteInfo()">完结</a-button>
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
import { onMounted, reactive ,ref } from 'vue';

const loginName = localStorage.getItem('loginName')
const admins = localStorage.getItem("admins");
const emit = defineEmits(['toPage','pageReset'])
const open = ref<boolean>(false);

const json: any = defineProps({
  data: {
    type: Object,
    default: null
  }
})
const data = json.data;

const status = loginName === data.headName || admins === 'true'

onMounted (function () {
  if(loginName === data.headName && data.workSummary != null && data.visitNode != null && data.handover != null ) {
    formState.deleteStatus = false
  } 
  if(admins === 'true') {
    formState.deleteStatus = false
  }
})

const style = {
  backgroundColor: '#0099DD',color: '#fff'
}

interface FormState {
  latitude: string
  longitude: string
  modalInfo: string
  status: boolean,
  status1: boolean
  deleteStatus: boolean
}

const formState = reactive<FormState>({
  latitude: '',
  longitude: '',
  modalInfo: '',
  status: false,
  status1: false,
  deleteStatus: true
});

const sign = async (id: number, workId: number) => {
  formState.status = false
  await httpGet('/workTime/sign',{
    id: id,
    workId: workId,
    updateName: loginName
  })
  formState.modalInfo = '签到成功'
  showModal();
}

const depart = async (id: any, workId: number) => {
  formState.status = false
  await httpGet('/workTime/depart',{
    id: id,
    workId: workId,
    updateName: loginName
  })
  formState.modalInfo = '离开成功'
  showModal();
}

const CompleteInfo = async () => {
  formState.modalInfo = '工单完成后，锁定核销数量！'
  formState.status = true
  showModal(); // 开启
}

const deleteInfo = async () => {
  formState.modalInfo = '请确认是否完结该工单！'
  formState.status1 = true
  showModal(); // 开启
}

const WriterInfo = (id: any, techId:any) => {
  emit('toPage','subWriter', { id: id, techId: techId })
}

const showModal = () => {
  open.value = true;
};

const workEdit = (id: any) => {
  emit('toPage','workEdit', { id: id })
}

const handleOk = async (data: any ) => {
  open.value = false;
  if(formState.status) {
    await httpGet('/workSheet/computer',{ // 计算核销
      timeId: data.timeId,
      workId: data.workId,
      projectId: data.projectId,
      headId: data.headId,
      type: data.type,
      techNames: data.techNames,
      updateName: loginName
    })
    formState.modalInfo = '离开成功'
    formState.status = false
    showModal();
  }
  if(formState.status1) {
    await httpGet('/workSheet/complete',{ // 工单完结
      workId: data.workId
    })
    formState.modalInfo = '工单已完成'
    formState.status1 = false
    showModal();
  }
  emit('pageReset')
};
</script>
<style scoped>

</style>