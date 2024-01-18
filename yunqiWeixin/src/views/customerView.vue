<template>
  <div>
    <div class="addUser">
      <div class="text1">Ta添加的</div>
      <div class="text2">
        员工
        <span v-for="item in formState.userList" :key="item.userid">
          <img :src="item.avatar" width="25px" height="25px"/>
          {{ item.name }}
        </span>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
import { httpGet } from '../config/interFace'

const follow_user: any = localStorage.getItem('follow_user')

onMounted(async function() {
  const users: any[] = JSON.parse(follow_user)
  for (let i = 0; i < users.length; i++) {
    if (typeof (users[i].oper_userid) !== 'undefined') {
      const res: any = await httpGet("/QyAgent/compUser", {
        agentId: localStorage.getItem("agentId"),
        userId: localStorage.getItem("userId")
      })
      if (res.errmsg === 'ok') {
        const obj = {
          userid: res.userid,
          avatar: res.avatar,
          name: res.name
        }
        formState.userList.push(obj)
      }
    }
  }
})

interface FormState {
  userList: any[]
}

const formState = reactive<FormState>({
  userList: []
})

</script>