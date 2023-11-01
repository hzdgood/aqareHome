<template>
  <div class="cardDiv"> <!--  -->
    <a-card :title="data.techNames + '&nbsp;&nbsp;' + data.type" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="workEdit(data.workId)">详情</a-button>
        <a-button :style="style" @click="personEdit(data.workId)">日志</a-button>
        <a-button :style="style" @click="workDisabled(data.workId)">无效</a-button>
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
          <td>客户地址</td>
          <td colspan="3">{{ data.address }}</td>
        </tr>
        <tr>
          <td>装修进度</td>
          <td>{{ data.schedule }}</td>
          <td>工单状态</td>
          <td>{{ data.workStatus }}</td>
        </tr>
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
import { reactive ,ref } from 'vue';

const loginName = localStorage.getItem('loginName')
const emit = defineEmits(['toPage','pageReset'])
const open = ref<boolean>(false);

defineProps({
  data: {
    type: Object,
    default: null
  }
})

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

const workDisabled = async (id: any) => {
  await httpGet('/workSheet/delete',{
    workId: id,
    updateName: loginName
  })
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
      updateName: loginName
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