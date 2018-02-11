package com.jiafan.utils;

import java.util.ArrayList;

import com.demo.common.model.Company;

public class ResPonseCompayBean {

	private String nowcampanyid;
	private ArrayList<Company> companys;
	public String getNowcampanyid() {
		return nowcampanyid;
	}
	public void setNowcampanyid(String nowcampanyid) {
		this.nowcampanyid = nowcampanyid;
	}
	public ArrayList<Company> getCompanys() {
		return companys;
	}
	public void setCompanys(ArrayList<Company> companys) {
		this.companys = companys;
	}
	@Override
	public String toString() {
		return "ResPonseCompayBean [nowcampanyid=" + nowcampanyid
				+ ", companys=" + companys + "]";
	}
	
	
	
	
}
