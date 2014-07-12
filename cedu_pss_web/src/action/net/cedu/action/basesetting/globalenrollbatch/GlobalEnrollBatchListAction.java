package net.cedu.action.basesetting.globalenrollbatch;

import net.cedu.action.BaseAction;

public class GlobalEnrollBatchListAction extends BaseAction{

	private static final long serialVersionUID = -184387946269938432L;

	private int viewtype;//用于判断是否是当前选中页面跳转action;
	/**
	 * 跳转至全局招生批次列表页面
	 * @return
	 * @throws Exception
	 */
    
	public String execute()throws Exception{	
		return SUCCESS;
	}
	//----------------get and set mothed--------------
	public int getViewtype() {
		return viewtype;
	}
	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}
}
