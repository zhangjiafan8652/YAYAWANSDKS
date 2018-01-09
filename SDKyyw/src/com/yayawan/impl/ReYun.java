package com.yayawan.impl;

import android.app.Activity;


import com.yayawan.sdk.jfutils.Sputils;
import com.yayawan.utils.DeviceUtil;

public class ReYun {

	// 热云初始化
	public static void init(Activity mactivity) {

		/*if (!DeviceUtil.getGameInfo(mactivity, "reyun").equals("yes")) {
			return;
		}
		System.out.println("热云初始化");
		ReYunTrack.initWithKeyAndChannelId(mactivity,
				DeviceUtil.getGameInfo(mactivity, "reyunappkey"),
				DeviceUtil.getGameInfo(mactivity, "reyunchannelid"));*/
	}

	// 热云首次登陆
	public static void firstLogin(Activity mactivity, String uid) {

		/*if (!DeviceUtil.getGameInfo(mactivity, "reyun").equals("yes")) {
			return;
		}
		Sputils sputils = new Sputils();
		int sPint = sputils.getSPint("rongyunfirtlogin", 0, mactivity);
		if (sPint == 0) {
			ReYunTrack.setRegisterWithAccountID(uid);
			sputils.putSPint("rongyunfirtlogin", 1, mactivity);
		}*/

		// ReYunTrack.setLoginSuccessBusiness（uid);

	}

	// 热云登陆
	public static void Login(Activity mactivity, String uid) {

	/*	if (!DeviceUtil.getGameInfo(mactivity, "reyun").equals("yes")) {
			return;
		}

		ReYunTrack.setLoginSuccessBusiness(uid);*/

	}

	// 热云发起充值
	public static void wantPay(Activity mactivity, String orderid, String uid,
			long money) {

		/*if (!DeviceUtil.getGameInfo(mactivity, "reyun").equals("yes")) {
			return;
		}

		ReYunTrack.setPaymentStart(orderid, "weixinpay", "CNY", money);
*/
	}

	// 热云发起充值
	public static void Payed(Activity mactivity, String orderid, String uid,
			long money) {
/*
		if (!DeviceUtil.getGameInfo(mactivity, "reyun").equals("yes")) {
			return;
		}

		ReYunTrack.setPayment(orderid, "weixinpay", "CNY", money);
*/
	}
	
	
	// 热云退出
		public static void reyunExit(Activity mactivity) {

		/*	if (!DeviceUtil.getGameInfo(mactivity, "reyun").equals("yes")) {
				return;
			}

			ReYunTrack.exitSdk ();*/

		}

}
