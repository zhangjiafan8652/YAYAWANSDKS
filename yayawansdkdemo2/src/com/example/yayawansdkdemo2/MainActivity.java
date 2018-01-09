package com.example.yayawansdkdemo2;

import java.io.File;

import com.yayawan.sdk.callback.YayaWanUpdateCallback;
import com.yayawan.sdk.callback.YayaWanUserCallback;
import com.yayawan.sdk.domain.User;
import com.yayawan.sdk.main.YayaWan;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private Activity mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActivity = this;
		Button but=(Button) findViewById(R.id.login);
		but.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 System.out.println("在搞了");
				YayaWan.login1(mActivity, new YayaWanUserCallback() {

		            public void onSuccess(User paramUser, int paramInt) {
		                System.out.println("登录成功");
		                System.out.println("用户信息" + paramUser);
		                /**
		                 * 检测更新
		                 */
		                YayaWan.update(MainActivity.this, new YayaWanUpdateCallback() {

		                    private static final long serialVersionUID = 1L;

		                    @Override
		                    public void onSuccess(File file) {
		                        System.out.println("下载成功" + file);
		                    }

		                    @Override
		                    public void onStart() {
		                        System.out.println("开始下载");
		                    }

		                    @Override
		                    public void onCancel() {
		                        System.out.println("退出下载");
		                    }
		                });
		            }

		            public void onError(int paramInt) {
		                System.out.println("登录失败");
		            }

		            public void onCancel() {
		                System.out.println("登录退出");
		            }

		            public void onLogout() {
		                System.out.println("注销");
		                YayaWan.login1(MainActivity.this, new YayaWanUserCallback() {

		                    @Override
		                    public void onSuccess(User paramUser, int paramInt) {
		                        // TODO Auto-generated method stub

		                    }

		                    @Override
		                    public void onError(int paramInt) {
		                        // TODO Auto-generated method stub

		                    }

		                    @Override
		                    public void onCancel() {
		                        // TODO Auto-generated method stub

		                    }

		                    @Override
		                    public void onLogout() {
		                        // TODO Auto-generated method stub

		                    }
		                });
		            }
		        });
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
