package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 书商状态修改
 * @author Administrator
 *
 */
@ParentPackage("json-default")
public class JsonUpdateBookSupplierStatusAction extends BaseAction implements ModelDriven<BookSupplier>{
	
	private static final long serialVersionUID = -250195408377127464L;
	
	@Autowired
	private BookSupplierBiz bsbiz;
	private BookSupplier bs=new BookSupplier();;
	private boolean results=false;
	
	
	@Action(value="update_book_supplier_status", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"includeProperties","results"
					} )})
	public String execute()
	{
		int status=bs.getStatus();
		try {
			bs=bsbiz.findBookSupplierById(bs.getId());
			bs.setStatus(status);
			bsbiz.updateBookSupplier(bs);
			results=true;
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

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}

	public BookSupplier getModel() {
	 
		return bs;
	}
	
}
