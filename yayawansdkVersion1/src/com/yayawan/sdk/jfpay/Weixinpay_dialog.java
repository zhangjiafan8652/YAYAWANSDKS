package com.yayawan.sdk.jfpay;

import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera.ErrorCallback;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView.FindListener;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.jxutils.HttpUtils;
import com.lidroid.jxutils.exception.HttpException;
import com.lidroid.jxutils.http.RequestParams;
import com.lidroid.jxutils.http.ResponseInfo;
import com.lidroid.jxutils.http.callback.RequestCallBack;
import com.lidroid.jxutils.http.client.HttpRequest.HttpMethod;
import com.yayawan.sdk.account.dao.UserDao;
import com.yayawan.sdk.account.engine.ObtainData;
import com.yayawan.sdk.base.AgentApp;
import com.yayawan.sdk.domain.Result;
import com.yayawan.sdk.domain.User;
import com.yayawan.sdk.jflogin.ViewConstants;
import com.yayawan.sdk.jfutils.Basedialogview;
import com.yayawan.sdk.jfutils.Utilsjf;
import com.yayawan.sdk.jfutils.Yayalog;
import com.yayawan.sdk.jfxml.GetAssetsutils;
import com.yayawan.sdk.jfxml.MachineFactory;
import com.yayawan.sdk.main.YayaWan;
import com.yayawan.sdk.receiver.AuthNumReceiver;
import com.yayawan.sdk.receiver.AuthNumReceiver.MessageListener;
import com.yayawan.sdk.utils.CodeCountDown;
import com.yayawan.sdk.utils.DeviceUtil;
import com.yayawan.sdk.utils.DialogUtil;

/**
 * 支付宝微信网页支付
 * 
 * @author zjf
 * 
 */
public class Weixinpay_dialog extends Basedialogview {

	private LinearLayout ll_mPre;
	private ImageButton iv_mPre;
	private EditText et_mPhone;
	private Button bt_mGetsecurity;
	private EditText et_mSecurity;

	private Button bt_mOk;
	private String mUserName;
	private String mCode;

