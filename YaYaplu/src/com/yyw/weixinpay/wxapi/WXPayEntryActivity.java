package com.yyw.weixinpay.wxapi;








import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
	
	private static String WEIXINPAYRESULT="WEIXINPAYRESULT";
	private static String BROADCAST_ACTION="com.yyw.weixinpay";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        api = WXAPIFactory.createWXAPI(this, "wxf5024e91d99cde0d");
       api.handleIntent(getIntent(), this);
       
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		  setIntent(intent);
	        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		 if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			 
			System.out.println("WXPayEntryActivity支付返回值"+resp.errCode);
			 Intent intent = new Intent();
		        intent.setAction(BROADCAST_ACTION);
		        intent.putExtra(WEIXINPAYRESULT, resp.errCode);
		        sendBroadcast(intent);
		        this.finish();
	        }
	}
}