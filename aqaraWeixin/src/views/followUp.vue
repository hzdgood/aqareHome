<template>
  <div>
    <table width="100%" class="projectTable">
      <tr v-for="item in logList" :key="item.id">
        <td>{{ item.name }}</td>
        <td>{{ item.date }}</td>
        <td>{{ item.info }}</td>
      </tr>
    </table>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import { logSelect } from '@/config/interFace'
@Component({})
export default class Home extends Vue {
  logList: any[] = [];
  async mounted () {
    const res = await logSelect()
    for (let i = 0; i < res.length; i++) {
      const obj = {
        id: res[i].id,
        name: res[i].name,
        date: res[i].date.substring(0, 10),
        info: res[i].info
      }
      this.logList.push(obj)
    }
  }
}
</script>
<style scoped></style>
