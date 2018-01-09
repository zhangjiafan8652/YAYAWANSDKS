package com.yayawan.proxy;

import com.yayawan.implyy.YYcontants;
import com.yayawan.utils.DeviceUtil;

import android.app.Application;

public class YYWApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("YYWApplication:applictaon");
		YYcontants.ISDEBUG = DeviceUtil.isDebug(getApplicationContext());
		GameApitest.getGameApitestInstants().sendTest(getPackageName()+"Application.oncreate");
	}
}
