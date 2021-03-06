package com.yayawan.impl;

import com.blankj.utilcode.utils.SPUtils;

import com.yayawan.callback.YYWUserCallBack;
import com.yayawan.domain.YYWUser;
import com.yayawan.main.YYWMain;
import com.yayawan.proxy.YYWLoginer;
import com.yayawan.sdk.callback.YayaWanUserCallback;
import com.yayawan.sdk.domain.User;
import com.yayawan.sdk.jfutils.Sputils;
import com.yayawan.sdk.main.YayaWan;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class LoginImpl implements YYWLoginer {

    @Override
    public void login(final Activity paramActivity, YYWUserCallBack userCallBack, String paramString) {

    	new Handler(Looper.getMainLooper()).post(new Runnable() {

            @Override
            public void run() {


			        YayaWan.login(paramActivity, new YayaWanUserCallback() {

			            @Override
			            public void onSuccess(User user, int arg1) {
			                if (YYWMain.mUserCallBack != null) {

			                	
			                	
			                    YYWUser yywUser = new YYWUser();

			                    yywUser.uid = user.uid + "";
			                    yywUser.icon = user.icon;
			                    yywUser.body = user.body;
			                    yywUser.lasttime = user.lasttime;
			                    yywUser.money = user.money;
			                    yywUser.nick = user.nick;
			                    yywUser.password = user.password;
			                    yywUser.phoneActive = user.phoneActive;
			                    yywUser.success = user.success;
			                    yywUser.token = user.token;
			                    yywUser.userName = user.userName;
			                    YYWMain.mUser=yywUser;
			                    YYWMain.mUserCallBack.onLoginSuccess(yywUser, "success");
			                    
			                    //热云统计
			                    ReYun.firstLogin(paramActivity, yywUser.uid);
			                  	ReYun.Login(paramActivity, yywUser.uid);
			                   
			                   
			                }
			            }

			            @Override
			            public void onLogout() {
			            	YayaWan.stop(paramActivity);
			                if (YYWMain.mUserCallBack != null) {
			                    YYWMain.mUserCallBack.onLogout("logout");
			                }
			                
			                
			            }

			            @Override
			            public void onError(int arg0) {
			            	
			                if (YYWMain.mUserCallBack != null) {
			                    YYWMain.mUserCallBack.onLoginFailed("failed", "");
			                }
			            }

			            @Override
			            public void onCancel() {
			                // TODO Auto-generated method stub
			            	if (YYWMain.mUserCallBack != null) {
			                    YYWMain.mUserCallBack.onCancel();
			                }
			            }
			        });

            	}

    	});

    }

    @Override
    public void relogin(Activity paramActivity, YYWUserCallBack userCallBack,
            String paramString) {

    }

}
