package net.cedu.action.book.purchaserequisitiondetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 申购单明细发货功能
 * @author YY
 *
 */
public class UpdatePurchaseRequisitionDetailAction extends BaseAction {
 
	private static final long serialVersionUID = -1779251047851374628L;

	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //申购单明细业务层
	@Autowired
	private PurchaseRequisitionBiz  purchaseRequisitionBiz; //申购单业务层
 
	private  PurchaseRequisitionDetail purchaseRequisitionDetail=new PurchaseRequisitionDetail(); //申购单明细实体
	
	private int purchaseId; //申购单id
	
	private List<PurchaseRequisitionDetail> detaillist=new ArrayList<PurchaseRequisitionDetail>();
	
	private String array; //发货数量数组
	
	@Action(value = "update_purchase_requisition_detail", results = { @Result(name = "input", type = "redirect", location = "index_headquarters_on_behalf")
			,@Result(name = "success", type = "redirect", location = "index_headquarters_on_behalf")})	                       
	public String execute() throws Exception {
		try{
		//查询申购单明细集合
		detaillist=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaseId);
		String[] ary =array.split(","); //转换成数组
		for (int i = 0; i <ary.length ; i++) {      //循环实现批量增加
			purchaseRequisitionDetail=detaillist.get(i);
			int x=Integer.parseInt(ary[i])+detaillist.get(i).getOrderedAmount();
			//判断是否超出申请数量
			if(purchaseRequisitionDetail.getRequiredAmount()>=x)
			{				
			if(purchaseRequisitionDetail.getRequiredAmount()==x)
			{
				PurchaseRequisition  pr= purchaseRequisitionBiz.findById(purchaseRequisitionDetail.getPurchaseRequisitionId());
				pr.setStatus(Constants.STATUS_HAS_DISTRIBUTED);
				purchaseRequisitionBiz.updatePurchaseRequisition(pr);
			}
			purchaseRequisitionDetail.setOrderedAmount(x);
			purchaseRequisitionDetailBiz.updatePurchaseRequisitionDetail(purchaseRequisitionDetail);
			}else{	
				return INPUT;
			}
		}
	} catch (Exception e) {
		e.getStackTrace();
		return INPUT;
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


	public int getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}


	public List<PurchaseRequisitionDetail> getDetaillist() {
		return detaillist;
	}


	public void setDetaillist(List<PurchaseRequisitionDetail> detaillist) {
		this.detaillist = detaillist;
	}


	public String getArray() {
		return array;
	}


	public void setArray(String array) {
		this.array = array;
	}
}
