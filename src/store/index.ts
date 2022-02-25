import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  reloadStatus: false
}
const mutations = {
  updateReload () {
    state.reloadStatus = true
  }
}
const actions = {
  updateReload () {
    state.reloadStatus = true
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
