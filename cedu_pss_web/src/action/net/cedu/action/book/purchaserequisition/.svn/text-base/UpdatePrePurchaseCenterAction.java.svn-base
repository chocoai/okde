package net.cedu.action.book.purchaserequisition;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.common.Constants;
import net.cedu.entity.book.PurchaseRequisition;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdatePrePurchaseCenterAction extends BaseAction {

	private static final long serialVersionUID = 8245522923658551199L;
	@Autowired
	private PurchaseRequisitionBiz purchaseRequisitionBiz; // 中心申购业务层

	private PurchaseRequisition purchaseRequisition = new PurchaseRequisition();// 中心申购实体

	private String address; // 地址
	private String array; // 申购单id字符串

	@Action(value = "update_purchase_requisition", results = { @Result(name = "success", type = "redirect", location = "index_page_purchase_requisition") })
	public String execute() {
		try {
			String[] ary = array.split(","); //转换字符串
			for (int i = 0; i < ary.length; i++) {
				//查询申购单
					purchaseRequisition = purchaseRequisitionBiz.findById(Integer
							.parseInt(ary[i]));
				//更新申购单
				if (purchaseRequisition != null) {
					purchaseRequisition.setAddress(address);
					purchaseRequisition.setStatus(Constants.BOOK_STATUS_PURCHASE);
					purchaseRequisitionBiz.updatePurchaseRequisition(purchaseRequisition);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public PurchaseRequisition getPurchaseRequisition() {
		return purchaseRequisition;
	}

	public void setPurchaseRequisition(PurchaseRequisition purchaseRequisition) {
		this.purchaseRequisition = purchaseRequisition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}
	
	
	
}
