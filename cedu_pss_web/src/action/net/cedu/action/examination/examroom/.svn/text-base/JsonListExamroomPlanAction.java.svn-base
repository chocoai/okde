package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.biz.examination.ExamroomPlanBiz;
import net.cedu.entity.examination.ExamPlan;
import net.cedu.entity.examination.ExamroomPlan;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
public class JsonListExamroomPlanAction extends BaseAction  {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -464604045563405343L;
	@Autowired
	private ExamroomPlanBiz examroomplanbiz;
	PageResult<ExamroomPlan> result=new PageResult<ExamroomPlan>();
	public PageResult<ExamroomPlan> getResult() {
		return result;
	}

	public void setResult(PageResult<ExamroomPlan> result) {
		this.result = result;
	}
	

	@Action(value="list_examroomplan",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(examroomplanbiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	//分页数量
	@Action(value="count_examroomplan",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(examroomplanbiz.findAllexamRoomplanCount(result));		
			} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
