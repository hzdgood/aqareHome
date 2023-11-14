<template>
  <div class="cardDiv">
    <a-card :title="'人员配置'" :bordered="false">
      <div class="buttonPos">
        <a-button type="primary" @click="formState.addStatus = true">新增技术</a-button>
        <a-button type="primary" @click="formState.editStatus = true" >修改负责人</a-button>
        <a-button type="primary" @click="resPage()">返回</a-button>
      </div>

      <div v-show='formState.addStatus'>
        <table class="cardTale">
          <tr>
            <td width="70px">上门技术</td>
            <td colspan="3">
              <a-select
                  v-model:value="value"
                  style="width: 100%"
                  placeholder="Please select"
                  :options="formState.options"
                  @change="changeVisit"
                ></a-select>
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" @click="addPerson">提交</a-button>
          <a-button @click="formState.addStatus = false" type="primary">取消</a-button>
        </div>
      </div>

      <div v-show='formState.editStatus'>
        <table class="cardTale">
          <tr>
            <td width="70px">主负责人</td>
            <td colspan="3">
              <a-select
                v-model:value="value"
                style="width: 100%"
                placeholder="Please select"
                :options="formState.options"
                @change="changeName"
              ></a-select>
          </td>
        </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" @click="editHead">提交</a-button>
          <a-button @click="formState.editStatus = false" type="primary">取消</a-button>
        </div>
      </div>
      <div v-show="formState.addStatus === false && formState.editStatus === false">
        <div v-for="dataList in formState.dataList" :key="dataList">
          <table class="cardTale">
            <tr>
              <td width="70px">上门技术</td>
              <td>
                <a-input :disabled="true" :value="dataList.techId" style="width: 100%"></a-input>
              </td>
              <td width="70px">工单状态</td>
              <td>
                <a-input :disabled="true" :value="dataList.status" style="width: 100%"></a-input>
              </td>
            </tr>
            <tr>
              <td>预约时间</td>
              <td>
                <a-input :disabled="true" :value="dateFilter(dataList.dateOfVisit,'yyyy-mm-dd')" style="width: 100%"></a-input>
              </td>
              <td>签到时间</td>
              <td>
                <a-input :disabled="true" :value="dateFilter(dataList.signTime,'yyyy-mm-dd')" style="width: 100%"></a-input>
              </td>
            </tr>
          </table>
          <div class="buttonPos">
            <a-button type="primary" v-show="dataList.status === '待上门'" @click="deletePerson(dataList.timeId, dataList.techId)">取消上门</a-button>
          </div>
        </div>
      </div>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk()">
      <p>{{ formState.modalInfo }}</p>
    </a-modal>
  </div>
</template>

<script lang="ts" setup>
import router from '@/router';
import { dateFilter } from '../../util/time'
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import { reactive, onMounted, ref } from 'vue';

const route = useRoute()
const loginName = localStorage.getItem('loginName')
const open = ref<boolean>(false);
let value = ref<string[]>([]);

onMounted (async function () {
  const res = await httpGet('/view/work',{
    workId: route.query.id
  })
  formState.dataList = res
  formState.techNames = res[0].techNames
  formState.workId = res[0].workId
  formState.projectId = res[0].projectId
  formState.projectName = res[0].projectName
  formState.techName = res[0].techName

  const tech = await httpGet('/tech/select',{})  // 查询技术
  const techs: object[] = []
  for(let i=0; i< tech.length; i++){
    const obj: any = {
      label: tech[i].name,
      value: tech[i].name
    }
    techs.push(obj)
  }
  formState.options = techs
 
});

interface FormState {
  dataList: any
  options: object
  techName: string
  headName: String
  projectName: String
  techNames: String
  type: string
  workId: string
  projectId: string
  addStatus: boolean
  editStatus: boolean
  modalInfo: string
}

const formState = reactive<FormState>({
  dataList: [],
  options: ref<string[]>([]),
  techName: '',
  headName: '',
  projectName: '',
  techNames: '',
  type: '',
  workId: '',
  projectId: '',
  addStatus: false,
  editStatus: false,
  modalInfo: ''
});

const addPerson = async () => {
  // 修改工单表
  await httpGet('/workSheet/updatePerson',{
    id: route.query.id,
    techIds: formState.techName + "," + formState.techNames,
    updateName: loginName
  })
  // 单人新增一条数据
  await httpGet('/workTime/insert',{
    workId: formState.workId,
    projectId: formState.projectId,
    techId: formState.techName,
    status: '待上门',
    createName: loginName
  })
  formState.modalInfo = '新增技术上门成功！'
  showModal()
};

const deletePerson = async (timeId: any, name: any) => { // 针对多个人
  let techIds = ""
  if(formState.techNames.includes(',')) {
    const str = formState.techNames.split(",")
    for(let i=0; i< str.length; i++) {
      if(str[i] !== name) {
        techIds =  techIds + str[i] + ","
      }
    }
    techIds = techIds.substring(0, techIds.length-1) // 去除，号
    // 修改工单表
    await httpGet('/workSheet/updatePerson',{
      id: route.query.id,
      techIds: techIds,
      updateName: loginName
    })
    // 取消一条单人数据
    await httpGet('/workTime/delete',{
      id: timeId,
      updateName: loginName
    })
    formState.modalInfo = '取消技术上门成功！'
    showModal()
  }
};

const editHead = async () => {
  await httpGet('/workSheet/updateHead',{
    id: route.query.id,
    headName: formState.headName,
    updateName: loginName
  })
  formState.modalInfo = '修改负责人成功！'
  showModal()
};

const handleOk = async () => {
  open.value = false
  router.push({name: 'sendSheet'})
};

const showModal = () => {
  open.value = true;
};

const changeVisit = (value: []) => {
  formState.techName = `${value}`
};

const changeName = (value: []) => {
  formState.headName = `${value}`
};

const resPage = () => {
  router.push({name: 'sendSheet'})
}

</script>