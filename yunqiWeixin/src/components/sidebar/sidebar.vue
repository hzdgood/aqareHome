<template>
  <div>
    <a-menu 
      v-model:openKeys="openKeys"
      v-model:selectedKeys="current" 
      mode="horizontal" 
      :items="items"
      @click="handleClick"
    />
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import type { MenuProps } from 'ant-design-vue';
import router from '@/router';

const current = ref<string[]>(['0']);
const openKeys = ref<string[]>(['0']);

const items = ref<MenuProps['items']>([
  {
    key: '0',
    label: '客户',
    title: '客户',
  }, {
    key: '1',
    label: '项目',
    title: '项目',
  }, {
    key: '2',
    label: '跟进',
    title: '跟进',
  }, {
    key: '3',
    label: '收款',
    title: '收款',
  }, {
    key: '4',
    label: '方案',
    title: '方案',
  }
])

const handleClick: MenuProps['onClick'] = e => {
  console.log('click', e);
  if(e.key === '0') {
    router.push('/customer')
  } if(e.key === '1') {
    router.push('/project')
  } else if(e.key === '2') {
    router.push('/follow')
  } else if(e.key === '3') {
    router.push('/collent')
  } else if(e.key === '4') {
    router.push('/scheme')
  }
};

</script>

<style>

</style>