<template>
  <div>
    <div class="floatDiv"></div>
    <div class="infoDiv">
      <div class="headerDiv">项目编辑</div>
      <table width="100%" class="projectTable">
        <tr>
          <td width="70px">项目类型</td>
          <td>
            <a-select @change="handleChange" style="width: 100%">
              <a-select-option value="全屋">全屋</a-select-option>
              <a-select-option value="零售">零售</a-select-option>
              <a-select-option value="工装">工装</a-select-option>
            </a-select>
          </td>
        </tr>
        <tr>
          <td>项目状态</td>
          <td>
            <a-select @change="handleChange" style="width: 100%">
              <a-select-option value="进行中">进行中</a-select-option>
              <a-select-option value="已完结">已完结</a-select-option>
              <a-select-option value="流失">流失</a-select-option>
              <a-select-option value="坏账">坏账</a-select-option>
            </a-select>
          </td>
        </tr>
        <tr>
          <td>房屋情况</td>
          <td>
            <a-select @change="handleChange" style="width: 100%">
              <a-select-option value="前装">前装</a-select-option>
              <a-select-option value="后装">后装</a-select-option>
            </a-select>
          </td>
        </tr>
        <tr>
          <td>联系方式</td>
          <td><a-input style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>小区名称</td>
          <td><a-input style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>所在区域</td>
          <td><a-input style="width: 100%"></a-input></td>
        </tr>
        <tr>
          <td>客户地址</td>
          <td><a-input style="width: 100%"></a-input></td>
        </tr>
      </table>
      <div class="buttonPos">
        <a-button type="primary">提交</a-button>
        <a-button type="primary" @click="closePage()">关闭</a-button>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
const emit = defineEmits(['close'])

onMounted(async function() {
  
})

const closePage = () => {
  emit('close')
}

const handleChange = (value: string) => {
  console.log(`selected ${value}`);
};
</script>