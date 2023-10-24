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
              <td>签到时间</td>
              <td colspan="3">
                <a-input :disabled="true" :value="dataList.signTime" style="width: 100%"></a-input>
              </td>
            </tr>
            <tr>
              <td>离开时间</td>
              <td colspan="3">
                <a-input :disabled="true" :value="dataList.departureTime" style="width: 100%"></a-input>
              </td>
            </tr>
            <tr>
              <td>核销时间</td>
              <td colspan="3">
                <a-input :disabled="true" :value="dataList.writerTime" style="width: 100%"></a-input>
              </td>
            </tr>
          </table>
          <div class="buttonPos">
            <a-button type="primary" v-show="dataList.status === '待上门'" @click="deletePerson(dataList.timeId, dataList.techId)">取消上门</a-button>
          </div>
        </div>
      </div>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
import router from '@/router';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import { reactive, onMounted, ref } from 'vue';

const route = useRoute()
const techId = localStorage.getItem('techId')
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
  editStatus: false
});

const addPerson = async () => {
  // 修改工单表
  const res = await httpGet('/workSheet/updatePerson',{
    id: route.query.id,
    techIds: formState.techName + "," + formState.techNames,
    updateName: techId
  })
  console.log(res);

  // 单人新增一条数据
  const res1 = await httpGet('/workTime/insert',{
    workId: formState.workId,
    projectId: formState.projectId,
    techId: formState.techName,
    status: '待上门',
    createName: techId
  })
  console.log(res1);
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
    const res = await httpGet('/workSheet/updatePerson',{
      id: route.query.id,
      techIds: techIds,
      updateName: techId
    })
    console.log(res);

    // 取消一条单人数据
    const res1 = await httpGet('/workTime/delete',{
      id: timeId,
      updateName: techId
    })
    console.log(res1);
  }
  console.log(techIds);
  
};

const editHead = async () => {
  const res = await httpGet('/workSheet/updateHead',{
    id: route.query.id,
    headName: formState.headName,
    updateName: techId
  })
  console.log(res);
};

const changeVisit = (value: []) => {
  formState.techName = `${value}`
};

const changeName = (value: []) => {
  formState.headName = `${value}`
};

const resPage = () => {
  router.push({name: 'workSheet'})
}

</script>