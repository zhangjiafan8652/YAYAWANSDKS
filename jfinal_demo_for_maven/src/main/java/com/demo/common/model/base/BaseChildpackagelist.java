package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseChildpackagelist<M extends BaseChildpackagelist<M>> extends Model<M> implements IBean {

	public M setPackinglistid(java.lang.Integer packinglistid) {
		set("packinglistid", packinglistid);
		return (M)this;
	}
	
	public java.lang.Integer getPackinglistid() {
		return getInt("packinglistid");
	}

	public M setGamebelongprojectid(java.lang.Integer gamebelongprojectid) {
		set("gamebelongprojectid", gamebelongprojectid);
		return (M)this;
	}
	
	public java.lang.Integer getGamebelongprojectid() {
		return getInt("gamebelongprojectid");
	}

	public M setGamename(java.lang.String gamename) {
		set("gamename", gamename);
		return (M)this;
	}
	
	public java.lang.String getGamename() {
		return getStr("gamename");
	}

	public M setGamepackagename(java.lang.String gamepackagename) {
		set("gamepackagename", gamepackagename);
		return (M)this;
	}
	
	public java.lang.String getGamepackagename() {
		return getStr("gamepackagename");
	}

	public M setGameiconpath(java.lang.String gameiconpath) {
		set("gameiconpath", gameiconpath);
		return (M)this;
	}
	
	public java.lang.String getGameiconpath() {
		return getStr("gameiconpath");
	}

	public M setGamefilename(java.lang.String gamefilename) {
		set("gamefilename", gamefilename);
		return (M)this;
	}
	
	public java.lang.String getGamefilename() {
		return getStr("gamefilename");
	}

	public M setGameoutputpath(java.lang.String gameoutputpath) {
		set("gameoutputpath", gameoutputpath);
		return (M)this;
	}
	
	public java.lang.String getGameoutputpath() {
		return getStr("gameoutputpath");
	}

	public M setGamemotherfileid(java.lang.Integer gamemotherfileid) {
		set("gamemotherfileid", gamemotherfileid);
		return (M)this;
	}
	
	public java.lang.Integer getGamemotherfileid() {
		return getInt("gamemotherfileid");
	}

	public M setGamemotherfilename(java.lang.String gamemotherfilename) {
		set("gamemotherfilename", gamemotherfilename);
		return (M)this;
	}
	
	public java.lang.String getGamemotherfilename() {
		return getStr("gamemotherfilename");
	}

	public M setIspacked(java.lang.Integer ispacked) {
		set("ispacked", ispacked);
		return (M)this;
	}
	
	public java.lang.Integer getIspacked() {
		return getInt("ispacked");
	}

	public M setBelongcompany(java.lang.Integer belongcompany) {
		set("belongcompany", belongcompany);
		return (M)this;
	}
	
	public java.lang.Integer getBelongcompany() {
		return getInt("belongcompany");
	}

	public M setGamesourceid(java.lang.String gamesourceid) {
		set("gamesourceid", gamesourceid);
		return (M)this;
	}
	
	public java.lang.String getGamesourceid() {
		return getStr("gamesourceid");
	}

	public M setGameplatformid(java.lang.String gameplatformid) {
		set("gameplatformid", gameplatformid);
		return (M)this;
	}
	
	public java.lang.String getGameplatformid() {
		return getStr("gameplatformid");
	}

	public M setCreatetime(java.util.Date createtime) {
		set("createtime", createtime);
		return (M)this;
	}
	
	public java.util.Date getCreatetime() {
		return get("createtime");
	}

	public M setUpdatetime(java.util.Date updatetime) {
		set("updatetime", updatetime);
		return (M)this;
	}
	
	public java.util.Date getUpdatetime() {
		return get("updatetime");
	}

}