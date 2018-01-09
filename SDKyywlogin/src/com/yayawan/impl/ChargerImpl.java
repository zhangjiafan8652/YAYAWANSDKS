package com.yayawan.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.yayawan.callback.YYWPayCallBack;
import com.yayawan.domain.YYWOrder;
import com.yayawan.domain.YYWUser;
import com.yayawan.main.YYWMain;
import com.yayawan.proxy.YYWCharger;
import com.yayawan.sdk.callback.YayaWanPaymentCallback;
import com.yayawan.sdk.domain.Order;
import com.yayawan.sdk.domain.User;
import com.yayawan.sdk.main.YayaWan;

public class ChargerImpl implements YYWCharger {

    @Override
    public void charge(Activity paramActivity, YYWOrder order,
            YYWPayCallBack callback) {
    }

    @Override
    public void pay(final Activity paramActivity, final YYWOrder order, YYWPayCallBack callback) {


    	new Handler(Looper.getMainLooper()).post(new Runnable() {

            @Override
            public void run() {

				    	return;

            }

    	});

    }

}
