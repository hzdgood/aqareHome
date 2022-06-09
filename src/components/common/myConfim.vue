<template>
  <div>
    <div class="floatDiv"></div>
    <div>
        {{ msg }}
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
  msg!: String;
}
</script>

<style scoped>
</style>
