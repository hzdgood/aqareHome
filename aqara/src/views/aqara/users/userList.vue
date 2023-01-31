<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="用户名称">
                <a-input v-model="queryParam.username" placeholder="请输入用户名称" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="英文名称">
                <a-input v-model="queryParam.engName" placeholder="请输入英文名称" />
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
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
            <a-menu-item key="1" @click="handleDelete(1)"><a-icon type="delete" />删除</a-menu-item>
          </a-menu>
          <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /> </a-button>
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
      <user-form
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
import { getPostData, getData } from '@/api/axios'
import UserForm from './modules/userForm'
const columns = [
  {
    title: '',
    scopedSlots: { customRender: 'serial' },
    width: 50
  },
  {
    title: '用户名称', // 企业，团队，个人
    dataIndex: 'userName',
    width: 100
  },
  {
    title: '英文名称',
    dataIndex: 'engName',
    ellipsis: true
  },
  {
    title: '密码',
    dataIndex: 'passWord',
    ellipsis: true
  },
  {
    title: '性别',
    dataIndex: 'sex',
    ellipsis: true
  },
  {
    title: '岗位',
    dataIndex: 'station'
  },
  {
    title: '部门',
    dataIndex: 'department'
  },
  {
    title: '状态',
    dataIndex: 'status',
    ellipsis: true
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '80px',
    scopedSlots: { customRender: 'action' }
  }
]
export default {
  name: 'UserList',
  components: {
    STable,
    Ellipsis,
    UserForm
  },
  data () {
    this.columns = columns
    return {
      formTitle: '',
      visible: false,
      confirmLoading: false,
      mdl: null,
      showTeam: false,
      teamList: [],
      queryParam: {},
      selectedRowKeys: [],
      selectedRows: [],
      loadData: (parameter) => {
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getPostData('/user/selectAll', requestParameters).then((res) => {
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
      this.formTitle = '新增用户信息'
      this.mdl = null
      this.visible = true
    },
    handleEdit (record) {
      this.formTitle = '修改用户信息'
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
          console.log('values', values)
          if (values.id > 0) {
            await getData('/user/update', values)
            this.visible = false
            this.confirmLoading = false
            // 重置表单数据
            form.resetFields()
            // 刷新表格
            this.$refs.table.refresh()
            this.$message.info('修改成功')
          } else {
            await getData('/user/insert', values)
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
          await getData('/user/delete', req)
          table.refresh()
          message.info('删除成功')
        },
        onCancel () {}
      })
    }
  }
}
</script>

<style>
</style>
