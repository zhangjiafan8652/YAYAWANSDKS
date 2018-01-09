package com.yayawan.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View.OnFocusChangeListener;


import com.yayawan.proxy.YYWActivityStub;
import com.yayawan.sdk.jfutils.Yayalog;
import com.yayawan.sdk.main.YayaWan;
import com.yayawan.utils.DeviceUtil;
import com.yayawan.utils.Handle;

public class ActivityStubImpl implements YYWActivityStub {

	
	
	public static Activity mactivity;

    @Override
    public void applicationInit(Activity paramActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCreate(Activity paramActivity) {
        // TODO Auto-generated method stub
    	//广点通激活
		GuangdiantongUtils.guangDiantongInit(paramActivity);

		ReYun.init(paramActivity);
    	YayaWan.initSdk(paramActivity);
    	Handle.active_handler(paramActivity);
    }

    @Override
    public void onStop(Activity paramActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume(Activity paramActivity) {
    	
    
    	
        YayaWan.init(paramActivity);
    }

    @Override
    public void onPause(Activity paramActivity) {
        YayaWan.stop(paramActivity);
    }

    @Override
    public void onRestart(Activity paramActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy(Activity paramActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void applicationDestroy(Activity paramActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onActivityResult(Activity paramActivity, int paramInt1,
            int paramInt2, Intent paramIntent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNewIntent(Intent paramIntent) {
        // TODO Auto-generated method stub

    }

	@Override
	public void initSdk(Activity arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 

}
