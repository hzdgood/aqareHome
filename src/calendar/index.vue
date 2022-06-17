<template>
  <div>
    <div>
      <head></head>
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
import selectInfo from './componments/selectInfo.vue'
import personInfo from './componments/personInfo.vue'
import baiduMap from './componments/baiduMap.vue'
@Component({
  name: 'App',
  components: {
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
