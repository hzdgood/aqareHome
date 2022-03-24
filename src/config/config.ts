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
  followTable: '2100000014956211', // 跟进表
  sheetDetail: '2100000014976390', // 工单明细
  productTable: '2100000014705410' // 产品信息表
}
export const field = {
  // 客户信息表
  customerName: 2200000144967973, // 客户名称
  userTable: 2200000166530102, // USERID
  salesperson: 2200000144968566, // 销售人员ID
  custominto: 2200000184136584, // 客户来源
  houseInfo: 2200000184134453, // 房屋情况
  hometype: 2200000184136583, // 房型
  personInfo: 2200000184136586, // 人员属性
  cAttribute: 2200000184136587, // 分类属性
  fitmentStage: 2200000184136588, // 装修阶段
  oneCustom: 2200000184136591, // 单品客户
  lostStatus: 2200000184136590, // 流失状态
  customerStage: 2200000184312981, // 客户阶段
  houseNeed: 2200000184645955, // 全屋需求
  gender: 2200000144967977, // 性别
  ctelephone: 2200000144967974, // 客户电话
  // 标签表
  tagListId: 2200000182035321, // 标签名称
  tagId: 2200000184166190, // 标签ID
  // 项目信息表
  projectCode: 2200000150460774, // 项目编码
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
  department: 2200000160830230, // 部门
  X: 2200000150942406,
  Y: 2200000150942407,
  // 客户方案表
  projectName: 2200000143113282, // 产品名称
  AllNumber: 2200000143342281, // 全款数量
  planNuber: 2200000143114502, // 方案数量
  price: 2200000143114391, // 销售单价
  discount: 2200000151416990, // 单价折扣
  servieFee: 2200000143114392, // 服务费
  notIssued: 2200000150138925, // 未发
  notInstalled: 2200000149183536, // 未装
  notAdjusted: 2200000149183537, // 未调
  // 工单表
  visitDate: 2200000145748099, // 上门日期 value
  orderType: 2200000146398516, // 订单类型 name
  technology: 2200000145748100, // 技术 title
  workOrderStatus: 2200000148897469, // 工单状态 name
  nextDoor: 2200000147636801, // 下次上门安排 value
  todayCompletion: 2200000164473502, // 今日完成情况 value
  orderId: 2200000145748098, // 订单ID value
  issuance: 2200000180807302, // 发货日期
  batchingState: 2200000147636548, // 配料状态
  // 工单明细
  orderPname: 1119011102000000, // 产品名称
  shipment: 2200000145127032, // 本次发货
  // 收款表
  pName: 2200000145309762, // 项目名称
  pType: 2200000145309763, // 收款类型
  cType: 2200000145309764, // 收款方式
  cMoney: 2200000145309766, // 收款金额
  uploadFile: 2200000145309769, // 上传图片
  // 物料表
  productName: 2200000143086697,
  productItemId: 2200000143113282,
  projectItemId: 2200000143342285,
  money: 2200000143114391,
  number: 2200000143342281,
  serviceFee: 2200000143114392,
  // 销售人员信息表
  localName: 2200000166537030
}
export const user = {
  userId: localStorage.getItem('userId'),
  localName: localStorage.getItem('localName'),
  userName: localStorage.getItem('userName')
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
  value: '1'
}, {
  name: '对公',
  value: '2'
}, {
  name: '现金',
  value: '3'
}, {
  name: '其他',
  value: '4'
}, {
  name: 'SAAS导入',
  value: '6'
}]
