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
        <a-form-item label="话术类型">
          <a-select @change="selectChange" placeholder="请选择类型" v-decorator="['type', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="企业话术">企业话术</a-select-option>
            <a-select-option value="团体话术">团体话术</a-select-option>
            <a-select-option value="个人话术">个人话术</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="所属人员" v-show="personStatus">
          <a-select placeholder="请选择所属人员" v-decorator="['affiliatePerson', { rules: [{ required: personStatus, message: '该字段是必填字段' }]}]">
            <a-select-option v-for="item in personList" :key="item.id" :value="item.name">{{ item.name }}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="公司名称">
          <a-select placeholder="请选择" v-decorator="['company', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="上海汇社">上海汇社</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="组名称">
          <a-input v-decorator="['team', {rules: [{required: true, min: 5, message: '请输入至少五个字符的规则描述！'}]}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>
<script>
import pick from 'lodash.pick'
import { getPostData } from '@/api/axios'
// 表单字段
const fields = ['type', 'company', 'id', 'team', 'affiliatePerson']
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
      personStatus: false,
      personList: [],
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
    this.personList = getPostData('/weixin/select', {}).then((res) => {
      return res.data
    })
  },
  methods: {
    selectChange (value) {
      if (value === '个人话术') {
        this.personStatus = true
      } else {
        this.personStatus = false
      }
    }
  }
}
</script>
