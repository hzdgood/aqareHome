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
        <!-- 检查是否有 id 并且大于0，大于0是修改。其他是新增，新增不显示主键ID -->
        <a-form-item v-show="model && model.id > 0" label="主键ID">
          <a-input v-decorator="['id', { initialValue: 0 }]" disabled />
        </a-form-item>
        <a-form-item label="快捷组">
          <a-select placeholder="请选择组" v-decorator="['teamId', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option v-for="item in teamList" :value="item.id" :key="item.id">
              {{ item.team }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="主题名称">
          <a-input v-decorator="['theme', {rules: [{required: true, min: 5, message: '请输入至少五个字符的规则描述！'}]}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>
<script>
import pick from 'lodash.pick'
import { getTeamData } from '@/api/axios'
// 表单字段
const fields = ['teamId', 'theme', 'id']
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
  this.teamList = []
  return {
    form: this.$form.createForm(this)
  }
},
created () {
  this.getThemeList()
    // 防止表单未注册
  fields.forEach(v => this.form.getFieldDecorator(v))
    // 当 model 发生改变时，为表单设置值
  this.$watch('model', () => {
    this.model && this.form.setFieldsValue(pick(this.model, fields))
  })
},
methods: {
  async getThemeList () {
    const obj = {
      pageNo: 1,
      pageSize: 10
    }
    const data = await getTeamData(obj)
    this.teamList = data.data.data
  }
}
}
</script>
