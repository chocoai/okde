package net.cedu.action.finance.branchchannelpayment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款单新增(招生返款单新增(现需返款的缴费单明细ajax)
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddPayBranchChannelForAddAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	private String feepdIds;//缴费单明细Ids字符串集合
	
	//*******************判断缴费单明细是否符合招生返款条件************************//
	private String oldFeepdIds;//缴费单明细Ids字符串集合(已经有的)
	private String addFeepdIds;//缴费单明细Ids字符串集合(新添加的，要判断的)
	private String newFeepdIds="";//缴费单明细Ids字符串集合(组合后的)
	private double allRebateMoney=0;//统计返款金额
	private List<FeePaymentDetail> hasFpdList=new ArrayList<FeePaymentDetail>();//已经存在的列表
	private List<FeePaymentDetail> notPoliceFpdList=new ArrayList<FeePaymentDetail>();//匹配不到返款政策的列表
	private boolean isback=true;
	@Autowired 
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目_业务层接口
	@Autowired
	private ChannelPolicyDetailStandardBiz cpdsBiz;//招生返款标准明细_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	//*******************移除选中的缴费单明细************************//
	private String allmoney;//返款总金额
	private String allFeepdIds;///缴费单明细Ids字符串集合(已经有的)
	private String delFeepdIds;//缴费单明细Ids字符串集合(移除的)
	private String newdelFeepdIds="";//缴费单明细Ids字符串集合(组合后的)
	private double alldelRebateMoney=0;//统计剩余返款金额
	private boolean isfail=true;//控制传人的参数是否正确
	
	
	/**
	 * 获取现需返款的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_feepaymentdetail_for_now_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelForNowCount() throws Exception 
	{
		if(feepdIds!=null && feepdIds.length()>0)
		{
			String[] ids=feepdIds.split(",");
			//去除数组中的重复项
			List nStr = new ArrayList();
			for (int i = 0; i < ids.length; i++)
			{
				if (!nStr.contains(ids[i]))
				{
					nStr.add(ids[i]);
				}
			}
			result.setRecordCount(nStr.size());
		}
		else
		{
			result.setRecordCount(0);
		}
		return SUCCESS;
	}

	/**
	 * 获取现需返款的缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channel_rebate_feepaymentdetail_for_now_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelForNowList() throws Exception 
	{	
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPageForNowAddChannelRebate(feepdIds,result));
		return SUCCESS;
	}
	
	/**
	 * 判断缴费单明细是否符合招生返款条件
	 * @return
	 * @throws Exception
	 */
	@Action(value = "test_feepaymentdetail_for_channel_rebate_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fpdforacademyrebate() throws Exception 
	{	
		if(oldFeepdIds!=null && !oldFeepdIds.equals(""))
		{
			newFeepdIds=oldFeepdIds;//赋值
		}
		if(addFeepdIds!=null && addFeepdIds.length()>0)
		{
			String[] ids=addFeepdIds.split(",");			
			for (int i = 0; i < ids.length; i++)
			{
				
				//判断是否存在
				if(oldFeepdIds!=null && !oldFeepdIds.equals(""))
				{
					String zuhefpd=","+oldFeepdIds+",";
					if(zuhefpd.indexOf(","+ids[i]+",")!=-1)
					{
						if(this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i]))!=null)
						{
							FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i]));
							if(fpd!=null)
							{
								//缴费单的编号
								if(fpd.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId())!=null)
								{
									fpd.setPaymentCode(feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId()).getCode());
								}
								else
								{
									fpd.setPaymentCode("");
								}
								//学生姓名
								if(this.studentBiz.findStudentById(fpd.getStudentId())!=null)
								{
									fpd.setStudentName(this.studentBiz.findStudentById(fpd.getStudentId()).getName());
								}
								else
								{
									fpd.setStudentName("");
								}
								//费用科目名称
								if(this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId())!=null)
								{
									fpd.setPaymentSubjectName(this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId()).getName());
								}
								else
								{
									fpd.setPaymentSubjectName("");
								}
								hasFpdList.add(fpd);
							}
						}
						isback=false;
						continue;
					}
				}
				//判断有没有返款标准
				if(this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i]))!=null && this.studentBiz.findStudentById(this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i])).getStudentId())!=null)
				{
					ChannelPolicyDetailStandard cpds=this.cpdsBiz.findByStudentFeeSubjectIdForChannelRebate(this.studentBiz.findStudentById(this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i])).getStudentId()), this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i])).getFeeSubjectId());
					if(cpds==null)
					{
						FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i]));
						if(fpd!=null)
						{
							//缴费单的编号
							if(fpd.getFeePaymentId()>0 && feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId())!=null)
							{
								fpd.setPaymentCode(feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId()).getCode());
							}
							else
							{
								fpd.setPaymentCode("");
							}
							//学生姓名
							if(this.studentBiz.findStudentById(fpd.getStudentId())!=null)
							{
								fpd.setStudentName(this.studentBiz.findStudentById(fpd.getStudentId()).getName());
							}
							else
							{
								fpd.setStudentName("");
							}
							//费用科目名称
							if(this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId())!=null)
							{
								fpd.setPaymentSubjectName(this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId()).getName());
							}
							else
							{
								fpd.setPaymentSubjectName("");
							}
							notPoliceFpdList.add(fpd);
						}
						isback=false;
						continue;
					}
					else
					{
						if(newFeepdIds!=null && !newFeepdIds.equals(""))
						{
							newFeepdIds+=","+ids[i];
						}
						else
						{
							newFeepdIds+=ids[i];
						}
						//统计返款总金额
						allRebateMoney=(new BigDecimal(cpdsBiz.findChannelRebateByfeePaymentDetailId(Integer.valueOf(ids[i]))).add(new BigDecimal(allRebateMoney))).doubleValue();
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 移除缴费单明细
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_feepaymentdetail_for_channel_rebate_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String delfpdforacademyrebate() throws Exception 
	{
		if(allFeepdIds!=null && !allFeepdIds.equals("") && delFeepdIds!=null && !delFeepdIds.equals(""))
		{
			String[] allIds=allFeepdIds.split(",");
			String[] delIds=delFeepdIds.split(",");
			boolean ishave=false;
			double adrmoney=0;
			for(int i=0;i<allIds.length;i++)
			{
				ishave=false;
				for(int k=0;k<delIds.length;k++)
				{
					if(allIds[i].equals(delIds[k]))
					{
						ishave=true;
						break;
					}
				}
				if(!ishave)
				{
					if(newdelFeepdIds.equals(""))
					{
						newdelFeepdIds+=allIds[i];
					}
					else
					{
						newdelFeepdIds+=","+allIds[i];
					}
				}
				else
				{
					adrmoney=(new BigDecimal(cpdsBiz.findChannelRebateByfeePaymentDetailId(Integer.valueOf(allIds[i]))).add(new BigDecimal(adrmoney))).doubleValue();
				}
			}
			alldelRebateMoney=(new BigDecimal(allmoney).subtract(new BigDecimal(adrmoney))).doubleValue();
		}
		else
		{
			isfail=false;
		}
		return SUCCESS;
	}
	

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public String getFeepdIds() {
		return feepdIds;
	}

	public void setFeepdIds(String feepdIds) {
		this.feepdIds = feepdIds;
	}

	public String getOldFeepdIds() {
		return oldFeepdIds;
	}

	public void setOldFeepdIds(String oldFeepdIds) {
		this.oldFeepdIds = oldFeepdIds;
	}

	public String getAddFeepdIds() {
		return addFeepdIds;
	}

	public void setAddFeepdIds(String addFeepdIds) {
		this.addFeepdIds = addFeepdIds;
	}

	public String getNewFeepdIds() {
		return newFeepdIds;
	}

	public void setNewFeepdIds(String newFeepdIds) {
		this.newFeepdIds = newFeepdIds;
	}

	public double getAllRebateMoney() {
		return allRebateMoney;
	}

	public void setAllRebateMoney(double allRebateMoney) {
		this.allRebateMoney = allRebateMoney;
	}

	public List<FeePaymentDetail> getHasFpdList() {
		return hasFpdList;
	}

	public void setHasFpdList(List<FeePaymentDetail> hasFpdList) {
		this.hasFpdList = hasFpdList;
	}

	public List<FeePaymentDetail> getNotPoliceFpdList() {
		return notPoliceFpdList;
	}

	public void setNotPoliceFpdList(List<FeePaymentDetail> notPoliceFpdList) {
		this.notPoliceFpdList = notPoliceFpdList;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getAllFeepdIds() {
		return allFeepdIds;
	}

	public void setAllFeepdIds(String allFeepdIds) {
		this.allFeepdIds = allFeepdIds;
	}

	public String getDelFeepdIds() {
		return delFeepdIds;
	}

	public void setDelFeepdIds(String delFeepdIds) {
		this.delFeepdIds = delFeepdIds;
	}

	public String getNewdelFeepdIds() {
		return newdelFeepdIds;
	}

	public void setNewdelFeepdIds(String newdelFeepdIds) {
		this.newdelFeepdIds = newdelFeepdIds;
	}

	public double getAlldelRebateMoney() {
		return alldelRebateMoney;
	}

	public void setAlldelRebateMoney(double alldelRebateMoney) {
		this.alldelRebateMoney = alldelRebateMoney;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
	
	
}
