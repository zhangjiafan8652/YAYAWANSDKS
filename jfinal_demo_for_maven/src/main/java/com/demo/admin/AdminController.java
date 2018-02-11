package com.demo.admin;

import java.io.File;
import java.util.ArrayList;

import javax.print.attribute.standard.Severity;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.eclipse.jetty.util.ajax.JSON;

import com.demo.common.model.Blog;
import com.demo.common.model.Childpackagelist;
import com.demo.common.model.Company;
import com.demo.common.model.Motherpackagelist;
import com.demo.common.model.Packing;
import com.demo.common.model.Project;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Page;
import com.jiafan.utils.ChildlistDatatableBean;
import com.jiafan.utils.CreateQianguoid;
import com.jiafan.utils.ExcelUtil;
import com.jiafan.utils.OkHttpUtil;
import com.jiafan.utils.PackingRunable;
import com.jiafan.utils.ResPonseCompayBean;
import com.sun.tools.classfile.Annotation.element_value;
import com.sun.xml.internal.rngom.parse.host.Base;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * BlogController 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller
 * 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(AdminInterceptor.class)
public class AdminController extends Controller {

	static AdminService service = new AdminService();
	private final Logger logger = Logger.getLogger("");
	public static String DISPRE =  PropKit.get("DISPRE");
	public void index() {
		// setAttr("blogPage", service.paginate(getParaToInt(0, 1), 10));
		String para = getPara("companyid");
	//	Company mcompany = service.findcompanyById(Integer.parseInt(para));
		//System.out.println("123" + mcompany);
		addcompanytrr(para);
		
		
		render("index.html");
	
	}

	// 返回公司列表
	public void company() {
		String para = getPara("companyid");
		ResPonseCompayBean resPonseCompayBean = new ResPonseCompayBean();
		resPonseCompayBean.setNowcampanyid(para);
		resPonseCompayBean.setCompanys(service.listAllcompany());
		
		
		renderJson(resPonseCompayBean);
	}
	public void addcompanytrr(String para) {
		
		if (para!=null&&para!="") {
			Company mcompany = service.findcompanyById(Integer.parseInt(para));
			setAttr("thiscompanyid", Integer.parseInt(para));
			setAttr("mcompany", mcompany);
			setAttr("mcompanys", service.listAllcompany());
		}else {
			
			
			setAttr("mcompanys", service.listAllcompany());
		}
		
		
	}

	// 返回项目主页
	public void projectindex() {
		
		String para = getPara("companyid");
		addcompanytrr(para);
		ArrayList<Project> listAllproject = service
				.listAllprojectbycompanyid(para);
		logger.info(listAllproject.toString());

		
		setAttr("projectlist", listAllproject);
		System.out.println(para);
		render("projectindex.html");
	}

	// 添加项目
	public void addproject() {
		String mprojectname = getPara("projectname");

		String para = getPara("companyid");
		Project project = new Project();
		project.setBelongcompany(Integer.parseInt(para));
		new Project().set("projectname", mprojectname)

		.set("belongcompany", para).save();
		redirect("/admin/projectindex?companyid=" + para);

	}

	// 项目列表
	public void projectlist() {

		String para = getPara("companyid");
		
		renderJson(service.listAllprojectbycompany(para));
	}

	// 返回母包列表主页
	public void motherpackageindex() {
		// service.listAllcompany();
		String para = getPara("companyid");
		addcompanytrr(para);

		ArrayList<Motherpackagelist> motherpackagelist = service
				.listAllmotherpackagebycompany(para);
		ArrayList<Project> mprojects = service.listAllprojectbycompany(para);

		
		setAttr("mprojects", mprojects);
		setAttr("motherpackagelist", motherpackagelist);

		System.out.println(para);
		render("motherpackageindex.html");
	}

	public void addmotherpackage() {
		// String mprojectname = getPara("projectname");
		logger.log(Priority.WARN, "11111111111111+++++++qqqqqqqqqqq");
		System.out.println("1111111");
		String para = getPara("companyid");
		Motherpackagelist bean = getBean(Motherpackagelist.class);
		logger.log(Priority.INFO, "11111111111111+++++++" + bean.toJson());
		bean.set("belongcompany", para).save();

		redirect("/admin/motherpackageindex?companyid=" + para);

	}

