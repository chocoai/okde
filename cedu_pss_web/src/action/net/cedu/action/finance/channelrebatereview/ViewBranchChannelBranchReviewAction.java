package net.cedu.action.finance.channelrebatereview;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz;
import net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.ChannelBatchRecruitRebateStandard;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 合作方返款单详情
 * 
 * @author Sauntor
 *
 */
public class ViewBranchChannelBranchReviewAction extends BaseAction
{
	private static final long serialVersionUID = 702478970614759299L;

	private int id;
	
	private OrderCeduChannel order;
	private double adjustedAmount = 0D; //调整金额
	
	private double totalAmount = 0D; //交款总额
	private int count = 0; //总人数
	
	private String branchName;
	private String channelTypeName;
	private String channelName;//主合作方
	private String minorChannelNames="";//次合作方名称    跨院校返款时才出现
	
	private List<FeePaymentDetail> fpdList;
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;

	@Autowired
	private ChannelBatchRecruitRebateStandardBiz cbrrsBiz;//合作方绑定招生返款标准关系_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private ChannelPolicyDetailStandardOverLoadBiz cpdsOverLoadBiz;//招生返款标准_业务层接口
	
	private String countChannelCount="";//统计
	
	private String countFpdAllMoney="0";//缴费单明细实缴总金额
	
	
	public String execute() throws Exception
	{
		order = orderCeduChannelBiz.findById(id);
		if(order!=null)
		{
			//招生途径
			EnrollmentSource enrollmentSource=enrollmentSourceBiz.findEnrollmentSourceById(order.getChannelType());
			if(enrollmentSource!=null)
			{
				channelTypeName = enrollmentSource.getName();
			}
			//合作方
			Channel channel = channelBiz.findChannel(order.getChannelId());
			if(channel!=null)
			{
				channelName = channel.getName();
			}
			//学习中心
			Branch branch=branchBiz.findBranchById(order.getBranchId());
			if(branch!=null)
			{
				branchName =branch.getName();
			}
			//查看统计用
			String cichannel="";
			setIl8nName("finance_payment");
			String minorChannelBranchNames="";
			//次合作方
			if(order.getMinorChannelIds()!=null && !order.getMinorChannelIds().equals(""))
			{
				String[] mchids=order.getMinorChannelIds().split(",");
				Channel minorch=null;
				for(int i=0;i<mchids.length;i++)
				{
					minorch=this.channelBiz.findChannel(Integer.valueOf(mchids[i]));
					if(minorch!=null)
					{
						if(!minorChannelNames.equals(""))
						{
							minorChannelNames+=","+minorch.getName();
							minorChannelBranchNames+=minorch.getName();
						}
						else
						{
							minorChannelNames+=minorch.getName();
							minorChannelBranchNames+=minorch.getName();
						}
						Branch br=branchBiz.findBranchById(minorch.getBranchId());
						if(br!=null)
						{
							minorChannelBranchNames+="["+br.getName()+"],";
						}
						else
						{
							minorChannelBranchNames+=",";
						}
					}
				}
				cichannel="&nbsp;&nbsp;&nbsp;&nbsp;"+getText("cichannelshow")+minorChannelNames;
			}
			
			fpdList = feePaymentDetailBiz.findFpdListByOrderCeduChannelId(order.getId());
			if(fpdList!=null && fpdList.size()>0)
			{			
				//统计过的合作方字符串
				String cbrsString="";
				//使用院校政策返款时的统计
				String oneAcademyString="";//统计过的专业
				String oneAcademyShow="";//要显示的标准
				for(FeePaymentDetail fpd : fpdList)
				{
					countFpdAllMoney=((new BigDecimal(countFpdAllMoney)).add(new BigDecimal(fpd.getJiaofeiValue()))).toString();
					
					fpd.setStatusName(getText("detail.status."+fpd.getStatus()));
					fpd.setRebateStatusName(getText("detail.status."+fpd.getRebateStatus()));
					
					//统计
					Student student =this.studentBiz.findStudentById(fpd.getStudentId());
					
					//合作方返款标准
					ChannelPolicyDetailStandard cpds=this.cpdsOverLoadBiz.findChannelPolicyDetailStandardById(fpd.getChannelPolicyStandardId());
					if(student!=null && cpds!=null)
					{
						//返款标准显示字符串
						String cpdsstring =getText("rebatestandardshow")+cpds.getEnrollmentFloor()+"--"+cpds.getEnrollmentCeil()+getText("peopleunit")+"&nbsp;&nbsp;"+cpds.getValue();
						if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
						{
							cpdsstring+=getText("baifenhao");
						}
						else
						{
							cpdsstring+=getText("jinedanwei");
						}
						if(order.getPoliceStatus()==Constants.CHANNEL_REBATE_POLICE_STATUS_ACADEMY)
						{	
							//过滤掉重复添加的问题  显示合作方人数
							if(cbrsString!=null && !cbrsString.equals(""))
							{
								String zuhecbrs=","+cbrsString+",";
								if(zuhecbrs.indexOf(","+order.getChannelId()+"#"+student.getAcademyId()+"#"+student.getEnrollmentBatchId()+",")==-1)
								{
									//匹配合作方是否已经第一次返款
									ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
									findCbrrs.setAcademyId(student.getAcademyId());
									findCbrrs.setChannelId(student.getChannelId());
									findCbrrs.setBatchId(student.getEnrollmentBatchId());
									ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
									if(cbrrs!=null)
									{
										if(cbrrs.getChannelTimeLimitId()==fpd.getChannelRebateTimeId())
										{
											countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<br/>";
										}
										else
										{
											countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+cbrrs.getRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("nowfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<br/>";
										}
									}
									else
									{
										countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<br/>";
									}
									
									cbrsString+=","+order.getChannelId()+"#"+student.getAcademyId()+"#"+student.getEnrollmentBatchId();
									
								}
							}
							else
							{
								//匹配合作方是否已经第一次返款
								ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
								findCbrrs.setAcademyId(student.getAcademyId());
								findCbrrs.setChannelId(student.getChannelId());
								findCbrrs.setBatchId(student.getEnrollmentBatchId());
								ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
								if(cbrrs!=null)
								{
									if(cbrrs.getChannelTimeLimitId()==fpd.getChannelRebateTimeId())
									{
										countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<br/>";
									}
									else
									{
										countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+cbrrs.getRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("nowfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<br/>";
									}
								}
								else
								{
									countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<br/>";
								}
								
								cbrsString+=order.getChannelId()+"#"+student.getAcademyId()+"#"+student.getEnrollmentBatchId();
								
							}
							//过滤掉重复添加的问题  显示标准
							if(oneAcademyString!=null && !oneAcademyString.equals(""))
							{
								String zuhecbrs=","+oneAcademyString+",";
								if(zuhecbrs.indexOf(","+student.getAcademyId()+"#"+student.getEnrollmentBatchId()+"#"+student.getLevelId()+"#"+student.getMajorId()+",")==-1)
								{
									oneAcademyShow+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("levelshow")+fpd.getLevelName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("majorshow")+fpd.getMajorName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
									oneAcademyString+=","+student.getAcademyId()+"#"+student.getEnrollmentBatchId()+"#"+student.getLevelId()+"#"+student.getMajorId();
								}
							}
							else
							{
								oneAcademyShow+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+getText("academyshow")+fpd.getSchoolName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("rebatebatchshow")+fpd.getAcademyenrollbatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("levelshow")+fpd.getLevelName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("majorshow")+fpd.getMajorName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
								oneAcademyString+=student.getAcademyId()+"#"+student.getEnrollmentBatchId()+"#"+student.getLevelId()+"#"+student.getMajorId();
							}
						}
						else
						{
							//过滤掉重复添加的问题
							if(cbrsString!=null && !cbrsString.equals(""))
							{
								String zuhecbrs=","+cbrsString+",";
								if(zuhecbrs.indexOf(","+order.getChannelId()+"#"+student.getGlobalBatch()+",")==-1)
								{
									//匹配合作方是否已经第一次返款
									ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
									findCbrrs.setAcademyId(student.getAcademyId());
									findCbrrs.setChannelId(student.getChannelId());
									findCbrrs.setBatchId(student.getEnrollmentBatchId());
									ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
									if(cbrrs!=null)
									{
										if(cbrrs.getChannelTimeLimitId()==fpd.getChannelRebateTimeId())
										{
											countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("globalbatchshow")+fpd.getGlobalBatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
										}
										else
										{
											countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("globalbatchshow")+fpd.getGlobalBatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+cbrrs.getRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("nowfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
										}
									}
									else
									{
										countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("globalbatchshow")+fpd.getGlobalBatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
									}
									
									cbrsString+=","+order.getChannelId()+"#"+student.getGlobalBatch();
									
								}
							}
							else
							{
								//匹配合作方是否已经第一次返款
								ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
								findCbrrs.setAcademyId(student.getAcademyId());
								findCbrrs.setChannelId(student.getChannelId());
								findCbrrs.setBatchId(student.getEnrollmentBatchId());
								ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
								if(cbrrs!=null)
								{
									if(cbrrs.getChannelTimeLimitId()==fpd.getChannelRebateTimeId())
									{
										countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("globalbatchshow")+fpd.getGlobalBatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
									}
									else
									{
										countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("globalbatchshow")+fpd.getGlobalBatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+cbrrs.getRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("nowfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
									}
								}
								else
								{
									countChannelCount+=getText("channleshow")+channelName+cichannel+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("globalbatchshow")+fpd.getGlobalBatchName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+getText("firstfeecount")+fpd.getChannelRebateCount()+getText("peopleunit")+"&nbsp;&nbsp;&nbsp;&nbsp;"+cpdsstring+"<br/>";
								}
								
								cbrsString+=order.getChannelId()+"#"+student.getGlobalBatch();
							}
						}
					}
				}
				countChannelCount+=oneAcademyShow;
			}
			adjustedAmount = order.getAmountPaied().subtract(order.getAmountToPay()).doubleValue();
			if(minorChannelBranchNames!=null && !minorChannelBranchNames.equals(""))
			{
				minorChannelBranchNames=minorChannelBranchNames.substring(0,(minorChannelBranchNames.length()-1));
			}
			minorChannelNames=minorChannelBranchNames;
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderCeduChannel getOrder() {
		return order;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public int getCount() {
		return count;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public String getChannelName() {
		return channelName;
	}

	public List<FeePaymentDetail> getFpdList() {
		return fpdList;
	}

	public double getAdjustedAmount() {
		return adjustedAmount;
	}

	public String getCountChannelCount() {
		return countChannelCount;
	}

	public void setCountChannelCount(String countChannelCount) {
		this.countChannelCount = countChannelCount;
	}

	public String getMinorChannelNames() {
		return minorChannelNames;
	}

	public void setMinorChannelNames(String minorChannelNames) {
		this.minorChannelNames = minorChannelNames;
	}

	public String getCountFpdAllMoney() {
		//转换金额
		countFpdAllMoney=MoneyUtil.formatMoney(countFpdAllMoney);
		return countFpdAllMoney;
	}

	public void setCountFpdAllMoney(String countFpdAllMoney) {
		this.countFpdAllMoney = countFpdAllMoney;
	}
	
}
