package com.yayawan.impl;

import android.app.Activity;
import android.content.Intent;

import com.yayawan.proxy.YYWActivityStub;
import com.yayawan.sdk.jfutils.Yayalog;
import com.yayawan.sdk.main.YayaWan;

public class ActivityStubImpl implements YYWActivityStub {

    @Override
    public void applicationInit(Activity paramActivity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCreate(Activity paramActivity) {
        // TODO Auto-generated method stub
    	YayaWan.initSdk(paramActivity);
    	//Yayalog.setCanlog(false);
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
