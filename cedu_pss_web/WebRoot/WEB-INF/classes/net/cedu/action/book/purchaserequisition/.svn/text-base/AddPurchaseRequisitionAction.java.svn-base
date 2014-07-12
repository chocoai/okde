package net.cedu.action.book.purchaserequisition;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 总部申购单，总部申购单明细增加
 * @author YY
 *
 */
public class AddPurchaseRequisitionAction extends BaseAction {

	private static final long serialVersionUID = 2443805231758360630L;

	@Autowired
	private PurchaseRequisitionBiz  purchaseRequisitionBiz; //申购单业务层
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //申购单明细业务层
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	@Autowired
	private BookBiz bookBiz; //教材业务层
	
	private PurchaseRequisitionDetail purchaseRequisitionDetail=new PurchaseRequisitionDetail();//申购单明细实体
	 
	private PurchaseRequisition  purchaseRequisition =new PurchaseRequisition(); //申购单实体
	
	private String array;   //申购数量字符串
	private String bookids; //教材id字符串
	private String address; //地址
	private double avg; //总金额
	@Action(results={@Result(name="success",type="redirect",location="purchase_requisition_book")})
	public String execute() throws Exception {
			//code生成器生成一个code
			String code=(buildCodeBiz.getCodes(CodeEnum.PurchaseRequisitionTB
					.getCode(), CodeEnum.PurchaseRequisition.getCode()));
			purchaseRequisition.setCode(code);			
			purchaseRequisition.setDeleteFlag(0);
			purchaseRequisition.setCreatorId(this.getUser().getUserId());
			purchaseRequisition.setUpdaterId(0);
			purchaseRequisition.setUpdatedTime(new Date());
			purchaseRequisition.setCreatedTime(new Date());
			purchaseRequisition.setRequisitiontime(new Date());
			purchaseRequisition.setAddress(address);
			purchaseRequisition.setStatus(1);
			purchaseRequisitionBiz.addPurchaseRequisition(purchaseRequisition);
			//增加明细单
			if(purchaseRequisition!=null)
			{
				String[] ary =array.split(","); //转换成数组
				String[] bok=bookids.split(","); //转换数组
				//循环增加申购明细单
				for (int i = 0; i < ary.length; i++) {
					purchaseRequisitionDetail.setPurchaseRequisitionId(purchaseRequisition.getId());
					purchaseRequisitionDetail.setBookId(Integer.parseInt(bok[i]));
					purchaseRequisitionDetail.setRequiredAmount(Integer.parseInt(ary[i]));
					purchaseRequisitionDetail.setOrderedAmount(0);
					purchaseRequisitionDetail.setDeleteFlag(0);
					purchaseRequisitionDetail.setCreatorId(this.getUser().getUserId());
					purchaseRequisitionDetail.setUpdaterId(0);
					purchaseRequisitionDetail.setUpdatedTime(new Date());
					purchaseRequisitionDetail.setCreatedTime(new Date());
					purchaseRequisitionDetail.setPurchaseTime(new Date());
					Book book=bookBiz.findBookById(Integer.parseInt(bok[i]));
					purchaseRequisitionDetail.setPrice((int)book.getPrice());
					avg+=(purchaseRequisitionDetail.getRequiredAmount()*book.getPrice());
					purchaseRequisitionDetailBiz.addPurchaseRequisitionDetail(purchaseRequisitionDetail);
				}
			}
			//给申购单总金额赋值
			if(0!=avg)
			{
				 purchaseRequisition.setAmount(avg);
				 purchaseRequisitionBiz.updatePurchaseRequisition(purchaseRequisition);
			}
		
		return SUCCESS;
	}
	
	
	public PurchaseRequisitionDetail getPurchaseRequisitionDetail() {
		return purchaseRequisitionDetail;
	}
	public void setPurchaseRequisitionDetail(
			PurchaseRequisitionDetail purchaseRequisitionDetail) {
		this.purchaseRequisitionDetail = purchaseRequisitionDetail;
	}
	public PurchaseRequisition getPurchaseRequisition() {
		return purchaseRequisition;
	}
	public void setPurchaseRequisition(PurchaseRequisition purchaseRequisition) {
		this.purchaseRequisition = purchaseRequisition;
	}
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}
	public String getBookids() {
		return bookids;
	}
	public void setBookids(String bookids) {
		this.bookids = bookids;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}

}
