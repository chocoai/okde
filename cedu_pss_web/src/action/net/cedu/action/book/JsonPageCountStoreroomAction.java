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
 * 库房位置-数量
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountStoreroomAction extends BaseAction {
 
	private static final long serialVersionUID = -2691870293392678521L;

	@Autowired
	private StoreroomBiz storeroombiz;
	
	private PageResult<Storeroom> result = new PageResult<Storeroom>();
	/**
	 * 根据条件查询教材分类数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_storeroom", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(storeroombiz.findAllStoreroomCount(result));
		return SUCCESS;
	}
	public PageResult<Storeroom> getResult() {
		return result;
	}
	public void setResult(PageResult<Storeroom> result) {
		this.result = result;
	}
	
	
}
