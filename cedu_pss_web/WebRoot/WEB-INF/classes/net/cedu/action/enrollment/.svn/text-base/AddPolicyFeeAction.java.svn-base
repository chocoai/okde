package net.cedu.action.enrollment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;

/**
 * 添加收费政策标准
 * @author lixiaojun
 *
 */
@Results({
	@Result(name="index",location="list_policy_fee",type="redirectAction")
	})
public class AddPolicyFeeAction extends BaseAction
{
	@Autowired
	private BaseDictBiz BaseDictBiz;//基础表
	private List<BaseDict> modelist=new ArrayList<BaseDict>();//基础表集合（查询学制）
	private BaseDict baseDict=new BaseDict();//基础表实体
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合	
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	@Autowired 
	private PolicyFeeBiz policyFeeBiz;//收费政策标准业务接口
	private PolicyFee policyFee=new PolicyFee();//收费政策实体
	
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准规则业务接口
	private FeeStandard feeStandard=null;//收费标准规则实体
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	
	//页面参数
	private String xuefen;//应修学分
	private String hiddiv1;//每学分金额
	private int globalid;//缴费次数
	private int academyid;//院校ID
	private int feesubjectid;//费用科目ID
	private int modeid;//学制ID
	private String titles;//标题
	private int isControl;//是否受院校控制
	
	public String execute() throws Exception 
	{		
		if(super.isGetRequest())
		{	
			modelist=this.BaseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_MODE);
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
			feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
			return INPUT;
		}
		//添加收费标准
		policyFee.setAcademyId(academyid);
		policyFee.setCode(buildCodeBiz.getCodes(CodeEnum.PolicyFeeTB.getCode(),CodeEnum.PolicyFeePrefix.getCode()));
		policyFee.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		policyFee.setCreatorId(super.getUser().getUserId());
		policyFee.setDeleteFlag(Constants.DELETE_FALSE);
		policyFee.setFeeSubjectId(feesubjectid);
		policyFee.setModeId(modeid);
		policyFee.setTitle(titles);
		policyFee.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		policyFee.setUpdaterId(super.getUser().getUserId());
		//this.policyFeeBiz.addPolicyFee(policyFee);
		
		List<FeeStandard> feeStandardList=new ArrayList<FeeStandard>();
		double allmoney=0;//总的缴费上限
		//添加收费标准规则
		baseDict=this.BaseDictBiz.findBaseDictById(modeid);//学制实体
		for(int i=1;i<globalid;i++)
		{
			feeStandard=new FeeStandard();
			//String ceil="fee2"+i;
			String floor="fee"+i;
			String start="starttime"+i;
			String end="endtime"+i;
			
			super.setIl8nName("enrollment");
			//System.out.println(super.getText("credit"));
			//System.out.println(super.getText("feebatch",new Object[]{i}));
			if(baseDict.getId()==Constants.MODE_CREDIT && isControl!=1)
			{
				feeStandard.setRevisedCredit(Integer.valueOf(xuefen));
				feeStandard.setCreditFee(Double.valueOf(hiddiv1));	
//				if(request.getParameter(ceil)==""||request.getParameter(ceil)==null)
//				{
//					feeStandard.setChargingCeil(0);
//				}	
//				else
//				{
//					//feeStandard.setChargingCeil(Double.valueOf(request.getParameter(ceil))*Double.valueOf(hiddiv1));
//					feeStandard.setChargingCeil(Double.valueOf(new BigDecimal(request.getParameter(ceil)).multiply(new BigDecimal(hiddiv1)).toString()));
//				}
				if(request.getParameter(floor)==""||request.getParameter(floor)==null)
				{
					feeStandard.setChargingFloor(0);
				}
				else
				{
					//feeStandard.setChargingFloor(Double.valueOf(request.getParameter(floor))*Double.valueOf(hiddiv1));
					feeStandard.setChargingFloor(Double.valueOf(new BigDecimal(request.getParameter(floor)).multiply(new BigDecimal(hiddiv1)).toString()));
				}
			}
			else
			{
//				if(request.getParameter(ceil)==""||request.getParameter(ceil)==null)
//				{
//					feeStandard.setChargingCeil(0);
//				}	
//				else
//				{
//					feeStandard.setChargingCeil(Double.valueOf(request.getParameter(ceil)));
//				}
				if(request.getParameter(floor)==""||request.getParameter(floor)==null)
				{
					feeStandard.setChargingFloor(0);
				}
				else
				{
					feeStandard.setChargingFloor(Double.valueOf(request.getParameter(floor)));
				}
			}	
			feeStandard.setEndTime(DateUtil.getTimestamp(request.getParameter(end), "yyyy-MM-dd"));
			feeStandard.setFeeBatchId(i);
			feeStandard.setFeeBatchName(super.getText("feebatch",new Object[]{i}));
			//feeStandard.setPolicyFeeDetailId(policyFee.getId());
			feeStandard.setStartTime(DateUtil.getTimestamp(request.getParameter(start), "yyyy-MM-dd"));
			//this.feeStandardBiz.addFeeStandard(feeStandard);
			allmoney=Double.valueOf(new BigDecimal(allmoney).add(new BigDecimal(feeStandard.getChargingFloor())).toString());
			feeStandardList.add(feeStandard);
		}
		//缴费上限为缴费次数的总缴费金额
		if(feeStandardList!=null && feeStandardList.size()>0)
		{
			for(FeeStandard fs:feeStandardList)
			{
				fs.setChargingCeil(allmoney);
			}
		}
		this.policyFeeBiz.addPolicyFeeFeeStandardList(policyFee, feeStandardList);
		return "index";
	}

	public List<BaseDict> getModelist() {
		return modelist;
	}

	public void setModelist(List<BaseDict> modelist) {
		this.modelist = modelist;
	}

	public List<Academy> getAcademylist() {
		return academylist;
	}

	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}

	public String getXuefen() {
		return xuefen;
	}

	public void setXuefen(String xuefen) {
		this.xuefen = xuefen;
	}

	public String getHiddiv1() {
		return hiddiv1;
	}

	public void setHiddiv1(String hiddiv1) {
		this.hiddiv1 = hiddiv1;
	}

	public int getGlobalid() {
		return globalid;
	}

	public void setGlobalid(int globalid) {
		this.globalid = globalid;
	}

	public int getAcademyid() {
		return academyid;
	}

	public void setAcademyid(int academyid) {
		this.academyid = academyid;
	}

	public int getFeesubjectid() {
		return feesubjectid;
	}

	public void setFeesubjectid(int feesubjectid) {
		this.feesubjectid = feesubjectid;
	}

	public int getModeid() {
		return modeid;
	}

	public void setModeid(int modeid) {
		this.modeid = modeid;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public int getIsControl() {
		return isControl;
	}

	public void setIsControl(int isControl) {
		this.isControl = isControl;
	}
	
}
