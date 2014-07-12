package net.cedu.action.finance.academyrebatereview;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.AcademyRebateAddRecordsBiz;
import net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonDeletePayAcademyCeduReviewAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	@Autowired
	private PlanedAcademyBillBiz pabBiz;//应收学校款_业务层接口
	
	@Autowired
	private AcademyBatchRebateCountBiz abrcBiz;//院校返款每个批次的返款总人数
	
	@Autowired
	private AcademyRebateFenPeiBranchBiz arfpbBiz;//院校返款追加金额分配给学习中心
	
	@Autowired
	private AcademyRebateAddRecordsBiz ararBiz;//院校返款追加记录
	
	private int payAcademyCeduId;//院校返款单Id
	private boolean isback=false;//页面返回参数
	private boolean isfail=false;//页面返回参数
	
	
	/**
	 * 删除院校返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_academy_rebate_cedu_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String darca() throws Exception 
	{
		if(payAcademyCeduId!=0)
		{
			//院校返款单被其他院校返款单追加金额时不允许删除
			AcademyRebateAddRecords arar =new AcademyRebateAddRecords();
			arar.setPayAcademyCeduAddId(payAcademyCeduId);
			int count=this.ararBiz.findAcademyRebateAddRecordsCountBy(arar);
			if(count==0)
			{
				//缴费单明细
				List<FeePaymentDetail> fpdList=this.fpdBiz.findFeePaymentDetailListByOrderAcademyCeduId(payAcademyCeduId);
				//应收院校款
				List<PlanedAcademyBill> pabList=this.pabBiz.findPlanedAcademyBillListByacademyRebateId(payAcademyCeduId);
				//院校返款每个批次的返款总人数
				List<AcademyBatchRebateCount> abrcList=this.abrcBiz.findAcademyBatchRebateCountListByPayAcademyCeduId(payAcademyCeduId);
				//院校返款追加金额分配给学习中心
				List<AcademyRebateFenPeiBranch> arfpbList=this.arfpbBiz.findAcademyRebateFenPeiBranchListByPayAcademyCeduId(payAcademyCeduId);
				//院校返款追加记录
				List<AcademyRebateAddRecords> ararList=this.ararBiz.findAcademyRebateAddRecordsListByPayAcademyCeduId(payAcademyCeduId);
				
				isback=this.payAcademyCeduBiz.deletePayAcademyCeduReview(payAcademyCeduId, fpdList, pabList,abrcList,arfpbList,ararList);
			}
			else
			{
				isfail=true;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 删除院校年度返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_academy_year_rebate_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String dayrca() throws Exception 
	{
		if(payAcademyCeduId!=0)
		{
			//院校返款单被其他院校返款单追加金额时不允许删除
			AcademyRebateAddRecords arar =new AcademyRebateAddRecords();
			arar.setPayAcademyCeduAddId(payAcademyCeduId);
			int count=this.ararBiz.findAcademyRebateAddRecordsCountBy(arar);
			if(count==0)
			{
				//缴费单明细
				List<FeePaymentDetail> fpdList=this.fpdBiz.findFpdListByOrderAcademyCeduIdForAcademyYearRebate(payAcademyCeduId);
				//院校返款每个批次的返款总人数
				List<AcademyBatchRebateCount> abrcList=this.abrcBiz.findAcademyBatchRebateCountListByPayAcademyCeduId(payAcademyCeduId);
				//院校返款追加金额分配给学习中心
				List<AcademyRebateFenPeiBranch> arfpbList=this.arfpbBiz.findAcademyRebateFenPeiBranchListByPayAcademyCeduId(payAcademyCeduId);
				//院校返款追加记录
				List<AcademyRebateAddRecords> ararList=this.ararBiz.findAcademyRebateAddRecordsListByPayAcademyCeduId(payAcademyCeduId);
				
				isback=this.payAcademyCeduBiz.deleteAcademyYearRebateReview(payAcademyCeduId, fpdList,abrcList,arfpbList,ararList);
			}
			else
			{
				isfail=true;
			}
		}
		return SUCCESS;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}


	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
	}


	public boolean isIsback() {
		return isback;
	}


	public void setIsback(boolean isback) {
		this.isback = isback;
	}


	public boolean isIsfail() {
		return isfail;
	}


	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
}
