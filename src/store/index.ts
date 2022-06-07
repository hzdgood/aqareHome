import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  reloadStatus: false,
  customTagStatus: false
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