	// 返回分包列表主页
	public void childpackagelistindex() {

		String para = getPara("companyid");

		addcompanytrr(para);
		ArrayList<Project> mprojects = service.listAllprojectbycompany(para);

		ArrayList<Motherpackagelist> motherpackagelist = service
				.listAllmotherpackagebycompany(para);

		/*ArrayList<Childpackagelist> mchildpackagelist = service
				.listAllcompanyhildpackagebycompany(Integer.parseInt(para));
		logger.log(Priority.INFO, mchildpackagelist);*/
		
		setAttr("mprojects", mprojects);
		setAttr("motherpackagelist", motherpackagelist);
		setAttr("mchildpackagelist", null);

		render("childpackagelistindex.html");
	}

	
	// 返回分包列表分页数据
	public void childpackagelistpage() {

		String para = getPara("companyid");
		String draw = getPara("draw");
		addcompanytrr(para);
		Page<Childpackagelist> childpackagelistpaginate = service.Childpackagelistpaginate(Integer.parseInt(draw), 10, para);
		ChildlistDatatableBean childlistDatatableBean = new ChildlistDatatableBean();
		childlistDatatableBean.setData(childpackagelistpaginate.getList());
		childlistDatatableBean.setDraw(Integer.parseInt(draw));
		childlistDatatableBean.setRecordsFiltered(childpackagelistpaginate.getTotalRow());
		childlistDatatableBean.setRecordsTotal(childpackagelistpaginate.getTotalRow());
		System.out.println("++++++++++++page++++++++++++"+draw);
		renderJson(childlistDatatableBean);
	}
	
	
	// 添加分包
	public void addchildpackage() {
		String mprojectname = getPara("projectname");

		String para = getPara("companyid");
		Childpackagelist mchildpackage = getBean(Childpackagelist.class);
		Motherpackagelist motherpackage = service
				.findmotherpackagebyid(mchildpackage.getGamemotherfileid());
		mchildpackage.setGamemotherfilename(motherpackage.getMothergamename());
		mchildpackage.setBelongcompany(Integer.parseInt(para));
		mchildpackage.save();
		redirect("/admin/childpackagelistindex?companyid=" + para);

	}

	// 批量添加分包
	public void addchildpackagebyxlsx() {
		String xlsxfilepath = getPara("xlsxfilepath");

		String para = getPara("companyid");
		getGameInfoToBen(xlsxfilepath, para);
		redirect("/admin/childpackagelistindex?companyid=" + para);

	}

	public void addtopacking() {
		String mchildid = getPara("childid");

		String para = getPara("companyid");
				Childpackagelist mchildpackage = service.findchildpackagebyid(mchildid);
		Motherpackagelist motherpackage = service
				.findmotherpackagebyid(mchildpackage.getGamemotherfileid());
		logger.log(Priority.INFO, motherpackage.toString());
		Packing packing = new Packing();
		boolean save = packing
				.set("gamename", mchildpackage.getGamename())
				.set("gamepackagename", mchildpackage.getGamepackagename())
				.set("gameiconpath", mchildpackage.getGameiconpath())

				.set("gamefilename", mchildpackage.getGamefilename())
				.set("gameoutputpath", mchildpackage.getGameoutputpath())
				.set("gamemotherfileid", motherpackage.getMotherpackageid())

				.set("gamemotherfilename", motherpackage.getMothergamename())
				.set("gamemotherfilepath", motherpackage.getApkpath())
				.set("gamemothername", motherpackage.getMothergamename())

				.set("gamemotherpackagename",
						motherpackage.getMothergamepackagename())
				.set("gameoutputpath", mchildpackage.getGameoutputpath())

				.set("gamesocurceid", mchildpackage.getGamesourceid())
				.set("gameplatformid", mchildpackage.getGameplatformid())
				.set("belongcompany", para)
				.set("mothergameplatformid",
						motherpackage.getMothergameplatformid())

				.set("ispacked", 0).save();
		if (save) {
			renderJson("issave", save);
		}

	}

