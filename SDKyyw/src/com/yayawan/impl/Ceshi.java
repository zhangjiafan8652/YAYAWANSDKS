package com.yayawan.impl;

import com.yayawan.sdk.jfutils.Yayalog;

import android.content.Context;
import android.os.Bundle;

public class Ceshi {

	
	public static void main(Context paramContext,a parm){
		
		
		
		Yayalog.loger(parm.toString());
		Yayalog.loger(parm.Y);
		Yayalog.loger(parm.Y);
		
		String k=getString();
		
		k="sbnihao";
		
		System.out.println(k);
	}
	
	public static String getString(){
		return "nihao";
	}
	
	public static final class a
	  {
	    public String W;
	    public String X;
	    public String Y;
	    public Bundle Z;
	    public int flags = -1;

	    public final String toString()
	    {
	      return "targetPkgName:" + this.W + ", targetClassName:" + this.X + ", content:" + this.Y + ", flags:" + this.flags + ", bundle:" + this.Z;
	    }
	  }
}
