package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.PolicyFeeDetail;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加收费政策明细
 * @author lixiaojun
 *
 */

@Results({
	@Result(name="index",location="list_policy_fee_detail",type="redirectAction")
	})
public class AddPolicyFeeDetailAction extends BaseAction
{
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合


	public String execute() throws Exception 
	{		
		if(super.isGetRequest())
		{	
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
			if(academylist!=null && academylist.size()>0)
			{
				Collections.sort(academylist, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Academy) arg0).getName();
						String name2 = ((Academy) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
			feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
			return INPUT;
		}
//		//添加政策
//		policyFeeDetail.setAduitStatus(Constants.AUDIT_STATUS_FALSE);
//		policyFeeDetail.setBatchId(batchids);
//		//policyFeeDetail.setBranches(branchids);
//		policyFeeDetail.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
//		policyFeeDetail.setCreatorId(super.getUser().getUserId());
//		policyFeeDetail.setDeleteFlag(Constants.DELETE_FALSE);
//		//policyFeeDetail.setFeeSubjectId(feesubjectids);
//		policyFeeDetail.setIsUsing(Constants.STATUS_DISABLE);
//		if(levelids!=-1)
//		{
//			policyFeeDetail.setLevelId(this.academyLevelBiz.findById(levelids).getLevelId());
//		}
//		else
//		{
//			policyFeeDetail.setLevelId(levelids);
//		}
//		//policyFeeDetail.setMajores(majorids);
//		policyFeeDetail.setPolicyFeeId(id);
//		//policyFeeDetail.setType(policyFee.getType());
//		policyFeeDetail.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
//		policyFeeDetail.setUpdaterId(super.getUser().getUserId());
//		this.policyFeeDetailBiz.addPolicyFeeDetail(policyFeeDetail);
				
		return SUCCESS;
	}


	public List<Academy> getAcademylist() {
		return academylist;
	}


	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}


	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}


	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}
	

	
}
