<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="6" :sm="24">
              <a-form-item label="话术类型">
                <a-select @change="selectType" placeholder="请选择" v-model="queryParam.type">
                  <a-select-option value="企业话术">企业话术</a-select-option>
                  <a-select-option value="团体话术">团体话术</a-select-option>
                  <a-select-option value="个人话术">个人话术</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24" v-show="personStatus">
              <a-form-item label="所属人员">
                <a-select placeholder="请选择所属人员" v-model="queryParam.person">
                  <a-select-option v-for="item in personList" :key="item.id" :value="item.engName">{{ item.userName }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item label="快捷组">
                <a-input v-model="queryParam.team" placeholder=""/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
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
      <theme-form
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
import themeForm from './modules/themeForm'
import moment from 'moment'
import { getPostData, getData } from '@/api/axios'
const columns = [{
  title: '',
  scopedSlots: { customRender: 'serial' },
  width: 50
}, {
  title: '话术类型', // 企业，团队，个人
  dataIndex: 'type',
  width: 100
}, {
  title: '所属人员',
  dataIndex: 'affiliatePerson',
  ellipsis: true
}, {
  title: '快捷组',
  dataIndex: 'team',
  ellipsis: true
}, {
  title: '主题',
  dataIndex: 'theme',
  ellipsis: true
}, {
  title: '公司名称',
  dataIndex: 'company'
}, {
  title: '创建时间',
  dataIndex: 'createTime',
  ellipsis: true,
  customRender: function (val) {
    return val ? moment(val).format('YYYY-MM-DD HH:mm:ss') : ''
  }
}, {
  title: '操作',
  dataIndex: 'action',
  width: '150px',
  scopedSlots: { customRender: 'action' }
}]

export default {
  name: 'MenuList',
  components: {
    STable,
    Ellipsis,
    themeForm
  },
  async created () {
    const obj = await getPostData('/user/select', {})
    this.personList = obj.data
  },
  data () {
    this.columns = columns
    return {
      personStatus: false,
      personList: [],
      formTitle: '',
      visible: false,
      confirmLoading: false,
      mdl: null,
      queryParam: {},
      selectedRowKeys: [],
      selectedRows: [],
      loadData: (parameter) => {
        const requestParameters = Object.assign({}, parameter, this.queryParam)
        return getPostData('/speedy/theme/select', requestParameters).then((res) => {
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
      this.formTitle = '新建快捷组'
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
            await getData('/speedy/theme/update', values)
            this.visible = false
            this.confirmLoading = false
            // 重置表单数据
            form.resetFields()
            // 刷新表格
            this.$refs.table.refresh()
            this.$message.info('修改成功')
          } else {
            await getData('/speedy/theme/insert', values)
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
      this.formTitle = '修改快捷组'
      this.visible = true
      this.mdl = { ...record }
    },
    selectType (value) {
      if (value === '个人话术') {
        this.personStatus = true
      } else {
        this.personStatus = false
      }
    },
    async handleDelete (key) {
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
          await getData('/speedy/theme/delete', req)
          table.refresh()
          message.info('删除成功')
        },
        onCancel () {}
      })
    }
  }
}
</script>
