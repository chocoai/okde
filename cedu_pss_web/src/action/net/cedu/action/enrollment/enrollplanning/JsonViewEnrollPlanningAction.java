package net.cedu.action.enrollment.enrollplanning;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewEnrollPlanningAction extends BaseAction{

	private static final long serialVersionUID = -5785804876544914671L;

	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	
	private Academy academy;										 //院校实体  
	private PageResult<Academy> result = new PageResult<Academy>();  // 分页结果
	
	/**
	 * 查询院校(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_academy_by_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
	}) })
	public String CountAcademy() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(academybiz.countAcademyByStatus(result));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return SUCCESS;
	}

	
	/**
	 * 查询院校(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_academy_by_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"excludeProperties",
			"result.*.pictureUrl,"+
			"result.*.shortName,"+
			"result.*.foundedYear,"+
			"result.*.website,"+
			"result.*.scale,"+
			"result.*.introduction,"+
			"result.*.auditer,"+
			"result.*.auditedDate,"+
			"result.*.account,"+
			"result.*.isForceFeePolicy,"+
			"result.*.deleteFlag,"+
			"result.*.creatorId,"+
			"result.*.createdTime,"+
			"result.*.updaterId,"+
			"result.*.updatedTime," +
			"result.*.address"
			
	}) })
	public String AcademyList() throws Exception {
		// 查询集合
		result.setList(academybiz.findAcademyByStatus(result));
		return SUCCESS;
	}
	
	//----------------------------------------------get and set methods---------------------------------------
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public PageResult<Academy> getResult() {
		return result;
	}
	public void setResult(PageResult<Academy> result) {
		this.result = result;
	}
	
	
}
