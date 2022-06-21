<template>
  <div>
    <div>
      <head-info></head-info>
    </div>
    <div>
      <div>
        <person-info></person-info>
      </div>
      <div>
        <select-info></select-info>
        <baidu-map></baidu-map>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import head from './componments/head.vue'
import selectInfo from './componments/selectInfo.vue'
import personInfo from './componments/personInfo.vue'
import baiduMap from './componments/baiduMap.vue'
@Component({
  name: 'App',
  components: {
    'head-info': head,
    'select-info': selectInfo,
    'person-info': personInfo,
    'baidu-map': baiduMap
  }
})
export default class Actions extends Vue {

}
</script>

<style>

</style>
