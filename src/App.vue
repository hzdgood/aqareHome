<template>
  <div id="app">
    <div v-if="uploadStaus == true">
      <div v-if="userStatus">
        <user></user>
        <customerTag></customerTag>
        <stage></stage>
        <sidebar-nav></sidebar-nav>
        <router-view />
        <footContent></footContent>
      </div>
      <div v-if="userStatus == false">
        <bind></bind>
      </div>
    </div>
    <div v-if="uploadStaus == false">
      <schemeEdit :upload="false" @closeScheme="close"></schemeEdit>
    </div>
  </div>
</template>

<script lang="ts">
import Nav from '@/components/nav/Nav.vue'
import user from '@/components/user/user.vue'
import bind from '@/components/user/bind.vue'
import customerTag from '@/components/infoEdit/customerTag.vue'
import stage from '@/components/infoEdit/stage.vue'
import footContent from '@/components/foot/footContent.vue'
import schemeEdit from '@/components/foot/footEdit/schemeEdit.vue'
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
import { table, field } from '@/config/config'

@Component({
  name: 'App',
  components: {
    'sidebar-nav': Nav,
    user: user,
    customerTag: customerTag,
    stage: stage,
    footContent: footContent,
    schemeEdit: schemeEdit
  }
})
export default class Actions extends Vue {
  uploadStaus = true;
  userStatus = true;
  projectInfo = table.projectInfo;
  userId = localStorage.getItem('userId');
  async mounted () {
    const url = window.location.href
    if (url.split('#')[1] === '/upload') {
      this.uploadStaus = false
    }

    const data = {
      where: {
        and: [
          {
            query: { or: [{ in: [this.userId] }] },
            query_option_mappings: [-1],
            field: field.projectUUid
          }
        ]
      },
      offset: 0,
      limit: 20,
      order_by: [
        { field: field.projectUUid, sort: 'desc' },
        { field: field.masterProject, sort: 'asc' }
      ]
    }
    const result = await SearchInfo(this.projectInfo, data)
    if (result.length === 0) {
      this.userStatus = false
    } else {
      this.userStatus = true
    }
  }

  close () {
    console.log(111)
  }
}
</script>
