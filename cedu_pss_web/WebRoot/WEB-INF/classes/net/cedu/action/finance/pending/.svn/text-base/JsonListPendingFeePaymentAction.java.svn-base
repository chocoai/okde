package net.cedu.action.finance.pending;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.finance.PendingFeePayment;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单外部接口
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonListPendingFeePaymentAction extends BaseAction
{
	@Autowired
	private AcademyBiz academyBiz;//院校业务层接口
	private List<Academy> academyList=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层接口
	private List<FeeSubject> feeSubjectList=new ArrayList<FeeSubject>();//费用科目集合
	
	//*******************批量生成**********************//
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;//待缴费单业务接口
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	
	//参数
	private int academyId;//院校Id
	private int feeSubjectId;//费用科目Id
	
	private boolean isfail=false;
	
	/**
	 * 获取院校和费用科目集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_academy_feesubject_list_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String policyCount() throws Exception 
	{		
		academyList=this.academyBiz.findAllAcademyByFlagFalse();
		feeSubjectList=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		return SUCCESS;
	}
	
	/**
	 * 批量生成待缴费单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_batch_pending_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addbatchpending() throws Exception 
	{		
		List<PendingFeePayment> pendingList=this.pendingFeePaymentBiz.generatePendingFeePaymentListByAcademyIdFeeSubjectId(academyId, feeSubjectId);
		if(pendingList!=null && pendingList.size()>0)
		{
			for(PendingFeePayment pending:pendingList)
			{
				pending.setCode(buildCodeBiz.getCodes(CodeEnum.PendingFeePaymentTB.getCode(),CodeEnum.PendingFeePaymentPrefix.getCode()));
				pending.setCreatorId(super.getUser().getUserId());
				pending.setUpdaterId(super.getUser().getUserId());
				pending.setUpdatedTime(new Date());
			}
			isfail=this.pendingFeePaymentBiz.addBatchPendingFeePayment(pendingList);
		}
		return SUCCESS;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	public List<FeeSubject> getFeeSubjectList() {
		return feeSubjectList;
	}

	public void setFeeSubjectList(List<FeeSubject> feeSubjectList) {
		this.feeSubjectList = feeSubjectList;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
	
}
