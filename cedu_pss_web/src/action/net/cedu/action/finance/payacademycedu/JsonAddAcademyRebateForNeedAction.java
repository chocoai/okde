package net.cedu.action.finance.payacademycedu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.StudentAcademyRebateBiz;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAcademyRebate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单新增(院校返款单新增(现需返款的缴费单明细ajax)__2012-05-07重构
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonAddAcademyRebateForNeedAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	private String feepdIds;//缴费单明细Ids字符串集合
	
	//*******************判断缴费单明细是否符合院校返款条件************************//
	private String oldFeepdIds;//缴费单明细Ids字符串集合(已经有的)
	private String addFeepdIds;//缴费单明细Ids字符串集合(新添加的，要判断的)
	private String newFeepdIds="";//缴费单明细Ids字符串集合(组合后的)
	
	private List<FeePaymentDetail> hasFpdList=new ArrayList<FeePaymentDetail>();//已经存在的列表
	private List<FeePaymentDetail> notPoliceFpdList=new ArrayList<FeePaymentDetail>();//匹配不到返款政策的列表
	private List<FeePaymentDetail> notPeopleCountFpdList=new ArrayList<FeePaymentDetail>();//人数达不到返款标准的列表
	private boolean isback=true;
	@Autowired 
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目_业务层接口
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz arpdsBiz;//院校返款标准明细_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;//院校返款政策_业务层接口
	@Autowired 
	private StudentAcademyRebateBiz studentAcademyRebateBiz;//学生绑定院校返款标准关系表
	
	//*******************移除选中的缴费单明细************************//

	private String allFeepdIds;///缴费单明细Ids字符串集合(已经有的)
	private String delFeepdIds;//缴费单明细Ids字符串集合(移除的)
	private String newdelFeepdIds="";//缴费单明细Ids字符串集合(组合后的)
	
	private boolean isfail=true;//控制传人的参数是否正确
	
	//****************************统计现需返款的缴费单明细招生返款总金额**********************//
	
	private String allMoney;//
	
	/**
	 * 获取现需返款的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_academy_rebate_fpd_for_need_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{
		if(feepdIds!=null && feepdIds.length()>0)
		{
			String[] ids=feepdIds.split(",");
//			//去除数组中的重复项
//			List nStr = new ArrayList();
//			for (int i = 0; i < ids.length; i++)
//			{
//				if (!nStr.contains(ids[i]))
//				{
//					nStr.add(ids[i]);
//				}
//			}
//			result.setRecordCount(nStr.size());
			result.setRecordCount(ids.length);
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
	@Action(value = "list_academy_rebate_fpd_for_need_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPageForNowAddAcademyRebate(feepdIds,result));
		return SUCCESS;
	}
	
	/**
	 * 判断缴费单明细是否符合院校返款条件
	 * @return
	 * @throws Exception
	 */
	@Action(value = "test_fpd_academy_rebate_for_need_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fpdforacademyrebate() throws Exception 
	{	
		String addnewfpdIds="";
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
								FeePayment fp=feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId());
								if(fpd.getFeePaymentId()>0 && fp!=null)
								{
									fpd.setPaymentCode(fp.getCode());
								}
								else
								{
									fpd.setPaymentCode("");
								}
								//学生姓名
								Student stu=this.studentBiz.findStudentById(fpd.getStudentId());
								if(stu!=null)
								{
									fpd.setStudentName(stu.getName());
								}
								else
								{
									fpd.setStudentName("");
								}
								//费用科目名称
								FeeSubject fs=this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId());
								if(fs!=null)
								{
									fpd.setPaymentSubjectName(fs.getName());
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
				FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(Integer.parseInt(ids[i]));
				if(fpd!=null && fpd.getFeeSubjectId()>0)
				{
					Student student=this.studentBiz.findStudentById(fpd.getStudentId());
					if(student!=null)
					{
						//匹配标准
						AcademyRebatePolicyDetailStandard arpds=null;
						//查询该学生是否已经绑定了院校返款标准
						StudentAcademyRebate sarfind=new StudentAcademyRebate();
						sarfind.setStudentId(fpd.getStudentId());
						sarfind.setFeeSubjectId(fpd.getFeeSubjectId());
						StudentAcademyRebate sar=this.studentAcademyRebateBiz.findStudentAcademyRebateListBy(sarfind);
						if(sar!=null)
						{
							arpds=this.arpdsBiz.findById(sar.getAcademyPoliceStandardId());
						}
						else
						{
							arpds=this.arpdsBiz.findAcademyRebateStandardByStudentIdFeeSubjectId(student, fpd.getFeeSubjectId());
						}
						if(arpds==null)
						{
							//缴费单的编号
							FeePayment fp=feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId());
							if(fpd.getFeePaymentId()>0 && fp!=null)
							{
								fpd.setPaymentCode(fp.getCode());
							}
							else
							{
								fpd.setPaymentCode("");
							}
							//学生姓名
							Student stu=this.studentBiz.findStudentById(fpd.getStudentId());
							if(stu!=null)
							{
								fpd.setStudentName(stu.getName());
							}
							else
							{
								fpd.setStudentName("");
							}
							//费用科目名称
							FeeSubject fs=this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId());
							if(fs!=null)
							{
								fpd.setPaymentSubjectName(fs.getName());
							}
							else
							{
								fpd.setPaymentSubjectName("");
							}
							//判断是不是有标准但是人数太少
							
							AcademyRebatePolicyDetail arpd=this.academyRebatePolicyDetailBiz.findForStudnet(student,fpd.getFeeSubjectId(),false);
							if(arpd==null)
							{
								notPoliceFpdList.add(fpd);//无政策
							}
							else
							{
								notPeopleCountFpdList.add(fpd);//有政策人数太少
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
							//处理新加的字符串
							if(addnewfpdIds!=null && !addnewfpdIds.equals(""))
							{
								addnewfpdIds+=","+ids[i];
							}
							else
							{
								addnewfpdIds+=ids[i];
							}
						}
					}		
				}
			}
		}
		if(addnewfpdIds!=null && !addnewfpdIds.equals(""))
		{
			this.arpdsBiz.updateBatchFpdForAcademyRebateByfpdIds(addnewfpdIds);
		}
		return SUCCESS;
	}
	
	/**
	 * 移除缴费单明细
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_fpd_academy_rebate_for_need_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String delfpdforacademyrebate() throws Exception 
	{
		if(allFeepdIds!=null && !allFeepdIds.equals("") && delFeepdIds!=null && !delFeepdIds.equals(""))
		{
			String[] allIds=allFeepdIds.split(",");
			String[] delIds=delFeepdIds.split(",");
			boolean ishave=false;
			for(int i=0;i<allIds.length;i++)
			{
				ishave=false;
				for(int k=0;k<delIds.length;k++)
				{
					if(allIds[i].equals(delIds[k]))
					{
						ishave=true;
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
			}
		}
		else
		{
			isfail=false;
		}
		return SUCCESS;
	}
	/**
	 * 统计现需返款的缴费单明细
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_academy_rebate_fpd_for_need_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelForNowMoneyList() throws Exception 
	{	
		if(feepdIds!=null && !feepdIds.equals(""))
		{
			allMoney=this.feePaymentDetailBiz.countFpdAllAcademyRebateMoneyByFpdIds(feepdIds);
		}
		else 
		{
			allMoney="0";
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

	public List<FeePaymentDetail> getNotPeopleCountFpdList() {
		return notPeopleCountFpdList;
	}

	public void setNotPeopleCountFpdList(
			List<FeePaymentDetail> notPeopleCountFpdList) {
		this.notPeopleCountFpdList = notPeopleCountFpdList;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
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

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public String getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}
	
	
}
