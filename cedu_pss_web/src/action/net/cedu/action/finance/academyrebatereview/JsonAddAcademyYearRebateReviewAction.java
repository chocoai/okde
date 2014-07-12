package net.cedu.action.finance.academyrebatereview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.entity.finance.PlanedAcademyBill;

/**
 * 院校年度返款单新增
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddAcademyYearRebateReviewAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz arpdsBiz;//院校返款标准明细_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private AcademyBatchRebateCountBiz academyBatchRebateCountBiz;//院校返款每个批次的返款总人数

	private PayAcademyCedu payAcademyCedu=new PayAcademyCedu();//院校返款实体
	private String branchtzmoney;//学习中心分配的调整金额字符串	
	private int yearId;//年份
	private int sfeeSubjectId;//费用科目
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;//全局批次_业务层接口
	private List<GlobalEnrollBatch> glblist=new ArrayList<GlobalEnrollBatch>();//全局批次集合
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//招生批次_业务层接口
	
	private boolean isback=false;
	
	private boolean isfail=false;
	
	
	/**
	 * 添加院校年度返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_academy_year_rebate_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String aarca() throws Exception 
	{
		if(payAcademyCedu!=null)
		{
			PayAcademyCedu pac=new PayAcademyCedu();
			pac.setRemitterId(payAcademyCedu.getRemitterId());
			pac.setCurrentYear(yearId);
			int count =this.payAcademyCeduBiz.findPayAcademyCeduCountByPage(pac, null, null, null);
			if(count>0)
			{
				isfail=true;
			}
			else
			{
				//返款单
				payAcademyCedu.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				payAcademyCedu.setCreatorId(super.getUser().getUserId());
				payAcademyCedu.setDeleteFlag(Constants.DELETE_FALSE);
				payAcademyCedu.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);
				payAcademyCedu.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				payAcademyCedu.setRemittanceDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				payAcademyCedu.setUpdaterId(super.getUser().getUserId());
				payAcademyCedu.setTypes(Constants.FALLBACK_TYPES_SUBMIT);
				payAcademyCedu.setAmount(new BigDecimal(0));
				payAcademyCedu.setCurrentYear(yearId);
				payAcademyCedu.setIsYearCount(Constants.ACADEMY_REBATE_DAN_YEAR_COUNT_YEAR);
				
				
				String abamString="";//院校批次人数已经追加过的
				//院校返款时记录每次返款时每个批次的返款总人数
				List<AcademyBatchRebateCount> abrclist=new ArrayList<AcademyBatchRebateCount>();
				// 院校返款追加记录
				List<AcademyRebateAddRecords> ararlist=new ArrayList<AcademyRebateAddRecords>();
				//查询追加缴费单明细
				List<FeePaymentDetail> feeplist=new ArrayList<FeePaymentDetail>();
				FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
				feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);	
				feePaymentDetail.setFeeSubjectId(sfeeSubjectId);
				Student student =new Student();
				student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
				student.setAcademyId(payAcademyCedu.getRemitterId());
				List<AcademyEnrollBatch> aeblist=new ArrayList<AcademyEnrollBatch>();
				//通过年份查询全局批次   通过全局批次和院校查询该院校下的招生批次
				if(yearId>0)
				{
					glblist=this.globalEnrollBatchBiz.findGlobalEnrollBatchByYear(yearId);
					if(glblist!=null && glblist.size()>0)
					{
						AcademyEnrollBatch aeb=null;
						for(GlobalEnrollBatch geb:glblist)
						{
							if(geb!=null)
							{
								aeb=this.academyEnrollBatchBiz.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(geb.getId(),student.getAcademyId());
								if(aeb!=null)
								{
									aeblist.add(aeb);
								}
							}
						}
					}
				}
				if(aeblist!=null && aeblist.size()>0)
				{
					feeplist=this.fpdBiz.findFpdListForAddAcademyYearRebateCountAllAddMoney( feePaymentDetail, student,aeblist);
				}
				if(feeplist!=null && feeplist.size()>0)
				{
					for(FeePaymentDetail feeObj:feeplist)
					{
						if(feeObj!=null && feeObj.getAcademyYearRebateAddMoney().compareTo(new BigDecimal(0))>0)
						{
							//缴费单明细
							feeObj.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							feeObj.setUpdaterId(super.getUser().getUserId());
							feeObj.setAcademyRebateAddMoney(feeObj.getAcademyRebateAddMoney().add(feeObj.getAcademyYearRebateAddMoney()));
							feeObj.setIsAcademyRebateYearCount(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_TRUE);
							
							//院校返款追加记录
							AcademyRebateAddRecords arar=new AcademyRebateAddRecords();
							arar.setAddMoney(feeObj.getAcademyYearRebateAddMoney());
							arar.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							arar.setCreatorId(super.getUser().getUserId());
							arar.setFeePaymentDetailId(feeObj.getId());
							arar.setPayAcademyCeduAddId(feeObj.getOrderAcademyCeduId());
							arar.setStudentId(feeObj.getStudentId());
							arar.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							arar.setUpdaterId(super.getUser().getUserId());
							ararlist.add(arar);
							
							//如果有追加金额则绑定年度返款人数
							Student stu=this.studentBiz.findStudentById(feeObj.getStudentId());
							if(stu!=null)
							{						
								//过滤掉重复添加的问题    按院校批次 费用科目过滤（主要是按记录的人数去过滤掉重复的计算）
								if(abamString!=null && !abamString.equals(""))
								{
									String zuhecbrs=","+abamString+",";
									if(zuhecbrs.indexOf(","+stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+feeObj.getFeeSubjectId()+",")==-1)
									{
										AcademyBatchRebateCount abrc=new AcademyBatchRebateCount();
										abrc.setAcademyId(stu.getAcademyId());
										abrc.setBatchId(stu.getEnrollmentBatchId());
										abrc.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
										abrc.setCreatorId(super.getUser().getUserId());
										abrc.setFeeSubjectId(feeObj.getFeeSubjectId());
										abrc.setGlobalBatchId(stu.getGlobalBatch());
										abrc.setIsYearCount(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_TRUE);
										abrc.setRebateCount(fpdBiz.findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(feeObj,stu,aeblist));
										abrc.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
										abrc.setUpdaterId(super.getUser().getUserId());
										abrclist.add(abrc);
										
										abamString+=","+stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+feeObj.getFeeSubjectId();
									}
								}
								else
								{	
									AcademyBatchRebateCount abrc=new AcademyBatchRebateCount();
									abrc.setAcademyId(stu.getAcademyId());
									abrc.setBatchId(stu.getEnrollmentBatchId());
									abrc.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									abrc.setCreatorId(super.getUser().getUserId());
									abrc.setFeeSubjectId(feeObj.getFeeSubjectId());
									abrc.setGlobalBatchId(stu.getGlobalBatch());
									abrc.setIsYearCount(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_TRUE);
									abrc.setRebateCount(fpdBiz.findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(feeObj,stu,aeblist));
									abrc.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									abrc.setUpdaterId(super.getUser().getUserId());
									abrclist.add(abrc);
									abamString+=stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+feeObj.getFeeSubjectId();
								}
							}
							
						}
					}
				}
				
				//调整金额分配给学习中心
				List<AcademyRebateFenPeiBranch> arfpblist=new ArrayList<AcademyRebateFenPeiBranch>();
				if(branchtzmoney!=null && !branchtzmoney.equals("") && payAcademyCedu.getAdjustPaied().compareTo(new BigDecimal(0))>0)
				{
					String[] btzm=branchtzmoney.split(",");
					for(int i=0;i<btzm.length;i++)
					{
						String[] mid=btzm[i].split("#");
						if(mid.length>=2)
						{
							AcademyRebateFenPeiBranch arfpb=new AcademyRebateFenPeiBranch();
							arfpb.setAddMoney(new BigDecimal(mid[1]));
							arfpb.setBranchId(Integer.valueOf(mid[0]));
							arfpb.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							arfpb.setCreatorId(super.getUser().getUserId());
							arfpb.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							arfpb.setUpdaterId(super.getUser().getUserId());
							arfpblist.add(arfpb);
						}
					}
	 			}
				isback=this.payAcademyCeduBiz.addAcademyYearRebateReview(payAcademyCedu, feeplist,arfpblist,abrclist,ararlist);
			}
		}
		return SUCCESS;
	}

	public PayAcademyCedu getPayAcademyCedu() {
		return payAcademyCedu;
	}

	public void setPayAcademyCedu(PayAcademyCedu payAcademyCedu) {
		this.payAcademyCedu = payAcademyCedu;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public String getBranchtzmoney() {
		return branchtzmoney;
	}

	public void setBranchtzmoney(String branchtzmoney) {
		this.branchtzmoney = branchtzmoney;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public int getSfeeSubjectId() {
		return sfeeSubjectId;
	}

	public void setSfeeSubjectId(int sfeeSubjectId) {
		this.sfeeSubjectId = sfeeSubjectId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
	
}
