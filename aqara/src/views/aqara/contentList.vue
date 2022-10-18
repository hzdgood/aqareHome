<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="主题名称">
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
import { getContentData } from '@/api/axios'

const columns = [
  {
    title: '序号',
    scopedSlots: { customRender: 'serial' }
  },
  {
    title: '主题名称',
    dataIndex: 'theme'
  },
  {
    title: '主题内容',
    dataIndex: 'content'
  },
  {
    title: '创建人员',
    dataIndex: 'createName'
  },
  {
    title: '创建时间',
    dataIndex: 'createTime'
  },
  {
    title: '修改人员',
    dataIndex: 'updateName'
  },
  {
    title: '修改时间',
    dataIndex: 'updateTime'
  },
  {
    title: '操作',
    dataIndex: 'action',
    width: '150px',
    scopedSlots: { customRender: 'action' }
  }
]

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
        console.log(requestParameters)
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
    handleDelete () {},
    handleCancel () {
      const form = this.$refs.createModal.form
      form.resetFields() // 清理表单数据（可不做）
      this.visible = false
    },
    handleOk () {}
  }
}
</script>
