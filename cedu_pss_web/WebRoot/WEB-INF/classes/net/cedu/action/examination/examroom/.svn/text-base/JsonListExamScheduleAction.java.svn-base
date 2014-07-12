package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamScheduleBiz;
import net.cedu.entity.examination.ExamSchedule;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("json-default")
public class JsonListExamScheduleAction extends BaseAction implements ModelDriven<ExamSchedule> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6461520260722261030L;
	@Autowired
	private ExamScheduleBiz examschedulebiz;
	private ExamSchedule examschedule=new  ExamSchedule();

	PageResult<ExamSchedule> result=new PageResult<ExamSchedule>();
	@Action(value="list_examschedule",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String executes()
	{
		try {
			result.setList(examschedulebiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	public ExamSchedule getExamschedule() {
		return examschedule;
	}

	public void setExamschedule(ExamSchedule examschedule) {
		this.examschedule = examschedule;
	}

	public PageResult<ExamSchedule> getResult() {
		return result;
	}

	public void setResult(PageResult<ExamSchedule> result) {
		this.result = result;
	}

	//分页结果
	@Action(value="findByconditions_examschedule",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String execute()
	{
		try {
			result.setList(examschedulebiz.findByConditions(examschedule,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_examschedule",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(examschedulebiz.findExamSchedulePageCount(examschedule, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public ExamSchedule getModel(){
		return examschedule;
	}

}
