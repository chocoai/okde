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
 * 年度招生总指标
 * @author gaole
 *
 */

public class RecruitAllYearAction extends BaseAction{

	private int tab=1;   //页签

	@Action(results = { @Result(name = "success",type="redirect" ,location = "list_recruit_all_year?tab=${tab}")
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
