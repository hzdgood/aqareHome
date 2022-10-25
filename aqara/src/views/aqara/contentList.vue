<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="快捷组">
                <a-input v-model="queryParam.team" placeholder=""/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="主题">
                <a-input v-model="queryParam.theme" placeholder=""/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <span class="table-page-search-submitButtons" style="overflow: hidden">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <div class="table-operator">
        <a-button type="primary" icon="plus" @click="handleAdd">新建</a-button>
        <a-dropdown v-action:edit v-if="selectedRowKeys.length > 0">
          <a-menu slot="overlay">
            <a-menu-item key="1" @click="handleDelete(1)"><a-icon type="delete"/>删除</a-menu-item>
          </a-menu>
          <a-button style="margin-left: 8px">
            批量操作 <a-icon type="down" />
          </a-button>
        </a-dropdown>
      </div>
      <s-table
        ref="table"
        size="default"
        rowKey="id"
        :columns="columns"
        :data="loadData"
        :alert="true"
        :rowSelection="rowSelection"
        showPagination="auto"
      >
        <span slot="serial" slot-scope="text, record, index">
          {{ index + 1 }}
        </span>
        <span slot="action" slot-scope="text, record">
          <template>
            <a @click="handleEdit(record)">修改</a>
          </template>
        </span>
      </s-table>
      <content-form
        ref="createModal"
        :title="formTitle"
        :visible="visible"
        :loading="confirmLoading"
        :model="mdl"
        @cancel="handleCancel"
        @ok="handleOk"
      />
    </a-card>
  </page-header-wrapper>
</template>
<script>
import { STable, Ellipsis } from '@/components'
import ContentForm from './modules/ContentForm'
import { getContentData, contentInsert, contentUpdate, contentDelete } from '@/api/axios'

const columns = [{
  title: '',
  scopedSlots: { customRender: 'serial' }
}, {
  title: '话术类型', // 企业，团队，个人
  dataIndex: 'type'
}, {
  title: '快捷组',
  dataIndex: 'team'
}, {
  title: '主题',
  dataIndex: 'theme'
}, {
  title: '内容类型',
  dataIndex: 'contentType'
}, {
  title: '内容信息',
  dataIndex: 'contentText'
}, {
  title: '文件名称',
  dataIndex: 'contentFile'
}, {
  title: '操作',
  dataIndex: 'action',
  width: '80px',
  scopedSlots: { customRender: 'action' }
}]

export default {
  name: 'ContentList',
  components: {
    STable,
    Ellipsis,
    ContentForm
  },
  data () {
    this.columns = columns
    return {
      formTitle: '',
      visible: false,
      confirmLoading: false,
      mdl: null,
      queryParam: {},
      selectedRowKeys: [],
      selectedRows: [],
      loadData: (parameter) => {
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getContentData(requestParameters).then((res) => {
          return res.data
        })
      }
    }
  },
  computed: {
    rowSelection () {
      return {
        selectedRowKeys: this.selectedRowKeys,
        onChange: this.onSelectChange
      }
    }
  },
  methods: {
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    handleAdd () {
      this.formTitle = '新建内容'
      this.mdl = null
      this.visible = true
    },
    handleEdit (record) {
      this.formTitle = '修改内容'
      this.visible = true
      this.mdl = { ...record }
    },
    handleCancel () {
      const form = this.$refs.createModal.form
      form.resetFields() // 清理表单数据（可不做）
      this.visible = false
    },
    handleOk () {
      const form = this.$refs.createModal.form
      this.confirmLoading = true
      form.validateFields(async (errors, values) => {
        if (!errors) {
          if (values.id > 0) {
            await contentUpdate(values)
            this.visible = false
            this.confirmLoading = false
            // 重置表单数据
            form.resetFields()
            // 刷新表格
            this.$refs.table.refresh()
            this.$message.info('修改成功')
          } else {
            await contentInsert(values)
            this.visible = false
            this.confirmLoading = false
            // 重置表单数据
            form.resetFields()
            // 刷新表格
            this.$refs.table.refresh()
            this.$message.info('新增成功')
          }
        }
      })
      this.confirmLoading = false
    },
    handleDelete (key) {
      const keys = this.selectedRowKeys
      const table = this.$refs.table
      const message = this.$message
      this.$confirm({
        title: '警告',
        content: `真的要删除吗?`,
        okText: '删除',
        okType: 'danger',
        cancelText: '取消',
        async onOk () {
          const req = {
            ids: keys.join()
          }
          await contentDelete(req)
          table.refresh()
          message.info('删除成功')
        },
        onCancel () {}
      })
    }
  }
}
</script>
