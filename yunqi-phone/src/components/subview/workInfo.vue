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
            <td width="70px">项目姓名:</td>
            <td>
              <a-input :disabled="true" style="width: 95%;" v-model:value="formState.projectName"></a-input>
            </td>
          </tr>
          <tr>
            <td>技术人员:</td>
            <td>
              <a-select
                v-model:value="value"
                mode="multiple"
                style="width: 95%"
                placeholder="Please select"
                :options="formState.options"
                @change="handleChange"
              ></a-select>
            </td>
          </tr>
          <tr>
            <td>工单类型:</td>
            <td>
              <a-select style="width: 95%;" v-model:value="formState.workType">
                <a-select-option value="安装">安装</a-select-option>
                <a-select-option value="调试">调试</a-select-option>
                <a-select-option value="交底">交底</a-select-option>
                <a-select-option value="验收">验收</a-select-option>
                <a-select-option value="检测">检测</a-select-option>
                <a-select-option value="售后">售后</a-select-option>
              </a-select>
            </td>
          </tr>
          <tr>
            <td>上门时间:</td>
            <td>
              <a-date-picker format="YYYY-MM-DD HH:mm" :show-time="{ format: 'HH:mm' }"
                 v-model:value="formState.time" style="width: 95%;"/>
            </td>
          </tr>
          <tr>
            <td>工单备注:</td>
            <td>
              <a-input style="width: 95%;" v-model:value="formState.remark"></a-input>
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" html-type="submit">发单</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-form>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>发单成功！</p>
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
const route = useRoute()
const techId = localStorage.getItem("techId");

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
  }
  const tech = await httpGet('/tech/select',{})
  const techs: object[] = []
  for(let i=0; i< tech.length; i++){
    const obj: any = {
      label: tech[i].name,
      value: tech[i].id
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
}

const formState = reactive<FormState>({
  options: ref<string[]>([]),
  techName: '',
  projectName: '',
  workType: '',
  time: null,
  remark: '',
  schedule: ''
});

const handleChange = (value: []) => {
  console.log(`selected ${value}`);
  formState.techName = `${value}`
};

const onFinish = async () => {
  const res = await httpGet('/workSheet/insert',{
    projectId: route.query.id,
    techIds: formState.techName, // 必须
    dateOfVisit: formState.time,
    type: formState.workType,  // 必须
    remark: formState.remark,
    // status: '待上门',
    schedule: formState.schedule,
    createName: techId
  })
  console.log('Success:', res);
  showModal()
};

const showModal = () => {
  open.value = true;
};

const handleOk = (e: MouseEvent) => {
  console.log(e);
  open.value = false;
  router.push({name: 'workSheet'})
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

</script>

<style lang="less" scoped>

</style>