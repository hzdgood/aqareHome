<template>
  <bm-overlay
    ref="customOverlay"
    :class="{ sample: true, active }"
    pane="labelPane"
    @draw="draw"
  >
    <div @click="showInfo">
      <img class="placeImg" v-if="technology === '葛栋梁'" src="../img/ge.png" />
      <img class="placeImg" v-if="technology === '张恒'" src="../img/heng.png" />
      <img class="placeImg" v-if="technology === '吴杰'" src="../img/jie.png" />
      <img class="placeImg" v-if="technology === '张凯凯'" src="../img/kk.png" />
      <img class="placeImg" v-if="technology === '李晓龙'" src="../img/long.png" />
      <img class="placeImg" v-if="technology === '娄德'" src="../img/lou.png" />
      <img class="placeImg" v-if="technology === '张鹏'" src="../img/peng.png" />
      <img class="placeImg" v-if="technology === '田世博'" src="../img/tian.png" />
      <img class="placeImg" v-if="technology === '汪克祥'" src="../img/wang.png" />
      <img class="placeImg" v-if="technology === '严熠'" src="../img/yan.png" />
      <img class="placeImg" v-if="technology === '岳笑笑'" src="../img/yue.png" />
      <img class="placeImg" v-if="technology === '陶行'" src="../img/tao.png" />
      <img class="placeImg" v-if="technology === '王占勇'" src="../img/zhan.png" />
    </div>
    <div v-show="infoStatus">
      <div v-for="item in date" :key="item.id" @click="handleClick">
        <table class="point">
          <tr>
            <td>{{ item.name }}</td>
            <td>{{ item.type }}</td>
          </tr>
          <tr>
            <td colspan="2">上门:{{ item.time }}</td>
          </tr>
          <tr>
            <td colspan="2">工时:{{ item.workTime }}</td>
          </tr>
          <tr>
            <td colspan="2">{{ item.technologys }}</td>
          </tr>
        </table>
      </div>
    </div>
  </bm-overlay>
</template>
<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {
  @Prop({
    type: Object,
    required: true
  })
  position!: any;

  @Prop({
    type: Array,
    required: true
  })
  date!: any;

  @Prop({
    type: Boolean,
    required: true
  })
  active!: any;

  @Prop({
    type: String,
    required: true
  })
  technology!: any;

  infoStatus = false;

  @Watch('position')
  setPosition () {
    if (typeof this.$refs.customOverlay !== 'undefined') {
      const customOverlay: any = this.$refs.customOverlay
      customOverlay.reload()
    }
  }

  draw ({ el, BMap, map }: any) {
    const { lng, lat } = this.position
    const pixel = map.pointToOverlayPixel(new BMap.Point(lng, lat))
    el.style.left = pixel.x - 60 + 'px'
    el.style.top = pixel.y - 20 + 'px'
  }

  handleClick () {
    this.$emit('click', this.date)
  }

  showInfo () {
    this.$store.state.dataList = this.date
    if (this.infoStatus) {
      this.infoStatus = false
    } else {
      this.infoStatus = true
    }
  }
}
</script>
