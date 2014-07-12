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
 * 区域管理
 * 
 * @author gaolei
 * 
 */

public class SettingsAreaBranchAction extends BaseAction {

	
	private int tab=1;   //页签

	@Action(results = { @Result(name = "success",type="redirect", location = "settings_area_branch_baseinfo?tab=${tab}")
	})
	public String excute() throws Exception
	{	
		return SUCCESS;	
	}
	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	


	
	
}
