<template>
  <div>
    <div class="Calendar" v-show="selectStatus">
      <Calendar :fullscreen="fullscreen" @change="onChange"></Calendar>
    </div>
    <div>
      <span>日期：</span>
      <input
        type="text"
        id="date"
        placeholder="请选择日期"
        @click="selectDate"
        :value="date"
        readonly
      />
      <button>查询</button>
    </div>
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
  fullscreen = false;
  selectStatus = false;
  date = '';

  selectDate () {
    this.selectStatus = true
  }

  onChange (value: any) {
    this.date = value.format('YYYY-MM-DD')
    this.selectStatus = false
  }
}
</script>

<style scoped>

</style>
