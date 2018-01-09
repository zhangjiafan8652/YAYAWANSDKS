package com.yayawan.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.yayawan.main.YYWMain;
import com.yayawan.proxy.YYWAnimation;
import com.yayawan.sdk.callback.YayawanStartAnimationCallback;
import com.yayawan.sdk.main.YayaWan;

public class AnimationImpl implements YYWAnimation {

    @Override
    public void anim(final Activity paramActivity) {
    	
    	 YYWMain.mAnimCallBack.onAnimSuccess("success", "");
        // TODO Auto-generated method stub
        //Toast.makeText(paramActivity, "播放动画", Toast.LENGTH_SHORT).show();

    	/*new Handler(Looper.getMainLooper()).post(new Runnable() {

             @Override
             public void run() {
            	
		       

             }

             });*/

    }

}
