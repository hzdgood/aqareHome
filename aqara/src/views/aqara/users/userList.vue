<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="用户名称">
                <a-input v-model="queryParam.theme" placeholder="" />
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
import { getPostData } from '@/api/axios'
const columns = [
  {
    title: '',
    scopedSlots: { customRender: 'serial' },
    width: 50
  },
  {
    title: '用户名称', // 企业，团队，个人
    dataIndex: 'name',
    width: 100
  },
  {
    title: '英文名称',
    dataIndex: 'userId',
    ellipsis: true
  },
  {
    title: '密码',
    dataIndex: 'password',
    ellipsis: true
  },
  {
    title: '性别',
    dataIndex: 'contentTitle',
    ellipsis: true
  },
  {
    title: '部门',
    dataIndex: 'contentType',
    width: 80
  },
  {
    title: '状态',
    dataIndex: 'contentText',
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
  name: 'ContentList',
  components: {
    STable,
    Ellipsis
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
        return getPostData('/speedy/content/select', requestParameters).then((res) => {
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
    handleAdd () {},
    handleEdit (record) {},
    handleCancel () {
      const form = this.$refs.createModal.form
      form.resetFields() // 清理表单数据（可不做）
      this.visible = false
    },
    handleOk () {},
    handleDelete (key) {}
  }
}
</script>

<style>
</style>
