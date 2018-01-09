package com.yyw.yayaplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class PayUtils {

	private static IWXAPI msgApi;
	private static Intent intent;
	public static void weiXinInit(Activity mactivity,String weixinid){
		 msgApi = WXAPIFactory.createWXAPI(mactivity, weixinid, true);
		if (msgApi!=null) {
			
			boolean registerApp = msgApi.registerApp(weixinid);
			if (registerApp) {
			
			}
		}
	}
	
	/**
	 * 开启微信支付
	 * @param msign       签名
	 * @param mtimestamp  
	 * @param mpackagename
	 * @param mnoncestr
	 * @param mpartnerid
	 * @param mappid
	 * @param mprepayid
	 */
	private static void weiXinpay(String msign,String mtimestamp,String mpackagename,String mnoncestr,String mpartnerid,String mappid,String mprepayid) {
		
		
		String sign =msign;
		String timestamp = mtimestamp;	
		String mpackage =mpackagename;	
		String noncestr = mnoncestr;	
		String partnerid =mpartnerid;	
		String appid =mappid;	
		String prepayid = mprepayid;	
		System.out.println("进来了");
		PayReq req = new PayReq();	
		//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
		req.appId			= appid;
		req.partnerId		= partnerid;
		req.prepayId		=prepayid;
		req.nonceStr		= noncestr;
		req.timeStamp		= timestamp;
		req.packageValue	=mpackage;
		req.sign			= sign;
		req.extData			= "app data"; // optional
		//Toast.makeText(MainActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();		
		// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
		msgApi.sendReq(req);
	}
	
}
