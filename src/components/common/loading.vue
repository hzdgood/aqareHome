<template>
  <div v-show="status">
    <div class="floatDiv"></div>
    <div>
        正在保存数据，请稍后！
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {
  @Prop({
    type: String,
    required: false
  })
  status!: String;
}
</script>

<style scoped>
</style>
