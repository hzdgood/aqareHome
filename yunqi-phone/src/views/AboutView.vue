<template>
  <div class="about">
    <h1>This is an about page</h1>
    <a-button type="primary">Add</a-button>
  </div>
</template>

<style>

@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
<script lang="ts">
import { Button } from 'ant-design-vue';
export default {
    components: {
      AButton: Button
    },
  };
</script>
