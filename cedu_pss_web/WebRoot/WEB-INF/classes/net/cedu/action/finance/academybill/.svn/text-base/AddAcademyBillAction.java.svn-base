package net.cedu.action.finance.academybill;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加应收院校款
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="input", type="dispatcher", location="add_academy_bill.jsp"),
	@Result(name="success", type="redirect", location="/finance/academybill/view_academy_bill?billId=${model.id}")
})
public class AddAcademyBillAction extends BaseAction implements ModelDriven<PlanedAcademyBill>
{
	private static final long serialVersionUID = 9095886108062242503L;
	
	private PlanedAcademyBill planedAcademyBill = new PlanedAcademyBill();
	
	private File upload;
	private String uploadFileName;
	
	private List<Academy> academies;
	private List<BaseDict> billWays;
	
//	private int billId;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademyBillBiz;
	
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	private List<Branch> branchlist=new ArrayList<Branch>();
	
	
	public String execute() throws Exception
	{
		if(isGetRequest()){
			return doView();
		}
		
		return doSave();
	}
	
	private String doView() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academies, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});			
		billWays = baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_BILLRECEIVEDWAY);
		branchlist=this.branchBiz.findBranchForModel();
		Collections.sort(branchlist, new Comparator() {
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
	
	private String doSave() throws Exception
	{
		if(upload != null){
			String attachmentPath = saveAttachment();
			planedAcademyBill.setAttachmentPath(attachmentPath);
		}
		
		planedAcademyBill.setCreatorId(getUser().getUserId());
		planedAcademyBill.setCreatedTime(new Date());
		
		planedAcademyBill.setUpdaterId(getUser().getUserId());
		planedAcademyBill.setUpdatedTime(new Date());
		
		planedAcademyBill.setDeleteFlag(Constants.DELETE_FALSE);
		
		
		int billId = planedAcademyBillBiz.add(planedAcademyBill);
		
		System.err.println(billId);
		
		return SUCCESS;
	}
	
	private String saveAttachment() throws IOException {
		String path = ResourcesTool.getText("finance", "academy.bill.attachment.path");
		path = ServletActionContext.getServletContext().getRealPath(path);
		
		try {
			return FileUtil.FileUploads(path, uploadFileName, upload);
		} catch (Exception e) {
			throw new IOException();
		}
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<BaseDict> getBillWays() {
		return billWays;
	}

	public PlanedAcademyBill getModel() {
		return planedAcademyBill;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}
	
}
