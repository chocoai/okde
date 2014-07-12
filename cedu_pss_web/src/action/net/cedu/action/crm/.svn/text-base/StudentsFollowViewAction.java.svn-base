package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人招生指标跳转
 * 
 * @author yangdongdong
 * 
 */
public class StudentsFollowViewAction extends BaseAction {
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 业务接口
	private int enrollmentBatchId;// 招生批次

	private int academyId;// 院校

	@Override
	public String execute() throws Exception {
		// 招生批次
		AcademyEnrollBatch academyEnrollBatch = academyenrollbatchBiz
				.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
						enrollmentBatchId, academyId);
		if (academyEnrollBatch != null) {
			enrollmentBatchId = academyEnrollBatch.getId();
		}
		// System.out.println(enrollmentBatchId+"-----"+academyId);
		return super.execute();
	}

	public int getEnrollmentBatchId() {
		return enrollmentBatchId;
	}

	public void setEnrollmentBatchId(int enrollmentBatchId) {
		this.enrollmentBatchId = enrollmentBatchId;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

}
