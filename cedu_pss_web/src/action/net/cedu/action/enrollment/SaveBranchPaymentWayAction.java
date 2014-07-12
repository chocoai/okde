package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBranchFeeWayBiz;
import net.cedu.entity.enrollment.AcademyBranchFeeWay;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存院校授权中心的返款方式
 * @author Sauntor
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="success", type="json")})
public class SaveBranchPaymentWayAction extends BaseAction
{
	private static final long serialVersionUID = -954693605098797921L;
	
	private int academyId;
	private int batchId;
	private int branchId;
	
	private int[] fwIds;
	
	@Autowired
	private AcademyBranchFeeWayBiz academyBranchFeeWayBiz;

	@Override
	public String execute() throws Exception
	{
		academyBranchFeeWayBiz.deleteByBatchAndBranch(batchId, branchId);
		
		if(fwIds == null || fwIds.length==0)
		{
			return SUCCESS;
		}
		
		for(int i=0; i<fwIds.length; i++)
		{
			AcademyBranchFeeWay abfw = new AcademyBranchFeeWay();
			abfw.setAcademyId(academyId);
			abfw.setBatchId(batchId);
			abfw.setBranchId(branchId);
			abfw.setFeeWayId(fwIds[i]);
			
			academyBranchFeeWayBiz.addAcademyBranchFeeWay(abfw);
		}
		
		return SUCCESS;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setFwIds(int[] fwIds) {
		this.fwIds = fwIds;
	}
}
