package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.entity.book.BookCategory;
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
public class JsonPageCountBookCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = 5965569944306297464L;

	@Autowired
	private BookCategoryBiz bookcategorybiz;
	
	private PageResult<BookCategory> result = new PageResult<BookCategory>();
	/**
	 * 根据条件查询教材分类数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_bookcategory", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(bookcategorybiz.findAllBookCategoryCount(result));
		return SUCCESS;
	}
	public PageResult<BookCategory> getResult() {
		return result;
	}
	public void setResult(PageResult<BookCategory> result) {
		this.result = result;
	}
}