	// 返回打包列表主页
	public void packingindex() {

		String para = getPara("companyid");

		addcompanytrr(para);
		ArrayList<Packing> mpackinglist = service.listAllpackingbycompany(para);
		logger.log(Priority.INFO, "打包列表：" + mpackinglist.toString());
	
		if (packingRunable!=null) {
			setAttr("isstartpacking", packingRunable.isenablepacking);
		}else {
			setAttr("isstartpacking",false);
		}
		
		setAttr("mpackinglist", mpackinglist);

		render("packinglistindex.html");
	}

	// 取消打包
	public void deletepackinglist() {
		String para = getPara("companyid");
		String packingid = getPara("packingid");
		boolean deletepackinglistitem = service
				.deletepackinglistitem(packingid);
		logger.log(Priority.INFO, "删除打包item成功：" + deletepackinglistitem);
		redirect("/admin/packingindex?companyid=" + para);
	}

	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中， 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(AdminValidator.class)
	public void save() {
		getBean(Blog.class).save();
		redirect("/blog");
	}

	

	public static PackingRunable packingRunable;
	private static ArrayList<Childpackagelist> mChildpackagelist;

	// 开始打包
	public void startpacking() {

		if (packingRunable != null) {
			
			if (packingRunable.isenablepacking) {

			} else {
				packingRunable.isenablepacking = true;
				new Thread(packingRunable).start();
			}
			
		} else {

			packingRunable = new PackingRunable();
			packingRunable.isenablepacking = true;
			new Thread(packingRunable).start();
		}

		String para = getPara("companyid");
		redirect("/admin/packingindex?companyid=" + para);
	}

	// 停止打包
	public void stoppacking() {
		if (packingRunable != null) {
			packingRunable.isenablepacking = false;
			
		} 

		
		String para = getPara("companyid");
		redirect("/admin/packingindex?companyid=" + para);
	}

	/**
	 * 获取gameinfo信息
	 * 
	 */
	public void getGameInfoToBen(String xlsxpath, String companyid) {

		mChildpackagelist = new ArrayList<Childpackagelist>();
		File file = new File(DISPRE+File.separator + xlsxpath);
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
		System.out.println(result.size());
		for (int i = 0; i < result.size(); i++) {
			Childpackagelist rePackBean = new Childpackagelist();
			if (i == 0) {

			} else {
				String isadd = "";
				for (int j = 0; j < result.get(i).size(); j++) {
					switch (j) {
					case 0:
						System.out.println("当前游戏名字："+result.get(i).get(j).toString()
								.trim());
						rePackBean.setGamename(result.get(i).get(j).toString()
								.trim());
						break;
					case 1:
						rePackBean.setGamepackagename(result.get(i).get(j)
								.toString().trim());

						break;
					case 2:
						rePackBean.setGameiconpath(result.get(i).get(j)
								.toString().trim());

						break;
					case 3:
						rePackBean.setGamefilename(result.get(i).get(j)
								.toString().trim());

						break;
					case 4:
						rePackBean.setGameoutputpath(result.get(i).get(j)
								.toString().trim());

						break;
					case 5:
						rePackBean.setGamesourceid(result.get(i).get(j)
								.toString().trim());

						break;
					case 6:
						if (companyid.contains("1")) {
							rePackBean.setGameplatformid(""
									+ result.get(i).get(j).toString().trim());
						}
						/*
						 * rePackBean.setGameplatformid(result.get(i).get(j)
						 * .toString().trim());
						 */
						// rePackBean.setGamebelongprojectid(k);
						break;
					case 7:
						String time = result.get(i).get(j).toString().trim();
						System.out
								.println(time.substring(0, time.indexOf(".")));
						if (time.contains(".")) {
							int k = Integer.parseInt(time.substring(0,
									time.indexOf(".")));

							rePackBean.setGamebelongprojectid(k);
						}else {
							rePackBean.setGamebelongprojectid(Integer.parseInt(time));
						}
						

						break;
					case 8:
						
						String time2 = result.get(i).get(j).toString().trim();
						if (time2.contains(".")) {
							System.out.println(time2.substring(0,
									time2.indexOf(".")));
							int y = Integer.parseInt(time2.substring(0,
									time2.indexOf(".")));
							
							rePackBean.setGamemotherfileid(y);
						}else {
							rePackBean.setGamemotherfileid(Integer.parseInt(time2));
						}
						

						

						break;
					case 9:

						isadd = result.get(i).get(j).toString().trim();

						break;

					default:
						break;
					}

				}
				// 循环一列后
				if (isadd.equals("否")||isadd==null||rePackBean.getGamemotherfileid()==null) {
					//System.out.println("跳过的项目："+rePackBean.getGamefilename());
				} else {
					rePackBean.setBelongcompany(Integer.parseInt(companyid));
					System.out.println("repack+++++++++++++++"+rePackBean.toString());
					Motherpackagelist findmotherpackagebyid = service.findmotherpackagebyid(rePackBean.getGamemotherfileid());
					
					
					rePackBean.setGamemotherfilename(findmotherpackagebyid.getMothergamename());
					if (rePackBean.getBelongcompany() == 2) {
						// 千果的需要获取平台id
						gotoqianguogetplatformid(rePackBean);
					} else {
						System.out.println(rePackBean.toJson());
						// 千其的直接保存
						rePackBean.save();
					}
				}

			}

		}

	}

