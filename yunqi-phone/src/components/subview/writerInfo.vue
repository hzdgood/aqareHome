<template>
  <div class="cardDiv">
    <a-card :title="formState.projectName + '-' + formState.type" :bordered="false">
      <div class="writerDiv">
        <span :class="formState.select1" @click="changeSelect(1)" 
          v-show="formState.type ==='安装' || formState.type ==='调试' || formState.type ==='检测'"
        >工单核销</span>
        <span :class="formState.select2" @click="changeSelect(2)">工单总结</span>
        <span :class="formState.select3" @click="changeSelect(3)">上传图片</span>
      </div>

      <div v-show="formState.select1 === 'selected'">
        <div v-for="item in formState.dataList" :key="item">
          <writerTable :data="item" @change="getChange"></writerTable>
        </div>
        <div class="buttonPos">
          <a-button type="primary" @click="submitWriter">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div> 
      </div>

      <div v-show="formState.select2 === 'selected'">
        <table class="cardTale">
          <tr>
            <td>*今日工作总结: </td>
            <td>
              <a-textarea v-model:value="formState.workSummary" :rows="5" />
            </td>
          </tr>
          <tr>
            <td>*下次上门节点: </td>
            <td>
              <a-textarea v-model:value="formState.visitNode" :rows="5" />
            </td>
          </tr>
          <tr>
            <td>*下次上门注意事项: </td>
            <td>
              <a-textarea v-model:value="formState.handover" :rows="5" />
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" @click="submitInfo">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </div>
      
      <div class="pictureDiv" v-show="formState.select3 === 'selected'">
        <div>
          <div>物料核对图片</div>
          <div class="buttonPos">
            <a-upload 
              :action="httpUrl + '/picture/upload'"
              list-type="picture"         
              class="upload-list-inline"
              v-model:file-list="fileList"
              @change="(value: UploadChangeParam<UploadFile<any>>) => handleChange(value, '物料核对图片')"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                点击上传
              </a-button>
            </a-upload> 
          </div>
          
          <div>工单汇报凭证图片</div>
          <div class="buttonPos">
            <a-upload
              :action="httpUrl + '/picture/upload'"
              list-type="picture"         
              class="upload-list-inline"
              v-model:file-list="fileList1"
              @change="(value: UploadChangeParam<UploadFile<any>>) => handleChange(value, '工单汇报凭证图片')"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                点击上传
              </a-button>
            </a-upload> 
          </div>

          <div>工单核销照片</div>
          <div class="buttonPos">
            <a-upload
              :action="httpUrl + '/picture/upload'"
              list-type="picture"         
              class="upload-list-inline"
              v-model:file-list="fileList2"
              @change="(value: UploadChangeParam<UploadFile<any>>) => handleChange(value, '工单核销照片')"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                点击上传
              </a-button>
            </a-upload> 
          </div>

          <div>签字单图片</div>
          <div class="buttonPos">
            <a-upload
              :action="httpUrl + '/picture/upload'"
              list-type="picture"         
              class="upload-list-inline"
              v-model:file-list="fileList3"
              @change="(value: UploadChangeParam<UploadFile<any>>) => handleChange(value, '签字单图片')"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                点击上传
              </a-button>
            </a-upload> 
          </div>

          <div>工单其他照片</div>
          <div class="buttonPos">
            <a-upload
              :action="httpUrl + '/picture/upload'"
              list-type="picture"         
              class="upload-list-inline"
              v-model:file-list="fileList4"
              @change="(value: UploadChangeParam<UploadFile<any>>) => handleChange(value, '工单其他照片')"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                点击上传
              </a-button>
            </a-upload> 
          </div>
        </div>
      </div>
    </a-card>
    <a-modal v-model:open="open" title="系统提示" @ok="handleOk">
      <p>{{ formState.modalInfo }}</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
// 工单核销
import { reactive, onMounted, ref } from 'vue';
import router from '@/router';
import { httpGet, httpUrl } from '../../config/interFace'
import { useRoute } from "vue-router";
import writerTable from './tables/writerTable.vue'
import type { UploadChangeParam, UploadFile } from 'ant-design-vue';
import { UploadOutlined } from '@ant-design/icons-vue';

const open = ref<boolean>(false);
const loginName = localStorage.getItem('loginName')
const route = useRoute()
const fileList = ref<FileItem[]>([]);
const fileList1 = ref<FileItem[]>([]);
const fileList2 = ref<FileItem[]>([]);
const fileList3 = ref<FileItem[]>([]);
const fileList4 = ref<FileItem[]>([]);
let formObj: any[] = []

const handleChange = async (info: UploadChangeParam, type: string) => {
  if (info.file.status === 'done') {
    // formState.imgUrl = info.file.response
    await httpGet('/picture/insert',{
      workId: route.query.id,
      type: type,
      imgUrl: info.file.response,
      createName: loginName
    })
    formState.modalInfo = '上传成功！'
    showModal()
  } else if (info.file.status === 'error') {
    formState.modalInfo = '上传成功！'
    showModal()
  }
};

