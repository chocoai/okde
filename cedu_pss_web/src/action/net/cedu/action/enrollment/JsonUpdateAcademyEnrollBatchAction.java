package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class JsonUpdateAcademyEnrollBatchAction extends BaseAction{

	private static final long serialVersionUID = 2556954100520835742L;

	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private SubAcademyEnrollBatchBiz subAcademyEnrollBatchBiz; 
	
	private AcademyEnrollBatch academyEnrollBatch;//院校招生批次实体
	private SubAcademyEnrollBatch subAcademyEnrollBatch;//院校招生子批次实体
	private int academyBatchId;//院校招生批次ID
	private int subacademyBatchId;//院校招生子批次ID
	private boolean updaterltbool=true;//判断修改操作是否成功
	
	/**
	 * 修改招生批次信息  BY HXJ
	 */
	@Action(value="update_academy_enroll_batch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		AcademyEnrollBatch academybatch = null;
		
		//按ID先获得实体的属性
		try {
			academybatch = new AcademyEnrollBatch();
			int userid = super.getUser().getUserId();
			academybatch = academyEnrollBatchBiz.findAcademyEnrollBatchById(academyBatchId);
			
			//根据传递过来的参数修改实体
			academybatch.setIsEnable(academyEnrollBatch.getIsEnable());
			academybatch.setUpdaterId(userid);
			academybatch.setUpdatedTime(new Date());
			
			updaterltbool = academyEnrollBatchBiz.modifyAcademyEnrollBatch(academybatch);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool=false;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 修改招生批次信息  BY HXJ
	 */
	@Action(value="update_sub_academy_enroll_batch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String updatesubacdemyenrollbatch(){
		SubAcademyEnrollBatch subacademybatch = null;
		List<SubAcademyEnrollBatch> batchlst=null;
		
		
		try {
			int userid = super.getUser().getUserId();
			batchlst=new ArrayList<SubAcademyEnrollBatch>();
			//按ID先获得实体的属性
			batchlst = subAcademyEnrollBatchBiz.findByAcademyBatchIdAndCurrentStatus(subAcademyEnrollBatch.getAcademyEnrollBatchId());
			if(batchlst!=null&&batchlst.size()>0){
				for(int i=0;i<batchlst.size();i++){
					batchlst.get(i).setIsCurrent(Constants.IS_CURRENT_FALSE);
					subAcademyEnrollBatchBiz.modifySubAcademyEnrollBatch(batchlst.get(i));
				}
			}
			subacademybatch = new SubAcademyEnrollBatch();
			subacademybatch = subAcademyEnrollBatchBiz.findSubAcademyEnrollBatchById(subacademyBatchId);
			
			subacademybatch.setIsCurrent(Constants.IS_CURRENT_TRUE);
			subacademybatch.setUpdaterId(userid);
			subacademybatch.setUpdatedTime(new Date());
			
			updaterltbool = subAcademyEnrollBatchBiz.modifySubAcademyEnrollBatch(subacademybatch);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool=false;
		}
		
		return SUCCESS;
	}
	//-----------------------------------------get and set methods-------------------------------
	
	public AcademyEnrollBatch getAcademyEnrollBatch() {
		return academyEnrollBatch;
	}
	public void setAcademyEnrollBatch(AcademyEnrollBatch academyEnrollBatch) {
		this.academyEnrollBatch = academyEnrollBatch;
	}
	public int getAcademyBatchId() {
		return academyBatchId;
	}
	public void setAcademyBatchId(int academyBatchId) {
		this.academyBatchId = academyBatchId;
	}
	public boolean isUpdaterltbool() {
		return updaterltbool;
	}
	public void setUpdaterltbool(boolean updaterltbool) {
		this.updaterltbool = updaterltbool;
	}
	public SubAcademyEnrollBatch getSubAcademyEnrollBatch() {
		return subAcademyEnrollBatch;
	}
	public void setSubAcademyEnrollBatch(SubAcademyEnrollBatch subAcademyEnrollBatch) {
		this.subAcademyEnrollBatch = subAcademyEnrollBatch;
	}
	public int getSubacademyBatchId() {
		return subacademyBatchId;
	}
	public void setSubacademyBatchId(int subacademyBatchId) {
		this.subacademyBatchId = subacademyBatchId;
	}
	
}
