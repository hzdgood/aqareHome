<template>
  <div class="pageConter">
    <div class="headTop">技术服务系统 版本:1.15</div>
    <div class="logout">{{ loginName }} <span class="logout-span" @click="logout">登出</span></div>
    <div class="wrapper">
      <div :class="formState.select0" @click="changeSelect(0)">
        <RouterLink to="/workSheet">我的工单</RouterLink>
      </div>
      <div :class="formState.select1" @click="changeSelect(1)">
        <RouterLink to="/sendSheet">已发工单</RouterLink>
      </div>
      <div :class="formState.select3" @click="changeSelect(3)">
        <RouterLink to="/project">我的项目</RouterLink>
      </div>
      <div :class="formState.select4" @click="changeSelect(4)">
        <RouterLink to="/write">核销记录</RouterLink>
      </div>
      <div :class="formState.select5" @click="changeSelect(5)">
        <RouterLink to="/measure">测量单</RouterLink>
      </div>
    </div>
    <div class="bodyContent">
      <RouterView></RouterView>
    </div>
  </div>
</template>
<script setup lang="ts">
import { reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { SearchInfo, httpGet } from '../config/interFace'

const router = useRouter()
const techId = localStorage.getItem('techId')
const loginName = localStorage.getItem('loginName')

if (localStorage.getItem('version') !== '1.1') {
  localStorage.clear()
  router.push({ name: 'login' })
}

const changeSelect = (id: number) => {
  if (id === 0) {
    formState.select0 = 'selected'
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = ''
    formState.select4 = ''
    formState.select5 = ''
  }
  if (id === 1) {
    formState.select0 = ''
    formState.select1 = 'selected'
    formState.select2 = ''
    formState.select3 = ''
    formState.select4 = ''
    formState.select5 = ''
  } else if (id === 2) {
    formState.select0 = ''
    formState.select1 = ''
    formState.select2 = 'selected'
    formState.select3 = ''
    formState.select4 = ''
    formState.select5 = ''
  } else if (id === 3) {
    formState.select0 = ''
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = 'selected'
    formState.select4 = ''
    formState.select5 = ''
  } else if (id === 4) {
    formState.select0 = ''
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = ''
    formState.select4 = 'selected'
    formState.select5 = ''
  } else if (id === 5) {
    formState.select0 = ''
    formState.select1 = ''
    formState.select2 = ''
    formState.select3 = ''
    formState.select4 = ''
    formState.select5 = 'selected'
  }
}

const getPosition = () => {
  const obj = {
    latitude: '',
    longitude: ''
  }
  var options = {
    enableHighAccuracy: true, //布尔值，表示系统是否使用最高精度来表示结果，注意，这会导致较慢的响应时间或者增加电量消耗（比如对于支持gps的移动设备来说）。如果值为false ，设备会通过更快响应以及/或者使用更少的电量等方法来尽可能的节约资源。默认值fasle
    timeout: 5000, //它表明的是设备必须在多长时间（单位毫秒）内返回一个位置。默认直到获取到位置才会返回值。
    maximumAge: 0 //表明可以返回多长时间（即最长年龄，单位毫秒）内的可获取的缓存位置。如果设置为 0, 说明设备不能使用一个缓存位置，而且必须去获取一个真实的当前位置。默认0
  }
  function success(position: any) {
    obj.latitude = position.coords.latitude //当前位置的纬度
    obj.longitude = position.coords.longitude //当前位置经度
    return obj
  }
  function error(err: { code: number }) {
    var errorType = ['您拒绝共享位置信息,请去app设置一下！', '获取不到位置信息', '获取位置信息超时']
    console.log(errorType[err.code - 1])
    return obj
  }
  navigator.geolocation.getCurrentPosition(success, error, options)
}

interface FormState {
  select0: string
  select1: string
  select2: string
  select3: string
  select4: string
  select5: string
}

const formState = reactive<FormState>({
  select0: 'selected',
  select1: '',
  select2: '',
  select3: '',
  select4: '',
  select5: ''
})

onMounted(async function () {
  const heads = localStorage.getItem('heads')
  if (typeof techId === 'undefined') {
    router.push({ name: 'login' })
  } else {
    if (heads === null) {
      router.push({ name: 'login' })
    } else {
      router.push({ name: 'workSheet' })
    }
  }
  const obj = {
    offset: 2000,
    limit: 2500,
    order_by: [
      {
        field: 'created_on',
        sort: 'desc'
      }
    ]
  }
  const res = await SearchInfo('2100000054696521', obj)
  let value: any = undefined
  for (let i = 0; i < res.length; i++) {
    var field = res[i].fields
    const itemId = res[i].item_id
    for (let j = 0; j < field.length; j++) {
      if (field[j].field_id === 1105001159000000) {
        value = field[j].values[0].value
      }
    }
    if (typeof value !== 'undefined') {
      const result = {
        projectId: itemId + '',
        latitude: value.coordinate.lat + '',
        longitude: value.coordinate.lon + ''
      }
      await httpGet('/position/insert', result)
    }
  }
})

const logout = () => {
  localStorage.clear()
  router.push({ name: 'login' })
}
</script>

<style src="../css/home.css" scoped></style>
