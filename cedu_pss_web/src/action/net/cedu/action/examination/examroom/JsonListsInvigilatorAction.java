package net.cedu.action.examination.examroom;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.examination.Invigilator;
import net.cedu.model.page.PageResult;

@ParentPackage("json-default")
public class JsonListsInvigilatorAction extends BaseAction implements ModelDriven<Invigilator>{
	
	private static final long serialVersionUID = 1951330514526504687L;
	
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	
	private Invigilator invigilator=new Invigilator();

	PageResult<Invigilator> result=new PageResult<Invigilator>();
	
	//分页数量
	@Action(value="count_invigilators",
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
	@Action(value="findByconditions_invigilators",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
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