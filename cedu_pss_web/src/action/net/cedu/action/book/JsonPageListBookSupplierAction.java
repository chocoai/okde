package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;
import net.cedu.model.page.PageResult;
/**
 * 书商分页结果
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = -7598181894791333537L;

	@Autowired
	private BookSupplierBiz  bsbiz;
	
	private PageResult<BookSupplier> result=new PageResult<BookSupplier>();
	
	
	@Action(value="page_list_booksupplier",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(bsbiz.findAllBookSupplier(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public PageResult<BookSupplier> getResult() {
		return result;
	}


	public void setResult(PageResult<BookSupplier> result) {
		this.result = result;
	}
	
}
