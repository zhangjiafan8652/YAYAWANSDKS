package com.demo.admin;

import com.jfinal.config.Routes;

public class AdminRoutes  extends Routes{
	 public void config() {
	       //setBaseViewPath("/view/admin");
	       addInterceptor(new AdminInterceptor());
	       add("/admin", AdminController.class);
	     
	       //add("/admin/user", UserController.class);
	    }
}
