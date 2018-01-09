package com.jiafancreatezipfile;

import java.io.File;
import java.util.ArrayList;

import javax.xml.crypto.Data;

public class ceshi1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rootpath="F:\\0azhangjiafan\\allsdk\\Sdkxiaomi\\src";
		String targetrootpath="F:\\0azhangjiafan\\f临时文件\\spv4";
		 File file = new File(rootpath);
		 if (file.isDirectory()) {
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				if (!list[i].equals("com")) {
					System.out.println(list[i]);
					Fileutils.copyFolder(rootpath+"\\"+list[i], targetrootpath);
				}
			}
		}
	}

}
