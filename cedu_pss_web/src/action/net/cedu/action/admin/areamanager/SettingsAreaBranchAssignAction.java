package net.cedu.action.admin.areamanager;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 区域管理学习中心设置
 * 
 * @author gaolei
 * 
 */

public class SettingsAreaBranchAssignAction extends BaseAction {

	
	
	@Action(results = { @Result(name = "success",location = "settings_area_branch_assign.jsp")
	})
	public String excute() throws Exception
	{	
		return SUCCESS;	
	}
	

	


	
	
}
