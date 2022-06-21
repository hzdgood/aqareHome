<template>
  <div>
    <div class="head">
      <head-info></head-info>
    </div>
    <div class="body-div">
      <table>
        <tr>
          <td>
            <person-info></person-info>
          </td>
          <td>
            <div>
              <select-info></select-info>
              <baidu-map></baidu-map>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import head from './componments/head.vue'
import selectInfo from './componments/selectInfo.vue'
import personInfo from './componments/personInfo.vue'
import baiduMap from './componments/baiduMap.vue'
import '@/calendar/css/index.css'
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

<style scoped>

</style>
