import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  reloadStatus: false,
  customTagStatus: false,
  // 日程系统
  layerList: [],
  CalendarDate: '',
  dataList: [],
  selectData: [],
  searchStatus: false
}
const mutations = {
  updateReload () {
    if (!state.reloadStatus) {
      state.reloadStatus = true
    } else {
      state.reloadStatus = false
    }
  },
  updataTag () {
    if (!state.customTagStatus) {
      state.customTagStatus = true
    } else {
      state.customTagStatus = false
    }
  },
  search () {
    if (!state.searchStatus) {
      state.searchStatus = true
    } else {
      state.searchStatus = false
    }
  }
}
const actions = {
  updateReload () {
    if (!state.reloadStatus) {
      state.reloadStatus = true
    } else {
      state.reloadStatus = false
    }
  },
  updataTag () {
    if (!state.customTagStatus) {
      state.customTagStatus = true
    } else {
      state.customTagStatus = false
    }
  },
  search () {
    if (!state.searchStatus) {
      state.searchStatus = true
    } else {
      state.searchStatus = false
    }
  }
}
const getters = {
}

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
})
