package net.cedu.action.enrollment.enrollquota;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学习中心指标
 * @author gaolei
 *
 */

public class SetRecruitAction extends BaseAction{

	
	@Action(results = { @Result(name = "success",location = "set_recruit.jsp")
	})
	public String excute() throws Exception
	{
		if(super.isGetRequest())
		{
		return SUCCESS;		
		}
		return SUCCESS;		
	}
	
	
	
}
