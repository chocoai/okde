package net.cedu.action.finance.refund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退费单明细
 * @author lixiaojun
 *
 */
public class ViewRefundPaymentAction extends BaseAction
{
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单业务层接口
	private FeePayment feePayment=new FeePayment();//缴费单实体
	private int feePaymentId;//缴费单Id
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务层接口
	private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//缴费单明细实体
	
	@Autowired
	private StudentBiz studentBiz;//学生业务层接口
	private Student student=new Student();//学生实体
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务层接口
	private String academyName;//院校名称
	
	@Autowired
	private AcademyEnrollBatchBiz batchBiz;//批次业务层接口
	private String batchName;//批次名称
	
	@Autowired
	private LevelBiz levelBiz;//层次业务层接口
	private String levelName;//层次名称
	
	@Autowired
	private MajorBiz majorBiz;//专业业务层接口
	private String majorName;//专业名称
	
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	private List<StudentAccountAmountManagement> saamlist=new ArrayList<StudentAccountAmountManagement>();//学生充值账户明细集合
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			//退费单
			feePayment=this.feePaymentBiz.findFeePaymentById(feePaymentId);
			if(feePayment!=null)
			{
				feePayment.setTotalAmount(0-feePayment.getTotalAmount());
				student=this.studentBiz.findStudentById(feePayment.getStudentId());
				if(student!=null)
				{
					if(this.academyBiz.findAcademyById(student.getAcademyId())!=null)
					{
						academyName=this.academyBiz.findAcademyById(student.getAcademyId()).getName();
					}
					else
					{
						academyName="";
					}
					if(this.batchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId())!=null)
					{
						batchName=this.batchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId()).getEnrollmentName();
					}
					else
					{
						batchName="";
					}
					if(this.levelBiz.findLevelById(student.getLevelId())!=null)
					{
						levelName=this.levelBiz.findLevelById(student.getLevelId()).getName();
					}
					else
					{
						levelName="";
					}
					if(this.majorBiz.findMajorById(student.getMajorId())!=null)
					{
						majorName=this.majorBiz.findMajorById(student.getMajorId()).getName();
					}
					else
					{
						majorName="";
					}
				}
				//退费单明细
				feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
				//重新计算退费的值
				if(feePaymentDetailList!=null && feePaymentDetailList.size()>0 && feePayment.getStatus()==Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN)
				{
					String allmoneyc="0";
					for(FeePaymentDetail fpd:feePaymentDetailList)
					{
						allmoneyc="0";
						if(fpd.getSupperId()!=0 && this.feePaymentDetailBiz.findById(fpd.getSupperId())!=null)
						{
							FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(fpd.getSupperId());
							allmoneyc=(new BigDecimal(feepd.getBranchAccount()).add(new BigDecimal(feepd.getCeduAccount())).add(new BigDecimal(feepd.getAcademyAccount())).add(new BigDecimal(feepd.getChannelAccount()))).toString();
							if(Double.valueOf(allmoneyc)-0==0)
							{
								allmoneyc="1";
							}
							//String payAcademyCedu="payAcademyCedu";//总部退学生金额
							//String payCeduAcademy="payCeduAcademy";//院校退学生金额
							//String paymentByChannel="paymentByChannel";//和作方退总部金额
							//String payBranchCedu="payBranchCedu";//中心退学生金额
							fpd.setCeduAccount((new BigDecimal(feepd.getCeduAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							fpd.setAcademyAccount((new BigDecimal(feepd.getAcademyAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							fpd.setChannelAccount((new BigDecimal(feepd.getChannelAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							fpd.setBranchAccount((new BigDecimal(feepd.getBranchAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
						}
						//退费单状态
						if(fpd.getStatus()!=0)
						{
							if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_yi_tian_tui_fei_dan"));
							}
							else if(fpd.getStatus()==Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_tui_fei_shen_pi_shi_bai"));
							}
							else if(fpd.getStatus()==Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_tui_fei_shen_pi_dai_que_ren"));
							}
							else if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_yi_tui_fei_que_ren"));
							}
							else
							{
								fpd.setStatusName("");
							}
						}
					}
				}
				else if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
				{
					for(FeePaymentDetail fpd:feePaymentDetailList)
					{
						fpd.setCeduAccount(0-fpd.getCeduAccount());
						fpd.setAcademyAccount(0-fpd.getAcademyAccount());
						fpd.setChannelAccount(0-fpd.getChannelAccount());
						fpd.setBranchAccount(0-fpd.getBranchAccount());
						
						//退费单状态
						if(fpd.getStatus()!=0)
						{
							if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_yi_tian_tui_fei_dan"));
							}
							else if(fpd.getStatus()==Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_tui_fei_shen_pi_shi_bai"));
							}
							else if(fpd.getStatus()==Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_tui_fei_shen_pi_dai_que_ren"));
							}
							else if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
							{
								fpd.setStatusName(ResourcesTool.getText("finance_payment","payment_status_yi_tui_fei_que_ren"));
							}
							else
							{
								fpd.setStatusName("");
							}
						}
					}
				}
				saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdTypes(feePayment.getId(), Constants.STATUS_APPLY_CONSUMPTION_TRUE);
			}
			return INPUT;
		}
		return SUCCESS;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}

	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public List<StudentAccountAmountManagement> getSaamlist() {
		return saamlist;
	}

	public void setSaamlist(List<StudentAccountAmountManagement> saamlist) {
		this.saamlist = saamlist;
	}
	
}