<template>
  <div>
    <div>
      <a-card title="" :bordered="false" >
        <div class="buttonPos">
          <a-button type="primary" @click="check1()">开合帘</a-button>
          <a-button type="primary" @click="check2()">卷帘</a-button>
        </div>
        <div v-show="formState.formDiv1">
          <table class="cardTale">
            <tr>
              <td width="75px">项目姓名</td>
              <td>
                <a-input :disabled="true" v-model:value="formState.projectName" style="width: 95%;"></a-input>
              </td>
              <td width="75px">区域</td>
              <td>
                <a-input v-model:value="formState.area" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>*电机型号</td>
              <td>
                <a-select v-model:value="formState.motorModel" style="width: 95%;">
                  <a-select-option value="C2电机">C2电机</a-select-option>
                  <a-select-option value="C3电机">C3电机</a-select-option>
                  <a-select-option value="B1电机">B1电机</a-select-option>
                  <a-select-option value="B2电机">B2电机</a-select-option>
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
                  <a-select-option value="异形轨">异形轨</a-select-option>
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
              <td>*数量</td>
              <td>
                <a-input v-model:value="formState.number" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>*开合方式</td>
              <td>
                <a-select v-model:value="formState.openMethod" style="width: 95%;">
                  <a-select-option value="单开">单开</a-select-option>
                  <a-select-option value="双开">双开</a-select-option>
                </a-select>
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
              <td>*窗帘盒宽</td>
              <td>
                <a-input v-model:value="formState.boxWidth" style="width: 95%;"></a-input>
              </td>
              <td>*安装面材质</td>
              <td>
                <a-select v-model:value="formState.surfaceMaterial" style="width: 95%;">
                  <a-select-option value="木工板">木工板</a-select-option>
                  <a-select-option value="混凝土">混凝土</a-select-option>
                  <a-select-option value="金属板">金属板</a-select-option>
                  <a-select-option value="石膏板">石膏板</a-select-option>
                </a-select>
              </td>
            </tr>
            <tr>
              <td>预留位置</td>
              <td>
                <a-select v-model:value="formState.placeholder" style="width: 95%;">
                  <a-select-option value="靠窗">靠窗</a-select-option>
                  <a-select-option value="居中">居中</a-select-option>
                  <a-select-option value="靠屋">靠屋</a-select-option>
                </a-select>
              </td>
              <td>扣减说明</td>
              <td>
                <a-select v-model:value="formState.deductionInfo" style="width: 95%;">
                  <a-select-option value="标准扣减3-4cm">标准扣减3-4cm</a-select-option>
                  <a-select-option value="自定义扣减">自定义扣减</a-select-option>
                  <a-select-option value="不扣减(成品尺寸)">不扣减</a-select-option>
                </a-select>
              </td>
            </tr>
            <tr>
              <td>*l1</td>
              <td>
                <a-input v-model:value="formState.l1" style="width: 95%;"></a-input>
              </td>
              <td>l2</td>
              <td>
                <a-input v-model:value="formState.l2" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
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
              <td>
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
        </div>
        <div v-show="formState.formDiv2">
          <table class="cardTale">
            <tr>
              <td width="15%">项目姓名</td>
              <td width="35%">
                <a-input :disabled="true" :value="formState.projectName" style="width: 95%;"></a-input>
              </td>
              <td width="15%">区域</td>
              <td width="35%">
                <a-input v-model:value="formState.area" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>*卷帘方案</td>
              <td>
                <a-input v-model:value="formState.rollingScheme" style="width: 95%;"></a-input>
              </td>
              <td>*长度</td>
              <td>
                <a-input v-model:value="formState.length" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>*高度</td>
              <td>
                <a-input v-model:value="formState.high" style="width: 95%;"></a-input>
              </td>
              <td>*安装面宽度</td>
              <td>
                <a-input v-model:value="formState.surfaceWidth" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>*安装方式</td>
              <td>
                <a-select v-model:value="formState.installMethod" style="width: 95%;">
                  <a-select-option value="外顶装">外顶装</a-select-option>
                  <a-select-option value="侧装">侧装</a-select-option>
                  <a-select-option value="内顶装">内顶装</a-select-option>
                </a-select>
              </td>
              <td>*扣减说明</td>
              <td>
                <a-select v-model:value="formState.deductionInfo" style="width: 95%;">
                  <a-select-option value="标准扣减1cm">标准扣减1cm</a-select-option>
                  <a-select-option value="尺寸已减">尺寸已减</a-select-option>
                </a-select>
              </td>
            </tr>
            <tr>
              <td>*数量</td>
              <td>
                <a-input v-model:value="formState.number" style="width: 95%;"></a-input>
              </td>
              <td>*安装面材质</td>
              <td>
                <a-select v-model:value="formState.surfaceMaterial" style="width: 95%;">
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
                <a-select v-model:value="formState.powerPosition" style="width: 95%;">
                  <a-select-option value="左">左</a-select-option>
                  <a-select-option value="右">右</a-select-option>
                  <a-select-option value="无电源">无电源</a-select-option>
                </a-select>
              </td>
              <td>*需要罩壳</td>
              <td>
                <a-select v-model:value="formState.cover" style="width: 95%;">
                  <a-select-option value="是">是</a-select-option>
                  <a-select-option value="否">否</a-select-option>
                </a-select>
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
              <td>
                <a-upload
                  v-model:file-list="fileList1"
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
        </div>
        <div class="buttonPos">
          <a-button type="primary" @click="submit">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-card>
    </div>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>{{ formState.modalInfo }}</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
