package com.jiafancreatezipfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class JieYaApk {

	
	private static BufferedReader br;

	public static void main(String[] args) {
		String apkname="zssj";
		String sdkpath = "F:\\0azhangjiafan\\apktoolfan\\";
		String cmd2 = "cmd /c start " + Myconstant.apktoolpath + " d" + " -f "
				+ sdkpath +apkname + ".apk" + " -o " + Myconstant.apktoolpath
				+ "\\" + apkname;
		execCommand(cmd2);
		System.out.println(cmd2);
	}
	
	public static void execCommand(String arstringCommand) {
		try {
			  Runtime rt = Runtime.getRuntime();  
		        //String command = "cmd /c ffmpeg -loglevel quiet -i "+srcpath+" -ab "+bitrate+"k -acodec libmp3lame "+desfile;  
		        System.out.println(arstringCommand);  
		        Process p = null;  
		        try{  
		            //在Linux下调用是其他写法  
		            p = rt.exec(arstringCommand ,null,new File("F:\\kankan"));  
		            p.waitFor();  
		           // System.out.println("线程返回,转码后的文件大小为："+desFile.length()+",现在可以做其他操作了,比如重新写入ID3信息。");  
		        }  
		        catch(Exception e){  
		            e.printStackTrace();  
		            try{  
		                p.getErrorStream().close();  
		                p.getInputStream().close();  
		                p.getOutputStream().close();  
		                }  
		            catch(Exception ee){}  
		        }  
			System.out.println("我执行完了");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
