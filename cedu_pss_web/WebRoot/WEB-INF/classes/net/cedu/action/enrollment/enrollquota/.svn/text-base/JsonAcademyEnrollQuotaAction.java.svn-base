package net.cedu.action.enrollment.enrollquota;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyEnrollQuotaBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON 院校指标
 * @author gaolei
 *
 */

@ParentPackage("json-default")
public class JsonAcademyEnrollQuotaAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	
	@Autowired
	private AcademyEnrollQuotaBiz academyenrollquotaBiz;
		
	private PageResult<AcademyEnrollQuota> result = new PageResult<AcademyEnrollQuota>();
	private int batchId;   //批次Id
	private int branchId;  //学习中心Id
	private String academyId; //院校Id
	private String target;    //指标
	
	
	/**
	 * 院校分配指标
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Action(value="addacademyenrollquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListBranchQuota() throws Exception {
		try {
			List alst = StringUtil.strTolists(academyId);
			List tlst=  StringUtil.strTolists(target);
			if((null!=alst && alst.size()>0) && (null!=tlst && tlst.size()>0) && alst.size()==tlst.size() )
			{
				for(int i=0; i<alst.size();i++)
				{
					//if(Integer.parseInt(tlst.get(i).toString())>0)
					//{
						//System.out.println(alst.get(i)+"__"+tlst.get(i));
						academyenrollquotaBiz.addAcademyEnrollQuota(batchId, branchId, Integer.parseInt(alst.get(i).toString()), Integer.parseInt(tlst.get(i).toString()), super.getUser().getUserId());
					//}
					
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//--------------------------------------get and set methods-----------------------------------------


	public PageResult<AcademyEnrollQuota> getResult() {
		return result;
	}


	public void setResult(PageResult<AcademyEnrollQuota> result) {
		this.result = result;
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


	public String getAcademyId() {
		return academyId;
	}


	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}



	
}
