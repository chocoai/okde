package net.cedu.action.book.purchaserequisitionerdetail;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.PurchaseRequisitionerDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.book.PurchaseRequisitionerDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 领用单领用方法
 * @author YY
 *
 */
public class UpdatePurchaseRequisitionerDetailAction extends BaseAction {
 
	private static final long serialVersionUID = -4203525102442022235L;

	@Autowired
	private PurchaseRequisitionerDetailBiz purchaseRequisitionerDetailBiz; //领用单业务层
//	@Autowired
//	private StudentBiz studentBiz ; //学生业务层
	private int purchaseid; //领用单id
	private PurchaseRequisitionerDetail  purchaseRequisitionerDetail=new PurchaseRequisitionerDetail(); //领用单实体
	
	
	@Action(value="update_purchase_requisitioner_detail",results={@Result(name="success",type="redirect",location="index_purchase_requisitioner_detail")})
	public String execute() throws Exception {
		purchaseRequisitionerDetail=purchaseRequisitionerDetailBiz.findByid(purchaseid);
		
		if(purchaseRequisitionerDetail!=null)
		{
			//已扣费功能 
//			Student student=studentBiz.findStudentById(purchaseRequisitionerDetail.getStudentId());
//			if(null!=student)
//			{
//				if(student.getHasAccount()==1)
//				{
//					
//				}
//			}	
		purchaseRequisitionerDetail.setStatus(Constants.BOOK_PURCHASE_REQUISITIONER_USE_COLLAR);
		purchaseRequisitionerDetail=purchaseRequisitionerDetailBiz.updateStatusByPurchaseRequisitionerDetail(purchaseRequisitionerDetail);
		return SUCCESS;
		}
		return INPUT;
	}
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}
	public PurchaseRequisitionerDetail getPurchaseRequisitionerDetail() {
		return purchaseRequisitionerDetail;
	}
	public void setPurchaseRequisitionerDetail(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail) {
		this.purchaseRequisitionerDetail = purchaseRequisitionerDetail;
	} 
	
}
