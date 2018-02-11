package com.demo.admin;

import java.util.ArrayList;

import com.demo.common.model.Blog;
import com.demo.common.model.Childpackagelist;
import com.demo.common.model.Company;
import com.demo.common.model.Motherpackagelist;
import com.demo.common.model.Packing;
import com.demo.common.model.Project;
import com.jfinal.plugin.activerecord.Page;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * BlogService
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class AdminService {
	
	/**
	 * 所有的 dao 对象也放在 Service 中
	 */
	private static final Blog dao = new Blog().dao();
	private static final Company companydao = new Company().dao();
	private static final Project projectdao = new Project().dao();
	private static final Motherpackagelist Motherpackagelistdao = new Motherpackagelist().dao();
	private static final Childpackagelist childpackagedao = new Childpackagelist().dao();
	private static final Packing packingdao = new Packing().dao();
	
	public Page<Blog> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from blog order by id asc");
	}
	
	public Blog findById(int id) {
		return dao.findById(id);
	}
	public Company findcompanyById(int id) {
		return companydao.findById(id);
	}
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Company> listAllcompany() {
		//return companydao.paginate(pageNumber, pageSize, "select *", "from company order by id asc");
		return (ArrayList<Company>) companydao.find("select * from company");
	}
	public ArrayList<Project> listAllprojectbycompanyid(String para) {
		// TODO Auto-generated method stub
		return (ArrayList<Project>) projectdao.find("select * from project where belongcompany="+para);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Project> listAllproject() {
		//return companydao.paginate(pageNumber, pageSize, "select *", "from company order by id asc");
		return (ArrayList<Project>) projectdao.find("select * from project");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Motherpackagelist> listAllmotherpackage() {
		//return companydao.paginate(pageNumber, pageSize, "select *", "from company order by id asc");
		//return (ArrayList<Motherpackagelist>) Motherpackagelistdao.find("select * from motherpackage order by motherpackageid asc");
		return (ArrayList<Motherpackagelist>) Motherpackagelistdao.find("select * from motherpackage ");
	}

	public ArrayList<Motherpackagelist> listAllmotherpackagebycompany(String para) {
		// TODO Auto-generated method stub
		return (ArrayList<Motherpackagelist>) Motherpackagelistdao.find("select * from motherpackagelist where belongcompany="+para+" order by motherpackageid DESC");
		//return (ArrayList<Motherpackagelist>) Motherpackagelistdao.find("select * from motherpackagelist where belongcompany="+para);
	}

	public ArrayList<Project> listAllprojectbycompany(String para) {
		// TODO Auto-generated method stub
		 return (ArrayList<Project>) projectdao.find("select * from project where belongcompany="+para);
	}

	public Project findprojectById(int parseInt) {
		// TODO Auto-generated method stub
		return projectdao.findById(parseInt);
	}

	public ArrayList<Childpackagelist> listAllcompanyhildpackagebycompany(
			Integer belongcompany) {
		// TODO Auto-generated method stub
		return (ArrayList<Childpackagelist>) childpackagedao.find("select * from childpackagelist where belongcompany="+belongcompany+" order by packinglistid DESC");
	}

	public Motherpackagelist findmotherpackagebyid(Integer gamemotherfileid) {
		// TODO Auto-generated method stub
		return Motherpackagelistdao.findById(gamemotherfileid);
	}

	public Page<Childpackagelist> Childpackagelistpaginate(int pageNumber, int pageSize,String belongcompany) {
		return childpackagedao.paginate(pageNumber, pageSize, "select *", "from childpackagelist where belongcompany="+belongcompany+" order by packinglistid DESC");
	}

	public ArrayList<Packing> listAllpackingbycompany(String para) {
		// TODO Auto-generated method stub
		return (ArrayList<Packing>) packingdao.find("select * from packing where belongcompany="+para);
	}
	
	public ArrayList<Packing> listAllpacking() {
		// TODO Auto-generated method stub
		return (ArrayList<Packing>) packingdao.find("select * from packing");
	}

	public Childpackagelist findchildpackagebyid(String mchildid) {
		// TODO Auto-generated method stub
		return childpackagedao.findById(mchildid);
	}

	public boolean deletepackinglistitem(String packingid) {
		// TODO Auto-generated method stub
		return packingdao.deleteById(packingid);
	}

	
	
	
}
