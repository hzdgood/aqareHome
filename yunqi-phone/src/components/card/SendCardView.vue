<template>
  <div class="cardDiv">
    <a-card :title="data.projectName + '&nbsp;&nbsp;' + data.type + '-' + data.techId" :bordered="false">
      <div class="buttonPos">
        <a-button :style="style" @click="workEdit(data.workId)">详情</a-button>
        <a-button :style="style" @click="personEdit(data.workId)">派单</a-button>
        <a-button :style="style" @click="workDisabled()">无效</a-button>
      </div>
      <table class="cardTale" >  
        <tr>
          <td>上门技术</td>
          <td>{{ data.techNames }}</td>
          <td>工单类型</td>
          <td>{{ data.type }}</td>
        </tr>
        <tr>
          <td>工单状态</td>
          <td>{{ data.workStatus }}</td>
          <td>预约时间</td>
          <td colspan="3">{{ dateFilter(data.dateOfVisit,'yyyy-mm-dd') }}</td>
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

const workDisabled = async () => {
  formState.modalInfo = '请确认无效改工单！'
  showModal()
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
  open.value = false
  await httpGet('/workSheet/delete',{
    id: data.workId,
    updateName: loginName
  })
  emit('pageReset')
};

const style = {
  backgroundColor: '#0099DD',color: '#fff'
}
</script>
<style scoped>

</style>