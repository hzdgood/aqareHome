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
        <a-form-item label="话术类型">
          <a-select @change="selectType" placeholder="请选择类型" v-decorator="['type', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option value="企业话术">企业话术</a-select-option>
            <a-select-option value="团体话术">团体话术</a-select-option>
            <a-select-option value="个人话术">个人话术</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="快捷组" v-show="showTeam">
          <a-select @change="selectTeam" placeholder="请选择组" v-decorator="['teamId', { rules: [{ required: true, message: '该字段是必填字段' }]}]">
            <a-select-option v-for="item in teamList" :value="item.id" :key="item.id">
              {{ item.team }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="主题" v-show="showTheme">
          <a-select placeholder="请选择主题名称" v-decorator="['themeId', { rules: [{ required: true, message: '该字段是必填字段' }] }]">
            <a-select-option v-for="item in menuList" :value="item.id" :key="item.id">
              {{ item.theme }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="标题">
          <a-input v-decorator="['contentTitle', { rules: [{ required: true, message: '该字段是必填字段' }] }]"/>
        </a-form-item>
        <a-form-item label="内容" v-show="textStatus">
          <a-input v-decorator="['contentText', { rules: [{ required: textStatus, message: '该字段是必填字段' }] }]"/>
        </a-form-item>
        <a-form-item label="等级">
          <a-select placeholder="请选择等级" v-decorator="['contentLevel', { rules: [{ required: true, message: '该字段是必填字段' }] }]">
            <a-select-option value="1">高</a-select-option>
            <a-select-option value="2">中</a-select-option>
            <a-select-option value="3">低</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="类型">
          <a-select @change="selectChange" placeholder="请选择内容类型" v-decorator="['contentType', { rules: [{ required: true, message: '该字段是必填字段' }] }]">
            <a-select-option value="文本">文本</a-select-option>
            <a-select-option value="文件">文件</a-select-option>
            <a-select-option value="图片">图片</a-select-option>
            <a-select-option value="视频">视频</a-select-option>
            <a-select-option value="组合">组合</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="上传" v-show="fileStatus">
          <a-upload name="file" :beforeUpload="beforeUpload" :showUploadList="false">
            <a-button icon="upload">请选择文件</a-button>
          </a-upload>
        </a-form-item>
        <a-form-item label="路径" v-show="fileStatus">
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
const fields = ['contentTitle', 'contentLevel', 'contentType', 'contentFile', 'contentText', 'themeId', 'id', 'teamId']
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
      menuList: [],
      teamList: [],
      fileStatus: false,
      textStatus: true,
      showTeam: false,
      showTheme: false,
      uploadFile: Object,
      form: this.$form.createForm(this)
    }
  },
  created () {
    this.getMenuList()
    this.getTeamList()
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
      const data = await getPostData('/speedy/theme/select', {})
      this.menuList = data.data.data
    },
    async getTeamList () {
      const data = await getPostData('/speedy/team/select', {})
      this.teamList = data.data.data
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
    async selectTeam (value) {
      const data = await getPostData('/speedy/theme/select', { teamId: value })
      if (data.data.data.length === 0) {
        this.menuList = []
        this.showTheme = false
        this.$message.info('请为当前组添加主题！')
      } else {
        this.showTheme = true
        this.menuList = data.data.data
      }
    },
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
    },
    selectChange (value) {
      if (value === '文本') {
        this.fileStatus = false
        this.textStatus = true
      } else if (value === '组合') {
        this.fileStatus = true
        this.textStatus = true
      } else {
        this.fileStatus = true
        this.textStatus = false
      }
    }
  }
}
</script>