onMounted (async function () {
  formObj = [];
  const res1 = await httpGet('/view/work',{ // 查询工单
    workId: route.query.id
  })
  formState.projectName = res1[0].projectName;
  formState.type = res1[0].type;
  formState.workSummary = res1[0].workSummary
  formState.visitNode = res1[0].visitNode
  formState.handover = res1[0].handover
  let res = await httpGet('/view/writer',{ // 查询可核销数据
    workId: route.query.id,
    techId: route.query.techId,
    // type: '检测' // ---- 只能核销上门类型
  })
  formState.dataList = res
})

const resPage = () => {
  router.push({name: 'workSheet'})
}

const getChange = (obj: any) => {
  if(obj.install > obj.notInstalled || obj.debug > obj.unregulated) { // 检查核销数量
    formState.modalInfo = '请确认核销数量！'
    showModal()
  } else {
    let status = false;
    if(formObj.length === 0 ) {
      formObj.push(obj)
    } else {
      for(let i=0; i < formObj.length; i++) {
        const productId = formObj[i].productId + '';
        if(productId === obj.productId + '') {
          formObj[i].install = obj.install
          formObj[i].debug = obj.debug
          status = true
        }
      }
      if(status === false) {
        formObj.push(obj)
      }
    }
  }
}

const getPicture = async () => { // ------
  const res = await httpGet('/picture/select', { //工单修改
    workId: route.query.id,
  })
  let i = 0
  for(let i=0; i<res.length; i++) {
    const name = res[i].imgUrl
    const imgUrl = res[i].imgUrl
    const thumbUrl = res[i].imgUrl

    const obj = {
      uid: i + '',
      name: name,
      status: 'done',
      url: imgUrl,
      thumbUrl: thumbUrl,
    }
    if(res[i].type === '物料核对图片') {
      fileList.value = [obj]
    } else if(res[i].type === '工单汇报凭证图片') {
      fileList1.value = [obj]
    } else if(res[i].type === '工单核销照片') {
      fileList2.value = [obj]
    } else if(res[i].type === '签字单图片') {
      fileList3.value = [obj]
    } else if(res[i].type === '工单其他照片') {
      fileList4.value = [obj]
    }
    i = i + 1
  }
}

interface FormState {
  projectName: string;
  type: string
  workSummary: string
  visitNode: string
  dataList: object
  modalInfo: String,
  handover: string
  select1: string
  select2: string
  select3: string
}

const formState = reactive<FormState>({
  projectName: '',
  type: '',
  workSummary: '',
  visitNode: '',
  dataList: [],
  modalInfo: '',
  handover: '',
  select1: '',
  select2: 'selected',
  select3: ''
});

const submitWriter = async () => {
  if(formObj.length === 0) {
    return
  }
  formState.modalInfo = '请确认核销内容！'
  showModal();
};

const showModal = () => {
  open.value = true;
};

const submitInfo = async () => {
  formState.modalInfo = '请确认填写内容！'
  showModal();
};

const handleOk = async () => {
  open.value = false;
  if(formState.modalInfo === '请确认核销数量！') { // 核销数量有问题
    return
  }
  if(formState.modalInfo === '请确认核销内容！') { // 核销新增 
    for(let i=0; i < formObj.length; i++){
      await httpGet('/writer/insert', formObj[i]) 
    }
    formState.modalInfo = '核销完成'
    showModal()
  }
  if(formState.modalInfo === '请确认填写内容！') { // 核销总结
    await httpGet('/workSheet/update', { //工单修改
      id: route.query.id,
      workSummary: formState.workSummary, // 今日工作总结
      visitNode: formState.visitNode, // 下次上门节点
      handover: formState.handover, // 下次上门注意事项
      updateName: loginName // 核销人
    })
    formState.modalInfo = '核销总结提交完成'
    showModal()
  }
  if(formState.modalInfo === '核销完成' || formState.modalInfo === '核销总结提交完成') {
    router.push({name: 'project'})
  }
}

const changeSelect = (id: number) => {
  if(id === 1 ) {
    formState.select1 = 'selected'
    formState.select2 = ''
    formState.select3 = ''
  } else if(id === 2 ) {
    formState.select1 = ''
    formState.select2 = 'selected'
    formState.select3 = ''
  } else if(id === 3 ) {
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = 'selected'
  }
}

interface FileItem {
  uid: string;
  name?: string;
  status?: string;
  response?: string;
  url?: string;
  thumbUrl?: string;
}

</script>

<style lang="less" scoped>
.upload-list-inline :deep(.ant-upload-list-item) {
  float: left;
  width: 200px;
  margin-right: 8px;
}
.upload-list-inline :deep(.ant-upload-animate-enter) {
  animation-name: uploadAnimateInlineIn;
}
.upload-list-inline :deep(.ant-upload-animate-leave) {
  animation-name: uploadAnimateInlineOut;
}

.writerDiv {
  width: 100%;
  height: 40px;
  border-radius: 5px;
  background-color: #2784ac;
}

.writerDiv span{
  font-size: 15px;
  color: #fff;
  padding: 10px;
  border-right: 1px solid #cecece;
  line-height: 40px;
}
.selected {
  border-radius: 5px 0px 0px 5px;
  background-color: rgb(22, 119, 255);
}

.pictureDiv div{
  padding: 5px 5px 0px 5px;
}
</style>