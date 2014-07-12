package net.cedu.action.book.purchaserequisitionerdetail;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.PurchaseRequisitionerDetailBiz;
import net.cedu.entity.book.PurchaseRequisitionerDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 学生领用分页
 * @author YY
 *
 */

@ParentPackage("json-default")
public class JsonPagePurchaseRequisitionerDetailAction extends BaseAction implements ModelDriven<PurchaseRequisitionerDetail> {
 
	private static final long serialVersionUID = 8590061511752004164L;
	
	@Autowired 
	private PurchaseRequisitionerDetailBiz purchaseRequisitionerDetailBiz; //中心申购单人员明细业务层
	private PurchaseRequisitionerDetail purchaseRequisitionerDetail=new PurchaseRequisitionerDetail(); //中心申购单人员明细实体
	private PageResult<PurchaseRequisitionerDetail> result=new PageResult<PurchaseRequisitionerDetail>(); //分页参数
	
	/**
	 * 分页数量
	 * @return
	 */
	@Action(value="page_count_purchaserequisitionerdetail", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String count()
	{
		try {
			result.setRecordCount(purchaseRequisitionerDetailBiz.findPurchaseRequisitionerDetailPageCountByConditions(purchaseRequisitionerDetail, result));
		} catch (Exception e) {			 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 分页集合
	 * @return
	 */
	@Action(value="page_list_purchaserequisitionerdetail", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String list()
	{
		try {
			result.setList(purchaseRequisitionerDetailBiz.findPurchaseRequisitionerDetailPageListByConditions(purchaseRequisitionerDetail, result));
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PurchaseRequisitionerDetail getPurchaseRequisitionerDetail() {
		return purchaseRequisitionerDetail;
	}

	public void setPurchaseRequisitionerDetail(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail) {
		this.purchaseRequisitionerDetail = purchaseRequisitionerDetail;
	}

	public PageResult<PurchaseRequisitionerDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<PurchaseRequisitionerDetail> result) {
		this.result = result;
	}
	public PurchaseRequisitionerDetail getModel() {
		 
		return purchaseRequisitionerDetail;
	}
	
}
