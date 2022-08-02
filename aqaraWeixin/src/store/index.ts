import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  reloadStatus: false,
  customTagStatus: false,
  Loading: false,
  confirm: false,
  confirmInfo: '',
  layerList: [],
  // 日程系统
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
  Loading () {
    if (!state.Loading) {
      state.Loading = true
    } else {
      state.Loading = false
    }
  },
  confirm (info: any) {
    state.confirmInfo = info
    if (!state.confirm) {
      state.confirm = true
    } else {
      state.confirm = false
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
  Loading () {
    if (!state.Loading) {
      state.Loading = true
    } else {
      state.Loading = false
    }
  },
  confirm (info: any) {
    state.confirmInfo = info
    if (!state.confirm) {
      state.confirm = true
    } else {
      state.confirm = false
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
