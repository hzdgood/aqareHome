<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="主题">
                <a-input v-model="queryParam.theme" placeholder=""/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
            </a-col>
          </a-row>
        </a-form>
      </div>

      <div class="table-operator">
        <a-button type="primary" icon="plus" @click="handleAdd">新建</a-button>
        <a-dropdown v-action:edit v-if="selectedRowKeys.length > 0">
          <a-menu slot="overlay">
            <a-menu-item key="1"><a-icon type="delete" @click="handleDelete"/>删除</a-menu-item>
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
      <menu-form
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
import MenuForm from './modules/MenuForm'
import { getThemeData, themeInsert } from '@/api/axios'

const columns = [
  {
    title: '',
    scopedSlots: { customRender: 'serial' }
  },
  {
    title: '话术类型', // 企业，团队，个人
    dataIndex: 'type'
  },
  {
    title: '快捷组',
    dataIndex: 'team'
  },
  {
    title: '主题',
    dataIndex: 'theme'
  },
  {
    title: '公司名称',
    dataIndex: 'company'
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '150px',
    scopedSlots: { customRender: 'action' }
  }
]

export default {
  name: 'MenuList',
  components: {
    STable,
    Ellipsis,
    MenuForm
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
        return getThemeData(requestParameters).then((res) => {
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
      this.formTitle = '新建主题'
      this.mdl = null
      this.visible = true
    },
    handleOk () {
      const form = this.$refs.createModal.form
      this.confirmLoading = true
      form.validateFields(async (errors, values) => {
        if (!errors) {
          console.log('values', values)
          if (values.id > 0) {
            // await themeInsert(values)
            this.visible = false
            this.confirmLoading = false
            // 重置表单数据
            form.resetFields()
            // 刷新表格
            this.$refs.table.refresh()
            this.$message.info('修改成功')
          } else {
            await themeInsert(values)
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
    handleCancel () {
      const form = this.$refs.createModal.form
      form.resetFields() // 清理表单数据（可不做）
      this.visible = false
    },
    handleEdit (record) {
      this.formTitle = '修改主题'
      this.visible = true
      this.mdl = { ...record }
    },
    handleDelete () {

    }
  }
}
</script>
