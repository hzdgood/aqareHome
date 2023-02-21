<template>
  <div v-show="visible">
    <div class="floatDiv"></div>
    <div class="ModalDiv">
      <div class="modalText">
        <p>{{ modalText }}</p>
      </div>
      <div class="buttonFloat">
        <button class="saveButton" @click="handleOk">确定</button>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
@Component({
  name: 'myMadal'
})
export default class Home extends Vue {
  @Prop({
    type: Boolean,
    required: true
  })
  visible!: any

  @Prop({
    type: String,
    required: false
  })
  modalText!: any

  handleOk () {
    this.$emit('close')
  }
}
</script>

<style scoped>
.ModalDiv{
  position: fixed;
  width: 50%;
  height: 17%;
  top: 33%;
  left: 26%;
  overflow: auto;
  z-index: 100;
  background-color: #fff;
  border: 1px solid red;
  border-radius: 8px;
  text-align: center;
  padding-top: 5%;
}
.buttonFloat{
  margin-right: 10px;
}
</style>
