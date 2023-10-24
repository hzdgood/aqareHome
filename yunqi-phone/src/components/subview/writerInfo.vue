<template>
  <div class="cardDiv">
    <a-card :title="formState.projectName + '-' + formState.type" :bordered="false">
      <div class="writerDiv">
        <span :class="formState.select1" @click="changeSelect(1)" >工单核销</span>
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
            <td>今日工作总结: </td>
            <td>
              <a-textarea v-model:value="formState.workSummary" :rows="4" />
            </td>
          </tr>
          <tr>
            <td>下次上门节点: </td>
            <td>
              <a-textarea v-model:value="formState.visitNode" :rows="4" />
            </td>
          </tr>
          <tr>
            <td>工作交接: </td>
            <td>
              <a-textarea v-model:value="formState.handover" :rows="4" />
            </td>
          </tr>
        </table>
        <div class="buttonPos">
          <a-button type="primary" @click="submitInfo">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </div>
      
      <div v-show="formState.select3 === 'selected'">
        <!-- <a-upload
          action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
          list-type="picture"
          v-model:file-list="fileList1"
          class="upload-list-inline"
        >
          <a-button>
            <upload-outlined></upload-outlined>
            物料核对
          </a-button>
        </a-upload>-->
        <a-button type="primary">物料核对</a-button>
        <a-button type="primary">汇报凭证</a-button>
        <a-button type="primary">核销照片</a-button>
        <a-button type="primary">签字单</a-button>
        <a-button type="primary">其他照片</a-button>


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
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";
import writerTable from './tables/writerTable.vue'

const open = ref<boolean>(false);
const techId = localStorage.getItem('techId')
const route = useRoute()

let formObj: any[] = []

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

  let res: any
  if(formState.type === '检测') {
      res = await httpGet('/view/writer',{ // 查询可核销数据
      workId: route.query.id,
      techId: route.query.techId,
      type: '检测' // 只能核销上门类型
    })
  } else {
      res = await httpGet('/view/writer',{ // 查询可核销数据
      workId: route.query.id,
      techId: route.query.techId
    })
  }
  if(res.length === 0) {
    router.push({name: 'workSheet'})
  } else {
    formState.dataList = res
  }
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
  select1: 'selected',
  select2: '',
  select3: ''
});

const submitWriter = async () => {
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
    const res = await httpGet('/workSheet/update', { //工单修改
      id: route.query.id,
      workSummary: formState.workSummary, //今日工作总结
      visitNode: formState.visitNode, //下次上门节点
      handover: formState.handover,
      updateName: techId // 核销人
    })
    formState.modalInfo = res
    showModal()
  }
};

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

// interface FileItem {
//   uid: string;
//   name?: string;
//   status?: string;
//   response?: string;
//   url?: string;
//   thumbUrl?: string;
// }

// const fileList1 = ref<FileItem[]>([
//   {
//     uid: '-1',
//     name: 'xxx.png',
//     status: 'done',
//     url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
//     thumbUrl: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
//   },
//   {
//     uid: '-2',
//     name: 'yyy.png',
//     status: 'done',
//     url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
//     thumbUrl: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
//   },
// ]);
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
  background-color: rgb(22, 119, 255);
}


</style>