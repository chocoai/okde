package net.cedu.action.examination.examroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.model.page.PageResult;
@ParentPackage("json-default")
public class JsonListAacademyAction extends BaseAction {

	private static final long serialVersionUID = 6416608032713002342L;
	
	@Autowired
	private ExamPlanBiz examplanbiz;
	
	PageResult<Academy> result=new PageResult<Academy>();
	public void setResult(PageResult<Academy> result) {
		this.result = result;
	}

	public PageResult<Academy> getResult() {
		return result;
	}

	//院校分页结果
	@Action(value="findByconditions_academy",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String execute()
	{
		try {
			result.setList(examplanbiz.findAllAcademyConditions(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	//院校分页数量
	@Action(value="count_academy",results={@Result(type="json", name = "success",params={"contentType","text/json"})})	
	public String count()
	{
		try {
			result.setRecordCount(examplanbiz.findAllAcademyCount(result));
			System.out.println(result.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	
}
