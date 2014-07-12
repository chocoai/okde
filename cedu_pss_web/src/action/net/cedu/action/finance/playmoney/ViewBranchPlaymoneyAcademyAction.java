package net.cedu.action.finance.playmoney;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayCeduAcademy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校打款(中心)_查看
 * 
 * @author lixiaojun
 * 
 */
public class ViewBranchPlaymoneyAcademyAction extends BaseAction
{
	
	@Autowired 
	private BranchBiz branchBiz;//学习中心业务接口
	
	@Autowired 
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//缴费单明细集合
	private List<FeePaymentDetail> feepdlist=new ArrayList<FeePaymentDetail>();//缴费单明细集合
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;//打款单_业务层接口
	private PayCeduAcademy payCeduAcademy=new PayCeduAcademy();//打款单实体
	private int id;//打款单Id;
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id>0)
			{
				payCeduAcademy=this.payCeduAcademyBiz.findById(id);
				if(payCeduAcademy!=null)
				{
					if(payCeduAcademy.getRemitterId()>0 && this.branchBiz.findBranchById(payCeduAcademy.getRemitterId())!=null)
					{
						payCeduAcademy.setRemitterName(this.branchBiz.findBranchById(payCeduAcademy.getRemitterId()).getName());
					}
					else
					{
						payCeduAcademy.setRemitterName("");
					}
					if(payCeduAcademy.getRemitteeId()>0 && this.academyBiz.findAcademyById(payCeduAcademy.getRemitteeId())!=null)
					{
						payCeduAcademy.setRemitteeName(this.academyBiz.findAcademyById(payCeduAcademy.getRemitteeId()).getName());
					}
					else
					{
						payCeduAcademy.setRemitteeName("");
					}
				}
				feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByPayCeduAcademyId(id);
				if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
				{
					for(FeePaymentDetail fpd:feePaymentDetailList)
					{
						boolean isfail=false;
						//设置招生批次id
						Student student=this.studentBiz.findStudentById(fpd.getStudentId());
						if(null!=student)
						{
							//批次
							fpd.setFeeWayId(student.getEnrollmentBatchId());
							//学习中心
							fpd.setIsSupper(student.getBranchId());
						}
						
						//判断
						if(feepdlist==null || feepdlist.size()<=0)
						{				
							//feepdList.add(feepd);
						}
						else
						{
							for(FeePaymentDetail fp:feepdlist)
							{
								if(fp.getFeeSubjectId()==fpd.getFeeSubjectId() && fp.getFeeWayId()==fpd.getFeeWayId() && fp.getIsSupper()==fpd.getIsSupper())
								{
									fp.setPayCeduAcademy(Double.valueOf(new BigDecimal(fp.getPayCeduAcademy()).add(new BigDecimal(fpd.getPayCeduAcademy())).toString()));
									isfail=true;
									break;
								}
							}
						}
						//不相等则不添加
						if(!isfail)
						{
							feepdlist.add(fpd);
						}
					}
				}
				feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByPayCeduAcademyId(id);
				if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
				{
					for(FeePaymentDetail fpd:feePaymentDetailList)
					{
						//缴费单状态
						fpd.setStatusName(ResourcesTool.getText("finance_payment","detail.status."+fpd.getStatus()));
					}
				}
			}
			return INPUT;
		}
		return SUCCESS;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}


	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
	}


	public List<FeePaymentDetail> getFeepdlist() {
		return feepdlist;
	}


	public void setFeepdlist(List<FeePaymentDetail> feepdlist) {
		this.feepdlist = feepdlist;
	}


	public PayCeduAcademy getPayCeduAcademy() {
		return payCeduAcademy;
	}


	public void setPayCeduAcademy(PayCeduAcademy payCeduAcademy) {
		this.payCeduAcademy = payCeduAcademy;
	}
	

	
}
