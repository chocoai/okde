package net.cedu.action.book.cedubookorderdetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 总部订购明细显示页面
 * @author YY
 *
 */
public class IndexCeduBookOrderDetailAction extends BaseAction {

	private static final long serialVersionUID = -3215066420285728706L;

	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //总部采购明细业务层
	@Autowired
	private BookBiz bookBiz; //教材业务层	
	private List<PurchaseRequisitionDetail> purchaseDetaillist=new ArrayList<PurchaseRequisitionDetail>();//总部采购明细集合
	private List<Book> booklist=new ArrayList<Book>(); //教材集合
	private String orderids;   //采购单id

	/**
	 * 根据id查询总部采购单明细
	 */
	@Action(value="index_cedu_book_order_detail")
	public String execute()
	{
		try {
			String[] order= orderids.split(","); // 转换成数组
			for (int i = 0; i < order.length; i++) {
				List<PurchaseRequisitionDetail> plist=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition((Integer.parseInt(order[i])));
				for (int j = 0; j < plist.size(); j++) {
					purchaseDetaillist.add(plist.get(i));
				}
			}
			//循环给订购单的教材属性复制
			for (PurchaseRequisitionDetail detail : purchaseDetaillist)
			{	
				if(detail!=null)
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

	public String getOrderids() {
		return orderids;
	}
	public void setOrderids(String orderids) {
		this.orderids = orderids;
	}
	public List<Book> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}
	
}
