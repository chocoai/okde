package net.cedu.action.enrollment.enrollquota;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.biz.enrollment.EnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.EnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON 批次总指标
 * @author gaolei
 *
 */

@ParentPackage("json-default")
public class JsonEnrollQuotaBatchAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	@Autowired
	private EnrollQuotaBiz enrollquotaBiz;                    //年度总指标biz
	@Autowired
	private EnrollQuotaBatchBiz enrollquotabatchBiz;                    //批次总指标biz       
	@Autowired
	private GlobalEnrollBatchBiz globalenrollbatchBiz;        //全局招生批次biz
	private EnrollQuotaBatch enrollquotabatch;                //批次总指标
	private List<EnrollQuota> enrollquotalst=new ArrayList<EnrollQuota>();
	private List<EnrollQuotaBatch> enrollquotabatchlst=new ArrayList<EnrollQuotaBatch>();
	private List<GlobalEnrollBatch> globalenrollbatchlst=new ArrayList<GlobalEnrollBatch>();
	// 分页结果
	private PageResult<EnrollQuotaBatch> result = new PageResult<EnrollQuotaBatch>();
	private int id; //主键Id
	private int year;//年度
	private int quota;//总指标
	private String batchIds; //批次(多)
	private int quotaId;     //年度Id
	private boolean bol;     //操作结果
	private int surplusquota; //剩余指标数
	
	
	/**
	 * 年度总指标创建批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="addenrollquotabatch",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AddEnrollQuotaBatch() throws Exception {
		try {
			
			globalenrollbatchlst=globalenrollbatchBiz.findGlobalEnrollBatchByYear(year);
			bol=enrollquotabatchBiz.addEnrollQuotaBatch(quotaId, globalenrollbatchlst, super.getUser().getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 批次总指标数据集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="listenrollquotabatch",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
			"includeProperties",
			"result.*,batchIds,quotaId,random",
			"excludeProperties",
			"result.*.enrollQuota," +
			"result.*.enrollYear," +
			"result.*.updatedTime," +
			"result.*.updaterId," 
			 
		})})	
	public String ListEnrollQuota() throws Exception {
		try {
			// 查询数据
			String strIds="";
			if(batchIds!=null && !batchIds.equals(""))
			{
				strIds=batchIds.substring(0,batchIds.length()-1 );
			}
			result.setList(enrollquotabatchBiz.findEnrollQuotaBatchBybatchIds(quotaId,strIds));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	

	
	
	/**
	 * 查询年度总指标批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="listenrollbatch",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListEnrollQuotaAll() throws Exception {
		
		try {
			EnrollQuota eq=enrollquotaBiz.findEnrollQuotaById(id);
			if(eq!=null)
			{
				year=eq.getEnrollYear();
			}else
			{
				year=0;
			}
			globalenrollbatchlst=globalenrollbatchBiz.findGlobalEnrollBatchByYear(year);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询年度总指标批次
	 * @return
	 * @throws Exception
	 */
	@Action(value="findenrollbatch",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String FindEnrollQuotaBatch() throws Exception {
		
		enrollquotabatch=enrollquotabatchBiz.findEnrollQuotaBatchAllById(id);
		return SUCCESS;
	}
	
	/**
	 * 分配批次总指标
	 * @return
	 * @throws Exception
	 */
	@Action(value="assignquotabatch",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AssignQuotaBatch() throws Exception {
		
		//获取批次指标
		enrollquotabatch=enrollquotabatchBiz.findEnrollQuotaBatchAllById(id);
		EnrollQuota enrollquota=new EnrollQuota();
		if(enrollquotabatch!=null)
		{
			//获得年度总指标
			enrollquota=enrollquotaBiz.findEnrollQuotaById(enrollquotabatch.getQuotaId());
			//获取所有批次指标总和
			List<EnrollQuotaBatch> eqblst=enrollquotabatchBiz.findEnrollQuotaBatchBybatchIds(enrollquotabatch.getQuotaId(), null);
			int count=0;
			for(int i=0;i<eqblst.size();i++)
			{
				count+=eqblst.get(i).getTarget();
			}
			if(quota>(enrollquota.getQuota()-count+enrollquotabatch.getTarget()))
			{
				bol=true;
				surplusquota=enrollquota.getQuota()-count+enrollquotabatch.getTarget();
			}else
			{
				bol=false;
				//批次指标分配
				enrollquotabatch.setTarget(quota);
				enrollquotabatch.setUpdaterId(super.getUser().getUserId());
				enrollquotabatch.setUpdatedTime(new Date());
				boolean bol=enrollquotabatchBiz.updateEnrollQuotaBatch(enrollquotabatch);
				//修改年度总指标非配状态
				if(bol)
				{
					enrollquota.setTypes(Constants.IS_POST_TRUE);
					enrollquotaBiz.updateEnrollQuota(enrollquota);
				}
				
			}
			
			
		}
		return SUCCESS;
	}
	
	public List<EnrollQuota> getEnrollquotalst() {
		return enrollquotalst;
	}

	public void setEnrollquotalst(List<EnrollQuota> enrollquotalst) {
		this.enrollquotalst = enrollquotalst;
	}
	
	public List<EnrollQuotaBatch> getEnrollquotabatchlst() {
		return enrollquotabatchlst;
	}

	public void setEnrollquotabatchlst(List<EnrollQuotaBatch> enrollquotabatchlst) {
		this.enrollquotabatchlst = enrollquotabatchlst;
	}

	public List<GlobalEnrollBatch> getGlobalenrollbatchlst() {
		return globalenrollbatchlst;
	}

	public void setGlobalenrollbatchlst(List<GlobalEnrollBatch> globalenrollbatchlst) {
		this.globalenrollbatchlst = globalenrollbatchlst;
	}
	
	public PageResult<EnrollQuotaBatch> getResult() {
		return result;
	}

	public void setResult(PageResult<EnrollQuotaBatch> result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public String getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}

	public int getQuotaId() {
		return quotaId;
	}




	public void setQuotaId(int quotaId) {
		this.quotaId = quotaId;
	}


	public boolean isBol() {
		return bol;
	}


	public void setBol(boolean bol) {
		this.bol = bol;
	}


	public EnrollQuotaBatch getEnrollquotabatch() {
		return enrollquotabatch;
	}


	public void setEnrollquotabatch(EnrollQuotaBatch enrollquotabatch) {
		this.enrollquotabatch = enrollquotabatch;
	}


	public int getSurplusquota() {
		return surplusquota;
	}
	
	public void setSurplusquota(int surplusquota) {
		this.surplusquota = surplusquota;
	}
	
	

	//--------------------------------------get and set methods-----------------------------------------

	
}
