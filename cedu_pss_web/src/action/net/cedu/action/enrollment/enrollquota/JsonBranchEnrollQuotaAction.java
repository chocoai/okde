package net.cedu.action.enrollment.enrollquota;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyEnrollQuotaBiz;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;
import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON 学习中心总指标
 * @author gaolei
 *
 */

@ParentPackage("json-default")
public class JsonBranchEnrollQuotaAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	
	@Autowired
	private BranchEnrollQuotaBiz branchenrollquotaBiz;        //学习中心总指标biz       
	
	@Autowired
	private EnrollQuotaBatchBiz enrollquotabatchBiz;         //批次总指标biz  
	
	@Autowired
	private AcademyEnrollQuotaBiz academyenrollquotaBiz;
	
	
	
	// 分页结果
	private PageResult<BranchEnrollQuota> result = new PageResult<BranchEnrollQuota>();
	private List<EnrollQuotaBatch> enrollquotabatchlst=new ArrayList<EnrollQuotaBatch>();
	private List<BranchEnrollQuota> branchenrollquotalst=new ArrayList<BranchEnrollQuota>();
	private List<AcademyEnrollQuota> academyenrollquotalst=new ArrayList<AcademyEnrollQuota>();
	private EnrollQuotaBatch enrollquotabatch;               //批次总指标
	private BranchEnrollQuota branchenrollquota;
	private int batchId;
	private int branchId; 
	private String branchIds;
	private String branchTarget;
	private boolean bol;
	
	/**
	 * 年度总指标创建批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="addbranchenrollquotas",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AddBranchEnrollQuotas() throws Exception {
		try {
			Object [] bids=branchIds.substring(0,branchIds.length()-1).split(",");
			Object [] bt=branchTarget.substring(0,branchTarget.length()-1).split(",");
			bol=branchenrollquotaBiz.addBranchEnrollQuotas(batchId, bids, bt, super.getUser().getUserId());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查询招生指标全局批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="searchglobalbatch",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
				"includeProperties",
				"enrollquotabatchlst.*",
				"excludeProperties",
				"enrollquotabatchlst.*.admitNumber,enrollquotabatchlst.*.admitfeePaymentProportion," +
				"enrollquotabatchlst.*.createdTime,enrollquotabatchlst.*.creatorId," +
				"enrollquotabatchlst.*.deleteFlag,enrollquotabatchlst.*.enrollQuota," +
				"enrollquotabatchlst.*.enrollYear,enrollquotabatchlst.*.feePaymentNumber," +
				"enrollquotabatchlst.*.registNumber,enrollquotabatchlst.*.registadmitProportion," +
				"enrollquotabatchlst.*.targetadmitProportion,enrollquotabatchlst.*.targetfeePaymentProportion," +
				"enrollquotabatchlst.*.types,enrollquotabatchlst.*.updatedTime," +
				"enrollquotabatchlst.*.updaterId"
			})})	
	public String SearchGlobalBatch() throws Exception {
		
		try {
			enrollquotabatchlst=enrollquotabatchBiz.findEnrollQuotaBatch();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询招生指标全局批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="listbranchenrollquotabatchid",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
			"includeProperties",
			"result.*,batchId",
			"excludeProperties",
			"result.list.*.academylst.*.address,result.list.*.academylst.*.introduction," +
			"result.list.*.academylst.*.pictureUrl,result.list.*.academylst.*.website"
	})})	
	public String ListBranchEnrollQuotaBatchId() throws Exception {
		
		try {
			result.setList(branchenrollquotaBiz.findBranchEnrollQuotalist(batchId, result));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	

	/**
	 * 查询招生指标全局批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="searchtarget",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String SearchTarget() throws Exception {
		
		try {
			//enrollquotabatch=enrollquotabatchBiz.findEnrollQuotaBatchById(batchId);
			enrollquotabatch=enrollquotabatchBiz.findEnrollQuotaBatchByQuotaIdBatchId(0,batchId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询学习中心指标按批次和中心Id
	 * @return
	 * @throws Exception
	 */
	@Action(value="listbequotabtachIdandbranchId",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListbequotaBtachIdAndBranchId() throws Exception {
		
		try {
			branchenrollquotalst=branchenrollquotaBiz.findBranchEnrollQuotaByBId(batchId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询学习中心指标按批次和中心Id
	 * @return
	 * @throws Exception
	 */
	@Action(value="listbequotabIdandbranchId",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListbequotaBIdAndBranchId() throws Exception {
		
		try {
			
			branchenrollquota=branchenrollquotaBiz.findBranchEnrollQuotaByBtachIdAndBranchId(batchId,branchId);
			academyenrollquotalst=academyenrollquotaBiz.findAcademyEnrollQuotaByBatchIdAndBranchId(batchId, branchId);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询学习中心指标按批次和中心Id
	 * @return
	 * @throws Exception
	 */
	@Action(value="update_branch_enroll_quota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String UpdateBranchEnrollQuota() throws Exception {
		
		try {
			
			bol=branchenrollquotaBiz.updateBranchEnrollQuotas(branchIds, branchTarget, super.getUser().getUserId());
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	


	public PageResult<BranchEnrollQuota> getResult() {
		return result;
	}

	public void setResult(PageResult<BranchEnrollQuota> result) {
		this.result = result;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getBranchTarget() {
		return branchTarget;
	}

	public void setBranchTarget(String branchTarget) {
		this.branchTarget = branchTarget;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public List<EnrollQuotaBatch> getEnrollquotabatchlst() {
		return enrollquotabatchlst;
	}

	public void setEnrollquotabatchlst(List<EnrollQuotaBatch> enrollquotabatchlst) {
		this.enrollquotabatchlst = enrollquotabatchlst;
	}

	public EnrollQuotaBatch getEnrollquotabatch() {
		return enrollquotabatch;
	}

	public void setEnrollquotabatch(EnrollQuotaBatch enrollquotabatch) {
		this.enrollquotabatch = enrollquotabatch;
	}

	public List<BranchEnrollQuota> getBranchenrollquotalst() {
		return branchenrollquotalst;
	}

	public void setBranchenrollquotalst(List<BranchEnrollQuota> branchenrollquotalst) {
		this.branchenrollquotalst = branchenrollquotalst;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
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

	//--------------------------------------get and set methods-----------------------------------------

	
}
