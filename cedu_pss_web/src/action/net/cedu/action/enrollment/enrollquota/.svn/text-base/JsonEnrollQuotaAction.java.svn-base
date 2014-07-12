package net.cedu.action.enrollment.enrollquota;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.biz.enrollment.EnrollQuotaBiz;
import net.cedu.entity.enrollment.EnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON 年度总指标
 * @author gaolei
 *
 */

@ParentPackage("json-default")
public class JsonEnrollQuotaAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	@Autowired
	private EnrollQuotaBiz enrollquotaBiz;   
	private List<EnrollQuota> enrollquotalst=new ArrayList<EnrollQuota>();
	// 分页结果
	private PageResult<EnrollQuota> result = new PageResult<EnrollQuota>();
	private int id; //主键Id
	private int year;//年度
	private int branch;//查询类别
	private int opeaing;//运算类型
	private int major;  //数量或比例
	private int quota;//总指标
	private boolean bol;
	private int target;
	private int totaltarget;
	private int batchtotaltarget = 0;//批次总指标
	@Autowired
	private EnrollQuotaBatchBiz enrollQuotaBatchBiz;
	
	/**
	 * 年度总指标数据数量
	 * @return
	 * @throws Exception
	 */
	@Action(value="countenrollquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
			"includeProperties",
			"result.*"
			})})	
	public String CountEnrollQuota() throws Exception {
		try {
			// 查询数量
			result.setRecordCount(enrollquotaBiz.countEnrollQuota(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 年度总指标数据集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="listenrollquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json",
			"includeProperties",
			"result.*"
	})})	
	public String ListEnrollQuota() throws Exception {
		try {
			// 查询数据
			result.setList(enrollquotaBiz.findEnrollQuotalist(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 年度总指标数据集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="addenrollquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AddEnrollQuota() throws Exception {
		try {
			// 查询数据
		int [] num=enrollquotaBiz.addEnrollQuotas(year, branch, opeaing, major, quota, super.getUser().getUserId());
		if(num!=null && num.length>0)
		{
			target=num[0];
			totaltarget=num[1];
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 年度总指标数据集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="addquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String AddQuota() throws Exception {
		try {
			EnrollQuota eq=enrollquotaBiz.findEnrollQuotaByYear(year);
			if(eq!=null)
			{
				bol=false;
			}else{
				eq=new EnrollQuota();
				eq.setEnrollYear(year);
				eq.setQuota(quota);
				eq.setCreatorId(super.getUser().getUserId());
				eq.setCreatedTime(new Date());
				bol=enrollquotaBiz.addEnrollQuota(eq);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			bol=false;
		}
		return SUCCESS;
	}

	
	/**
	 * 查询所有年度总指标
	 * @return
	 * @throws Exception
	 */
	@Action(value="listenrollquotaall",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListEnrollQuotaAll() throws Exception {
		
		try {
			enrollquotalst=enrollquotaBiz.findEnrollQuotaAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改年度总指标
	 * @return
	 * @throws Exception
	 */
	@Action(value="updateenrollquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String UpdateEnrollQuota() throws Exception {
		
		try {
			EnrollQuota enrollquota=enrollquotaBiz.findEnrollQuotaById(id);
			enrollquota.setQuota(quota);
			enrollquota.setUpdaterId(super.getUser().getUserId());
			enrollquota.setUpdatedTime(new Date());
			enrollquotaBiz.updateEnrollQuota(enrollquota);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询年度总指标下的批次总指标总和
	 * @return
	 * @throws Exception
	 */
	@Action(value="findbatchenrollquotatarget",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String findbatchenrollquotatarget() throws Exception {
		
		try {
			List<EnrollQuotaBatch> eqbList = enrollQuotaBatchBiz.findEnrollQuotaBatchBybatchIds(id,null);
			if(eqbList!=null && eqbList.size()>0)
			{
				for(EnrollQuotaBatch eqb : eqbList)
				{
					batchtotaltarget += eqb.getTarget();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<EnrollQuota> getEnrollquotalst() {
		return enrollquotalst;
	}

	public void setEnrollquotalst(List<EnrollQuota> enrollquotalst) {
		this.enrollquotalst = enrollquotalst;
	}

	public PageResult<EnrollQuota> getResult() {
		return result;
	}

	public void setResult(PageResult<EnrollQuota> result) {
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

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getOpeaing() {
		return opeaing;
	}

	public void setOpeaing(int opeaing) {
		this.opeaing = opeaing;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getTotaltarget() {
		return totaltarget;
	}

	public void setTotaltarget(int totaltarget) {
		this.totaltarget = totaltarget;
	}

	public int getBatchtotaltarget() {
		return batchtotaltarget;
	}

	public void setBatchtotaltarget(int batchtotaltarget) {
		this.batchtotaltarget = batchtotaltarget;
	}
	

	//--------------------------------------get and set methods-----------------------------------------

	
}
