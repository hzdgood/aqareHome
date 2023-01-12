<template>
  <a-modal
    :title="title"
    :width="640"
    :visible="visible"
    :confirmLoading="loading"
    @ok="() => {$emit('ok')}"
    @cancel="() => {$emit('cancel')}"
  >
    <a-spin :spinning="loading">
      <a-form :form="form" v-bind="formLayout">
        <a-form-item v-show="model && model.id > 0" label="主键ID">
          <a-input v-decorator="['id', { initialValue: 0 }]" disabled />
        </a-form-item>
        <a-form-item label="用户名称">
          <a-input v-decorator="['userName', { rules: [{ required: true, message: '该字段是必填字段' }]}]" />
        </a-form-item>
        <a-form-item label="英文名称">
          <a-input v-decorator="['engName', { rules: [{ required: true, message: '该字段是必填字段' }]}]" />
        </a-form-item>
        <a-form-item label="密码">
          <a-input-password type="password" v-decorator="['passWord', { rules: [{ required: true, message: '该字段是必填字段' }]}]" />
        </a-form-item>
        <a-form-item label="性别">
          <a-select @change="selectChange" placeholder="请选择类型" v-decorator="['sex', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="男">男</a-select-option>
            <a-select-option value="女">女</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="岗位">
          <a-select @change="selectChange" placeholder="请选择类型" v-decorator="['station', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="销售">销售</a-select-option>
            <a-select-option value="技术">技术</a-select-option>
            <a-select-option value="管理">管理</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="部门">
          <a-input v-decorator="['department', { rules: [{ required: true, message: '该字段是必填字段' }]}]" />
        </a-form-item>
        <a-form-item label="状态">
          <a-select @change="selectChange" placeholder="请选择类型" v-decorator="['status', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="在职">在职</a-select-option>
            <a-select-option value="离职">离职</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import pick from 'lodash.pick'
const fields = ['userName', 'engName', 'passWord', 'sex', 'station', 'department', 'status']
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
      form: this.$form.createForm(this)
    }
  },
  async created () {
    // 防止表单未注册
    fields.forEach((v) => this.form.getFieldDecorator(v))
    // 当 model 发生改变时，为表单设置值
    this.$watch('model', () => {
      this.model && this.form.setFieldsValue(pick(this.model, fields))
    })
  }
}
</script>

<style>
</style>
