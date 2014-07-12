package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.entity.book.Storeroom;
import net.cedu.model.page.PageResult;
/**
 * 库房位置分页-list
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListStoreroomAction extends BaseAction {
	
	 
	private static final long serialVersionUID = -139297591722500438L;

	@Autowired
	private StoreroomBiz storeroombiz;
	
	private PageResult<Storeroom> result = new PageResult<Storeroom>();
	
	@Action(value="page_list_storeroom",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(storeroombiz.findAllStoreroom(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<Storeroom> getResult() {
		return result;
	}

	public void setResult(PageResult<Storeroom> result) {
		this.result = result;
	}
	
}
