<template>
  <div>
    <a-modal
      title='Title'
      :visible='visible'
      :confirm-loading='confirmLoading'
      @ok='handleOk'
    >
      <p>{{ modalText }}</p>
    </a-modal>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator'
import { Modal } from 'ant-design-vue'
@Component({
  name: 'myMadal',
  components: {
    'a-Modal': Modal
  }
})
export default class Home extends Vue {
  @Prop({
    type: Boolean,
    required: true
  })
  visible!: any;

  @Prop({
    type: String,
    required: true
  })
  modalText!: any;

  confirmLoading = false

  handleOk () {
    this.confirmLoading = true
    setTimeout(() => {
      this.visible = false
    }, 1000)
  }
}
</script>

<style>
</style>
