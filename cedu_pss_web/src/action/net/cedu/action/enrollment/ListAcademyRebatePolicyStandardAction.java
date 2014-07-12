package net.cedu.action.enrollment;

import java.util.Iterator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款政策标准 列表查询(Ajax)
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results(@Result(name="success", type="json", params={"contentType", "text/json"}))
public class ListAcademyRebatePolicyStandardAction extends BaseAction
{
	private static final long serialVersionUID = -2492437830647073530L;

	/* 查询条件 */
	private int academyId;
	private int feeSubjectId;
	
	/* 分页参数 */
	private int pageSize;
	private int pageIndex;
	
	private int total;
	private List<AcademyRebatePolicy> list;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolciyBiz;
	
	/**
	 * 列表数据
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception
	{	
		AcademyRebatePolicy policy = new AcademyRebatePolicy();
		policy.setAcademyId(academyId);
		policy.setFeeSubjectId(feeSubjectId);
		
//		PageParame param = new PageParame();
//		param.setCurrentPageIndex(pageIndex);
//		param.setPageSize(pageSize);
		
		total = academyRebatePolciyBiz.countList(policy);
		
		if(total>0){
			PageResult<AcademyRebatePolicy> prPageResult=new PageResult<AcademyRebatePolicy>();
			prPageResult.setCurrentPageIndex(pageIndex);
			prPageResult.setPageSize(pageSize);
			list = academyRebatePolciyBiz.list(policy, prPageResult);
//			list = academyRebatePolciyBiz.list(policy, pageIndex, pageSize);
			Iterator<AcademyRebatePolicy> arpIter = list.iterator();
			while(arpIter.hasNext())
			{
				AcademyRebatePolicy arp = arpIter.next();
				
				if(arp.getAcademyId()!=AcademyRebatePolicy.ACADEMY_ID_ALL){
					Academy academy = academyBiz.findAcademyById(arp.getAcademyId());
					
					if(academy != null)
						arp.setAcademyName(academy.getName());
				}
				
				arp.setStandards(academyRebatePolicyDetailStandardBiz.findByPolicyId(arp.getId()));
				FeeSubject fs = feeSubjectBiz.findFeeSubjectById(arp.getFeeSubjectId());
				if(fs != null)
				{
					arp.setFeeSubjectName(fs.getName());
				}
			}
		}
		
		return SUCCESS;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotal() {
		return total;
	}

	public List<AcademyRebatePolicy> getList() {
		return list;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
}
