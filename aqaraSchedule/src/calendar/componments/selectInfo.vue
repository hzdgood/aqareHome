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
    <div>
      <button :class="mapModel? 'showButton':'hidButton'" @click="setMapModel">地图模式</button>
      <button :class="tabModel? 'showButton':'hidButton'" @click="setTabModel">表单模式</button>
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
  mapModel = true
  tabModel = false

  mounted () {
    const date = new Date()
    let m: any = date.getMonth() + 1
    let d: any = date.getDate()
    if (m < 10) {
      m = '0' + m
    }
    if (d < 10) {
      d = '0' + d
    }
    this.date = date.getFullYear() + '-' + m + '-' + d
    this.$store.state.CalendarDate = this.date
    setTimeout(() => {
      this.select()
    }, 1000)
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

  setMapModel () {
    this.$emit('setModel', true)
    this.mapModel = true
    this.tabModel = false
    console.log(111)
  }

  setTabModel () {
    this.$emit('setModel', false)
    this.mapModel = false
    this.tabModel = true
    console.log(111)
  }
}
</script>
