package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeBatch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费次数设置
 * @author lixiaojun
 *
 */

public class ViewAcademyPaymentFrequencyAction extends BaseAction 
{
	@Autowired
	private FeeBatchBiz feeBatchBiz;
	private List<FeeBatch> feeList=new ArrayList<FeeBatch>();//默认缴费次数
	
	@Autowired
	private AcademyBiz academyBiz;
	private int academyId;//院校Id
	private Academy academy=new Academy();//院校
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生批次biz
	private List<AcademyEnrollBatch> studyList=new ArrayList<AcademyEnrollBatch>();
	
	public String execute() throws Exception 
	{
		feeList=feeBatchBiz.getBystatus(1);
		studyList=academyEnrollBatchBiz.findByAcademyIdAndFlagAndIsEnable(academyId,1);
		if(super.isGetRequest())
		{	
			academy=academyBiz.findAcademyById(academyId);
			return INPUT;
		}
		return SUCCESS;
	}
	public List<FeeBatch> getFeeList() {
		return feeList;
	}
	public void setFeeList(List<FeeBatch> feeList) {
		this.feeList = feeList;
	}
	
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public List<AcademyEnrollBatch> getStudyList() {
		return studyList;
	}
	public void setStudyList(List<AcademyEnrollBatch> studyList) {
		this.studyList = studyList;
	}
	
	
}
