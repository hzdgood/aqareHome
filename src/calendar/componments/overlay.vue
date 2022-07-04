<template>
  <div>
    <bm-overlay
      pane="labelPane"
      :class="{ sample: true, active }"
      @draw="draw"
      @mouseover.native="active = true"
      @mouseleave.native="active = false"
    >
      <div @click="handleClick" v-for="item in date" :key="item.id">
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
    </bm-overlay>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
@Component({})
export default class Actions extends Vue {
  active = false;

  @Prop({
    type: Object,
    required: true
  })
  position!: any;

  @Prop({
    type: Object,
    required: true
  })
  date!: any;

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
