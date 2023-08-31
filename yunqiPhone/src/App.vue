<template>
  <div class="appbody">
    <sidebar-nav></sidebar-nav>
    <router-view />
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Nav from '@/components/nav/Nav.vue'
import '@/css/aqara.css'
@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav
  }
})
export default class Actions extends Vue {
}
</script>
