package net.cedu.action.examination.examroom;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamBatchBiz;
import net.cedu.entity.examination.ExamBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonExamBatchAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6427113991969974872L;
	@Autowired
	private ExamBatchBiz exambatchbiz;
	private ExamBatch exambatch=new ExamBatch();
	private String name;
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isIsart() {
		return isart;
	}
	public void setIsart(boolean isart) {
		this.isart = isart;
	}
	public ExamBatch getExambatch() {
		return exambatch;
	}
	public void setExambatch(ExamBatch exambatch) {
		this.exambatch = exambatch;
	}
	private boolean isart=true;
	@Action(value ="add_exambatch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddExamSchedule() throws Exception {

		try {
			
			exambatch.setCode(code);
			exambatch.setName(name);
			exambatch.setCreatedTime(new Date());
			exambatch.setCreatorId(super.getUser().getUserId());
			exambatch.setDeleteFlag(0);
			if(!isart){
				return INPUT;
			}
			// 执行添加操作
			isart=exambatchbiz.createNew(exambatch);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}
	

}
