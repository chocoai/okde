package net.cedu.action.book.purchaserequisitiondetail;

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
 * 申购单明细的ajax方法
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonPurchaseRequisitionDetailAction extends BaseAction {
 
	private static final long serialVersionUID = 5668377736825707713L;
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //申购单明细业务层
	@Autowired
	private BookBiz  bookBiz;  //教材业务层
	private List<PurchaseRequisitionDetail> purchaseRedetaillist=new ArrayList<PurchaseRequisitionDetail>(); //申购单明细集合	
	private int purchaseReDetailId; //申购单Id
	//根据申购单id查询申购明细表
	@Action(value = "purchase_requisitionby_detail_orderid_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })	
	public String execute(){		
		try {
			purchaseRedetaillist=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaseReDetailId);
			//根据教材id查询教材循环为申购明细显示教材信息循环赋值
			for (PurchaseRequisitionDetail prd : purchaseRedetaillist) {
			 Book book=bookBiz.findBookById(prd.getBookId());
			 prd.setBookauthor(book.getAuthor());
			 prd.setBookcode(book.getCode());
			 prd.setBookedition(book.getEdition());
			 prd.setBookisbn(book.getIsbn());
			 prd.setBookname(book.getName());
			 prd.setBookpress(book.getPress());
			 prd.setBookprice(book.getPrice());
			}			
		} catch (Exception e) {			 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public List<PurchaseRequisitionDetail> getPurchaseRedetaillist() {
		return purchaseRedetaillist;
	}
	public void setPurchaseRedetaillist(
			List<PurchaseRequisitionDetail> purchaseRedetaillist) {
		this.purchaseRedetaillist = purchaseRedetaillist;
	}
	public int getPurchaseReDetailId() {
		return purchaseReDetailId;
	}
	public void setPurchaseReDetailId(int purchaseReDetailId) {
		this.purchaseReDetailId = purchaseReDetailId;
	}
	
	
	
	
	
 
	
	
	
}
