<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td>
            <a-input :disabled="true" :value="formState.dataList.projectName" style="width: 100%"></a-input>
          </td>
          <td width="70px">负责人</td>
          <td>
            <a-input :disabled="true" :value="formState.headName" style="width: 100%"></a-input>
          </td>
        </tr>
        <tr>
          <td>工单类型</td>
          <td>
            <a-input :disabled="true" :value="formState.dataList.type" style="width: 100%"></a-input>
          </td>
          <td>工单状态</td>
          <td>
            <a-input :disabled="true" :value="formState.dataList.status" style="width: 100%"></a-input>
          </td>
        </tr>
        <tr>
          <td width="70px">项目地址</td>
          <td colspan="3">
            <a-input :disabled="true" :value="formState.dataList.address" style="width: 100%"></a-input>
          </td>
        </tr>
        <tr>
          <td>主负责人</td>
          <td colspan="3">
            <a-select
                v-model:value="headName"
                style="width: 100%"
                placeholder="Please select"
                :options="formState.options"
                @change="handleChange"
              ></a-select>
          </td>
        </tr>
        <tr>
          <td>上门技术</td>
          <td colspan="3">
            <a-select
                v-model:value="value"
                mode="multiple"
                style="width: 100%"
                placeholder="Please select"
                :options="formState.options"
                @change="handleChange"
              ></a-select>
          </td>
        </tr>
        <tr>
          <td>上门时间</td>
          <td colspan="3">
            <a-date-picker format="YYYY-MM-DD HH:mm" :disabled="formState.dataList.signTime !== null"
            :show-time="{ format: 'HH:mm' }" style="width: 100%"
            v-model:value="formState.time"/>
          </td>
        </tr>
        <tr>
          <td>无效说明</td>
          <td colspan="3">
            <a-select v-model:value="errorInfo" style="width: 100%">
              <a-option value="客户意向变更">客户意向变更</a-option>
              <a-option value="用户放弃服务">用户放弃服务</a-option>
              <a-option value="不符合安装环境">不符合安装环境</a-option>
              <a-option value="实物与工单不符">实物与工单不符</a-option>
              <a-option value="用户信息错误">用户信息错误</a-option>
            </a-select>
          </td>
        </tr>
        <tr>
          <td>工单备注</td>
          <td colspan="3">
            <a-input v-model:value="formState.remark"></a-input>
          </td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button type="primary" v-show="formState.dataList.departureTime === null" @click="submit">提交</a-button>
        <a-button type="primary" @click="resPage()">返回</a-button>
        <a-button type="primary" @click="disabled()">无效</a-button>
        <a-button type="primary" @click="complete()">完结</a-button>
      </div>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>工单修改完成！</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
/// 工单详情
import router from '@/router';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import { reactive, onMounted, ref } from 'vue';
import dayjs, { Dayjs } from 'dayjs';
import { dateFilter } from '../../util/time'

let value = ref<string[]>([]);
let errorInfo = ref<string[]>([]);
let headName = ref<string[]>([]);

const route = useRoute()
const open = ref<boolean>(false);

onMounted (async function () {
  const res = await httpGet('/view/work',{
    workId: route.query.id,
    techIds: 1
  })
  if(res[0].dateOfVisit !== null ) { // 上门时间
    const time = dateFilter(res[0].dateOfVisit,'yyyy-mm-dd hh:mm:ss')
    formState.time = ref<Dayjs>(dayjs(time, 'YYYY-MM-DD hh:mm')); // 上门时间
  }
  const tech = await httpGet('/tech/select',{})  // 查询技术
  const techs: object[] = []
  for(let i=0; i< tech.length; i++){
    const obj: any = {
      label: tech[i].name,
      value: tech[i].name
    }
    if(formState.headId + '' === tech[i].id + '') { 
      formState.headName = tech[i].name // 负责人
    }
    techs.push(obj)
  }
  
  const techName = res[0].techNames + ''
  if(techName.includes(',')) {
    const str = techName.split(',');
    value = ref(str);
  } else {
    value = ref([res[0].techName]);
  }
  errorInfo = ref([res[0].headName]);

  formState.dataList = res[0] // 工单List
  formState.projectId = res[0].projectId //项目ID
  formState.headId = res[0].headId  // 负责人ID
  formState.options = techs // 上门技术 主负责人 select
  formState.techName = res[0].techName // 上门技术 BUG
  formState.headName = res[0].headName // 主负责人
})

const resPage = () => {
  router.push({name: 'workSheet'})
}

const complete = async () => {
  await httpGet('/worksheet/disabled',{
    workId: route.query.id
  })
}

const disabled = async () => {
  await httpGet('/worksheet/disabled',{
    workId: route.query.id,
    disInfo: formState.disInfo
  })
}

interface FormState {
  dataList: any,
  options: object
  techName: string;
  time: any;
  remark: String;
  headName: String
  projectId: String
  headId: String
  disInfo: String
}

const formState = reactive<FormState>({
  dataList: [],
  options: ref<string[]>([]),
  techName: '',
  time: null,
  remark: '',
  headName: '',
  projectId: '',
  headId: '',
  disInfo: ''
});

const handleChange = (value: []) => {
  // 有个BUG  
  formState.techName = `${value}`
};

const submit = async () =>  {
  await httpGet('/workSheet/updateInfo',{ //工单修改 上门，技术
    id: route.query.id,
    projectId: formState.projectId,
    techIds: formState.techName,
    dateOfVisit: formState.time,
    remark: formState.remark,
  })
  showModal()
}

const showModal = () => {
  open.value = true;
};

const handleOk = (e: MouseEvent) => {
  console.log(e);
  open.value = false;
  router.push({name: 'workSheet'})
};

</script>

<style lang="less" scoped>

</style>