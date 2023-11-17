<template>
  <a-card title="工单详情" :bordered="false">
    <table class="cardTale">
      <tr>
        <td width="70px">项目姓名</td>
        <td>
          <a-input :disabled="true" :value="route.query.name" style="width: 100%"></a-input>
        </td>
        <td>预约时间</td>
        <td>
          <a-input :disabled="true" :value="formState.dataList.dateOfVisit" style="width: 100%"></a-input>
        </td>
      </tr>
      <tr>
        <td>工单状态</td>
        <td>
          <a-input :disabled="true" :value="formState.dataList.workStatus" style="width: 100%"></a-input>
        </td>
        <td>工单类型</td>
        <td>
          <a-input :disabled="true" :value="formState.dataList.type" style="width: 100%"></a-input>
        </td>
      </tr>
      <tr>
        <td>工单负责人</td>
        <td>
          <a-input :disabled="true" :value="formState.dataList.headName" style="width: 100%"></a-input>
        </td>
        <td>上门技术</td>
        <td>
          <a-input :disabled="true" :value="formState.dataList.techIds" style="width: 100%"></a-input>
        </td>
      </tr>
      <tr>
        <td width="70px">客户地址</td>
        <td colspan="3">
          <a-input :disabled="true" :value="route.query.address" style="width: 100%"></a-input>
        </td>
      </tr>
      <tr>
        <td>工单备注</td>
        <td colspan="3">
          <a-textarea :disabled="true" :rows="4" v-model:value="formState.dataList.remark"></a-textarea>
        </td>
      </tr>
    </table>
    <table class="cardTale">
      <tr>
        <td width="70px">今日工作总结</td>
        <td colspan="3">
          <a-textarea v-model:value="formState.dataList.workSummary" :rows="5" />
        </td>
      </tr>
      <tr>
        <td width="70px">下次上门节点</td>
        <td colspan="3">
          <a-textarea v-model:value="formState.dataList.visitNode" :rows="5" />
        </td>
      </tr>
      <tr>
        <td width="70px">下次上门注意事项</td>
        <td colspan="3">
          <a-textarea v-model:value="formState.dataList.handover" :rows="5" />
        </td>
      </tr>
      <tr>
        <td>工单备注</td>
        <td colspan="3">
          <a-textarea :disabled="true" :rows="4" v-model:value="formState.dataList.remark"></a-textarea>
        </td>
      </tr>
    </table>
    <div class="buttonPos">
      <a-button type="primary" @click="resPage()">返回</a-button>
    </div>
  </a-card>
</template>

<script setup lang="ts">
import router from '@/router';
import { httpGet } from '../../config/interFace'
import { reactive, onMounted } from 'vue';
import { useRoute } from "vue-router";

const route = useRoute()

onMounted (async function () {
  const res = await httpGet('/view/workAll',{
    workId: route.query.id
  })
  formState.dataList = res
})

interface FormState {
  dataList: any
}

const formState = reactive<FormState>({
  dataList: [],
});

const resPage = () => {
  router.push({name: 'workSheet'})
}
</script>