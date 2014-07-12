package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;
/**
 * 书商增加
 * @author XFY
 *
 */
public class AddBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = -8490160421439661772L;

	@Autowired
	private	BookSupplierBiz bsbiz;
	
	private BookSupplier bs;
	private boolean result=false;
	
	@Action(value="add_booksupplier",results={@Result(type="redirect",location="index_book_supplier")})
	public String execute()
	{
		try {
			bsbiz.addBookSupplier(bs);
			result=true;
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public BookSupplier getBs() {
		return bs;
	}

	public void setBs(BookSupplier bs) {
		this.bs = bs;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}
