package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.entity.book.SupplierCategory;
import net.cedu.model.page.PageResult;
/**
 * 查询书商分类列表_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListSupplierCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = -2848495424505044305L;

	@Autowired
	private SupplierCategoryBiz suppliercategoryBiz;
	
	private PageResult<SupplierCategory> result = new PageResult<SupplierCategory>();
	
	@Action(value="page_list_suppliercategory",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(suppliercategoryBiz.findAllSupplierCategory(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<SupplierCategory> getResult() {
		return result;
	}

	public void setResult(PageResult<SupplierCategory> result) {
		this.result = result;
	}

	
}
