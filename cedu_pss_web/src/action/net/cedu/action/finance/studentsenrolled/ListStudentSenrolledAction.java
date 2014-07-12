package net.cedu.action.finance.studentsenrolled;

import net.cedu.action.BaseAction;

/**
 * 学生录取
 * @author lixiaojun
 *
 */
public class ListStudentSenrolledAction extends BaseAction
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
