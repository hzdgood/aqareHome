<template>
  <div>
    <div class="AllButton">
      <button class="myButton" @click="allClick">全部选择</button>
      <button class="myButton" @click="allCancle">取消全选</button>
    </div>
    <div class="persons">
      <div
        :id="item.id"
        v-for="item in personList"
        :key="item.id"
        @click="click(item.id)"
      >
        <div>
          {{ item.name }} &nbsp;&nbsp;&nbsp;&nbsp; 休息日：{{ item.wait }}
        </div>
        <div style="margin-bottom: 5px">
          <table class="point">
            <tr style="height: 20px">
              <td :class="item.s1"></td>
              <td :class="item.s2"></td>
              <td :class="item.s3"></td>
              <td :class="item.s4"></td>
              <td :class="item.s5"></td>
              <td :class="item.s6"></td>
              <td :class="item.s7"></td>
              <td :class="item.s8"></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { SearchInfo } from '@/config/interFace'
@Component({})
export default class Actions extends Vue {
  personList: any[] = [];
  resultList: any[] = [];
  async mounted () {
    this.getDataList()
  }

  async getDataList () {
    // 获取所有技术人员信息
    this.personList = []
    const obj = {
      where: { and: [{ field: 2200000155616806, query: { in: [1] } }] },
      offset: 0,
      limit: 20
    }
    const result = await SearchInfo('2100000015050396', obj)
    this.resultList = result
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const item_id = result[i].item_id
      let name = ''
      let wait = ''
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000149382960) {
          name = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000151780694) {
          wait = fields[j].values[0].value
        }
      }
      const data = {
        id: item_id,
        name: name,
        wait: wait,
        s1: 'fee',
        s2: 'fee',
        s3: 'fee',
        s4: 'fee',
        s5: 'fee',
        s6: 'fee',
        s7: 'fee',
        s8: 'fee'
      }
      this.personList.push(data)
    }
  }

  @Watch('$store.state.layerList')
  async updateStatus () {
    this.personList = []
    const result = this.resultList
    for (let i = 0; i < result.length; i++) {
      const fields = result[i].fields
      const item_id = result[i].item_id
      let name = ''
      let wait = ''
      for (let j = 0; j < fields.length; j++) {
        if (fields[j].field_id === 2200000149382960) {
          name = fields[j].values[0].value
        }
        if (fields[j].field_id === 2200000151780694) {
          wait = fields[j].values[0].value
        }
      }
      const data = {
        id: item_id,
        name: name,
        wait: wait,
        s1: 'fee',
        s2: 'fee',
        s3: 'fee',
        s4: 'fee',
        s5: 'fee',
        s6: 'fee',
        s7: 'fee',
        s8: 'fee'
      }
      this.personList.push(data)
    }
    const data1 = this.$store.state.layerList
    const data = this.personList
    for (let i = 0; i < data1.length; i++) {
      for (let j = 0; j < data.length; j++) {
        const time = Number(data1[i].date[0].time.split(':')[0])
        let workTime = data1[i].date[0].workTime
        const technologys = data1[i].date[0].technologys
        workTime = Math.ceil(workTime)
        if (technologys.indexOf(',') !== -1) {
          const d1 = technologys.split(',')
          for (let m = 0; m < d1.length; m++) {
            for (let n = 0; n < data.length; n++) {
              if (d1[m] === data[n].name) {
                for (let m = 0; m < workTime; m++) {
                  const times = time + m
                  if (times === 10) {
                    data[n].s1 = 'busy'
                  } else if (times === 11) {
                    data[n].s2 = 'busy'
                  } else if (times === 12) {
                    data[n].s3 = 'busy'
                  } else if (times === 13) {
                    data[n].s4 = 'busy'
                  } else if (times === 14) {
                    data[n].s5 = 'busy'
                  } else if (times === 15) {
                    data[n].s6 = 'busy'
                  } else if (times === 16) {
                    data[n].s7 = 'busy'
                  } else if (times === 17) {
                    data[n].s8 = 'busy'
                  }
                }
              }
            }
          }
        } else {
          if (technologys === data[j].name) {
            for (let m = 0; m < workTime; m++) {
              const times = time + m
              if (times === 10) {
                data[j].s1 = 'busy'
              } else if (times === 11) {
                data[j].s2 = 'busy'
              } else if (times === 12) {
                data[j].s3 = 'busy'
              } else if (times === 13) {
                data[j].s4 = 'busy'
              } else if (times === 14) {
                data[j].s5 = 'busy'
              } else if (times === 15) {
                data[j].s6 = 'busy'
              } else if (times === 16) {
                data[j].s7 = 'busy'
              } else if (times === 17) {
                data[j].s8 = 'busy'
              }
            }
          }
        }
      }
    }
  }

  // 点击方法
  click (item: any) {
    const dom: any = document.getElementById(item)
    if (dom.className === 'selects') {
      dom.className = ''
    } else {
      dom.className = 'selects'
    }
    this.SelectList()
  }

  // 全选方法
  allClick () {
    for (let i = 0; i < this.personList.length; i++) {
      const id = this.personList[i].id
      const dom: any = document.getElementById(id)
      dom.className = 'selects'
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
    const obj: any = document.getElementsByClassName('selects')
    for (let i = 0; i < obj.length; i++) {
      const id = parseInt(obj[i].id)
      list.push(id)
    }
    this.$store.state.selectData = list
  }
}
</script>
