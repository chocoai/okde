package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.entity.examination.ClassRoom;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("json-default")
public class JsonListClassroom extends BaseAction implements ModelDriven<ClassRoom> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6512368548456189020L;
	@Autowired
	private ClassRoomBiz classroombiz;
	
	private  ClassRoom classroom=new ClassRoom();
	PageResult<ClassRoom> result=new PageResult<ClassRoom>();
	
	
	public ClassRoom getClassroom() {
		return classroom;
	}


	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}


	public PageResult<ClassRoom> getResult() {
		return result;
	}


	public void setResult(PageResult<ClassRoom> result) {
		this.result = result;
	}


	//分页结果
	@Action(value="list_classroom",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(classroombiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_classroom",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(classroombiz.findAllClassRoomCount(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//条件查询
	@Action(value="find_classroom",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String executes()
	{
		try {
			result.setList(classroombiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	
	
	
	
	
	public ClassRoom getModel(){
		return classroom;
	}
	
	
	
	

}
