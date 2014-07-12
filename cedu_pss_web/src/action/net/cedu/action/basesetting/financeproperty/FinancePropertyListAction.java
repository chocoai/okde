package net.cedu.action.basesetting.financeproperty;

import net.cedu.action.BaseAction;

public class FinancePropertyListAction extends BaseAction{
	
	private static final long serialVersionUID = 7468425847150662869L;
	
	private int viewtype;//用于判断是否是当前选中页面跳转action;
	
	/**
	 * 跳转财务参数页面
	 * @return
	 * @throws Exception
	 */
	public String execute()throws Exception{	
		return SUCCESS;
	}
	
	//----------------get and set motheds--------------
	
	public int getViewtype() {
		return viewtype;
	}
	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}
}
