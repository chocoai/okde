package net.cedu.action.worklog;

import net.cedu.action.BaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
/**
 * 
 * @功能：总部添加日报
 *
 * @作者： 杨栋栋
 * @作成时间：2012-1-9 上午09:57:12
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
public class AdminAddWorklogAction extends BaseAction{
	
	@Action(results = {@Result(name="success",location="add_worklog",type="redirect")})
	public String execute() throws Exception{
		return SUCCESS;
	}
}
