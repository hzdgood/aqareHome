<template>
  <div class="cardDiv">
    <a-card title="工单修改" :bordered="false">
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
            <a-input :disabled="true" :value="formState.dataList.workStatus" style="width: 100%"></a-input>
          </td>
        </tr>
        <tr>
          <td>项目电话</td>
          <td>
            <a-input :disabled="true" :value="formState.dataList.telephone" style="width: 100%"></a-input>
          </td>
          <td>装修进度</td>
          <td>
            <a-input :disabled="true" :value="formState.dataList.schedule" style="width: 100%"></a-input>
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
            <a-select :disabled="true"
                v-model:value="headName"
                style="width: 100%"
                placeholder="Please select"
                :options="formState.options"
                @change="changeName"
              ></a-select>
          </td>
        </tr>
        <tr>
          <td>上门技术</td>
          <td colspan="3">
            <a-select :disabled="true"
                v-model:value="value"
                mode="multiple"
                style="width: 100%"
                placeholder="Please select"
                :options="formState.options"
                @change="changeVisit"
              ></a-select>
          </td>
        </tr>
        <tr>
          <td>上门时间</td>
          <td colspan="3">
            <a-date-picker format="YYYY-MM-DD HH:mm" :disabled="formState.status !== null"
            :show-time="{ format: 'HH:mm' }" style="width: 100%"
            v-model:value="formState.time"/>
          </td>
        </tr>
        <tr>
          <td>工单说明</td>
          <td colspan="3">
            <a-textarea :rows="4" v-model:value="formState.remark"></a-textarea>
          </td>
        </tr>
      </table>
      
      <div style="font-size: 16px; font-weight: bold;">工单明细</div>
      <schemeInfo :data="formState.schemeList"></schemeInfo>

      <div class="buttonPos">
        <a-button type="primary" @click="submit">提交</a-button>
        <a-button type="primary" @click="resPage()">返回</a-button>
      </div>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>工单修改完成！</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
/// 工单详情
import schemeInfo from './info/schemeInfo.vue'
import router from '@/router';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import { reactive, onMounted, ref } from 'vue';
import dayjs, { Dayjs } from 'dayjs';
import { dateFilter } from '../../util/time'

let value = ref<string[]>([]);
let headName = ref<string[]>([]);

const route = useRoute()
const open = ref<boolean>(false);
const techId = localStorage.getItem('techId')

onMounted (async function () {
  const res = await httpGet('/view/work',{
    workId: route.query.id
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
  
  const techNames = res[0].techNames + ''
  if(techNames.includes(',')) {
    const str = techNames.split(',');
    value = ref(str);
  } else {
    value = ref([res[0].techNames]);
  }
  headName = ref([res[0].headName]);

  formState.dataList = res[0] // 工单List
  formState.projectId = res[0].projectId //项目ID
  formState.headId = res[0].headId  // 负责人ID
  formState.options = techs // 上门技术 主负责人 select
  formState.techName = res[0].techNames // 上门技术 BUG
  formState.headName = res[0].headName // 主负责人
  formState.remark = '今日完成情况:' + res[0].workSummary + "\r\n" + "下次工作安排:" + res[0].visitNode // 备注
  formState.status = res[0].workStatus // 总状态

  let scheme
  if(res[0].type === '安装') {
    scheme = await httpGet('/view/scheme',{
      projectId: res[0].projectId,
      notInstalled: 0
    })
    formState.schemeList = scheme
  } else if(res[0].type === '调试') {
    scheme = await httpGet('/view/scheme',{
      projectId: res[0].projectId,
      unregulated: 0
    })
    formState.schemeList = scheme
  }
})

const resPage = () => {
  router.push({name: 'workSheet'})
}

interface FormState {
  dataList: any
  schemeList: any
  options: object
  techName: string
  time: any;
  remark: String
  headName: String
  projectId: String
  headId: String
  status: any
}

const formState = reactive<FormState>({
  dataList: [],
  schemeList: [],
  options: ref<string[]>([]),
  techName: '',
  time: null,
  remark: '',
  headName: '',
  projectId: '',
  headId: '',
  status: null //限制工单状态
});

const changeName = (value: []) => {
  // 有个BUG  
  formState.headName = `${value}`
};

const changeVisit = (value: []) => {
  // 有个BUG  
  formState.techName = `${value}`
};

const submit = async () =>  {
  await httpGet('/workSheet/updateInfo',{ //工单修改 时间，说明
    id: route.query.id,
    dateOfVisit: formState.time,
    remark: formState.remark,
    updateName: techId
  })
  showModal()
}

const showModal = () => {
  open.value = true;
};

const handleOk = () => {
  open.value = false;
  router.push({name: 'workSheet'})
};

</script>

<style lang="less" scoped>

</style>