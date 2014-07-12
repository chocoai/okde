package net.cedu.action.enrollment.enrollquota;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.UserEnrollQuotaBiz;
import net.cedu.entity.enrollment.UserEnrollQuota;
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
public class JsonUserEnrollQuotasAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	
	@Autowired
	private UserEnrollQuotaBiz userenrollquotaBiz;
	
	
	
	private PageResult<UserEnrollQuota> result = new PageResult<UserEnrollQuota>();
	

	private int batchId;   //批次Id
	private int branchId;  //学习中心Id
	
	
	/**
	 * 查询中心人员指标按批次和中心Id
	 * @return
	 * @throws Exception
	 */
	@Action(value="listuserenrollquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListUserEnrollQuota() throws Exception {
		
		try {
			//result.setList(userenrollquotaBiz.findUserEnrollQuotaByBIdBId(batchId, branchId, result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	

	//--------------------------------------get and set methods-----------------------------------------
	
	
}
