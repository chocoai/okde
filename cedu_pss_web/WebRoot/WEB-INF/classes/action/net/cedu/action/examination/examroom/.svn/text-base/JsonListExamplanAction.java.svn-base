package net.cedu.action.examination.examroom;



import net.cedu.action.BaseAction;

import net.cedu.biz.examination.ExamPlanBiz;

import net.cedu.entity.examination.ExamPlan;

import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;



@ParentPackage("json-default")
public class JsonListExamplanAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 898395589684230433L;
	@Autowired
	private ExamPlanBiz examplanbiz;

	
	private String name;
	private int bid;//院校Id
	private int branchId;
	private int majorId;
	private int batchId;
	private int levelId;
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	private ExamPlan examplan=new ExamPlan();
	PageResult<ExamPlan> result=new PageResult<ExamPlan>();
	public ExamPlan getExamplan() {
		return examplan;
	}

	public void setExamplan(ExamPlan examplan) {
		this.examplan = examplan;
	}
	

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public PageResult<ExamPlan> getResult() {
		return result;
	}

	public void setResult(PageResult<ExamPlan> result) {
		this.result = result;
	}
	public ExamPlan getModel(){
		return examplan;
	}

	@Action(value="list_examplan",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String executes()
	{
		try {
			result.setList(examplanbiz.page(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	//分页结果
	@Action(value="findByconditions_examplan",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String execute()
	{
		try {
			result.setList(examplanbiz.findByConditions(examplan,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}

	
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	//分页数量
	@Action(value="count_examplan",
		results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String count()
	{
	try {
		result.setRecordCount(examplanbiz.findExamPlanPageCount(examplan,result));
	} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value="findByconditions_examplans",results={@Result(type="json", name = "success",params={"contentType","text/json"})})
	public String executeds()
	{
		try {
			result.setList(examplanbiz.findByConditions(bid, batchId,branchId,levelId,majorId,result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	//分页数量
	@Action(value="count_examplans",
			results={
				@Result(type="json", name = "success",params={"contentType","text/json"}
					   )
			})	
	public String counts()
	{
		try {
			System.out.println(bid);
			result.setRecordCount(examplanbiz.findExamPlanPageCount(bid, batchId,branchId,levelId,majorId,result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	

}
