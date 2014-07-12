package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json"),@Result(name="error", type="json")})
public class SaveAcademyBatchBranchListAction extends BaseAction
{
	/**
	 * @date 2011-08-08 17:34
	 */
	private static final long serialVersionUID = -393256353028830883L;
	
//	private int academyId;
	private int batchId;
	
	private int[] branchIds;
	
	private int errno = 0; //错误编号
	
	@Autowired
	private AcademyBatchBranchBiz academyBatchBranchBiz;
//	@Autowired
//	private AcademyBiz academyBiz;
//	@Autowired
//	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
	@Override
	public String execute() throws Exception {
		try {
			academyBatchBranchBiz.update(batchId, branchIds);
		} catch (Exception e) {
			e.printStackTrace();
			
			if(branchIds == null){
				errno = 1;
			} else {
				errno = -1;
			}
		}
		
		return SUCCESS;
	}

//	public void setAcademyId(int academyId) {
//		this.academyId = academyId;
//	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setBranchIds(int[] branchIds) {
		this.branchIds = branchIds;
	}

	public int getErrno() {
		return errno;
	}
	
	public String getErrnoMsg(){
		this.setIl8nName("enrollment");
		return this.getText("save_academy_batch_branch_list.errno."+errno);
	}
}
