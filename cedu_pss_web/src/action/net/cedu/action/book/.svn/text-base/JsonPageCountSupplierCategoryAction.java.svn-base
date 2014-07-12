package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.entity.book.SupplierCategory;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询教材分类条数_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountSupplierCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = 8942586083804569081L;

	@Autowired
	private SupplierCategoryBiz suppliercategorybiz;
	
	private PageResult<SupplierCategory> result = new PageResult<SupplierCategory>();
	/**
	 * 根据条件查询教材分类数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_suppliercategory", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
			
			
	public String execute() throws Exception 
	{
		result.setRecordCount(suppliercategorybiz.findAllSupplierCategoryCount(result));
		return SUCCESS;
	}
	
	
	public PageResult<SupplierCategory> getResult() {
		return result;
	}
	public void setResult(PageResult<SupplierCategory> result) {
		this.result = result;
	}
	
	}

