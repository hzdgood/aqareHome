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
        <a-form-item v-show="model && model.parentId > 0" label="主题ID">
          <a-input v-decorator="['parentId', { initialValue: 0 }]" disabled />
        </a-form-item>
        <a-form-item label="主题名称">
          <a-select placeholder="请选择" v-decorator="['theme', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option v-for="item in menuList" :value="item.id" :key="item.id">
              {{ item.theme }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="主题内容">
          <a-input v-decorator="['content', {rules: [{required: true, min: 5, message: '请输入至少五个字符的规则描述！'}]}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>
<script>
import pick from 'lodash.pick'
import { getThemeData } from '@/api/axios'
// 表单字段
const fields = ['content', 'theme', 'parentId']
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
  this.menuList = []
  return {
    form: this.$form.createForm(this)
  }
},
created () {
  this.getMenuList()
    // 防止表单未注册
  fields.forEach(v => this.form.getFieldDecorator(v))
    // 当 model 发生改变时，为表单设置值
  this.$watch('model', () => {
    this.model && this.form.setFieldsValue(pick(this.model, fields))
  })
},
methods: {
  async getMenuList () {
    const data = await getThemeData()
    this.menuList = data.data.data
  }
}
}
</script>
