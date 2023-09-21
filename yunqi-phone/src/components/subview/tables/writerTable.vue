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
          <a-input :disabled="props.data.departureTime === null" style="width: 90%;" v-model:value="formState.install" @change="formChange()"></a-input>
        </td>
        <td>本次调试:</td>
        <td>
          <a-input :disabled="props.data.departureTime === null" style="width: 90%;" v-model:value="formState.debug" @change="formChange()"></a-input>
        </td>
      </tr>
    </table>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
const techId = localStorage.getItem("techId")
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
    install = '0'
  }
  if(debug === '') {
    debug = '0'
  }
  // 判断数量
  const obj = {
    projectId: props.data.projectId,
    schemeId: props.data.schemeId,
    workId: props.data.workId,
    productId: props.data.productId,
    techId: techId,
    install: install,
    debug: debug,
    updateName: techId
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