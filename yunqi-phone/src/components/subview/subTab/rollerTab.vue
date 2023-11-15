<template>
  <div>
    <table class="cardTale">
      <tr>
        <td width="75px">项目姓名</td>
        <td>
          <a-input :disabled="true" :value="data.name" style="width: 95%"></a-input>
        </td>
        <td width="75px">区域</td>
        <td>
          <a-input v-model:value="formState.area" style="width: 95%"></a-input>
        </td>
      </tr>
      <tr>
        <td>*卷帘方案</td>
        <td>
          <a-select @change="selectChange" :options="options" style="width: 95%;"> 
          </a-select>
        </td>
        <td>*长度</td>
        <td>
          <a-input v-model:value="formState.length" style="width: 95%"></a-input>
        </td>
      </tr>
      <tr>
        <td>*高度</td>
        <td>
          <a-input v-model:value="formState.high" style="width: 95%"></a-input>
        </td>
        <td>*安装面宽度</td>
        <td>
          <a-input v-model:value="formState.surfaceWidth" style="width: 95%"></a-input>
        </td>
      </tr>
      <tr>
        <td>*安装方式</td>
        <td>
          <a-select v-model:value="formState.installMethod" style="width: 95%">
            <a-select-option value="外顶装">外顶装</a-select-option>
            <a-select-option value="侧装">侧装</a-select-option>
            <a-select-option value="内顶装">内顶装</a-select-option>
          </a-select>
        </td>
        <td>*扣减说明</td>
        <td>
          <a-select v-model:value="formState.deductionInfo" style="width: 95%">
            <a-select-option value="标准扣减1cm">标准扣减1cm</a-select-option>
            <a-select-option value="尺寸已减">尺寸已减</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>*数量</td>
        <td>
          <a-input v-model:value="formState.number" style="width: 95%"></a-input>
        </td>
        <td>*安装面材质</td>
        <td>
          <a-select v-model:value="formState.surfaceMaterial" style="width: 95%">
            <a-select-option value="木工板">木工板</a-select-option>
            <a-select-option value="混凝土">混凝土</a-select-option>
            <a-select-option value="金属板">金属板</a-select-option>
            <a-select-option value="石膏板">石膏板</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>*电源位置</td>
        <td>
          <a-select v-model:value="formState.powerPosition" style="width: 95%">
            <a-select-option value="左">左</a-select-option>
            <a-select-option value="右">右</a-select-option>
            <a-select-option value="无电源">无电源</a-select-option>
          </a-select>
        </td>
        <td>*需要罩壳</td>
        <td>
          <a-select v-model:value="formState.cover" style="width: 95%">
            <a-select-option value="是">是</a-select-option>
            <a-select-option value="否">否</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>备注</td>
        <td colspan="3">
          <a-input v-model:value="formState.remark" style="width: 95%"></a-input>
        </td>
      </tr>
      <tr>
        <td>现场照片</td>
        <td colspan="3">
          <a-upload
            v-model:file-list="fileList"
            name="file"
            :action="httpUrl + '/picture/upload'"
            @change="handleChange"
          >
            <a-button>
              <upload-outlined></upload-outlined>
              Click to Upload
            </a-button>
          </a-upload>
        </td>
      </tr>
    </table>
    <div class="buttonPos">
      <a-button type="primary" @click="submit">提交</a-button>
      <a-button type="primary" @click="resPage()">返回</a-button>
    </div>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>{{ formState.modalInfo }}</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { httpGet, httpUrl } from '../../../config/interFace'
import type { UploadChangeParam, SelectProps } from 'ant-design-vue';
import { reactive, onMounted, ref, watch } from 'vue';

const loginName = localStorage.getItem('loginName')
const techId = localStorage.getItem("techId");
const open = ref<boolean>(false);
const fileList = ref([]);
const emit = defineEmits(['resPage'])
const options = ref<SelectProps['options']>([]);

const props = defineProps({
  data: {
    type: Object,
    default: null
  },
  id: {
    type: Object,
    default: null
  }
})

let data = props.data
watch(props, (newValue) => {
  data = newValue.data[0]
})

const selectChange = (value: string, options: any) => {
  formState.rollingScheme = options.lable
};

onMounted (async function () {
  const roller = await httpGet('/measure/rollerScheme',{
    projectId: props.id.id
  })
  console.log(roller);
  for(let i=0; i<roller.length; i++) {
    var obj = {
      value: roller[i].productName,
      lable: roller[i].sId
    }
    options.value?.push(obj)
  }
})

interface FormState {
  itemId: string
  area: string
  rollingScheme: string
  length: number
  high: number
  surfaceWidth: number
  installMethod: string
  deductionInfo: string
  number: number
  surfaceMaterial: string
  powerPosition: string
  cover:  string
  remark:  string
  modalInfo: string
  imgUrl: string
  status: boolean
}

const formState = reactive<FormState>({
  itemId: '',
  area: '',
  rollingScheme: '',
  length: 0,
  high: 0,
  surfaceWidth: 0,
  installMethod: '',
  deductionInfo: '',
  number: 0,
  surfaceMaterial: '',
  modalInfo: '',
  powerPosition: '',
  cover:  '',
  remark:  '',
  imgUrl: '',
  status: false
});

const handleChange = (info: UploadChangeParam) => {
  if (info.file.status === 'done') {
    formState.imgUrl =  formState.imgUrl + info.file.response + ","
    formState.modalInfo = '上传成功！'
    showModal()
  } else if (info.file.status === 'error') {
    formState.modalInfo = '上传失败！'
    showModal()
  }
};

const handleOk = async () => {
  open.value = false;
  if(formState.imgUrl === '' && formState.status ) {
    formState.modalInfo = '请上传图片！'
    formState.status = false
    showModal()
    return
  }
  if(formState.imgUrl !== '' && formState.status)  {
    await httpGet('/measure/addRoller',{
      projectId: data.id,
      techId: techId,
      itemId: data.itemId,
      projectName: data.name,
      area: formState.area,
      rollingScheme: formState.rollingScheme,
      length: formState.length,
      high: formState.high,
      surfaceWidth: formState.surfaceWidth,
      installMethod: formState.installMethod,
      deductionInfo: formState.deductionInfo,
      number: formState.number,
      surfaceMaterial: formState.surfaceMaterial,
      powerPosition: formState.powerPosition,
      cover: formState.cover,
      remark: formState.remark,
      imgUrl: formState.imgUrl,
      createName: loginName
    })
    resPage() 
  }
}

const submit = async () => {
  formState.modalInfo = '请确认新增测量单！'
  formState.status = true
  showModal()
}

const showModal = () => {
  open.value = true;
};

const resPage = () => {
  emit('resPage')
}

</script>
