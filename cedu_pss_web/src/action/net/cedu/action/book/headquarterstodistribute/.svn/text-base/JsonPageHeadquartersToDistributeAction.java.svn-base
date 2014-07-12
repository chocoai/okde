package net.cedu.action.book.headquarterstodistribute;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.HeadquartersToDistributeBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.entity.book.HeadquartersToDistribute;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 总部派发派发单分页AJAX
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonPageHeadquartersToDistributeAction extends BaseAction {
 
	private static final long serialVersionUID = 3857742217787704173L;
	@Autowired
	private HeadquartersToDistributeBiz headquartersToDistributeBiz;  //总部派发业务层
	private List<HeadquartersToDistribute> purchaseList=new ArrayList<HeadquartersToDistribute>(); //总部派发集合
	
	private HeadquartersToDistribute headquartersToDistribute=new HeadquartersToDistribute();//总部派发实体
	private PageResult<HeadquartersToDistribute> result=new PageResult<HeadquartersToDistribute>(); //分页参数
	
	@Action(value="page_list_headquarterstodistribute", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType", "text/json"
					})})
	/**
	 *  分页集合
	 */
	public String list()
	{
		
		try {
			 
			result.setList(headquartersToDistributeBiz.findHeadquartersToDistributePageList(headquartersToDistribute, result));
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value="page_count_headquarterstodistribute", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	/**
	 *  分页数量
	 */
	public String count()
	{
		
		try {
			result.setRecordCount(headquartersToDistributeBiz.findHeadquartersToDistributePageCount(headquartersToDistribute, result));

		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<HeadquartersToDistribute> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<HeadquartersToDistribute> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public HeadquartersToDistribute getHeadquartersToDistribute() {
		return headquartersToDistribute;
	}

	public void setHeadquartersToDistribute(
			HeadquartersToDistribute headquartersToDistribute) {
		this.headquartersToDistribute = headquartersToDistribute;
	}

	public PageResult<HeadquartersToDistribute> getResult() {
		return result;
	}

	public void setResult(PageResult<HeadquartersToDistribute> result) {
		this.result = result;
	}
	
	 
	
	
	
}
