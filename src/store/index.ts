import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  reloadStatus: false,
  projectData: {}
}
const mutations = {
  updateReload () {
    state.reloadStatus = true
  },
  updateProjectData (data: any) {
    state.projectData = data
  }
}
const actions = {
  updateReload () {
    state.reloadStatus = true
  },
  updateProjectData (data: any) {
    state.projectData = data
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
