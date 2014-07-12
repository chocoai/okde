package net.cedu.action.enrollment.enrollquota;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollQuotaBiz;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;
import net.cedu.biz.enrollment.UserEnrollQuotaBiz;
import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON 中心指标
 * @author gaolei
 *
 */

@ParentPackage("json-default")
public class JsonUserEnrollQuotaAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	
	@Autowired
	private UserEnrollQuotaBiz userenrollquotaBiz;
	
	@Autowired
	private BranchEnrollQuotaBiz branchenrollquotaBiz;
	
	@Autowired
	private AcademyEnrollQuotaBiz academyenrollquotaBiz;
	
	@Autowired
	private AcademyBatchBranchBiz academybatchbranchBiz;
	
	private PageResult<User> result = new PageResult<User>();
	private List<AcademyEnrollQuota> academyenrollquotalst=new ArrayList<AcademyEnrollQuota>();
	
	private BranchEnrollQuota branchenrollquota;
	private int batchId;   //批次Id
	private int branchId;  //学习中心Id
	private int academyId; //院校Id
	private int target;    //指标
	private int completed; //期望指标
	private int userId;    //中心人员
	
	private String userIdStr;	//中心人员集合
	private String academyIdStr;	//院校Id集合
	private String targetStr;	//指标集合
	private String completedStr;	//期望指标集合
	
	/**
	 * 查询学习中心指标
	 * @return
	 * @throws Exception
	 */
	@Action(value="searchenrollqoutabranch",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
				"includeProperties",
				"branchenrollquota.*,academyenrollquotalst.*,batchId",
				"excludeProperties",
				"academyenrollquotalst.*.batchName,academyenrollquotalst.*.branchName," +
				"academyenrollquotalst.*.createdTime,academyenrollquotalst.*.creatorId," +
				"academyenrollquotalst.*.deleteFlag,academyenrollquotalst.*.updatedTime," +
				"academyenrollquotalst.*.updaterId,academyenrollquotalst.*.userName," +
				"branchenrollquota.*.academylst,branchenrollquota.*.createdTime," +
				"branchenrollquota.*.creatorId,branchenrollquota.*.deleteFlag," +
				"branchenrollquota.*.enrollQuota,branchenrollquota.*.quotaYear," +
				"branchenrollquota.*.updatedTime,branchenrollquota.*.updaterId"
			})})	
	public String ListBranchQuota() throws Exception {
		try {
			
		 branchenrollquota=branchenrollquotaBiz.findBranchEnrollQuotaByBtachIdAndBranchId(batchId,super.getUser().getOrgId());
		 academyenrollquotalst=academyenrollquotaBiz.findAcademyEnrollQuotaByBatchIdAndBranchId(batchId, super.getUser().getOrgId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 中心人员分配指标列表
	 * @return
	 * @throws Exception
	 */
	@Action(value="listacademyenrollqouta",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
				"includeProperties",
				"result.*,batchId,branchId",
				"excludeProperties",
				"result.list.*.code,result.list.*.createdTime,result.list.*.creatorId," +
				"result.list.*.deleteFlag,result.list.*.department,result.list.*.departmentId," +
				"result.list.*.email,result.list.*.password," +
				"result.list.*.job,result.list.*.jobId,result.list.*.mobile," +
				"result.list.*.org,result.list.*.orgId,result.list.*.photoUrl," +
				"result.list.*.status,result.list.*.telephone,result.list.*.type," +
				"result.list.*.updatePasswordTime,result.list.*.updatedTime,result.list.*.updaterId," +
				"result.list.*.academylst.*.account,result.list.*.academylst.*.address," +
				"result.list.*.academylst.*.auditStatus,result.list.*.academylst.*.auditedDate," +
				"result.list.*.academylst.*.auditer,result.list.*.academylst.*.code," +
				"result.list.*.academylst.*.contractEndtime," +
				"result.list.*.academylst.*.createdTime,result.list.*.academylst.*.creatorId," +
				"result.list.*.academylst.*.deleteFlag,result.list.*.academylst.*.foundedYear," +
				"result.list.*.academylst.*.interfaceSettingStatus,result.list.*.academylst.*.introduction," +
				"result.list.*.academylst.*.isForceFeePolicy,result.list.*.academylst.*.pictureUrl," +
				"result.list.*.academylst.*.projectManagerId,result.list.*.academylst.*.projectManagerName," +
				"result.list.*.academylst.*.rebatesFeesubject,result.list.*.academylst.*.scale," +
				"result.list.*.academylst.*.shortName,result.list.*.academylst.*.telephone," +
				"result.list.*.academylst.*.updatedTime,result.list.*.academylst.*.updaterId," +
				"result.list.*.academylst.*.usingStatus,result.list.*.academylst.*.website"
			})})	
	public String ListAcademyEnrollQouta() throws Exception {
		try {
			result.setList(userenrollquotaBiz.findUserEnrollQuotaByBatchIdAndBranchId(batchId,branchId,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 中心人员分配指标列表
	 * @return
	 * @throws Exception
	 */
	@Action(value="listacademyenrollqoutas",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListAcademyEnrollQoutas() throws Exception {
		try {
			result.setList(userenrollquotaBiz.findUserEnrollQuotaByBatchIdAndBranchId(batchId,branchId,result));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 中心人员分配指标
	 * @return
	 * @throws Exception
	 */
	@Action(value="adduserenrollqouta",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AddUserEnrollQouta() throws Exception {
		try {
			userenrollquotaBiz.addUserEnrollQuota(batchId, branchId, academyId, target,completed, userId, super.getUser().getUserId());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 批量中心人员分配指标
	 * @return
	 * @throws Exception
	 */
	@Action(value="adduserenrollqoutas",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AddUserEnrollQoutas() throws Exception {
		try {
			if((null!=userIdStr && userIdStr.indexOf(",")!=-1) && 
				(null!=academyIdStr && academyIdStr.indexOf(",")!=-1) &&
				(null!=targetStr && targetStr.indexOf(",")!=-1) &&
				(null!=completedStr && completedStr.indexOf(",")!=-1))
			{
				Object[] userIdObj=userIdStr.split(",");//获取用户Id数组
				Object[] academyIdObj=academyIdStr.split(",");//获取中心Id数组
				Object[] targetObj=targetStr.split(",");//获取指标数组
				Object[] completedObj=completedStr.split(",");//获取期望指标数组
				if(((null!=userIdObj &&  userIdObj.length>0)&& 
					(null!=academyIdObj &&  academyIdObj.length>0)&&
					(null!=targetObj &&  targetObj.length>0)&&
					(null!=completedObj &&  completedObj.length>0)) && 
					userIdObj.length==targetObj.length )
				{
					for(int i=1;i<userIdObj.length;i++)
					{
						userenrollquotaBiz.addUserEnrollQuota(batchId, branchId,Integer.parseInt(academyIdObj[i].toString()), Integer.parseInt(targetObj[i].toString()),Integer.parseInt(completedObj[i].toString()),Integer.parseInt(userIdObj[i].toString()), super.getUser().getUserId());
					}
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	
	
	
	

	//--------------------------------------get and set methods-----------------------------------------
	
	public String getUserIdStr() {
		return userIdStr;
	}


	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}


	public String getAcademyIdStr() {
		return academyIdStr;
	}


	public void setAcademyIdStr(String academyIdStr) {
		this.academyIdStr = academyIdStr;
	}


	public String getTargetStr() {
		return targetStr;
	}


	public void setTargetStr(String targetStr) {
		this.targetStr = targetStr;
	}


	public String getCompletedStr() {
		return completedStr;
	}


	public void setCompletedStr(String completedStr) {
		this.completedStr = completedStr;
	}


	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getTarget() {
		return target;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}

	public PageResult<User> getResult() {
		return result;
	}

	public void setResult(PageResult<User> result) {
		this.result = result;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BranchEnrollQuota getBranchenrollquota() {
		return branchenrollquota;
	}

	public void setBranchenrollquota(BranchEnrollQuota branchenrollquota) {
		this.branchenrollquota = branchenrollquota;
	}

	public List<AcademyEnrollQuota> getAcademyenrollquotalst() {
		return academyenrollquotalst;
	}

	public void setAcademyenrollquotalst(
			List<AcademyEnrollQuota> academyenrollquotalst) {
		this.academyenrollquotalst = academyenrollquotalst;
	}


	public int getCompleted() {
		return completed;
	}


	public void setCompleted(int completed) {
		this.completed = completed;
	}

	

	
}
