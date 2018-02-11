package com.jiafan.utils;

import java.util.List;

import com.demo.common.model.Childpackagelist;

public class ChildlistDatatableBean {

	public int draw;
	public int recordsTotal;
	public int recordsFiltered;
	public  List<Childpackagelist> data;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<Childpackagelist> getData() {
		return data;
	}
	public void setData(List<Childpackagelist> data) {
		this.data = data;
	}
	
}
