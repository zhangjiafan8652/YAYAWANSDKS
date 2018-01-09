package com.yyw.yayaplugin;


import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yyw.weixinpay.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;



public class MyActivity extends Activity {

	private WebView webView;
	private IWXAPI msgApi;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//初始化微信支付
		initWeixinpay();
		intent = getIntent();
		//开启微信支付
		weiXinpay();
		this.finish();
	}

	private void initWeixinpay() {
		msgApi = WXAPIFactory.createWXAPI(MyActivity.this, "wxf5024e91d99cde0d", true);
		if (msgApi!=null) {
			
			boolean registerApp = msgApi.registerApp("wxf5024e91d99cde0d");
			if (registerApp) {
			
			}
		}
	}
	
	public void ceshi(View view){
		weiXinpay();
		
	}

	private void weiXinpay() {
		Bundle bundleExtra = intent.getBundleExtra("bundle");
		if (bundleExtra==null) {
			return;
		}
		String sign = bundleExtra.getString("sign");
		String timestamp = bundleExtra.getString("timestamp");	
		String mpackage = bundleExtra.getString("package");	
		String noncestr = bundleExtra.getString("noncestr");	
		String partnerid = bundleExtra.getString("partnerid");	
		String appid =bundleExtra.getString("appid");	
		String prepayid = bundleExtra.getString("prepayid");	
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
	
	public void gudingpay(View view){
		
		PayReq req = new PayReq();
		
		//req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
		req.appId			= "wxf5024e91d99cde0d";
		req.partnerId		= "1390481202";
		req.prepayId		="wx201612281833498c7c4f1a6e0882222509";
		req.nonceStr		= "Ml9YYojn8n04MPGc";
		req.timeStamp		= "1482921229";
		req.packageValue	="Sign=WXPay";
		req.sign			= "0B673F51C0E24E0137925B4F582184A0";
		req.extData			= "app data"; // optional
		//Toast.makeText(MainActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
		
		System.out.println("zhifutype:"+req.getType());
		
		// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
		msgApi.sendReq(req);
	
	}
}
