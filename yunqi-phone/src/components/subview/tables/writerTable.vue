<template>
  <div>
    <table class="cardTale">
      <tr>
        <td width="70px">产品名称:</td>
        <td colspan="3">
          <a-input :disabled="true" style="width: 95%;" :value="props.data.productName"></a-input>
        </td>
      </tr>
      <tr>
        <td>未安装:</td>
        <td>
          <a-input :disabled="true" style="width: 90%;" :value="props.data.notInstalled"></a-input>
        </td>
        <td width="70px">未调试:</td>
        <td>
          <a-input :disabled="true" style="width: 90%;" :value="props.data.unregulated"></a-input>
        </td>
      </tr>
      <tr>
        <td>本次安装:</td>
        <td>
          <a-input :disabled="props.data.status === '已完成'" 
            style="width: 90%;" v-model:value="formState.install" @change="formChange()"></a-input>
        </td>
        <td>本次调试:</td>
        <td>
          <a-input :disabled="props.data.status === '已完成' || props.data.type === '安装'" 
            style="width: 90%;" v-model:value="formState.debug" @change="formChange()"></a-input>
        </td>
      </tr>
    </table>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
const techId = localStorage.getItem("techId")
const loginName = localStorage.getItem('loginName')
const props = defineProps({
  data: {
    type: Object,
    default: null
  }
})

const emit = defineEmits(['change'])

const formChange = () => {
  let install = formState.install;
  let debug = formState.debug;
  if(install === '') {
    install = '0' //默认0
  }
  if(debug === '') {
    debug = '0' //默认0
  }
  // 判断数量
  const obj = {
    projectId: props.data.projectId,
    schemeId: props.data.schemeId,
    workId: props.data.workId,
    productId: props.data.productId,
    techId: techId,
    type: props.data.type,
    install: install,
    debug: debug,
    notInstalled: props.data.notInstalled, // 未安装
    unregulated: props.data.unregulated, // 未调试
    createName: loginName // 用于新增
  }
  emit('change',obj)
}

interface FormState {
  install: string;
  debug: string
}

const formState = reactive<FormState>({
  install: '',
  debug: '',
});

</script>