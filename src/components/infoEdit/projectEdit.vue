<template>
    <div></div>
</template>
<script>
import { Component, Vue } from 'vue-property-decorator'
@Component({})
export default class Home extends Vue {
    userId = localStorage.getItem('userId');
    async mounted () {
      console.log(111)
    }
}
</script>
<style></style>
