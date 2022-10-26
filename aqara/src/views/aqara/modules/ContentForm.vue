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
        <a-form-item label="主题名称">
          <a-select placeholder="请选择主题名称" v-decorator="['themeId', { rules: [{ required: true, message: '该字段是必填字段' }] }]">
            <a-select-option v-for="item in menuList" :value="item.id" :key="item.id">
              {{ item.theme }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="内容类型">
          <a-select @change="selectChange" placeholder="请选择内容类型" v-decorator="['contentType', { rules: [{ required: true, message: '该字段是必填字段' }] }]">
            <a-select-option value="文本">文本</a-select-option>
            <a-select-option value="文件">文件</a-select-option>
            <a-select-option value="图片">图片</a-select-option>
            <a-select-option value="视频">视频</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="主题内容" v-show="textStatus">
          <a-input v-decorator="['contentText', { rules: [{ required: true, message: '该字段是必填字段' }] }]"/>
        </a-form-item>
        <a-form-item label="内容上传" v-show="fileStatus">
          <a-upload name="file" :beforeUpload="beforeUpload" :showUploadList="false">
            <a-button icon="upload">请选择文件</a-button>
          </a-upload>
        </a-form-item>
        <a-form-item label="文件路径" v-show="fileStatus">
          <a-input v-decorator="['contentFile', { initialValue: '' }]" disabled />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>
<script>
import pick from 'lodash.pick'
import { getPostData, uploadFile } from '@/api/axios'
// 表单字段
const fields = ['contentType', 'contentFile', 'contentText', 'themeId', 'id']
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
    this.fileStatus = false
    this.textStatus = true
    return {
      uploadFile: Object,
      form: this.$form.createForm(this)
    }
  },
  created () {
    this.getMenuList()
    // 防止表单未注册
    fields.forEach((v) => this.form.getFieldDecorator(v))
    // 当 model 发生改变时，为表单设置值
    this.$watch('model', () => {
      this.model && this.form.setFieldsValue(pick(this.model, fields))
    })
    this.$watch('uploadFile', () => {
      this.uploadFile && this.form.setFieldsValue(pick(this.uploadFile, fields))
    })
  },
  methods: {
    async getMenuList () {
      const obj = {
        pageNo: 1,
        pageSize: 10
      }
      const data = await getPostData('/speedy/theme/select', obj)
      this.menuList = data.data.data
    },
    async beforeUpload (file) {
      const formData = new FormData()
      formData.append('file', file, file.name)
      const res = await uploadFile(formData, '/speedy/content/upload')
      this.uploadFile = {
        contentText: '已上传文件',
        contentFile: res
      }
    },
    selectChange (value) {
      if (value === '文本') {
        this.fileStatus = false
        this.textStatus = true
      } else {
        this.fileStatus = true
        this.textStatus = false
        this.uploadFile = {
          contentText: '未上传文件',
          contentFile: ''
        }
      }
    }
  }
}
</script>
