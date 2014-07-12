package net.cedu.action.book.purchaserequisition;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.entity.admin.User;
import net.cedu.entity.book.PurchaseRequisition;

/**
 * 申购单的ajax方法
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonPurchaseRequisitionAction extends BaseAction {
 
	private static final long serialVersionUID = 2738800702920766829L;
	
	@Autowired
	private PurchaseRequisitionBiz purchaseRequisitionBiz; //申购单业务层
	@Autowired
	private UserBiz  userBiz;  //用户业务层
	private List<PurchaseRequisition> purchaseRelist=new ArrayList<PurchaseRequisition>(); //申购单集合	
	private int purchaseReId; //申购单Id
	@Action(value = "purchase_requisitionby_id_ajax", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })	
	public String execute(){		
		try {
			//根据订购单编号查询订购单
			purchaseRelist=purchaseRequisitionBiz.findIdByPurchaseRequisition(purchaseReId);
			//循环给订购人赋值
			for (PurchaseRequisition pr : purchaseRelist) {
				User user=userBiz.findUserById(pr.getRequisitioner());
				pr.setRequisitionername(user.getFullName());
			}			
		} catch (Exception e) {			 
			e.printStackTrace();
		}
		return SUCCESS;
	}
 
	public List<PurchaseRequisition> getPurchaseRelist() {
		return purchaseRelist;
	}
	public void setPurchaseRelist(List<PurchaseRequisition> purchaseRelist) {
		this.purchaseRelist = purchaseRelist;
	}
	public int getPurchaseReId() {
		return purchaseReId;
	}
	public void setPurchaseReId(int purchaseReId) {
		this.purchaseReId = purchaseReId;
	}

}
