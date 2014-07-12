package net.cedu.demo.action.user;

import net.cedu.action.BaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * demo
 * @author yangdongdong
 *
 */
@Results( { @Result(name = "index", location = "/WEB-INF/content/demo/index.jsp") })
public class DUserAction extends BaseAction {

	@Action(value = "userlist")
	public String userList() {
		return "index";
	}
}
