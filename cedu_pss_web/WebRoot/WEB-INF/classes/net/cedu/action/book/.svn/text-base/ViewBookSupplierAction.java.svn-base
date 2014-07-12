package net.cedu.action.book;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;
/**
 * 书商单个查询
 * @author 	YY
 *
 */
public class ViewBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = -4339334871590368021L;

	@Autowired
	private BookSupplierBiz bsbiz; //书商业务层
	
	private int id; //书商id
	
	private BookSupplier bs; //书商实体
	
	public String execute()
	{
		try {
			bs=bsbiz.findBookSupplierById(id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
