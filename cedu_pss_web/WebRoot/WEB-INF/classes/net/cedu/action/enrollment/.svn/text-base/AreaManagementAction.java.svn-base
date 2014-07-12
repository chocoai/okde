package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;

import org.apache.struts2.convention.annotation.Action;

/**
 * 区域管理TAB页
 * 
 * @author Sauntor
 *
 */
public class AreaManagementAction extends BaseAction
{
	private static final int TAB_MANAGER = 1;
	private static final int TAB_BRANCH_SWITCHER = 2;
	
	private static final String TABNAME_MANAGER = "manager";
	private static final String TABNAME_BRANCH_SWITCHER = "switch";
	
	private int tab;
	
	@Action(value="area_management")
	public String execute() throws Exception
	{
		switch(tab)
		{
		case TAB_MANAGER: //区域经理页
			return TABNAME_MANAGER;
		case TAB_BRANCH_SWITCHER: //学习中心设置
			return TABNAME_BRANCH_SWITCHER;
		}
		
		return ERROR;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}
}
