package net.cedu.action.examination.examroom;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.entity.examination.ExamRoom;
import net.cedu.model.page.PageResult;

@ParentPackage("json-default")
public class JsonListExamRoomAction extends BaseAction implements ModelDriven<ExamRoom>{
	
	private static final long serialVersionUID = 1951330514526504687L;
	
	@Autowired
	private ExamRoomBiz examroombiz;
	
	private ExamRoom examroom=new ExamRoom();

	PageResult<ExamRoom> result=new PageResult<ExamRoom>();
	@Action(value="list_examroom",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String executes()
	{
		try {
			result.setList(examroombiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	//分页结果
	@Action(value="findByconditions_examroom",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String execute()
	{
		try {
			result.setList(examroombiz.findByConditions(examroom,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_examroom",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(examroombiz.findExamRoomPageCount(examroom, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public ExamRoom getExamroom() {
		return examroom;
	}


	public void setExamroom(ExamRoom examroom) {
		this.examroom = examroom;
	}


	public PageResult<ExamRoom> getResult() {
		return result;
	}

	public void setResult(PageResult<ExamRoom> result) {
		this.result = result;
	}
	public ExamRoom getModel() {
		return examroom;
	}
}