<template>
  <div>
    <!-- <div class='AllButton'>
      <button class='myButton' @click='allClick'>全部选择</button>
      <button class='myButton' @click='allCancle'>取消全选</button>
    </div> -->
    <div class='persons'>
      <div :id='item.id' v-for='item in personList' :key='item.id' @click='click(item.id)'>
        <div>
          {{ item.name }} &nbsp;&nbsp;<span :class='item.workStatus'></span>&nbsp;&nbsp; 休息日：{{ item.wait }}
        </div>
        <div style='margin-bottom: 5px'>
          <table class='point'>
            <tr style='height: 20px'>
              <td :class='item.s1'></td>
              <td :class='item.s2'></td>
              <td :class='item.s3'></td>
              <td :class='item.s4'></td>
              <td :class='item.s5'></td>
              <td :class='item.s6'></td>
              <td :class='item.s7'></td>
              <td :class='item.s8'></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Watch } from 'vue-property-decorator'
import { httpGet } from '@/config/interFace'
@Component({})
export default class Actions extends Vue {
  personList: any[] = [];
  resultList: any[] = [];

  @Watch('$store.state.CalendarDate')
  async getTechStatus () {
    this.resultList = []
    const result = await httpGet('/schedule/time', {
      dateOfVisit: this.$store.state.CalendarDate
    })
    const tech = await httpGet('/tech/select', {})
    for (let i = 0; i < tech.length; i++) {
      const waitday = tech[i].waitDay
      const name = tech[i].name
      let workStatus = ''
      let count = 0
      for (let j = 0; j < result.length; j++) {
        const status = result[j].status
        if (result[j].name === tech[i].name) {
          if (status === '上门中') {
            workStatus = 'workStatus'
            count = count + 1
          } else if (status === '待上门') {
            if (workStatus === '') {
              workStatus = 'workStatus1'
              count = count + 1
            }
          }
        }
      }
      if (count === 0) {
        workStatus = 'workStatus2'
      }
      const data = {
        id: name,
        name: name,
        wait: waitday,
        workStatus: workStatus,
        s1: 'fee',
        s2: 'fee',
        s3: 'fee',
        s4: 'fee',
        s5: 'fee',
        s6: 'fee',
        s7: 'fee',
        s8: 'fee'
      }
      this.resultList.push(data)
    }
  }

  @Watch('$store.state.layerList')
  async updateStatus () {
    this.personList = this.resultList
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
                for (let m = 0; m <= workTime; m++) {
                  const times = time + m
                  if (times === 9) {
                    data[n].s1 = 'busy'
                  } else if (times === 10) {
                    data[n].s2 = 'busy'
                  } else if (times === 11) {
                    data[n].s3 = 'busy'
                  } else if (times === 12) {
                    data[n].s4 = 'busy'
                  } else if (times === 13) {
                    data[n].s5 = 'busy'
                  } else if (times === 14) {
                    data[n].s6 = 'busy'
                  } else if (times === 15) {
                    data[n].s7 = 'busy'
                  } else if (times === 16) {
                    data[n].s8 = 'busy'
                  }
                }
              }
            }
          }
        } else {
          if (technologys === data[j].name) {
            for (let m = 0; m <= workTime; m++) {
              const times = time + m
              if (times === 9) {
                data[j].s1 = 'busy'
              } else if (times === 10) {
                data[j].s2 = 'busy'
              } else if (times === 11) {
                data[j].s3 = 'busy'
              } else if (times === 12) {
                data[j].s4 = 'busy'
              } else if (times === 13) {
                data[j].s5 = 'busy'
              } else if (times === 14) {
                data[j].s6 = 'busy'
              } else if (times === 15) {
                data[j].s7 = 'busy'
              } else if (times === 16) {
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