	// 去千果返回平台id
	private Boolean gotoqianguogetplatformid(Childpackagelist rePackBean) {
		// TODO Auto-generated method stub
		Project findprojectById = service.findprojectById(rePackBean
				.getGamebelongprojectid());
		// 发送 GET 请求
		// String
		url="http://cpanel.kingoo.com.cn?act=game.pack_tool_api&key=wxj9dlpe&action=get_appid&pname="+rePackBean.getGamemotherfilename()+"&name="+rePackBean.getGamename()+"&numstr=1&ename="+rePackBean.getGamesourceid();
		// String para = getPara("key");
		//String url = "http://localhost:8888/admin/ceshi";
		String s = "";
		try {
			// s = HttpRequest.doGet("http://www.baidu.com");
			String syncString = OkHttpUtil.getSyncString(url);
			// Response sync =
			// OkHttpUtil._getSyncString("http://www.baidu.com");
			System.out.println(url);
			System.out.println(syncString);
			Gson gson = new Gson();
			CreateQianguoid fromJson = gson.fromJson(syncString,
					CreateQianguoid.class);
			String appid = fromJson.getAppid();
			// json.
			rePackBean.setGameplatformid("kk" + appid);
			
			rePackBean.save();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public void ceshi() {
		CreateQianguoid createQianguoid = new CreateQianguoid();
		createQianguoid.setAppid("" + System.currentTimeMillis());
		createQianguoid.setErr_code(0);
		createQianguoid.setErr_msg("成功");
		renderJson(createQianguoid);
	}

	public static String url = "http://cpanel.kingoo.com.cn?act=game.pack_tool_api&key=wxj9dlpe&action=get_appid&pname=青云诀&name=青云诀打包测试2&numstr=1&ename=qyj02";
	private JsonElement jsonElement;

	public void ceshi1() {

		String s = "";
		try {
			// s = HttpRequest.doGet("http://www.baidu.com");
			String syncString = OkHttpUtil.getSyncString(url);
			// Response sync =
			// OkHttpUtil._getSyncString("http://www.baidu.com");
			Gson gson = new Gson();
			CreateQianguoid fromJson = gson.fromJson(syncString,
					CreateQianguoid.class);
			String appid = fromJson.getAppid();
			// json.
			renderJson("issave", syncString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// renderJson("issave","");
	}

	
	public void infos() {
		String keystorepath= PropKit.get("keystroepath");
		//String DISPRE= PropKit.get("DISPRE");
		renderHtml("pokit信息:"+keystorepath+DISPRE);
	}

	
	public void edit() {
		setAttr("blog", service.findById(getParaToInt()));
	}

	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中， 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(AdminValidator.class)
	public void update() {
		getBean(Blog.class).update();
		redirect("/blog");
	}

	public void delete() {
		service.deleteById(getParaToInt());
		redirect("/blog");
	}
}
