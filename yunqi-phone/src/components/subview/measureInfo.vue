<template>
  <div>
    <div>
      <a-card title="" :bordered="false" >
        <div>
          <a-button @click="check1()">开合帘</a-button>
          <a-button @click="check2()">卷帘</a-button>
        </div>
        <div v-show="formState.formDiv1">
          <table class="cardTale">
            <tr>
              <td width="70px">项目姓名</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td width="80px">区域</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>电机型号</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>轨道类型</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>安装方式</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>数量</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>开合方式</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>电源位置</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>窗帘盒宽</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>安装面材质</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>预留位置</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>扣减说明</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>备注</td>
              <td colspan="3">
                <a-input style="width: 95%;"></a-input>
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
                <a-input style="width: 95%;"></a-input>
              </td>
              <td width="90px">区域</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>卷帘方案</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>长度</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>高度</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>安装面宽度</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>安装方式</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>扣减说明</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>数量</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>安装面材质</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>电源位置</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
              <td>需要罩壳</td>
              <td>
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>备注</td>
              <td colspan="3">
                <a-input style="width: 95%;"></a-input>
              </td>
            </tr>
            <tr>
              <td>现在照片</td>
              <td></td>
            </tr>
          </table>
        </div>
        <div class="buttonPos">
          <a-button>提交</a-button>
          <a-button @click="resPage()">返回</a-button>
        </div>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
//  测量
import router from '@/router';
import { reactive } from 'vue';

interface FormState {
  formDiv1: boolean;
  formDiv2: boolean;
}

const formState = reactive<FormState>({
  formDiv1: true,
  formDiv2: false,
});

const resPage = () => {
  router.push({name: 'project'})
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