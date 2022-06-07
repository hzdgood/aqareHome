<template>
  <div>
    <div class="userDiv">
      <table>
        <tr>
          <td>
            <img width="40px" height="40px;" src="../../img/name.png" />
          </td>
          <td>
            <span class="username">{{ name }}</span>
          </td>
          <td>
            <button @click="editPage()">编辑</button>
          </td>
        </tr>
      </table>
    </div>
    <div v-if="editShow">
      <custom-edit @close="closeEdit()" @reload="reloadPage()"></custom-edit>
    </div>
    <div v-if="chatShow">
      <project-edit @close="closeEdit()" @reload="reloadPage()"></project-edit>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field } from '@/config/config'
import customerEdit from '@/components/infoEdit/customerEdit.vue'
import projectEdit from '@/components/infoEdit/projectEdit.vue'
@Component({
  name: 'App',
  components: {
    'custom-edit': customerEdit,
    'project-edit': projectEdit
  }
})
export default class Home extends Vue {
  userName = localStorage.getItem('userName');
  userId = localStorage.getItem('userId');
  contactType = localStorage.getItem('contactType');
  name = '';
  editShow = false;
  chatShow = false;

  async mounted () {
    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.userTable
          }
        ]
      },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo(table.customerInfo, data)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === field.customerName) {
          const values = fields[j].values[0].value
          this.name = values
        }
      }
    }
  }

  editPage () {
    if (this.contactType === 'single_chat_tools') {
      this.editShow = true
    } else {
      this.chatShow = true
    }
  }

  closeEdit () {
    this.editShow = false
    this.chatShow = false
  }

  reloadPage () {
    this.$store.dispatch('updataTag')
  }
}
</script>
<style scoped></style>
