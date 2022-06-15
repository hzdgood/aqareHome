<template>
  <div v-show="status">
    <div class="floatDiv"></div>
    <div class="loadDiv">
      <img src="../../img/loading.gif"/>
        正在保存数据，请稍后！
        <div class="buttonSite">
          <div class="buttonSite">
          <input
            class="closeButton"
            type="button"
            value="关闭"
            @click="closeClick()"
          />
        </div>
        </div>
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

  closeClick () {
    this.$store.dispatch('Loading')
  }
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
  z-index: 30;
  background-color: #fff;
  border-radius: 8px;
  text-align: center;
  padding-top: 5%;
}
</style>
