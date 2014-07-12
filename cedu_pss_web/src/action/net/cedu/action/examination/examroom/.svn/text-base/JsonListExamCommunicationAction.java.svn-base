package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamCommunicationRecordBiz;
import net.cedu.entity.examination.ExamCommunicationRecord;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonListExamCommunicationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8868376964058157738L;
	@Autowired
	private ExamCommunicationRecordBiz examcommunicationrecordbiz;
	private int id;
	public int getId() {
		return id;
	}
	public PageResult<ExamCommunicationRecord> getResult() {
		return result;
	}
	public void setResult(PageResult<ExamCommunicationRecord> result) {
		this.result = result;
	}
	public void setId(int id) {
		this.id = id;
	}
	PageResult<ExamCommunicationRecord> result=new PageResult<ExamCommunicationRecord>();
	
	@Action(results = {@Result(name = "success", location = "exam_communication.jsp")})
	public String exceute(){
		return SUCCESS;
	}
	@Action(value="list_communication",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String executes()
	{
		try {
			result.setList(examcommunicationrecordbiz.findExamCommunicationRecordPage(id, result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	@Action(value="count_communication",results={@Result(type="json",name="success",params={"contentType","text/json"})})
	public String cout(){
		try {
			result.setRecordCount(examcommunicationrecordbiz.findExamCommunicationRecordPageCount(id, result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
