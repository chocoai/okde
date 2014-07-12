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
 * 批次总指标
 * @author gaolei
 *
 */

public class ListRecruitAllAction extends BaseAction{

	
	@Action(results = { @Result(name = "success",location = "list_recruit_all.jsp")
	})
	public String excute() throws Exception
	{
		return SUCCESS;		
	}
	
	
	
}
