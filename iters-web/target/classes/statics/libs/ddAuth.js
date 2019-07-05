var ddAuth = {//钉钉授权登录
  debug: 0,
  config: {
    // 必填，微应用ID
    agentId: "",
    // 必填，企业ID
    corpId: "",
    // 必填，生成签名的时间戳
    timeStamp: "",
    // 必填，生成签名的随机串
    nonceStr: "",
    // 必填，签名
    signature: "",
    // 选填，0表示微应用的jsapi，1表示服务窗的jsapi，不填默认为0。该参数从dingtalk.js的0.8.3版本开始支持
    type: 0,
    // 必填，需要使用的jsapi列表，注意：不要带dd。
    jsApiList: [
      "runtime.permission.requestAuthCode",
      "device.audio.startRecord",
      "device.audio.stopRecord",
      "device.audio.onRecordEnd",
      "device.audio.play",
      "device.audio.stop",
      "device.audio.onPlayEnd",
      "device.audio.download",
      "biz.user.get"
    ]
  },
  getData:function(key,value){
    key=key||"wxCode";
    if(this.config.type==1){
       return window.sessionStorage&&window.sessionStorage[value?"setItem":"getItem"](key,value);
    }else if(this.config.type==2){
       return window.localStorage&&window.localStorage[value?"setItem":"getItem"](key,value);
    }
  },
  getQueryString:function(name) { //搜索URL某个字段的值
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURIComponent(r[2]);
    return "";
  },
  is: ["android", "ios", "pc"], //dd.pc
  isDD: function () { //是否钉钉
    return (typeof dd !="undefined") && dd && dd.version;
  },
  getConfig:function(fn) { //获取配置
    var self = this;
    if (this.config.corpId){
      return fn.call(self);    
    }

    $.ajax({
      type: "get",
      url: "/admin/dingTalk/signature",
      dataType: "json",
      success: function (res) {
        self.debug && alert("config:"+JSON.stringify(res));
        self.configSuccess(res.data,fn);
      },
      error:function(){
        self.debug&&alert("获取配置信息失败")
        self.errFn("getConfig");
      }
    });

    // axios.get("/admin/dingTalk/signature").then(res => {
    //   self.configSuccess(res,fn);
    // }).catch(function(error){
    //   self.debug&&alert("获取配置信息失败")
    // })
  },
  configSuccess:function(data,fn){
    var self=this;  
    for(var i  in self.config){//赋值
      data[i]&&!self.config[i]&&(self.config[i]=data[i]);
    }
    typeof fn=="function"&&fn.call(self);
  },
  openLink:function(url){
    //var url=location.origin + "/login.html?url=dingtalkpc-code&code=" + result.code; //要打开链接的地址
    dd.biz.util.openLink({
        url: url,
        onSuccess: function (result) {
          /**/
        },
        onFail: function () {}
    })
  },
  getCode:function(fn){
    var self=this;
    var code=this.getQueryString("code");
    if(code){
        self.debug && alert("codeUrl:"+JSON.stringify(code));
        return typeof fn=="function"&&fn.call(self,code);//res.code
    }  
    dd.runtime.permission.requestAuthCode({
        corpId: this.config.corpId,
        onSuccess: function(res){
            self.debug && alert("code:"+JSON.stringify(res));
            typeof fn=="function"&&fn.call(self,res.code);//res.code
        },
        onFail:function(error){self.debug&&alert("ddCode:"+JSON.stringify(error));self.errFn("getCode")}
    });
  },
  getUserInfo:function(fn){
    var self=this;
    dd.biz.user.get({
        // 可选参数，如果不传则使用用户当前企业的corpId。
        corpId: this.config.corpId,
        onSuccess: function(info){
          self.debug && alert("info:"+JSON.stringify(info));
          typeof fn=="function"&&fn.call(self,info);//info.emplId
        },
        onFail:function(error){self.debug&&alert("ddUserInfo:"+JSON.stringify(error));self.errFn("getUserInfo")}
    });
  },
  init:function(successFn, errFn) { //钉钉授权登录
    //this.$toast('搜索关键字不能为空')
    if(!this.isDD()){return}
    var self=this;
    this.successFn = successFn;
    this.errFn = errFn||function(error){self.debug&&alert("error:"+JSON.stringify(error));};
    this.getConfig(function () {
        dd.config(this.config);
        dd.error(this.errFn);
        dd.ready(function(){
            self.debug&&alert("realConfig:"+JSON.stringify(self.config));
            self.successFn();
        });
    });
  },
  demo:function(){
    var self=this;
    if(ddAuth.isDD()){//如果是钉钉
        ddAuth.init(function(){
            this.getUserInfo(function(info){
                self.username=info.emplId;
                self.password="zoRaw3kTP";
                self.login();
            });
        },function(error){
            self.show=1;
        });
    }else{
        this.show=1;
    }
  }
}
