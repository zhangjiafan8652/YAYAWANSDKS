package com.yayawan.sdk.utils;


public class UrlUtil {

	
	
	public static final String BASEPASSPORTURL="http://kingoo.com.cn";
	
	public static final String BASEYAYAWAN_HTTP_URL="http://kingoo.com.cn";
	public static final String BASEPASSPORT_HTTPS_URL="http://kingoo.com.cn";
	
	public static final String BASEPAY_HTTPS_URL="http://kingoo.com.cn";
	public static final String BASEPAY_HTTP_URL="http://kingoo.com.cn";
	
	
	public static final String BASEUNION_HTTP_URL="http://kingoo.com.cn";
	
	// 联合数据下单
    public static final String PAY_HANDLER = BASEPASSPORTURL+"/pay_handler";

	
    // 激活
    public static final String ACTIVE = BASEPASSPORTURL+"/api/active";

    // 角色信息
    public static final String ROLEDATA = BASEPASSPORTURL+"/apiv2/role";

    // 登录
    public static final String LOGIN = BASEPASSPORTURL+"/apiv2/login?";

    // 注册
	public static final String REGISTER = BASEPASSPORTURL+"/apiv2/register";

    // 获取找回密码验证码
    public static final String GETPHONENUM = BASEPASSPORTURL+"/api/getuserphone";

    // 检测验证码
    public static final String CHECKCODE = BASEPASSPORTURL+"/api/checkcode";

    // 重设密码
    public static final String RESETPASSWORD = BASEPASSPORTURL+"/apiv2/forget";

    // 完善信息
    public static final String COMPLETEINFO = BASEPASSPORTURL+"/api/addmemberinfo";

    // 获取礼包
    public static final String GET_GIFT = BASEPASSPORTURL+"/api/get_gift";

    // 已获取礼包列表
    public static final String MY_GIFTS = BASEPASSPORTURL+"/api/gifts_view";

    // 获取礼包详细信息
    public static final String GIFT_INFO = BASEPASSPORTURL+"/api/gift_info";

    // 攻略详情
    public static final String STRATEGY_INFO = BASEYAYAWAN_HTTP_URL+"/wap/article/";

   
    // //充值记录
    // public static final String PAYLOG =
    // "http://passport.yayawan.com/api/paylog";
    // 游戏详情
    public static final String GAME_DETAIL = BASEYAYAWAN_HTTP_URL+"/api/game_view";

    // 更多游戏
    public static final String MOREGAMES = BASEYAYAWAN_HTTP_URL+"/api/game_recommend";

    // 修改密码
    public static final String MODIFYPASSWORD = BASEPASSPORTURL+"/apiv2/changepw?";

    // 修改绑定手机
    public static final String CHANGEPHONE = BASEPASSPORTURL+"/api/changephone";

    // 发送绑定手机验证码
    public static final String SENDSMS = BASEPASSPORTURL+"/api/sendsms?";
    
    // 更改手机发送绑定手机验证码
    public static final String CHANGEPHONESENDSMS = BASEPASSPORTURL+"/api/changephone_sms";
    
    // 更改手机发送绑定手机验证码
    public static final String CHANGEPHONES = BASEPASSPORTURL+"/api/changephone";

    // 绑定手机
    public static final String PHONEBIND = BASEPASSPORTURL+"/apiv2/phonebind?";

    // 获取手机注册信息
    public static final String FETCHSMS = BASEPASSPORTURL+"/apiv3/tx_sms";

    // 验证码登录获取验证码
    public static final String GETLOGINCODE = BASEPASSPORTURL+"/apiv2/getcode";

    // 验证码登录
    public static final String SECURITYLOGIN = BASEPASSPORT_HTTPS_URL+"/apiv3/smscode_login";

    // 论坛get
    public static final String GOBBS = BASEPASSPORTURL+"/api/go_bbs";

    // 获取用户信息
    public static final String GETUSERINFO = BASEPASSPORTURL+"/api/userinfo";

    // 充值记录
    public static final String RECHARGELOG = BASEPASSPORTURL+"/apiv2/rechargelog";

    // 消费记录
    public static final String PAYLOG = BASEPASSPORTURL+"/apiv2/paylog";

   
  
    // 绑卡支付发送验证短信
    public static final String BINDSENDSMS = BASEPASSPORTURL+"/apiv/bind_pay_sendsms";

   
    // 反馈信息
    public static final String FAQ = BASEPASSPORTURL+"/faq";

    // 添加反馈
    public static final String FAQADD = BASEPASSPORTURL+"/faq/add";

    // 查询支付结果
    public static final String BILL_STATUS = BASEPASSPORTURL+"/apiv2/bill_status";
   
    // 根据包名获取信息
    public static final String GET_PACKNAME = BASEYAYAWAN_HTTP_URL+"/api/check_app_update";

    // key生成验证码登录url
    public static final String SECRET_KEYLOGIN = BASEPASSPORTURL+"/apiv3/onecode_login";
    
  
    //游戏公告
    public static final String NOTICE = BASEUNION_HTTP_URL+"/game_notice";
    
    
 // 上传头像
 	public static String SETAVATER = BASEPASSPORTURL+"/api/useravater";
    
	// 获取支付方式接口
	public static String getpaytype = BASEUNION_HTTP_URL+"/switch_handler";
	
	// 用户信息接口
		public static String USERINFO_URL = BASEPASSPORTURL+"/api/userinfo";
	
	// 实名认证
	public static String REALAUTH =BASEPASSPORTURL+"/apiv3/idcard";
	
	
	 // 支付
    public static final String PAYMENT = BASEPAY_HTTPS_URL+"/payv2/respond";

    // 丫丫币支付
    public static final String YAYAPAY = BASEPAY_HTTPS_URL+"/payv2/yayapay";
    
    // 绑卡支付
    public static final String DISPOSE = BASEPAY_HTTPS_URL+"/payv2/dispose";
    // 单机 查询支付结果
    public static final String BILL_STATUS_SINGLE = BASEPAY_HTTPS_URL+"/payv2/bill_status";
    
    // 单机支付
    public static final String SINGLE_PAY = BASEPAY_HTTPS_URL+"/payv2/respond";
    
    // 用户余额信息
    public static final String MONEYINFO = BASEPAY_HTTP_URL+"/payv2/moneyinfo";

    // 帮助中心
    public static final String HELP = BASEPAY_HTTP_URL+"/help";

    // 用户余额信息
    public static final String UPDATE = BASEPAY_HTTP_URL+"/update";

    // SDK版本号
    public static final String VERSION = "v0.27";
}
