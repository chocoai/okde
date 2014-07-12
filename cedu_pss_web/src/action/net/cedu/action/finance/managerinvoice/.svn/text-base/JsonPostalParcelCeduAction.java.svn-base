package net.cedu.action.finance.managerinvoice;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PostalParcelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.finance.PostalParcel;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json邮寄单
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonPostalParcelCeduAction extends BaseAction {

	
	
	@Autowired
	private PostalParcelBiz postalparcelBiz;              //邮寄单biz
	
	


	// 分页结果
	private PageResult<PostalParcel> result = new PageResult<PostalParcel>();
	private int id;                                         //Id
	private int branchId;                                   //中心ID
	private Object[] chk;                                   //Id集合
	private String chkName;                   
	private String statusIds="0";                            
	
	/**
	 * 发票集合(分页)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listpostalparcel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId,statusIds" }) })
	public String ListPostalParcel() throws Exception {
		// 1,已配送，未签收  2,已配送已签收
		result.setList(postalparcelBiz.findPostalParcelByBranchId(branchId,statusIds, result)); 
		return SUCCESS;
	}
	
	
	/**
	 * 发票(分页数量)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countpostalparcel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId,statusIds"
	}) })
	public String CountPostalParcel() throws Exception {
		// 1,已配送，未签收  2,已配送已签收
		result.setRecordCount(postalparcelBiz.countPostalParcelByBranchId(branchId,statusIds, result)); 
		return SUCCESS;
	}
	
	
	
	/**
	 * 批量配送邮寄单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updtepostalparcelstatus", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdatePostalParcelStatus() throws Exception {
		
		
		chk=chkName.split(",");
		for(int i=0;i< chk.length;i++)
		{
			PostalParcel postalparcel=postalparcelBiz.findPostalParcelById(Integer.valueOf(chk[i].toString()));
			//postalparcel.setStatus(Constants.IS_POST_TRUE);
			//已配送
			postalparcel.setStatus(1);
			postalparcel.setUpdaterId(super.getUser().getUserId());
			postalparcel.setUpdatedTime(new Date());
			postalparcelBiz.updatePostalParcel(postalparcel);
		}
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 邮寄单签收
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updtepostalparcel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdatePostalParcel() throws Exception {
		
		
		PostalParcel postalparcel=postalparcelBiz.findPostalParcelById(id);
		//postalparcel.setStatus(Constants.IS_POST_TRUE);
		//签收
		postalparcel.setStatus(2);
		postalparcel.setUpdaterId(super.getUser().getUserId());
		postalparcel.setUpdatedTime(new Date());
		postalparcelBiz.updatePostalParcel(postalparcel);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	

	public PageResult<PostalParcel> getResult() {
		return result;
	}
	
	
	public void setResult(PageResult<PostalParcel> result) {
		this.result = result;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Object[] getChk() {
		return chk;
	}

	public void setChk(Object[] chk) {
		this.chk = chk;
	}


	public String getChkName() {
		return chkName;
	}


	public void setChkName(String chkName) {
		this.chkName = chkName;
	}


	public String getStatusIds() {
		return statusIds;
	}


	public void setStatusIds(String statusIds) {
		this.statusIds = statusIds;
	}
	
	
	
	
	
	
	
	
	
	
}
