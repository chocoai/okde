package net.cedu.action.enrollment;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.SubAcademyEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddAcademyEnrollBatchAction extends BaseAction{

	private static final long serialVersionUID = -6675590407646726451L;

	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired 
	private SubAcademyEnrollBatchBiz subAcademyEnrollBatchBiz;
	
	private AcademyEnrollBatch academyEnrollBatch;//院校招生批次实体
	private SubAcademyEnrollBatch subAcademyEnrollBatch;//院校招生子批次实体
	private boolean addrltbool=true;//判断添加操作是否成功
	private boolean errormsg=true;//有重复数据时的判断
	
	/**
	 * 添加院校招生批次信息  BY HXJ
	 */
	@Action(value="add_academy_enroll_batch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		try{
			int userid = super.getUser().getUserId();
			
			//各实体各参数赋值
			academyEnrollBatch.setIsEnable(Constants.STATUS_DISABLE);
			academyEnrollBatch.setRegisterName(academyEnrollBatch.getEnrollmentName());
			academyEnrollBatch.setDeleteFlag(Constants.DELETE_FALSE);
			academyEnrollBatch.setCreatorId(userid);
			academyEnrollBatch.setCreatedTime(new Date());
			academyEnrollBatch.setUpdaterId(userid);
			academyEnrollBatch.setUpdatedTime(new Date());
			
			errormsg = academyEnrollBatchBiz.addAcademyEnrollBatch(academyEnrollBatch);
		}catch (Exception e) {
			e.printStackTrace();
			addrltbool=false;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 添加院校招生子批次信息  BY HXJ
	 */
	@Action(value="add_sub_academy_enroll_batch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String addsubacademybatch(){
		
		
		
		try {
			int userid = super.getUser().getUserId();
			//根据院校招生批次ID 查询对应批次的实体 +赋值
			subAcademyEnrollBatch.setAcademyEnrollBatch(academyEnrollBatchBiz
					.findAcademyEnrollBatchById(subAcademyEnrollBatch.getAcademyEnrollBatchId()));
			
			//各实体各参数赋值
			subAcademyEnrollBatch.setIsCurrent(Constants.IS_CURRENT_FALSE);
			subAcademyEnrollBatch.setDeleteFlag(Constants.DELETE_FALSE);
			subAcademyEnrollBatch.setCreatorId(userid);
			subAcademyEnrollBatch.setCreatedTime(new Date());
			subAcademyEnrollBatch.setUpdaterId(userid);
			subAcademyEnrollBatch.setUpdatedTime(new Date());
			
			errormsg = subAcademyEnrollBatchBiz.addSubAcademyEnrollBatch(subAcademyEnrollBatch);
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool=false;
		}
		
		return SUCCESS;
	}
	
	//-----------------------------------------get and set methods------------------------------
	
	public AcademyEnrollBatch getAcademyEnrollBatch() {
		return academyEnrollBatch;
	}
	public void setAcademyEnrollBatch(AcademyEnrollBatch academyEnrollBatch) {
		this.academyEnrollBatch = academyEnrollBatch;
	}
	public boolean isAddrltbool() {
		return addrltbool;
	}
	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
	public SubAcademyEnrollBatch getSubAcademyEnrollBatch() {
		return subAcademyEnrollBatch;
	}
	public void setSubAcademyEnrollBatch(SubAcademyEnrollBatch subAcademyEnrollBatch) {
		this.subAcademyEnrollBatch = subAcademyEnrollBatch;
	}
	public boolean isErrormsg() {
		return errormsg;
	}
	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}
	
}
