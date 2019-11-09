const commonUtils = {
  StringFormat: function () {
    if (arguments.length == 0)
      return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
      var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
      str = str.replace(re, arguments[i]);
    }
    return str;
  },
  Log(msg) {
    var switchLog = localStorage.getItem("switchLog");
    if(!switchLog){
      return;
    }
    if (typeof console != undefined) {
      var str = commonUtils.StringFormat("【{0}】{1}", (new Date()).Format("yyyy-MM-dd hh:mm:ss"), msg);
      console.log(str);
    }
  },
  HtmlEncode(str) {
    var s = "";
    if (str.length == 0)
      return "";
    s = str.replace(/&/g, "&amp;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    s = s.replace(/ /g, "&nbsp;");
    s = s.replace(/\'/g, "&#39;");
    s = s.replace(/\"/g, "&quot;");
    return s;
  },
  b64EncodeUnicode(str){
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function(match, p1) {
      return String.fromCharCode('0x' + p1);
    }));
  },
  isEmpty(data){
    return (data == "" || data == undefined || data == null) ? true : false;
  }
}
export default commonUtils;
