<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="主题名称">
                <a-select v-model="queryParam.menu" placeholder="请选择" default-value="0">
                  <a-select-option value="0">1</a-select-option>
                  <a-select-option value="1">2</a-select-option>
                  <a-select-option value="2">3</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <span class="table-page-search-submitButtons" style="overflow: hidden">
                <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
              </span>
            </a-col>
          </a-row>
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <span class="table-page-search-submitButtons" style="overflow: hidden">
                <a-button type="primary">新增</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
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
      </s-table>
    </a-card>
  </page-header-wrapper>
</template>
  <script>
import { STable } from '@/components'
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
    title: '公司名称',
    dataIndex: 'theme'
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
  }
]

export default {
  name: 'CustomerList',
  components: {
    STable
  },
  data () {
    this.columns = columns
    return {
      visible: false,
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
    }
  }
}
</script>
