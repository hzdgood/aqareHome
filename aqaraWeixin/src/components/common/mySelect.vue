<template>
  <div>
    <select :id="selectId">
      <option v-for="item in selectList" :key="item.value" :value="item.value">
        {{ item.name }}
      </option>
    </select>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {
  @Prop({
    type: [],
    required: true,
    default: ''
  })
  selectList!: any;

  @Prop({
    type: String,
    required: true,
    default: ''
  })
  selectId!: String;

  selectValue!: String;

  mounted () {
    const id = parseInt('' + this.selectValue)
    const selectId = '' + this.selectId
    const projectType: any = document.getElementById(selectId)
    for (let i = 0; i < projectType.options.length; i++) {
      if (parseInt(projectType.options[i].value) === id) {
        projectType.options[i].selected = true
      }
    }
  }

  @Watch('this.selectValue')
  select () {
    const id = parseInt('' + this.selectValue)
    const selectId = '' + this.selectId
    const projectType: any = document.getElementById(selectId)
    for (let i = 0; i < projectType.options.length; i++) {
      if (parseInt(projectType.options[i].value) === id) {
        projectType.options[i].selected = true
      }
    }
  }
}
</script>

<style></style>
