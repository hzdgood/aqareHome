<template>
  <a-modal
    :title="title"
    :width="640"
    :visible="visible"
    :confirmLoading="loading"
    @ok="() => { $emit('ok') }"
    @cancel="() => { $emit('cancel') }"
  >
    <a-spin :spinning="loading">
      <a-form :form="form" v-bind="formLayout">
        <a-form-item v-show="model && model.id > 0" label="主键ID">
          <a-input v-decorator="['id', { initialValue: 0 }]" disabled />
        </a-form-item>
        <a-form-item label="话术类型">
          <a-select @change="selectType" placeholder="请选择类型" v-decorator="['type', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="企业话术">企业话术</a-select-option>
            <a-select-option value="团体话术">团体话术</a-select-option>
            <a-select-option value="个人话术">个人话术</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="快捷组" v-show="showTeam">
          <a-select placeholder="请选择组" v-decorator="['teamId', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option v-for="item in teamList" :value="item.id" :key="item.id">
              {{ item.team }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="主题名称">
          <a-input v-decorator="['theme', { rules: [{ required: true, message: '该字段是必填字段' }]}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>
<script>
import pick from 'lodash.pick'
import { getPostData } from '@/api/axios'
// 表单字段
const fields = ['teamId', 'theme', 'id', 'type']
export default {
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    loading: {
      type: Boolean,
      default: () => false
    },
    model: {
      type: Object,
      default: () => null
    },
    title: {
      type: String,
      default: ''
    }
  },
  data () {
    this.formLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 13 }
      }
    }
    return {
      teamList: [],
      showTeam: false,
      form: this.$form.createForm(this)
    }
  },
  created () {
      // 防止表单未注册
    fields.forEach(v => this.form.getFieldDecorator(v))
      // 当 model 发生改变时，为表单设置值
    this.$watch('model', () => {
      this.model && this.form.setFieldsValue(pick(this.model, fields))
    })
  },
  methods: {
    async selectType (value) {
      const data = await getPostData('/speedy/team/select', { type: value })
      if (data.data.data.length === 0) {
        this.teamList = []
        this.showTeam = false
        this.$message.info('请为该话术添加组！')
      } else {
        this.teamList = data.data.data
        this.showTeam = true
      }
    }
  }
}
</script>
