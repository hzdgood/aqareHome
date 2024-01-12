<template>
  <div class="stageDiv">
    <div class="stageButton">
      <span>
        <img src="../img/x1.png" width="22%" />
      </span>
      <span>
        <img src="../img/person/state-1.png" width="73%" v-show="stageImg1" />
        <img src="../img/person/state-2.png" width="73%" v-show="stageImg2" />
        <img src="../img/person/state-3.png" width="73%" v-show="stageImg3" />
        <img src="../img/person/state-4.png" width="73%" v-show="stageImg4" />
        <img src="../img/person/state-5.png" width="73%" v-show="stageImg5" />
        <img src="../img/person/state-6.png" width="73%" v-show="stageImg6" />
        <img src="../img/person/state-7.png" width="73%" v-show="stageImg7" />
        <img src="../img/person/state-8.png" width="73%" v-show="stageImg8" />
      </span>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
const stageImg1 = true;
const stageImg2 = false;
const stageImg3 = false;
const stageImg4 = false;
const stageImg5 = false;
const stageImg6 = false;
const stageImg7 = false;
const stageImg8 = false;
</script>

<style>

</style>