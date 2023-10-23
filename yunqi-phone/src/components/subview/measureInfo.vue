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
              <td width="70px">项目姓名</td>
              <td>
                <a-input :disabled="true" :value="formState.projectName" style="width: 95%;"></a-input>
              </td>
              <td width="80px">区域</td>
              <td>
                <a-input :value="formState.area" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>电机型号</td>
              <td>
                <a-input :value="formState.motorModel" style="width: 95%;"></a-input>
              </td>
              <td>轨道类型</td>
              <td>
                <a-input :value="formState.trackType" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>安装方式</td>
              <td>
                <a-input :value="formState.installMethod" style="width: 95%;"></a-input>
              </td>
              <td>数量</td>
              <td>
                <a-input :value="formState.number" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>开合方式</td>
              <td>
                <a-input :value="formState.openMethod" style="width: 95%;"></a-input>
              </td>
              <td>电源位置</td>
              <td>
                <a-input :value="formState.powerPosition" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>窗帘盒宽</td>
              <td>
                <a-input :value="formState.boxWidth" style="width: 95%;"></a-input>
              </td>
              <td>安装面材质</td>
              <td>
                <a-input :value="formState.surfaceMaterial" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>预留位置</td>
              <td>
                <a-input :value="formState.placeholder" style="width: 95%;"></a-input>
              </td>
              <td>扣减说明</td>
              <td>
                <a-input :value="formState.deductionInfo" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>备注</td>
              <td colspan="3">
                <a-input :value="formState.remark" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>现在照片</td>
              <td></td>
            </tr>
          </table>
        </div>
        <div v-show="formState.formDiv2">
          <table class="cardTale">
            <tr>
              <td width="70px">项目姓名</td>
              <td>
                <a-input :disabled="true" :value="formState.projectName" style="width: 95%;"></a-input>
              </td>
              <td width="90px">区域</td>
              <td>
                <a-input :value="formState.area" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>卷帘方案</td>
              <td>
                <a-input :value="formState.rollingScheme" style="width: 95%;"></a-input>
              </td>
              <td>长度</td>
              <td>
                <a-input :value="formState.length" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>高度</td>
              <td>
                <a-input :value="formState.high" style="width: 95%;"></a-input>
              </td>
              <td>安装面宽度</td>
              <td>
                <a-input :value="formState.surfaceWidth" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>安装方式</td>
              <td>
                <a-input :value="formState.installMethod" style="width: 95%;"></a-input>
              </td>
              <td>扣减说明</td>
              <td>
                <a-input :value="formState.deductionInfo" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>数量</td>
              <td>
                <a-input :value="formState.number" style="width: 95%;"></a-input>
              </td>
              <td>安装面材质</td>
              <td>
                <a-input :value="formState.surfaceMaterial" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>电源位置</td>
              <td>
                <a-input :value="formState.powerPosition" style="width: 95%;"></a-input>
              </td>
              <td>需要罩壳</td>
              <td>
                <a-input :value="formState.cover" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>备注</td>
              <td colspan="3">
                <a-input :value="formState.remark" style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>现在照片</td>
              <td></td>
            </tr>
          </table>
        </div>
        <div class="buttonPos">
          <a-button type="primary" @click="submit">提交</a-button>
          <a-button type="primary" @click="resPage()">返回</a-button>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
//  测量
import router from '@/router';
import { reactive } from 'vue';
import { httpGet } from '../../config/interFace'
import { useRoute } from "vue-router";

const route = useRoute()

interface FormState {
  formDiv1: boolean
  formDiv2: boolean
  projectName: string
  area: string,
  motorModel: string,
  trackType: string,
  installMethod: string,
  number: string,
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
  cover: string
}

const formState = reactive<FormState>({
  formDiv1: true,
  formDiv2: false,
  projectName: '',
  area: '',
  motorModel: '',
  trackType: '',
  installMethod: '',
  number: '',
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
  cover: ''
});

const resPage = () => {
  router.push({name: 'project'})
}

const submit = async () => {
  if(formState.formDiv1) {
    const res = await httpGet('/measuer/addOpen',{
      projectId: route.query.id,
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
      remark: formState.remark
    })
    console.log(res);
  } else if (formState.formDiv2) {
    const res = await httpGet('/measuer/addRoller',{
      projectId: route.query.id,
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
      remark: formState.remark
    })
    console.log(res);
  }
}

const check1 = () => {
  formState.formDiv1 = true
  formState.formDiv2 = false
}

const check2 = () => {
  formState.formDiv1 = false
  formState.formDiv2 = true
}

</script>

<style lang="less" scoped>

</style>