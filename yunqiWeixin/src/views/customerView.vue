<template>
  <div>
    <div class="addUser">
      <!-- <div class="text1">Ta添加的</div>
      <div class="text2">
        员工
        <span v-for="item in formState.userList" :key="item.userid">
          <img :src="item.avatar" width="25px" height="25px"/>
          {{ item.name }}
        </span>
      </div> -->
      <table class="tagTable">
        <tr>
          <td>客户来源</td>
          <td>
            <button v-for="item in formState.custominto" :key="item.id">
              {{ item.title }}
            </button>
          </td>
          <td>客户房型</td>
          <td>
            <button v-for="item in formState.hometype" :key="item.id">
              {{ item.title }}
            </button>
          </td>
        </tr>
        <tr>
          <td>房屋情况</td>
          <td>
            <button v-for="item in formState.houseInfo" :key="item.id">
              {{ item.title }}
            </button>
          </td>
          <td>人员属性</td>
          <td>
            <button v-for="item in formState.personInfo" :key="item.id">
              {{ item.title }}
            </button>
          </td>
        </tr>
        <tr>
          <td>全屋需求</td>
          <td colspan="3">
            <button v-for="item in formState.houseNeed" :key="item.id">
              {{ item.title }}
            </button>
          </td>
        </tr>
        <tr>
          <td>客户属性</td>
          <td colspan="3">
            <button v-for="item in formState.cAttribute" :key="item.id">
              {{ item.title }}
            </button>
          </td>
        </tr>
        <tr v-if="formState.lostStatus.length !== 0">
          <td>流失状态</td>
          <td colspan="3">
            <button v-for="item in formState.lostStatus" :key="item.id">
              {{ item.title }}
            </button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
import { httpGet } from '../config/interFace'

const follow_user: any = localStorage.getItem('follow_user')

onMounted(async function() {
  const users: any[] = JSON.parse(follow_user)
  // for (let i = 0; i < users.length; i++) {
  //   if (typeof (users[i].oper_userid) !== 'undefined') {
  //     const res: any = await httpGet("/QyAgent/compUser", {
  //       agentId: localStorage.getItem("agentId"),
  //       userId: users[i].userid
  //     })
  //     if (res.errmsg === 'ok') {
  //       const obj = {
  //         userid: res.userid,
  //         avatar: res.avatar,
  //         name: res.name
  //       }
  //       formState.userList.push(obj)
  //     }
  //   }
  // }
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

</script>