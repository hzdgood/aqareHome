<template>
  <div>
    <table class="tagTable">
      <tr>
        <td>客户来源</td>
        <td>
          <button v-for="item in custominto" :key="item.id">
            {{ item.title }}
          </button>
        </td>
        <td>房型</td>
        <td>
          <button v-for="item in hometype" :key="item.id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr>
        <td>房屋情况</td>
        <td>
          <button v-for="item in houseInfo" :key="item.id">
            {{ item.title }}
          </button>
        </td>
        <td>人员属性</td>
        <td>
          <button v-for="item in personInfo" :key="item.id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="houseNeed.length !== 0">
        <td>全屋需求</td>
        <td colspan="3">
          <button v-for="item in houseNeed" :key="item.id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="cAttribute.length !== 0">
        <td>客户属性</td>
        <td colspan="3">
          <button v-for="item in cAttribute" :key="item.id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="oneCustom.length !==0">
        <td>单品客户</td>
        <td colspan="3">
          <button v-for="item in oneCustom" :key="item.id">
            {{ item.title }}
          </button>
        </td>
      </tr>
      <tr v-if="lostStatus.length !== 0">
        <td>流失状态</td>
        <td colspan="3">
          <button v-for="item in lostStatus" :key="item.id">
            {{ item.title }}
          </button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive } from 'vue';

const custominto: any[] = [];
const hometype: any[] = [];
const houseInfo: any[] = [];
const personInfo: any[] = [];

const houseNeed: any[] = [];
const cAttribute: any[] = [];
const oneCustom: any[] = [];
const lostStatus: any[] = [];


</script>

<style>

</style>