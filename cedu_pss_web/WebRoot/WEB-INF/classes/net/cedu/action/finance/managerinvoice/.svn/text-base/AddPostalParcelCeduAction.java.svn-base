package net.cedu.action.finance.managerinvoice;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.biz.finance.PostalParcelBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.InvoiceManagement;
import net.cedu.entity.finance.PostalParcel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加邮寄单
 * 
 * @author gaolei
 * 
 */

public class AddPostalParcelCeduAction extends BaseAction {

	
	
	@Autowired
	private BranchBiz branchbiz;                   //学习中心Biz

	@Autowired
	private PostalParcelBiz postalparcelbiz;    //邮寄包Biz
	
	@Autowired
	private  InvoiceManagementBiz  invoicemanagementbiz; //发票管理Biz
	
	
	
	private List<Branch> branchlst=new ArrayList<Branch>();//学习中心数据
	
	private String invoiceCode;      //发票号
	private Date  issuedTime;        //开票日期
	private int branchId;            //学习中心
	private int studentId;           //学生ID
	private Object[] checkbox123;    //发票号Ids
	private String ispost;           //是否邮寄
	private String postSerialNo;     //邮寄单号
	private String code;             //邮包号
	private String forwarder;        //货运公司
	private String invoiceids;       //发票IDs
	
	
	
	@Action(results = { @Result(name = "input", location = "add_postal_parcel_cedu.jsp"),
			@Result(name = "success",type="redirect", location = "list_postal_parcel_cedu?tab=2")
	})
	public String excute() throws Exception
	{
		if(super.isGetRequest())
		{
			//学习中心信息
			branchlst=branchbiz.findListById(super.getUser().getOrgId());
			Collections.sort(branchlst, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			return INPUT;	
		}
		PostalParcel postalparcel=new PostalParcel();
		if(ispost!=null)
		{
			postalparcel.setStatus(Constants.IS_POST_TRUE);
		}
		if(checkbox123!=null)
		{
			checkbox123=invoiceids.split(",");
			postalparcel.setInvoiceIds(StringUtil.strObjects(checkbox123));
		}
		postalparcel.setPostSerialNo(postSerialNo);
		postalparcel.setCode(code);
		postalparcel.setForwarder(forwarder);
		postalparcel.setBranchId(branchId);
		postalparcel.setCreatorId(super.getUser().getUserId());
		postalparcel.setCreatedTime(new Date());
		Boolean bol=postalparcelbiz.addPostalParcel(postalparcel);
		if(bol)
		{
			Object [] ids=StringUtil.strToObject(postalparcel.getInvoiceIds());
			if(ids!=null && ids.length>0)
			{
				for(int i=0 ;i<ids.length;i++)
				{
					InvoiceManagement im=invoicemanagementbiz.findInvoiceManagementById(Integer.valueOf(ids[i].toString()));
					im.setIsPost(Constants.IS_POST_TRUE);
					im.setUpdaterId(super.getUser().getUserId());
					im.setUpdatedTime(new Date());
					invoicemanagementbiz.updateInvoiceManagement(im);
				}
			}
			
		}
		
		
		return SUCCESS;	
	}

	public List<Branch> getBranchlst() {
		return branchlst;
	}

	public void setBranchlst(List<Branch> branchlst) {
		this.branchlst = branchlst;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	

	public Object[] getCheckbox123() {
		return checkbox123;
	}

	public void setCheckbox123(Object[] checkbox123) {
		this.checkbox123 = checkbox123;
	}

	public String getPostSerialNo() {
		return postSerialNo;
	}

	public void setPostSerialNo(String postSerialNo) {
		this.postSerialNo = postSerialNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForwarder() {
		return forwarder;
	}

	public void setForwarder(String forwarder) {
		this.forwarder = forwarder;
	}

	public String getIspost() {
		return ispost;
	}

	public void setIspost(String ispost) {
		this.ispost = ispost;
	}

	public String getInvoiceids() {
		return invoiceids;
	}

	
	public void setInvoiceids(String invoiceids) {
		this.invoiceids = invoiceids;
	}


	

	

	
	

	
	
}
