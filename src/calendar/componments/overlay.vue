<template>
  <bm-overlay
    ref="customOverlay"
    :class="{ sample: true, active }"
    pane="labelPane"
    @draw="draw"
  >
    <div v-for="item in date" :key="item.id" @click="handleClick">
      <table class="point">
        <tr>
          <td colspan="4">{{ item.name }}</td>
        </tr>
        <tr>
          <td colspan="4">时间:{{ item.time }}</td>
        </tr>
        <tr>
          <td :class="item.c1">{{ item.s1 }}</td>
          <td :class="item.c2">{{ item.s2 }}</td>
          <td :class="item.c3">{{ item.s3 }}</td>
          <td :class="item.c4">{{ item.s4 }}</td>
        </tr>
      </table>
    </div>
    <img class="placeImg" src="../img/persons.png" />
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

  @Watch('position')
  setPosition () {
    if (typeof (this.$refs.customOverlay) !== 'undefined') {
      this.$refs.customOverlay.reload()
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
}
</script>
