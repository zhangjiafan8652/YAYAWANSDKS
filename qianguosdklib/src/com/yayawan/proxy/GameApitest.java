package com.yayawan.proxy;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.widget.Toast;

import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.SDCardUtils;
import com.yayawan.implyy.YYcontants;
import com.yayawan.main.YYWMain;
import com.yayawan.sdk.account.db.SDBHelper;
import com.yayawan.sdk.jflogin.ViewConstants;
import com.yayawan.sdk.jfutils.Yayalog;
import com.yayawan.sdk.utils.DeviceUtil;

public class GameApitest {

	public static GameApitest mGameapitest;
	public static Activity mActivity;

	public static Yibuhttputils yibuhttputils;

	public static String TestFilePath = "test";

	public static String DB_DIRPATH = Environment.getExternalStorageDirectory()
			.getPath()
			+ File.separator
			+ "qianguoUserData"
			+ File.separator
			+ TestFilePath + File.separator + "test.log";

	// DB_DIR

	public static String DB_DIR = Environment.getExternalStorageDirectory()
			.getPath()
			+ File.separator
			+ "qianguoUserData"
			+ File.separator
			+ TestFilePath;
	static {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			DB_DIR = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + "qianguoUserData" + File.separator
					+ TestFilePath;
		} else {
			DB_DIR = Environment.getRootDirectory().getPath() + File.separator
					+ "qianguoUserData" + File.separator + TestFilePath;
		}

		File dbFolder = new File(DB_DIR);
		if (!dbFolder.exists()) {
			dbFolder.mkdirs();
		}
	}

	public static GameApitest getGameApitestInstants(Activity mactivity) {
		if (mGameapitest != null) {
			mActivity = mactivity;

			return mGameapitest;
		} else {
			mActivity = mactivity;

			mGameapitest = new GameApitest();

			return mGameapitest;
		}
	}

	public static GameApitest getGameApitestInstants() {
		if (mGameapitest != null) {

			return mGameapitest;
		} else {

			mGameapitest = new GameApitest();

			return mGameapitest;
		}
	}

	String temp="";
	/**
	 * 
	 * @param type
	 */
	public void sendTest(String type) {

		if (YYcontants.ISDEBUG) {
			try {
				File file = new File(DB_DIRPATH);
				if (!FileUtils.isFileExists(DB_DIRPATH)) {
					file.createNewFile();
				}
				if (type.contains("Application")) {
					file.delete();
					file.createNewFile();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (temp.contains(type)) {
				return;
			}
			temp=temp+"-"+type;
			FileUtils.writeFileFromString(DB_DIRPATH, type + "\r\n", true);
		}

	}

	public void sendTest(String type, String value) {

		if (YYcontants.ISDEBUG) {
			if (!FileUtils.isFileExists(DB_DIRPATH)) {
				File file = new File(DB_DIRPATH);
			}
			FileUtils.writeFileFromString(DB_DIRPATH, type + ":" + value
					+ "\r\n", true);
		}

	}

}
