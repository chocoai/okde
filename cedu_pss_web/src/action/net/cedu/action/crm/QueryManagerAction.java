package net.cedu.action.crm;

import net.cedu.action.BaseAction;

/**
 * 经理查询（在线客服）
 * @author yangdongdong
 *
 */
public class QueryManagerAction extends BaseAction{
	
	private int userId;
	private String fullName;
	
	@Override
	public String execute() throws Exception {
		userId=super.getUser().getUserId();
		fullName=super.getUser().getFullName();
		return super.execute();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}