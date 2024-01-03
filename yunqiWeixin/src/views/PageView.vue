<template>
  <div class="appbody">
    <div style="background-color: #f2f3fb;">
      <div class="appdiv">
        <user></user>
        <div class="lineType"></div>
        <customer></customer>
      </div>
      <div class="appdiv">
        <stage></stage>
      </div>
      <div class="appdiv">
        <sidebar></sidebar>
        <router-view />
        <foot></foot>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive } from 'vue';
import user from '../components/user/user.vue'
import sidebar from '../components/sidebar/sidebar.vue'
import foot from '../components/foot/foot.vue'
import customer from '../components/customer/customer.vue'
import stage from '../components/stage/stage.vue'

onMounted(async function() {
  
})

interface FormState {
  userId: any[]
}

const formState = reactive<FormState>({
  userId: []
})
</script>
