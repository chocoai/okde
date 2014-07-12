/**
 * 文件名：JsonInterfaceAction.java
 * 包名：net.cedu.action.admin.areamanager
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-9 下午03:23:41
 *
*/
package net.cedu.action.admin.areamanager;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.admin.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonInterfaceAction extends BaseAction {

	@Autowired
	private UserBiz userBiz;
	private List<User> areaUserList=new ArrayList<User>();
	
	@Action(value = "interface_search_area_manager_user", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String searchAreaManagerUser()throws Exception {
		areaUserList=userBiz.findAllAreaManagerByStatusAndDeleteFlag();
		return SUCCESS;
	}

	public List<User> getAreaUserList() {
		return areaUserList;
	}
	
}
