import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  taskId: ''
}
const mutations = {
  updateTaskid(state: any, id: string) {
    state.taskId = id;
  }
}
const actions = {
  updateTaskid(state: any, id: string) {
    state.taskId = id;
  }
}
const getters = {
  taskId: (state: any) => state.taskId
}

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
})
