package com.yayawan.sdk.jfpay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.yayawan.sdk.base.AgentApp;
import com.yayawan.sdk.domain.Order;
import com.yayawan.sdk.jfpay.XiaomiPayxml.XiaomiPayListener;
import com.yayawan.sdk.main.YayaWan;
import com.yayawan.utils.DeviceUtil;

public class XiaoMipayActivity extends Activity {

	XiaomiPayxml xiaomiPayxml;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 xiaomiPayxml = new XiaomiPayxml(this);
		//View initViewxml = new XiaomiPayxml(this).initViewxml();
		setContentView(xiaomiPayxml.initViewxml());
		xiaomiPayxml.setPrice(Integer.parseInt(AgentApp.mPayOrder.money/100+""));
		
		String gamename=DeviceUtil.getGameInfo(getApplicationContext(), "gamename");
		String moneyname=DeviceUtil.getGameInfo(getApplicationContext(), "moneyname");
		xiaomiPayxml.setGoodsText(gamename+"-"+moneyname);
		xiaomiPayxml.addXiaomiPayListener(new XiaomiPayListener() {
			
			@Override
			public void onGoToPay(int selectpaytype) {
				// TODO Auto-generated method stub
				System.out.println(selectpaytype);
				if (selectpaytype==xiaomiPayxml.ALIPAY) {
					WeiXinZhiFuBaoPay weiXinZhiFuBaoPay = new WeiXinZhiFuBaoPay(XiaoMipayActivity.this, AgentApp.mPayOrder,WeiXinZhiFuBaoPay.ALIPAY , YayaWan.mPaymentCallback);
					weiXinZhiFuBaoPay.weiXinpay();
				}else if(selectpaytype==xiaomiPayxml.WEIXINPAY){
					WeiXinZhiFuBaoPay weiXinZhiFuBaoPay = new WeiXinZhiFuBaoPay(XiaoMipayActivity.this, AgentApp.mPayOrder,WeiXinZhiFuBaoPay.WEIXINPAY , YayaWan.mPaymentCallback);
					weiXinZhiFuBaoPay.weiXinpay();
				}else {
					//选择了小米支付，就把界面关闭，以后都不会打开了
					WeiXinZhiFuBaoPay.isselectxiaomipay=true;
					Toast.makeText(XiaoMipayActivity.this, "小米钱包初始化完毕，请重新点击商品", Toast.LENGTH_LONG).show();
					finish();
				}
				
				
			}
		});
	}
}
