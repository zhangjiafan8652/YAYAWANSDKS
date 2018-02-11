package com.jiafan.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.demo.admin.AdminService;
import com.demo.common.model.Motherpackagelist;
import com.demo.common.model.Packing;
import com.jfinal.kit.PropKit;
import com.sun.tools.classfile.Annotation.element_value;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PackingRunable implements Runnable {

	static AdminService service = new AdminService();
	public static boolean isenablepacking = false;
	/*private static String keystroepath = "D:\\repackapkapp\\yayawan.keystore";
	private static String key_password =  "43597441";
	private static String key_bieming = "wjy";
	private static String jdkBinPath = "D:\\jdk7\\jdk\\bin";
	private static String basepath = "D:\\";*/
	
	private static String keystroepath =  PropKit.get("keystroepath");;
	private static String key_password =  PropKit.get("key_password");
	private static String key_bieming =  PropKit.get("key_bieming");
	private static String jdkBinPath = PropKit.get("jdkBinPath");
	private static String basepath =PropKit.get("basepath");
	
	private final static Logger logger = Logger.getLogger("");
	private static String apppath = "";
	private static File mfile;

	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("开始执行打包，打包参数："+key_bieming+key_password);
		while (isenablepacking) {
			ArrayList<Packing> listAllpacking = service.listAllpacking();
			System.out.println("开始打包，还有未打包个数："+listAllpacking.size());
			if (listAllpacking.size() > 0) {
				Packing packing = listAllpacking.get(0);
				try {
					topackingone(packing);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textLog("打包成功。。。");
				service.deletepackinglistitem(packing.getPackinglistid()+"");
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(1000*60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}

	public static void topackingone(Packing packing) throws Exception {
		try {
			textLog("正在打包参数。。。" + packing.toString());
			mfile = jieYaapp(basepath + packing.getGamemotherfilepath());
			textLog("解压成功。。。");
			if (packing.getBelongcompany() == 1) {
				xiuGaiBao3(packing.getGamemotherpackagename(),
						packing.getGamepackagename(),
						packing.getGamesocurceid(),
						packing.getGameplatformid(), "",
						packing.getGamemothername(), packing.getGamename(),
						basepath + packing.getGameiconpath(), apppath);

			} else {
				xiuGaiBao3(packing.getGamemotherpackagename(),
						packing.getGamepackagename(),
						"", "",
						packing.getGameplatformid(),
						packing.getGamemothername(), packing.getGamename(),
						basepath + packing.getGameiconpath(), apppath);

			}
			daBao(apppath, mfile);
			qianMing(apppath, basepath + packing.getGameoutputpath(),
					packing.getGamefilename(), keystroepath, key_password,
					key_bieming, jdkBinPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			textLog("解压失败。。。" + e.toString());
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			textLog("解压失败。。。" + e.toString());
			e.printStackTrace();
		}
	}

	// 反编译apk
	/**
	 * 
	 * @param motherapkpath
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static File jieYaapp(String motherapkpath) throws IOException,
			InterruptedException {
		Process process;
		// String motherapkpath="D:\\repackapk\\wzzb.apk";
		String dir = motherapkpath.substring(0,
				motherapkpath.lastIndexOf("\\") + 1);
		System.out.println(dir);
		String filename = motherapkpath.substring(
				motherapkpath.lastIndexOf("\\") + 1, motherapkpath.length());
		apppath = dir;
		System.out.println(filename);
		File file = new File(dir);
		// File file1 =new File(appPath+"app");
		String cmdcommd = "cmd /c  apktool d -f " + dir + filename + " -o "
				+ dir + "app";
		textLog(cmdcommd);
		process = Runtime.getRuntime().exec(cmdcommd, null, file);
		process.getErrorStream();

		// 输出错误信息
		Log(process.getErrorStream(), 1);
		Log(process.getInputStream(), 0);
		if (process.waitFor() != 0)
			textLog("waitFor解压失败。。。");

		return file;
	}
	
	// 反编译apk
	/**
	 * 
	 * @param motherapkpath
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Motherpackagelist getMontherappinfo(String motherapkpath,boolean hadsouceid) throws IOException,
			InterruptedException {
		Process process;
		// String motherapkpath="D:\\repackapk\\wzzb.apk";
		String dir = motherapkpath.substring(0,
				motherapkpath.lastIndexOf("\\") + 1);
		System.out.println(dir);
		String filename = motherapkpath.substring(
				motherapkpath.lastIndexOf("\\") + 1, motherapkpath.length());
		String filedirname=filename.substring(0,filename.lastIndexOf("."));
		System.out.println(filedirname);
		String apppath = dir;
		System.out.println(filename);
		File file = new File(dir);
		// File file1 =new File(appPath+"app");
		String cmdcommd = "cmd /c  apktool d -f " + dir + filename + " -o "
				+ dir + filedirname;
		
		textLog(cmdcommd);
		process = Runtime.getRuntime().exec(cmdcommd, null, file);
		process.getErrorStream();

		// 输出错误信息
		Log(process.getErrorStream(), 1);
		Log(process.getInputStream(), 0);
		if (process.waitFor() != 0)
			textLog("waitFor解压失败。。。");

		
		Motherpackagelist motherpackagelist = new Motherpackagelist();
		
		
		SAXReader saxReader = new SAXReader();
		

		try {
			// textLog("文件路径"+delpath3);
			Document document = saxReader.read(new File(dir + filedirname));
			Element rootElement = document.getRootElement();
			Attribute rootattribute = rootElement.attribute("package");
			// 获取到包名
			String packagename = rootattribute.getValue();
			motherpackagelist.setMothergamepackagename(packagename);
			// textLog(packagename);
			Element application = rootElement.element("application");

			

			List<Element> elements = application.elements("meta-data");
			// textLog(""+elements.size());
			for (int i = 0; i < elements.size(); i++) {

				String an_name = elements.get(i).attributeValue("name");
				if (an_name.equals("yayawan_source_id")) {

					String str_sourceid=elements.get(i).attributeValue("value");

				}

			}

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return file;
		return motherpackagelist;
	}

	/**
	 * 
	 * @param oldpagename
	 * @param newpagename
	 * @param yayawan_source_id
	 * @param oldgamename
	 * @param newgamename
	 * @param iconpath
	 * @param appPath
	 * @throws Exception 
	 */
	private static void xiuGaiBao3(String oldpagename, String newpagename,
			String yayawan_source_id, String yayaplatgameid,
			String qianguoplatgameid, String oldgamename, String newgamename,
			String iconpath, String appPath) throws Exception {
		Process process;

		// Fileutils.copyFolder(appPath+"app",appPath+"temp_app");

		// 修改包名
		textLog("在修改包名：" + oldpagename + "  " + newpagename);
		rePagename(appPath + "app\\AndroidManifest.xml", oldpagename,
				newpagename);

		// 修改yayawan_source_id
		reValues(appPath + "app\\AndroidManifest.xml", "yayawan_source_id",
				yayawan_source_id);
		// 修改yayawangameid
		reValues(appPath + "app\\AndroidManifest.xml", "yayawan_game_id",
				yayaplatgameid);
		// 修改千果appid
		reValues(appPath + "app\\AndroidManifest.xml", "qianguo_app_id",
				qianguoplatgameid);

		// reValues(appPath+"app\\AndroidManifest.xml",readConfig.valuesname7,readConfig.value7);
		// 修改游戏名
		reAppname(appPath + "app\\res\\values\\strings.xml", oldgamename,
				newgamename);
		String iconrespath ="";
		if (iconpath.endsWith("zip")) {
			Fileutils.unzip(iconpath, iconpath.replace(".zip", ""));
			System.out.println("newicon=========" + iconpath.replace(".zip", "")
					+ "\\res");
			 iconrespath =iconpath.replace(".zip", "") + "\\res";
		}else {
			 iconrespath = ImageHelper.createiconzip(iconpath);
		}
		
		
		
		
		// 修改icon
		reIcon(appPath + "app\\AndroidManifest.xml",
				iconrespath, appPath + "app\\res");

	}

	private static void daBao(String appPath, File file) throws IOException,
			InterruptedException {
		Process process;
		textLog("打包的命令：" + "cmd.exe /c apktool b -f " + appPath + "app -o "
				+ appPath + "app.apk");
		// System.out.println("cmd.exe /c apktool b -f " + appPath + "app -o "
		// + appPath + "app.apk");
		process = Runtime.getRuntime().exec(
				"cmd.exe /c apktool b -f " + appPath + "app -o " + appPath
						+ "app.apk", null, file);
		// System.out.println();
		Log(process.getErrorStream(), 1);
		Log(process.getInputStream(), 0);
		if (process.waitFor() != 0)
			textLog("打包失败。。。");
	}

	private static void qianMing(String appPath, String outputpath,
			String outputapkname, String keystroepath, String key_password,
			String key_bieming, String jdkBinPath) throws IOException,
			InterruptedException {
		Process process;
		String sign_cmd = "cmd.exe /c jarsigner -keystore " + keystroepath
				+ " -storepass " + key_password + " -keypass " + key_password
				+ " -signedjar " + outputpath + "\\" + outputapkname + ".apk "
				+ appPath + "app.apk " + key_bieming;
		File bin = new File(jdkBinPath);

		textLog(sign_cmd);
		process = Runtime.getRuntime().exec(sign_cmd, null, bin);
		if (process.waitFor() != 0)
			textLog("签名失败。。。");
	}

	// 更改文件的包名
	private static void rePagename(String mest_filepath, String oldpagename,
			String newpagename) {

		// TODO Auto-generated method stub
		if (oldpagename.trim().isEmpty() || oldpagename.equals("1")) {
			return;
		}
		String read = Filetextutils.read(mest_filepath, "UTF-8");
		read = read.replace("null", "");
		String replace = read.replace(oldpagename, newpagename);
		Filetextutils.write(mest_filepath, false, replace, "utf-8");
	}

	// 更改应用名
	public static void reAppname(String mest_filepath, String oldname,
			String newname) {
		// TODO Auto-generated method stub
		if (oldname.trim().isEmpty() || oldname.equals("1")) {
			return;
		}
		textLog("更改应用名：" + oldname + "  " + newname);
		String read = Filetextutils.read(mest_filepath, "UTF-8");
		// System.out.println("++++++++++++++++++++++++++++改名字\n" + read);
		read = read.replace("null", "");
		String replace = read.replace(oldname, newname);
		Filetextutils.write(mest_filepath, false, replace, "UTF-8");
	}

	private static void reIcon(String mest_filepath, String newicon_path,
			String oldicon_path) {
		// TODO Auto-generated method stub
		if (newicon_path != null) {

			if (newicon_path.isEmpty()) {
				return;
			}

			String read = Filetextutils.read(mest_filepath, "utf-8");
			// textLog(read);
			// if (zippathtaget.contains("QQ")) {

			SAXReader saxReader = new SAXReader();

			try {
				// textLog("文件路径"+delpath3);
				Document document = saxReader.read(new File(mest_filepath));
				Element rootElement = document.getRootElement();
				Attribute rootattribute = rootElement.attribute("package");
				// 获取到包名
				// packagename = rootattribute.getValue();
				// textLog(packagename);
				Element application = rootElement.element("application");

				Attribute icon_name_at = application.attribute("icon");

				// String icon_name = icon_name_at.getValue();
				// icon_name=icon_name.replace("@drawable/", "");
				textLog("icon名字:" + icon_name_at.getValue());
				String asXML = document.asXML();
				asXML = asXML
						.replace(icon_name_at.getValue(), "@drawable/icon");
				// icon_name_at.setValue("@drawable/icon");
				Filetextutils.write(mest_filepath, false, asXML, "UTF-8");
				textLog("icon改后:" + newicon_path + "========" + oldicon_path);
				System.out.println("icon改后:" + newicon_path + "========"
						+ oldicon_path);

				Fileutils.copyFolder(newicon_path, oldicon_path);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 修改属性值
	private static void reValues(String mest_filepath, String valuename,
			String value) {
		// TODO Auto-generated method stub

		if (valuename != null) {

			if (valuename.trim().isEmpty() || valuename.equals("1")
					|| value.trim().isEmpty()) {
				return;
			}
			textLog("修改values" + valuename + "  " + value);
			String read = Filetextutils.read(mest_filepath, "utf-8");
			// textLog(read);
			// if (zippathtaget.contains("QQ")) {

			SAXReader saxReader = new SAXReader();

			try {
				// textLog("文件路径"+delpath3);
				Document document = saxReader.read(new File(mest_filepath));
				Element rootElement = document.getRootElement();
				Attribute rootattribute = rootElement.attribute("package");
				// 获取到包名
				// packagename = rootattribute.getValue();
				// textLog(packagename);
				Element application = rootElement.element("application");

				List<Element> elements = application.elements("meta-data");
				// textLog(""+elements.size());
				for (int i = 0; i < elements.size(); i++) {

					String an_name = elements.get(i).attributeValue("name");
					if (an_name.equals(valuename)) {

						elements.get(i).setAttributeValue("value", value);

					}

				}
				String docString = document.asXML();
				// textLog(appid);
				read = document.asXML();
				// System .out.println(attribute.getValue());
				Filetextutils.write(mest_filepath, false, read, "utf-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void textLog(String msg) {
		logger.log(Priority.INFO, "packing======         " + msg);
	}

	public static void Log(final InputStream stream, final int type) {
		// InputStream errorStream = process.getErrorStream();
		// process.getErrorStream();
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				try {
					StringBuffer sbOut = new StringBuffer(1000);
					BufferedReader br;

					br = new BufferedReader(new InputStreamReader(stream));
					String line = null;
					while ((line = br.readLine()) != null) {
						if (type == 1) {
							// System.out.println(line);
							textLog("错误信息：" + line);
						} else {
							// System.out.println(line);
							textLog(line);
						}

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}
}
