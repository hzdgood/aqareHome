<template>
  <div v-show="loadVisible">
    <div class="floatDiv"></div>
    <div class="loadDiv">
      <a-spin tip="Loading..."></a-spin>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import { Spin } from 'ant-design-vue'
@Component({
  name: 'loading',
  components: {
    'a-spin': Spin
  }
})
export default class Home extends Vue {
  @Prop({
    type: Boolean,
    required: true
  })
  loadVisible!: Boolean;
}
</script>

<style scoped>
.loadDiv{
  position: fixed;
  width: 60%;
  height: 20%;
  top: 33%;
  left: 20%;
  overflow: auto;
  z-index: 100;
  background-color: #fff;
  border-radius: 8px;
  text-align: center;
  padding-top: 5%;
}
</style>
