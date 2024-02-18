<template>
  <div>
    <table class="tagTable">
      <tr>
        <td>客户来源</td>
        <td>
          <button v-for="item in custominto" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
        <td>房型</td>
        <td>
          <button v-for="item in hometype" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr>
        <td>房屋情况</td>
        <td>
          <button v-for="item in houseInfo" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
        <td>人员属性</td>
        <td>
          <button v-for="item in personInfo" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="houseNeed.length !== 0">
        <td>全屋需求</td>
        <td colspan="3">
          <button v-for="item in houseNeed" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="cAttribute.length !== 0">
        <td>客户属性</td>
        <td colspan="3">
          <button v-for="item in cAttribute" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="oneCustom.length !==0">
        <td>单品客户</td>
        <td colspan="3">
          <button v-for="item in oneCustom" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="lostStatus.length !== 0">
        <td>流失状态</td>
        <td colspan="3">
          <button v-for="item in lostStatus" :key="item.item_id">
            {{ item.title }}
          </button>
        </td>
      </tr>
    </table>
  </div>
</template>
<script setup lang="ts">

const houseInfo: any[] = [];
const personInfo: any[] = [];
const cAttribute: any[] = [];
const oneCustom: any[] = [];
const lostStatus: any[] = [];
const hometype: any[] = [];
const custominto: any[] = [];
const houseNeed: any[] = [];

</script>