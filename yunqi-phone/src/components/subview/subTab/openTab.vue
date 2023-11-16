<template>
  <div>
    <table class="cardTale">
      <tr>
        <td width="75px">项目姓名</td>
        <td>
          <a-input :disabled="true" v-model:value="data.name" style="width: 95%;"></a-input>
        </td>
        <td width="75px">区域</td>
        <td>
          <a-input v-model:value="formState.area" style="width: 95%;"></a-input>
        </td>
      </tr>
      <tr>
        <td>用户方案</td>
        <td>
          <a-select @change="selectChange" :options="options" style="width: 95%;"> 
          </a-select>
        </td>
        <td>*数量</td>
        <td>
          <a-input v-model:value="formState.number" style="width: 95%;"></a-input>
        </td>
      </tr>
      <tr>
        <td>*电机型号</td>
        <td>
          <a-select v-model:value="formState.motorModel" style="width: 95%;">
            <a-select-option value="C2电机">C2电机</a-select-option>
            <a-select-option value="C3电机">C3电机</a-select-option>
            <a-select-option value="B1电机">B1电机</a-select-option>
            <a-select-option value="L2电机">L2电机</a-select-option>
            <a-select-option value="Zigbee电机">Zigbee电机</a-select-option>
            <a-select-option value="梦幻帘">梦幻帘</a-select-option>
            <a-select-option value="手动轨">手动轨</a-select-option>
          </a-select>
        </td>
        <td>*轨道类型</td>
        <td>
          <a-select v-model:value="formState.trackType" style="width: 95%;">
            <a-select-option value="直轨">直轨</a-select-option>
            <a-select-option value="L型左直角">L型左直角</a-select-option>
            <a-select-option value="L型右直角">L型右直角</a-select-option>
            <a-select-option value="异型轨">异形轨</a-select-option>
            <a-select-option value="超静音">超静音</a-select-option>
            <a-select-option value="嵌入式">嵌入式</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>*安装方式</td>
        <td>
          <a-select v-model:value="formState.installMethod" style="width: 95%;">
            <a-select-option value="顶装">顶装</a-select-option>
            <a-select-option value="侧装">侧装</a-select-option>
          </a-select>
        </td>
        <td>*开合方式</td>
        <td>
          <a-select v-model:value="formState.openMethod" style="width: 95%;">
            <a-select-option value="单开">单开</a-select-option>
            <a-select-option value="双开">双开</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>*窗帘盒宽</td>
        <td>
          <a-input v-model:value="formState.boxWidth" style="width: 95%;"></a-input>
        </td>
        <td>*电源位置</td>
        <td>
          <a-select v-model:value="formState.powerPosition" style="width: 95%;">
            <a-select-option value="左">左</a-select-option>
            <a-select-option value="右">右</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>*安装面材质</td>
        <td>
          <a-select v-model:value="formState.surfaceMaterial" style="width: 95%;">
            <a-select-option value="木工板">木工板</a-select-option>
            <a-select-option value="混凝土">混凝土</a-select-option>
            <a-select-option value="金属板">金属板</a-select-option>
            <a-select-option value="石膏板">石膏板</a-select-option>
          </a-select>
        </td>
        <td>预留位置</td>
        <td>
          <a-select v-model:value="formState.placeholder" style="width: 95%;">
            <a-select-option value="靠窗">靠窗</a-select-option>
            <a-select-option value="居中">居中</a-select-option>
            <a-select-option value="靠屋">靠屋</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>扣减说明</td>
        <td>
          <a-select v-model:value="formState.deductionInfo" style="width: 95%;">
            <a-select-option value="标准扣减3-4cm">标准扣减3-4cm</a-select-option>
            <a-select-option value="自定义扣减">自定义扣减</a-select-option>
            <a-select-option value="不扣减(成品尺寸)">不扣减(成品尺寸)</a-select-option>
          </a-select>
        </td>
        <td>*l1</td>
        <td>
          <a-input v-model:value="formState.l1" style="width: 95%;"></a-input>
        </td>
      </tr>
      <tr>
        <td>l2</td>
        <td>
          <a-input v-model:value="formState.l2" style="width: 95%;"></a-input>
        </td>
        <td>l3</td>
        <td>
          <a-input v-model:value="formState.l3" style="width: 95%;"></a-input>
        </td>
      </tr>
      <tr>
        <td>备注</td>
        <td colspan="3">
          <a-input v-model:value="formState.remark" style="width: 95%;"></a-input>
        </td>
      </tr>
      <tr>
        <td>现场照片</td>
        <td colspan="3">
          <a-upload
            v-model:file-list="fileList"
            name="file"
            :multiple="true"
            :action="httpUrl + '/picture/uploadImg'"
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

onMounted (async function () {
  const open = await httpGet('/measure/openScheme',{
    projectId: props.id.id
  })
  console.log(open);
  for(let i=0; i<open.length; i++) {
    var obj = {
      value: open[i].productName,
      lable: open[i].sId
    }
    options.value?.push(obj)
  }
})

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

interface FormState {
  itemId: string
  custerScheme: string
  area: string
  motorModel: string
  trackType: string
  installMethod: string
  number: number
  openMethod: string
  surfaceMaterial: string
  boxWidth: number
  powerPosition: string
  placeholder: string
  deductionInfo: string
  remark: string
  imgUrl: string
  l1: number
  l2: number
  l3: number
  modalInfo: string
  status: boolean
}

const selectChange = (value: string, options: any) => {
  formState.custerScheme = options.lable
};

const formState = reactive<FormState>({
  itemId: '',
  custerScheme: '',
  area: '',
  motorModel: '',
  trackType: '',
  installMethod: '',
  number: 0,
  openMethod: '',
  surfaceMaterial: '',
  boxWidth: 0,
  powerPosition: '',
  placeholder: '',
  deductionInfo: '',
  remark: '',
  imgUrl: '',
  l1: 0,
  l2: 0,
  l3: 0,
  modalInfo: '',
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
    await httpGet('/measure/addOpen',{
      projectId: data.id,
      techId: techId,
      itemId: data.itemId,
      projectName: data.name,
      custerScheme: formState.custerScheme,
      area: formState.area,
      motorModel: formState.motorModel,
      trackType: formState.trackType,
      installMethod: formState.installMethod,
      number: formState.number,
      openMethod: formState.openMethod,
      powerPosition: formState.powerPosition,
      boxWidth: formState.boxWidth,
      surfaceMaterial: formState.surfaceMaterial,
      placeholder: formState.placeholder,
      deductionInfo: formState.deductionInfo,
      remark: formState.remark,
      imgUrl: formState.imgUrl,
      l1: formState.l1,
      l2: formState.l2,
      l3: formState.l3,
      createName: loginName
    })
    resPage()
  }
}

const showModal = () => {
  open.value = true;
};

const submit = async () => {
  formState.modalInfo = '请确认新增测量单！'
  formState.status = true
  showModal()
}

const resPage = () => {
  emit('resPage')
}
</script>