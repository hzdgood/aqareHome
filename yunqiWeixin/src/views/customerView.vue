<template>
  <div>
    <table width="100%" class="projectTable">
      <tr>
        <td width="70px">客户来源</td>
        <td>
          <a-select @change="handleChange" style="width: 100%">
            <a-select-option value="门店">门店</a-select-option>
            <a-select-option value="设计师">设计师</a-select-option>
            <a-select-option value="导流">导流</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>客户房型</td>
        <td>
          <a-select @change="handleChange" style="width: 100%">
            <a-select-option value="别墅">别墅</a-select-option>
            <a-select-option value="一房">一房</a-select-option>
            <a-select-option value="二房">二房</a-select-option>
            <a-select-option value="三房">三房</a-select-option>
            <a-select-option value="四房">四房</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>使用情况</td>
        <td>
          <a-select @change="handleChange" style="width: 100%">
            <a-select-option value="装修中">装修中</a-select-option>
            <a-select-option value="未交房">未交房</a-select-option>
            <a-select-option value="已入住">已入住</a-select-option>
            <a-select-option value="准备装修">准备装修</a-select-option>
            <a-select-option value="交付中">交付中</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>装修进度</td>
        <td>
          <a-select @change="handleChange" style="width: 100%">
            <a-select-option value="未进场">未进场</a-select-option>
            <a-select-option value="水电进场">水电进场</a-select-option>
            <a-select-option value="水电完成">水电完成</a-select-option>
            <a-select-option value="木工进场">木工进场</a-select-option>
            <a-select-option value="灯具完成">灯具完成</a-select-option>
            <a-select-option value="网络开通">网络开通</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>人员属性</td>
        <td>
          <a-select @change="handleChange" style="width: 100%">
            <a-select-option value="业主">业主</a-select-option>
            <a-select-option value="设计师">设计师</a-select-option>
            <a-select-option value="供应商">供应商</a-select-option>
            <a-select-option value="其他">其他</a-select-option>
          </a-select>
        </td>
      </tr>
      <tr>
        <td>全屋需求</td>
        <td>
          <a-select @change="handleChange" style="width: 100%">
            <a-select-option value="全屋顶控">全屋顶控</a-select-option>
            <a-select-option value="全屋遮阳">全屋遮阳</a-select-option>
            <a-select-option value="智能调光">智能调光</a-select-option>
            <a-select-option value="智能暖通">智能暖通</a-select-option>
            <a-select-option value="智能门锁">智能门锁</a-select-option>
            <a-select-option value="全屋网络">全屋网络</a-select-option>
          </a-select>
        </td>
      </tr>
    </table>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { httpGet } from '../config/interFace'
const follow_user: any = localStorage.getItem('follow_user')
onMounted(async function() {

})

interface FormState {
  userList: any[]
  custominto: any[]
  hometype: any[]
  houseInfo: any[]
  personInfo: any[]
  houseNeed: any[]
  cAttribute: any[]
  oneCustom: any[]
  lostStatus: any[]
}

const formState = reactive<FormState>({
  userList: [],
  custominto: [],
  hometype: [],
  houseInfo: [],
  personInfo: [],
  houseNeed: [],
  cAttribute: [],
  oneCustom: [],
  lostStatus: []
})

const handleChange = (value: string) => {
  console.log(`selected ${value}`);
};

</script>