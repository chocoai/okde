package net.cedu.action.finance.studentsenrolled;

import net.cedu.action.BaseAction;

/**
 * 学生置为跟进中
 * @author lixiaojun
 *
 */
public class ListStudentFollowUpAction extends BaseAction
{
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			return INPUT;
		}
		return SUCCESS;
	}
}
