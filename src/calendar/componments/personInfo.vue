<template>
  <div>
    <div>
      <button @click="allClick">全部选择</button>
      <button @click="allCancle">取消全选</button>
    </div>
    <div class="persons">
      <div v-for="item in personList" :key="item.id" @click="click(item.id)">
        {{ item.name }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Actions extends Vue {
  personList: any[] = [];
  async mounted () {
    // 获取所有技术人员信息
    const obj = {
      where: { and: [{ field: 2200000145711106, query: { em: false } }] },
      offset: 0,
      limit: 30
    }
    const result = await SearchInfo('2100000015050396', obj)
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const item_id = result[i].item_id
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000149382960) {
          // 名称
          const data = {
            id: item_id,
            name: fields[j].values[0].value
          }
          this.personList.push(data)
        }
      }
    }
  }

  // 点击方法
  click (item: any) {
    const dom: any = document.getElementById(item)
    if (dom.className === 'selected') {
      dom.className = ''
    } else {
      dom.className = 'selected'
    }
    this.SelectList()
  }

  // 全选方法
  allClick () {
    for (let i = 0; i < this.personList.length; i++) {
      const id = this.personList[i].id
      const dom: any = document.getElementById(id)
      dom.className = 'selected'
    }
    this.SelectList()
  }

  allCancle () {
    for (let i = 0; i < this.personList.length; i++) {
      const id = this.personList[i].id
      const dom: any = document.getElementById(id)
      dom.className = ''
    }
    this.SelectList()
  }

  SelectList () {
    const list = []
    const obj: any = document.getElementsByClassName('selected')
    for (let i = 0; i < obj.length; i++) {
      if (obj[i].checked === true) {
        const id = obj[i].id
        list.push(id)
      }
    }
  }
}
</script>
