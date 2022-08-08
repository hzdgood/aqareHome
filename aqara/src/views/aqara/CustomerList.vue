<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="销售">
                <a-select v-model="queryParam.sales" placeholder="请选择" default-value="0">
                  <a-select-option value="0">全部</a-select-option>
                  <a-select-option value="1">关闭</a-select-option>
                  <a-select-option value="2">运行中</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="门店">
                <a-select v-model="queryParam.department" placeholder="请选择" default-value="0">
                  <a-select-option value="0">全部</a-select-option>
                  <a-select-option value="1">关闭</a-select-option>
                  <a-select-option value="2">运行中</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="开始日期">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model="queryParam.createTime" style="width: 100%" placeholder="开始日期" />
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="结束日期">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model="queryParam.endTime" style="width: 100%" placeholder="结束日期" />
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
      <s-table
        ref="table"
        size="default"
        rowKey="key"
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
import { getCustomer } from '@/api/manage'

const columns = [
  {
    title: '',
    scopedSlots: { customRender: 'serial' }
  },
  {
    title: '客户名称',
    dataIndex: 'name'
  },
  {
    title: '客户电话',
    dataIndex: 'telephone'
  },
  {
    title: '销售',
    dataIndex: 'sales'
  },
  {
    title: '所属门店',
    dataIndex: 'department'
  },
  {
    title: '性别',
    dataIndex: 'sex'
  },
  {
    title: '创建人',
    dataIndex: 'createName'
  },
  {
    title: '创建时间',
    dataIndex: 'createTime'
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
        return getCustomer(requestParameters)
          .then(res => {
            console.log(res.result)
            return res.result
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
