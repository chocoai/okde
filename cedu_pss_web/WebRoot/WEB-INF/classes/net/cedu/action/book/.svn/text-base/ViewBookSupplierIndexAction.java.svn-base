package net.cedu.action.book;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;
/**
 * 设置教材目录
 * @author YY
 *
 */
public class ViewBookSupplierIndexAction extends BaseAction {
 
	private static final long serialVersionUID = -8439462958503741091L;

	@Autowired
	private BookSupplierBiz bsbiz; //书商业务层接口
	
	private int id;  //书商id
	
	private BookSupplier bs =new BookSupplier();   //书商实体
	
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
