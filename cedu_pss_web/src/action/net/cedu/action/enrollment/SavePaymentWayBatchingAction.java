package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBranchFeeWayBiz;
import net.cedu.entity.enrollment.AcademyBranchFeeWay;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 批量保存院校授权中心的返款方式
 * @author Sauntor
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="success", type="json")})
public class SavePaymentWayBatchingAction extends BaseAction
{
	private static final long serialVersionUID = -954693605098797921L;
	
	private int academyId;
	private int batchId;
	private int branchIds[];
	
	private int[] fwIds;
	
	@Autowired
	private AcademyBranchFeeWayBiz academyBranchFeeWayBiz;

	@Override
	public String execute() throws Exception
	{
		for(int j=0; j<branchIds.length; j++)
		{
			academyBranchFeeWayBiz.deleteByBatchAndBranch(batchId, branchIds[j]);
			
			if(fwIds == null || fwIds.length==0)
			{
				continue;
			}
			
			for(int i=0; i<fwIds.length; i++)
			{
				AcademyBranchFeeWay abfw = new AcademyBranchFeeWay();
				abfw.setAcademyId(academyId);
				abfw.setBatchId(batchId);
				abfw.setBranchId(branchIds[j]);
				abfw.setFeeWayId(fwIds[i]);
				
				academyBranchFeeWayBiz.addAcademyBranchFeeWay(abfw);
			}
		}
		
		return SUCCESS;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setBranchIds(int[] branchIds) {
		this.branchIds = branchIds;
	}

	public void setFwIds(int[] fwIds) {
		this.fwIds = fwIds;
	}
}
