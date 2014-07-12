package net.cedu.action.enrollment.returningvisit;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ReturningVisitBiz;
import net.cedu.entity.enrollment.ReturningVisit;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewReturningvisitAction extends BaseAction{

	private static final long serialVersionUID = -3429043677496663403L;

	@Autowired
	private ReturningVisitBiz returningVisitBiz;
	
	private ReturningVisit returningVisit;
	// 分页结果
	private PageResult<ReturningVisit> result = new PageResult<ReturningVisit>();

	
	/**
	 * 查询回访记录列表集合通过条件
	 */
	@Action(value="list_returning_visit_by_flag",
			results={
			@Result(type="json", name = "success",
					params={"contentType","text/json",
							}			
			 		)
		})	
	public String execute()throws Exception{
		result.setList(returningVisitBiz.findReturningVisitPageList(returningVisit, result));
		return SUCCESS;
	}
	
	/**
	 *  回访记录 列表纪录数量
	 * @return
	 * @throws Exception
	 */
	@Action(value="count_returning_visit",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String StudentCount() throws Exception {
		result.setRecordCount(returningVisitBiz.findReturningVisitPageCount(returningVisit, result));
		return SUCCESS;
	}

	//---------------------------------------get and set methods----------------------------------

	public ReturningVisit getReturningVisit() {
		return returningVisit;
	}

	public void setReturningVisit(ReturningVisit returningVisit) {
		this.returningVisit = returningVisit;
	}

	public PageResult<ReturningVisit> getResult() {
		return result;
	}

	public void setResult(PageResult<ReturningVisit> result) {
		this.result = result;
	}
	
}
