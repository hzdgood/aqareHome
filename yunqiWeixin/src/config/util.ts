export const getQueryString = (name: String) => {
  let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
  var context = "";
  if (r != null) {
    context = decodeURIComponent(r[2]);
  }
  return context == null || context == "" || context == "undefined" ? "" : context;
}