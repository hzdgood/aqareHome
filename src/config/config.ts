export const config = {
  application_id: '1002449', // 伙伴云应用ID
  application_secret: '5F5aMmUtCBbhNM4ahhYeG1wMK4mstbsG85VpI9Qw' // 伙伴云应用密钥
}
export const table = {
  customerInfo: '2100000014955786', // 客户信息表
  tagInfo: '2100000019532232', // 标签
  projectInfo: '2100000014956047', // 项目信息表
  saleManInfo: '2100000014955862', // 销售人员表
  areaInfo: '2100000015998321', // 区域表
  departmentInfo: '2100000016791383', // 部门表
  workSheet: '2100000015054992', // 工单表
  customerPlan: '2100000014708369', // 客户方案表
  priceList: '2100000019332138', // 报价单
  collectTable: '2100000015000019', // 收款表
  followTable: '2100000014956211' // 跟进表
}
export const field = {
  // 客户信息表
  userTable: 2200000166530102, // 销售人员
  salesperson: 2200000144968566, // USERID
  custominto: 2200000184136584, // 客户来源
  houseInfo: 2200000184134453, // 房屋情况
  hometype: 2200000184136583, // 房型
  personInfo: 2200000184136586, // 人员属性
  cAttribute: 2200000184136587, // 分类属性
  fitmentStage: 2200000184136588, // 装修阶段
  oneCustom: 2200000184136591, // 单品客户
  lostStatus: 2200000184136590, // 流失状态
  customerStage: 2200000184312981, // 客户阶段
  houseNeed: '', // 全屋需求
  // 标签表
  tagListId: 2200000182035321, // 标签名称
  tagId: 2200000184166190, // 标签ID
  // 项目信息表
  projectUUid: 1101001121000000, // UserId
  projectCustom: 2200000160386765, // 姓名
  telephone: 2200000160386766, // 手机号
  projectVillage: 2200000144970061, // 小区
  projectHometype: 2200000144970062, // 房型
  projectArea: 2200000153720999, // 区域
  projectStage: 2200000149037697, // 项目装修阶段
  projectAddress: 2200000144970060, // 地址
  projectType: 2200000148980437, // 项目类型
  masterProject: 2200000184791041, // 主项目
  saleMan: 2200000145489594, // 销售
  department: 2200000160830230 // 部门
}
export const wxInfo = {
  corpId: 'ww9a717b03b06063e3',
  agentId: '1000046'
}
export const user = {
  userId: 'woZMyYCQAAKVX-wFMjmwX8IgoXvT09gg'
}

// 房屋类型
export const houseType = [{
  name: '一室',
  value: '1'
}, {
  name: '两室',
  value: '2'
}, {
  name: '三室',
  value: '3'
}, {
  name: '四室及以上',
  value: '4'
}, {
  name: '复式',
  value: '5'
}, {
  name: '别墅',
  value: '6'
}, {
  name: '工装',
  value: '7'
}]

// 装修进度
export const fitmentStage = [{
  name: '未开工',
  value: '9'
}, {
  name: '水电进场',
  value: '8'
}, {
  name: '水电完成',
  value: '1'
}, {
  name: '木工完成',
  value: '2'
}, {
  name: '灯具完成',
  value: '3'
}, {
  name: '网络家具',
  value: '4'
}, {
  name: '调试完结',
  value: '10'
}]

// 项目类型
export const projectType = [{
  name: '全屋',
  value: '1'
}, {
  name: '单品导流',
  value: '2'
}, {
  name: '零售',
  value: '3'
}, {
  name: '工装',
  value: '4'
}, {
  name: '测试',
  value: '6'
}]

// 收款方式
export const collectType = [{
  name: '银联',
  value: ''
}, {
  name: '对公',
  value: ''
}, {
  name: '现金',
  value: ''
}, {
  name: '其他',
  value: ''
}, {
  name: 'SAAS导入',
  value: ''
}]
