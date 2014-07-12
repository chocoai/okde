package net.cedu.action.examination.invigilator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.examination.ExamRoom;
import net.cedu.entity.examination.Invigilator;
import net.cedu.model.page.PageResult;

@ParentPackage("json-default")
public class JsonInvigilatorShenpiAction extends BaseAction implements ModelDriven<Invigilator>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2077854554777537076L;

	@Autowired
	private InvigilatorBiz invigilatorbiz;
	
	private Invigilator invigilator=new Invigilator();

	PageResult<Invigilator> result=new PageResult<Invigilator>();
	
	//分页结果
	@Action(value="list_invigilator",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(invigilatorbiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	//分页数量
	@Action(value="count_invigilator",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
		try {
			result.setRecordCount(invigilatorbiz.findInvigilatorPageCount(invigilator, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//条件查询
	@Action(value="findByconditions_invigilator",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String findByconditions() 
	{
		try {
			result.setList(invigilatorbiz.findByConditions(invigilator,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public PageResult<Invigilator> getResult() {
		return result;
	}

	public void setResult(PageResult<Invigilator> result) {
		this.result = result;
	}
	
	
	public Invigilator getInvigilator() {
		return invigilator;
	}


	public void setInvigilator(Invigilator invigilator) {
		this.invigilator = invigilator;
	}



	public Invigilator getModel() {
		return invigilator;
	}
}
