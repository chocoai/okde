package net.cedu.action.book.cedubookorderdetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 总部订购明细显示页面ajax方法
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonCeduBookOrderDetailAction extends BaseAction {

	private static final long serialVersionUID = -3215066420285728706L;

	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //总部采购明细业务层
	@Autowired
	private BookBiz bookBiz; //教材业务层
	
	private List<PurchaseRequisitionDetail> purchaseDetaillist=new ArrayList<PurchaseRequisitionDetail>();//总部采购明细集合
	private List<Book> booklist=new ArrayList<Book>(); //教材集合
	private int purchaseReDetailId;   //申购单id

	/**
	 * 根据id查询总部采购单明细
	 */
	@Action(value = "find_cedubookorderdetail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute()
	{
		try {
			 
			purchaseDetaillist=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaseReDetailId);
				 
			//循环给订购单的教材属性复制
			for (PurchaseRequisitionDetail detail : purchaseDetaillist)
			{
				Book book=bookBiz.findBookById(detail.getBookId());
				detail.setBookauthor(book.getAuthor());
				detail.setBookcode(book.getCode());
				detail.setBookisbn(book.getIsbn());
				detail.setBookedition(book.getEdition());
				detail.setBookname(book.getName());
				detail.setBookpress(book.getPress());
				detail.setBookprice(book.getPrice());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
 
	public List<PurchaseRequisitionDetail> getPurchaseDetaillist() {
		return purchaseDetaillist;
	}

	public void setPurchaseDetaillist(
			List<PurchaseRequisitionDetail> purchaseDetaillist) {
		this.purchaseDetaillist = purchaseDetaillist;
	}

 
	public List<Book> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	public int getPurchaseReDetailId() {
		return purchaseReDetailId;
	}

	public void setPurchaseReDetailId(int purchaseReDetailId) {
		this.purchaseReDetailId = purchaseReDetailId;
	}

 
	
}