//  测量
import router from '@/router';
import { reactive, onMounted, ref } from 'vue';
import { httpGet, httpUrl } from '../../config/interFace'
import { useRoute } from "vue-router";

import { UploadOutlined } from '@ant-design/icons-vue';
import type { UploadChangeParam } from 'ant-design-vue';

const open = ref<boolean>(false);
const fileList = ref([]);
const fileList1 = ref([]);
const route = useRoute()
const techId = localStorage.getItem("techId");
const loginName = localStorage.getItem('loginName')

onMounted (async function () {
  const res = await httpGet('/project/selectId',{
    id: route.query.id
  })
  formState.projectName = res[0].name
  formState.itemId = res[0].itemId
});

interface FormState {
  itemId: string
  formDiv1: boolean
  formDiv2: boolean
  projectName: string
  area: string,
  motorModel: string,
  trackType: string,
  installMethod: string,
  number: Number,
  openMethod: string,
  powerPosition: string,
  boxWidth: string,
  surfaceMaterial: string,
  placeholder: string,
  deductionInfo: string 
  remark: string,
  rollingScheme: string,
  length: string,
  high: string,
  surfaceWidth: string,
  cover: string,
  imgUrl: string,
  imgUrl1: string
  modalInfo: string
  l1: Number,
  l2: Number,
  l3: Number
}

const formState = reactive<FormState>({
  itemId: '',
  formDiv1: true,
  formDiv2: false,
  projectName: '',
  area: '',
  motorModel: '',
  trackType: '',
  installMethod: '',
  number: 0,
  openMethod: '',
  powerPosition: '',
  boxWidth: '',
  surfaceMaterial: '',
  placeholder: '',
  deductionInfo: '',
  remark: '',
  rollingScheme: '',
  length: '',
  high: '',
  surfaceWidth: '',
  cover: '',
  imgUrl: '',
  imgUrl1: '',
  modalInfo: '',
  l1: 0,
  l2: 0,
  l3: 0
});

const resPage = () => {
  router.push({name: 'project'})
}

const submit = async () => {
  if(formState.formDiv1) {
    await httpGet('/measure/addOpen',{
      projectId: route.query.id,
      techId: techId,
      itemId: formState.itemId,
      projectName: formState.projectName,
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
      imgUrl: formState.imgUrl1,
      l1: formState.l1,
      l2: formState.l2,
      l3: formState.l3,
      createName: loginName
    })
    formState.modalInfo = '开合帘新增成功'
    showModal()
  } else if (formState.formDiv2) {
    await httpGet('/measure/addRoller',{
      projectId: route.query.id,
      itemId: formState.itemId,
      techId: techId,
      projectName: formState.projectName,
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
    formState.modalInfo = '卷帘新增成功'
    showModal()
  }
}

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  open.value = false;
}

const check1 = () => {
  formState.formDiv1 = true
  formState.formDiv2 = false
  formState.area = ''
}

const check2 = () => {
  formState.formDiv1 = false
  formState.formDiv2 = true
  formState.area = ''
}

const handleChange = (info: UploadChangeParam) => {
  if (info.file.status === 'done') {
    if(formState.formDiv1) {
      formState.imgUrl =  formState.imgUrl + info.file.response + ","
    } else {
      formState.imgUrl1 =  formState.imgUrl + info.file.response + ","
    }
    formState.modalInfo = '上传成功！'
    showModal()
  } else if (info.file.status === 'error') {
    formState.modalInfo = '上传成功！'
    showModal()
  }
  if( formState.modalInfo === '开合帘新增成功' || formState.modalInfo === '卷帘新增成功') {
    router.push({name: 'project'})
  }
};

</script>

<style lang="less" scoped>

</style>