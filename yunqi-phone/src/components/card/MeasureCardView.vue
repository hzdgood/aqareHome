<template>
  <div class="cardDiv">
    <div v-show="type === '开合帘'">
      <OpenCard :data="data" @pageReset="pageReset"></OpenCard>
    </div>
    <div v-show="type === '卷帘'">
      <RollerCard :data="data" @pageReset="pageReset"></RollerCard>
    </div>
  </div>
</template>

<script setup lang="ts">
import OpenCard from './subCard/OpenCard.vue'
import RollerCard from './subCard/RollerCard.vue'

const emit = defineEmits(['pageReset', 'toPage'])
defineProps({
  data: {
    type: Object,
    default: null
  },
  type: {
    type: String,
    default: null
  }
})

const pageReset =() =>  {
  emit('pageReset')
}
</script>