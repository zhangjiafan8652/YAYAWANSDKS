package com.yayawan.sdk.jfpay;

import java.math.BigDecimal;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yayawan.sdk.base.AgentApp;
import com.yayawan.sdk.callback.YayaWanPaymentCallback;
import com.yayawan.sdk.domain.Order;
import com.yayawan.sdk.domain.PayResult;
import com.yayawan.sdk.domain.PayResult_jf;
import com.yayawan.sdk.domain.User;
import com.yayawan.sdk.jflogin.BaseLogin_Activity;
import com.yayawan.sdk.jflogin.ViewConstants;
import com.yayawan.sdk.jfutils.Utilsjf;
import com.yayawan.sdk.jfutils.Yayalog;
import com.yayawan.sdk.payment.engine.ObtainData;
import com.yayawan.sdk.utils.ToastUtil;

public class WeiXinZhiFuBaoPay {
	
	public static boolean isselectxiaomipay=false;
	
	private static final int WXPAY_NEWFIRSTRESULT = 35;// x宝通新支付方式
	private static final int NETERROR = 18;
	public Activity mContext;
	public Activity mActivity;
	private YayaWanPaymentCallback mPaymentCallback;
	public static final int ALIPAY=37;
	public static final int WEIXINPAY=36;
	public int mPaytype=1;
	/**
	 * 
	 * @param macti
	 * @param paramOrder
	 * @param paytype
	 * @param paramCallback
	 */
	public WeiXinZhiFuBaoPay(Activity macti,Order paramOrder,int paytype,
			YayaWanPaymentCallback paramCallback) {
		mPaytype=paytype;
		mPaymentCallback=paramCallback;
		AgentApp.mPayOrder=paramOrder;
		mContext = macti;
		mActivity = macti;
		// mContext
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@SuppressLint("Registered")
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case WXPAY_NEWFIRSTRESULT:

				// TODO
				Utilsjf.stopDialog();
				if (mWFirstResult.success == 0) {

					String weixinpayaction = mWFirstResult.action;
					AgentApp.mPayOrder.id = mWFirstResult.params
							.get("sdcustomno");
					// 进行支付
					PullWX(weixinpayaction);

				} else {
					// onError(0);
				}
				break;

			case NETERROR:
				 Utilsjf.stopDialog();
				Toast.makeText(mContext, "网络连接错误,请重新连接", Toast.LENGTH_SHORT)
						.show();
				mActivity.finish();

				break;

			default:
				break;
			}
		}

	};

	/**
	 * 调起微信方法
	 * 
	 * @param pay_str
	 *            调起串
	 */

	private void PullWX(String pay_str) {
		// Yayalog.loger(pay_str);
		if (isWeixinAvilible()) {
			try {
				System.out.println(pay_str);

				Weixinpay_dialog weixinpay_dialog = new Weixinpay_dialog(
						mActivity);
				weixinpay_dialog.dialogShow();
				WebView webView = weixinpay_dialog.getWebview();
				webView.loadUrl(pay_str);
				webView.setWebViewClient(new WebViewClient() {

					@Override
					public boolean shouldOverrideUrlLoading(WebView view,
							String url) {
						Yayalog.loger("重复的url:" + url);

						if (url.startsWith("weixin://wap/pay?")) {
							Intent intent = new Intent();
							intent.setAction(Intent.ACTION_VIEW);
							intent.setData(Uri.parse(url));
							mActivity.startActivity(intent);

							return true;
						} else if (parseScheme(url)) {
							try {

								Intent intent;
								intent = Intent.parseUri(url,
										Intent.URI_INTENT_SCHEME);
								intent.addCategory("android.intent.category.BROWSABLE");
								intent.setComponent(null);
								// intent.setSelector(null);
								mActivity.startActivity(intent);
								//
								return true;
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (url.contains("success=0")) {
							onSuccess(AgentApp.mUser, AgentApp.mPayOrder, 1);
						} else {
							view.loadUrl(url);
						}
						return super.shouldOverrideUrlLoading(view, url);
					}

					@Override
					public void onPageStarted(WebView view, String url,
							Bitmap favicon) {
						// TODO Auto-generated method stub
						// Yayalog.loger("onPageStarted重复的url:"+url);
						super.onPageStarted(view, url, favicon);
					}

					public void onPageFinished(WebView view, String url) {
						// Yayalog.loger("onPageFinished重复的url:"+url);
					};

				});

			} catch (Exception e) {
				System.out.println(e.toString());
				mActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(mActivity.getApplicationContext(),
								"网络出错，请重新支付", Toast.LENGTH_LONG).show();
					}
				});
			}
		} else {
			mActivity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(mActivity.getApplicationContext(), "微信未安装",
							Toast.LENGTH_LONG).show();
				}
			});

		}

	}

	// 是否安装微信
	public boolean isWeixinAvilible() {
		final PackageManager packageManager = mActivity.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mm")) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean parseScheme(String url) {
		System.out.println("parseScheme的url：" + url);

		if (url.contains("platformapi/startApp")
				|| url.contains("platformapi/startapp")) {
			return true;
		} else if ((Build.VERSION.SDK_INT > 19)
				&& (url.contains("platformapi") && url.contains("startapp"))) {
			return true;
		} else {
			return false;
		}
	}

	public void weiXinpay() {
		Utilsjf.safePaydialog(mActivity, "初始化安全支付...");

		AgentApp.mentid = mPaytype;
		WxpaynewKuaipayNow();
	}

	private PayResult mWFirstResult;

	// 微信支付 35新多宝通支付 2.24更新
	private void WxpaynewKuaipayNow() {

		// 进入支付流程
		new Thread() {

			@Override
			public void run() {
				try {
					Yayalog.loger(AgentApp.mPayOrder.toString()
							+ "weixindin++++++++++++++++++");
					mWFirstResult = com.yayawan.sdk.payment.engine.ObtainData
							.wxpayment(mContext, AgentApp.mPayOrder,
									AgentApp.mUser, AgentApp.mPayOrder.paytype);
					// payclickcontrol = false;
					mHandler.sendEmptyMessage(WXPAY_NEWFIRSTRESULT);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(NETERROR);
				}
			}

		}.start();
	}

	public void onSuccess(User paramUser, Order paramOrder, int paramInt) {
		if (mPaymentCallback != null) {
			mPaymentCallback.onSuccess(paramUser, paramOrder, paramInt);
		}
		mPaymentCallback = null;
		mActivity.finish();
	}

}
