<template>
  <div class="cardDiv">
    <a-card title="" :bordered="false">
      <table class="cardTale">
        <tr>
          <td width="70px">项目姓名</td>
          <td><a-input :disabled="true" :value="formState.dataList.projectName" style="width: 100%"></a-input></td>
          <td width="70px">负责人</td>
          <td><a-input :disabled="true" :value="formState.dataList.techName" style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>目前任务</td>
          <td><a-input :disabled="true" :value="formState.dataList.node" style="width: 100%"></a-input></td>
          <td>装修进度</td>
          <td><a-input :disabled="true" :value="formState.dataList.schedule" style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>类型</td>
          <td><a-input :disabled="true" :value="formState.dataList.type" style="width: 100%"></a-input></td>
          <td>房型</td>
          <td><a-input :disabled="true" :value="formState.dataList.houseState" style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>区域</td>
          <td><a-input :disabled="true" :value="formState.dataList.region" style="width: 100%"></a-input></td>
          <td>小区</td>
          <td><a-input :disabled="true" :value="formState.dataList.village" style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>项目地址</td>
          <td colspan="3"><a-input :disabled="true" :value="formState.dataList.address" style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>备注</td>
          <td colspan="3"><a-input :disabled="true" :value="formState.dataList.remark" style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>交接信息</td>
          <td colspan="3">
            <a-textarea v-model:value="formState.desc" placeholder="Basic usage" :rows="4" />
          </td>
        </tr>
      </table>
      

      <div class="buttonPos">
        <a-button @click="resPage()">返回</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
/// 项目详情页
import router from '@/router';
import { reactive, onMounted, ref } from 'vue';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";

const resPage = () => {
  router.push({name: 'project'})
}

onMounted (async function () {
  const route = useRoute()
  const res = await httpGet('/view/project',{
    projectId: route.query.id
  })
  formState.dataList = res[0]

  const work = await httpGet('/view/work',{ // 项目工单查询
    projectId: route.query.id,
    techIds: 1,
    headId: 1
  })
  console.log(work);
  formState.workList = work
})

interface FormState {
  dataList: any,
  workList: any,
  options: object
  techName: string;
  time: any;
  remark: String;
  headName: String
  desc: string
}

const formState = reactive<FormState>({
  dataList: [],
  workList: [],
  options: ref<string[]>([]),
  techName: '',
  time: null,
  remark: '',
  headName: '',
  desc: ''
});

</script>

<style scoped lang="less">

</style>