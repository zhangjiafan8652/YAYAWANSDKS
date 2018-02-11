package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseProject<M extends BaseProject<M>> extends Model<M> implements IBean {

	public M setProjectid(java.lang.Integer projectid) {
		set("projectid", projectid);
		return (M)this;
	}
	
	public java.lang.Integer getProjectid() {
		return getInt("projectid");
	}

	public M setProjectname(java.lang.String projectname) {
		set("projectname", projectname);
		return (M)this;
	}
	
	public java.lang.String getProjectname() {
		return getStr("projectname");
	}

	public M setBelongcompany(java.lang.Integer belongcompany) {
		set("belongcompany", belongcompany);
		return (M)this;
	}
	
	public java.lang.Integer getBelongcompany() {
		return getInt("belongcompany");
	}

	public M setCreatetime(java.util.Date createtime) {
		set("createtime", createtime);
		return (M)this;
	}
	
	public java.util.Date getCreatetime() {
		return get("createtime");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
