package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.entity.book.BookCategory;
import net.cedu.model.page.PageResult;
/**
 * 查询教材分类列表_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListBookCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = 3837430941243719795L;

	@Autowired
	private BookCategoryBiz bookcategoryBiz;
	
	private PageResult<BookCategory> result = new PageResult<BookCategory>();
	
	@Action(value="page_list_bookcategory",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(bookcategoryBiz.findAllBookCategory(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<BookCategory> getResult() {
		return result;
	}

	public void setResult(PageResult<BookCategory> result) {
		this.result = result;
	}
}
