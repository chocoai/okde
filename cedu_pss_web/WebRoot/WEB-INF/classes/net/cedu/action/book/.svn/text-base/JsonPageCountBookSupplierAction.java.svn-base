package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 书商-分页数量
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = 626828829796219030L;

	@Autowired
	private BookSupplierBiz bsbiz;
	
	private PageResult<BookSupplier> result = new PageResult<BookSupplier>();
	
	@Action(value = "page_count_booksupplier", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(bsbiz.findAllBookSupplierCount(result));
		return SUCCESS;
	}

	public PageResult<BookSupplier> getResult() {
		return result;
	}

	public void setResult(PageResult<BookSupplier> result) {
		this.result = result;
	}
	
	
	
	
}
