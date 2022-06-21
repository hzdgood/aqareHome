<template>
  <div>
    <div class="Calendar" v-show="selectStatus">
      <Calendar :fullscreen="fullscreen"></Calendar>
    </div>
    日期：<input type="text" placeholder="请选择日期"/>
    <button>查询</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Calendar } from 'ant-design-vue'
@Component({
  name: 'calendar',
  components: {
    Calendar: Calendar
  }
})
export default class Actions extends Vue {
  fullscreen = false
  selectStatus = false
}
</script>

<style scoped>
.Calendar{
  width: 290px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
}
</style>