	protected static final int MODIFYPASSRESULT = 4;
	private boolean flag;
	private CodeCountDown mCodeCountDown;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Utilsjf.stopDialog();
			switch (msg.what) {
			case MODIFYPASSRESULT:

				break;

			default:
				break;
			}
		}

	};

	private AuthNumReceiver mAuthNumReceiver;
	private EditText mOldPassword;
	private EditText mNewPassword;

	public WebView getWebview() {
		return webview;
	}

	public void setWebview(WebView webview) {
		this.webview = webview;
	}

	private TextView tv_fogetpassword;

	public Weixinpay_dialog(Activity activity) {
		super(activity);
	}

	@Override
	public void createDialog(Activity mActivity) {

		onStart();

		dialog = new Dialog(mActivity);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		int ho_height = 600;
		int ho_with = 750;
		int po_height = 650;
		int po_with = 700;

		int height = 0;
		int with = 0;
		// 设置横竖屏
		String orientation = DeviceUtil.getOrientation(mContext);
		if (orientation == "") {

		} else if ("landscape".equals(orientation)) {
			height = ho_height;
			with = ho_with;
		} else if ("portrait".equals(orientation)) {
			height = po_height;
			with = po_with;
		}

		baselin = new LinearLayout(mActivity);
		baselin.setOrientation(LinearLayout.VERTICAL);
		MachineFactory machineFactory = new MachineFactory(mActivity);
		machineFactory.MachineView(baselin, with, height, "LinearLayout");
		baselin.setBackgroundColor(Color.TRANSPARENT);
		baselin.setGravity(Gravity.CENTER_VERTICAL);

		// 过度中间层
		LinearLayout ll_content = new LinearLayout(mActivity);
		machineFactory.MachineView(ll_content, with, height, "LinearLayout", 2,
				25);
		ll_content.setBackgroundColor(Color.WHITE);
		ll_content.setGravity(Gravity.CENTER_HORIZONTAL);
		ll_content.setOrientation(LinearLayout.VERTICAL);

		// 标题栏
		RelativeLayout rl_title = new RelativeLayout(mActivity);
		machineFactory.MachineView(rl_title, MATCH_PARENT, 96, mLinearLayout);
		rl_title.setBackgroundColor(Color.parseColor("#999999"));

		ll_mPre = new LinearLayout(mActivity);
		machineFactory.MachineView(ll_mPre, 96, MATCH_PARENT, 0,
				mRelativeLayout, 0, 0, 0, 0, RelativeLayout.CENTER_VERTICAL);
		ll_mPre.setGravity(Gravity_CENTER);
		ll_mPre.setClickable(true);
		// 返回上一层的图片
		iv_mPre = new ImageButton(mActivity);
		machineFactory.MachineView(iv_mPre, 40, 40, 0, mLinearLayout, 0, 0, 0,
				0, RelativeLayout.CENTER_VERTICAL);
		iv_mPre.setClickable(false);

		iv_mPre.setBackgroundDrawable(GetAssetsutils.getDrawableFromAssetsFile(
				"yaya_pre.png", mActivity));
		ll_mPre.addView(iv_mPre);
		// 设置点击事件.点击窗口消失
		ll_mPre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// 注册textview
		TextView tv_zhuce = new TextView(mActivity);
		machineFactory.MachineTextView(tv_zhuce, MATCH_PARENT, MATCH_PARENT, 0,
				"正在支付", 38, mLinearLayout, 0, 0, 0, 0);
		tv_zhuce.setTextColor(Color.WHITE);
		tv_zhuce.setGravity(Gravity_CENTER);

		// TODO
		rl_title.addView(ll_mPre);
		rl_title.addView(tv_zhuce);

		// 中间内容层
		LinearLayout ll_content1 = new LinearLayout(mActivity);
		ll_content1 = (LinearLayout) machineFactory.MachineView(ll_content1,
				660, MATCH_PARENT, 0, mLinearLayout, 0, 40, 0, 0,
				LinearLayout.VERTICAL);
		ll_content1.setOrientation(LinearLayout.VERTICAL);

		tv_fogetpassword = new TextView(mActivity);
		machineFactory.MachineTextView(tv_fogetpassword, WRAP_CONTENT,
				WRAP_CONTENT, 0, "必须填入真实姓名和身份证号，认证防沉迷", 22, mRelativeLayout, 0,
				0, 20, 0, RelativeLayout.ALIGN_PARENT_RIGHT);
		tv_fogetpassword.setTextColor(Color.RED);
		ll_content1.addView(tv_fogetpassword);

		// 手机号码输入行
		LinearLayout ll_phone = new LinearLayout(mActivity);
		ll_phone = (LinearLayout) machineFactory.MachineView(ll_phone,
				MATCH_PARENT, 96, mLinearLayout);

		// 手机号码输入框
		et_mPhone = new EditText(mActivity);
		machineFactory.MachineEditText(et_mPhone, MATCH_PARENT, MATCH_PARENT,
				0, "请输入姓名", 32, mLinearLayout, 0, 0, 0, 0);
		et_mPhone
				.setBackgroundDrawable(GetAssetsutils
						.get9DrawableFromAssetsFile("yaya_biankuang2.9.png",
								mActivity));
		et_mPhone.setPadding(machSize(20), 0, 0, 0);

		// TODO
		ll_phone.addView(et_mPhone);

		// 验证码输入框
		et_mSecurity = new EditText(mActivity);
		machineFactory.MachineEditText(et_mSecurity, MATCH_PARENT, 96, 0,
				"请输入身份证号", 32, mLinearLayout, 0, 20, 0, 0);
		et_mSecurity
				.setBackgroundDrawable(GetAssetsutils
						.get9DrawableFromAssetsFile("yaya_biankuang2.9.png",
								mActivity));
		et_mSecurity.setPadding(machSize(20), 0, 0, 0);

		// 确定按钮
		bt_mOk = new Button(mActivity);
		machineFactory.MachineButton(bt_mOk, MATCH_PARENT, 96, 0, "确认", 36,
				mLinearLayout, 0, 30, 0, 0);
		bt_mOk.setTextColor(Color.WHITE);
		bt_mOk.setBackgroundDrawable(GetAssetsutils.crSelectordraw(
				"yaya_yellowbutton.9.png", "yaya_yellowbutton1.9.png",
				mActivity));
		bt_mOk.setGravity(Gravity_CENTER);

		// TODO
		ll_content1.addView(ll_phone);
		ll_content1.addView(et_mSecurity);

		ll_content1.addView(bt_mOk);

		ll_content.addView(rl_title);

		
		
		
		webview = new WebView(mActivity);
		machineFactory.MachineView(webview, with, height, "LinearLayout", 2,
				25);
		ll_content.addView(webview);
		//baselin.addView(webview);

		dialog.setContentView(ll_content);

		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);

		lp.alpha = 0.9f; // 透明度

		lp.dimAmount = 0.5f; // 设置背景色对比度
		dialogWindow.setAttributes(lp);
		dialog.setCanceledOnTouchOutside(false);

		android.widget.RelativeLayout.LayoutParams ap2 = new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);

		dialog.setCanceledOnTouchOutside(true);
		dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());

		initlogic();
	}

	private String name;
	private String authnumber;

	private Result mResult;

	private void initlogic() {

		
		webview.getSettings().setPluginState(PluginState.ON);
		webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webview.getSettings().setSupportMultipleWindows(true);
		webview.getSettings().setSupportZoom(true);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.getSettings().setAllowFileAccess(true);
		webview.getSettings().setJavaScriptEnabled(true);// 设置使用够执行JS脚本
		webview.getSettings().setDomStorageEnabled(true);
		webview.getSettings().setDomStorageEnabled(true);
	
		//webview.loadUrl("http://www.baidu.com");
		
	}

	
	
	  /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{17}\\w$)|(^\\d{14}\\w$)";
	private WebView webview;
    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
	public void onStart() {
	

	}

}
