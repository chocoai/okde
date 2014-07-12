package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.basesetting.FeeBatch;

import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 院校 自定义缴费次数
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonViewAcademyPaymentFrequencyAction extends BaseAction {
	@Autowired
	private FeeBatchBiz feeBatchBiz;//缴费次数biz
	
	
	private List<FeeBatch> feelist = new ArrayList<FeeBatch>();
	private FeeBatch feeBatch=new FeeBatch();//缴费次数实体
	
	//查询条件
	private int academyId;//院校Id
	private int batchId;//批次Id
	
	//删除条件
	private int id;//缴费次数ID
	
	//添加条件
	private String feename;//缴费次数名称
	private String starttime;//开始时间
	private String endtime;//结束时间

	/**
	 * 查询自定义缴费次数列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "branchfeebatchlist", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchlist() throws Exception {
		feelist=this.feeBatchBiz.getByProperty(0, academyId, batchId);
		return SUCCESS;
	}
	
	/**
	 * 删除缴费次数
	 * @return
	 * @throws Exception
	 */
	@Action(value = "branchfeebatchdelete", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchFeeDelete() throws Exception {
		feeBatchBiz.deleteConfigFeeBatchById(id);
		return SUCCESS;
	}
	/**
	 * 添加缴费次数
	 * @return
	 * @throws Exception
	 */
	@Action(value = "branchfeebatchadd", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addBranchFee() throws Exception {
		feeBatch.setAcademyId(academyId);
		feeBatch.setBatchId(batchId);
		feeBatch.setCode(DateUtil.getNowDate("yyyyMMddHHmmss"));
		feeBatch.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feeBatch.setCreatorId(super.getUser().getUserId());
		feeBatch.setDeleteFlag(0);
		feeBatch.setEndTime(DateUtil.getTimestamp(endtime, "yyyy-MM-dd"));
		feeBatch.setName(feename);
		feeBatch.setStartTime(DateUtil.getTimestamp(starttime, "yyyy-MM-dd"));
		feeBatch.setStatus(0);
		feeBatch.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feeBatch.setUpdaterId(super.getUser().getUserId());
		feeBatchBiz.addFeeBatch(feeBatch);
		return SUCCESS;
	}

	public List<FeeBatch> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<FeeBatch> feelist) {
		this.feelist = feelist;
	}

	public FeeBatch getFeeBatch() {
		return feeBatch;
	}

	public void setFeeBatch(FeeBatch feeBatch) {
		this.feeBatch = feeBatch;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	

}
