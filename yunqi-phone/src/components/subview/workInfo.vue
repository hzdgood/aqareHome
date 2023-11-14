<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <a-form
        :model="formState"
        name="workInfo"
        class="workInfo"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <table class="cardTale">
          <tr>
            <td width="80px">项目姓名:</td>
            <td>
              <a-input :disabled="true" style="width: 95%;" v-model:value="formState.projectName"></a-input>
            </td>
          </tr>
          <tr>
            <td>*主负责人:</td>
            <td>
              <a-select
                v-model:value="headName"
                style="width: 95%"
                placeholder="Please select"
                :options="formState.options"
                @change="changeHead"
              ></a-select>
            </td>
          </tr>
          <tr>
            <td>*上门技术:</td>
            <td>
              <a-select
                v-model:value="value"
                mode="multiple"
                style="width: 95%"
                placeholder="Please select"
                :options="formState.options"
                @change="changeTech"
              ></a-select>
            </td>
          </tr>
          <tr>
            <td>*工单类型:</td>
            <td>
              <a-select style="width: 95%;" v-model:value="formState.workType">
                <a-select-option value="安装">安装</a-select-option>
                <a-select-option value="调试">调试</a-select-option>
                <a-select-option value="交底">交底</a-select-option>
                <a-select-option value="验收">验收</a-select-option>
                <a-select-option value="检测">检测</a-select-option>
                <a-select-option v-show="admins" value="售后">售后</a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>预约时间:</td>
            <td>
              <a-date-picker format="YYYY-MM-DD HH:mm" :show-time="{ format: 'HH:mm' }"
                 v-model:value="formState.time" style="width: 95%;"/>
            </td>
          </tr>
          <tr>
            <td>工单说明:</td>
            <td>
              <a-textarea :rows="4" style="width: 95%;" v-model:value="formState.remark"></a-textarea>
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">确定</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>{{ formState.modalInfo }}</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
//发单
import { reactive, onMounted, ref } from 'vue';
import router from '@/router';
import { useRoute } from "vue-router";
import { httpGet } from '../../config/interFace'

const open = ref<boolean>(false);
const value = ref<string[]>([]);
const headName = ref<string[]>([]);
const route = useRoute()
const loginName = localStorage.getItem('loginName')
const admins = localStorage.getItem("admins");

const resPage = () => {
  router.push({name: 'project'})
}

onMounted (async function () {
  const res = await httpGet('/project/selectId',{
    id: route.query.id
  })
  if(res.length !== 0) {
    formState.projectName = res[0].name
    formState.schedule = res[0].schedule
    formState.remark = res[0].remark
  }
  const tech = await httpGet('/tech/select',{})
  const techs: object[] = []
  for(let i=0; i< tech.length; i++){
    const obj: any = {
      label: tech[i].name,
      value: tech[i].name
    }
    techs.push(obj)
  }
  formState.options = techs
})

interface FormState {
  options: object
  schedule: string
  techName: string;
  projectName: string;
  workType: string;
  time: any;
  remark: String;
  headName: String
  modalInfo: String
}

const formState = reactive<FormState>({
  options: ref<string[]>([]),
  schedule: '',
  techName: '',
  projectName: '',
  workType: '',
  time: null,
  remark: '',
  headName: '',
  modalInfo: ''
});

const changeTech = (value: []) => {
  formState.techName = `${value}`
};

const changeHead = (value: []) => {
  formState.headName = `${value}`
};

const onFinish = async () => {
  formState.modalInfo = '是否确认发单！'
  showModal()
};

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  open.value = false;
  if(formState.modalInfo === '发单完成') {
    router.push({name: 'sendSheet'})
  } else {
    const res = await httpGet('/workSheet/insert',{
      projectId: route.query.id,
      techIds: formState.techName, // 必须
      dateOfVisit: formState.time,
      type: formState.workType,  // 必须
      remark: formState.remark,
      headName: formState.headName, // 必须
      schedule: formState.schedule,
      createName: loginName
    })
    formState.modalInfo = res
    showModal()
  }
};

const onFinishFailed = () => {
};

</script>

<style lang="less" scoped>

</style>