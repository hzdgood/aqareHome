<template>
  <div>
    <div class="Calendar" v-show="selectStatus">
      <Calendar :fullscreen="fullscreen" @change="onChange"></Calendar>
    </div>
    <div class="selectData">
      <span>日期：</span>
      <input
        type="text"
        id="date"
        placeholder="请选择日期"
        @click="selectDate"
        :value="date"
        readonly
      />
      <button class="myButton" @click="select">查询</button>
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

  mounted () {
    const date = new Date()
    let m: any = date.getMonth() + 1
    let d: any = date.getDate()
    if (m < 10) {
      m = '0' + m
    }
    if (d < 10) {
      d = '0' + m
    }
    this.date = date.getFullYear() + '-' + m + '-' + d
    this.$store.state.CalendarDate = this.date
  }

  selectDate () {
    if (this.selectStatus) {
      this.selectStatus = false
    } else {
      this.selectStatus = true
    }
  }

  onChange (value: any) {
    this.date = value.format('YYYY-MM-DD')
    this.$store.state.CalendarDate = value.format('YYYY-MM-DD')
    this.selectStatus = false
  }

  select () {
    this.$store.dispatch('search')
  }
}
</script>
