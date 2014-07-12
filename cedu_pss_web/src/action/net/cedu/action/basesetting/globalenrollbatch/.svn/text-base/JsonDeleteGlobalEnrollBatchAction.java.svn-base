package net.cedu.action.basesetting.globalenrollbatch;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteGlobalEnrollBatchAction extends BaseAction{

	private static final long serialVersionUID = -6899517158242763579L;

	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;
	
	
	private boolean delrltbool=false;//判断删除一条全局招生批次是否成功
	private int globalenrollbatchid;//删除GlobalEnrollBatch的ID
	
	/**
	 * 按id删除全局招生批次(真)
	 * @return
	 */
	@Action(value="deleteglobalenrollbatch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		//创建GlobalBatch对象
		GlobalEnrollBatch globalEnrollBatch = new GlobalEnrollBatch();
		try {
			globalEnrollBatch = globalEnrollBatchBiz.deleteConfigGlobalEnrollBatch(globalenrollbatchid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			delrltbool=false;
		}
		
		if(globalEnrollBatch!=null&&!("").equals(globalEnrollBatch)){
			delrltbool=true;
		}
		return SUCCESS;
	}
	

//
//	/**
//	 * 按id删除全局招生批次(假)
//	 * @return
//	 */
//	@Action(value="deleteglobalenrollbatchstatus",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String deleteGlobalBatchByStatus(){
//		int num=0;
//		try {
//			num=globalEnrollBatchBiz.deleteGlobalEnrollBatchByFlag(globalenrollbatchid);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			delrltbool=false;
//		}
//		
//		//判断是否修改成功
//		if(num>0){
//			delrltbool=true;
//		}
//		return SUCCESS;
//	}
	//--------------------------get and set methods--------------
	public boolean isDelrltbool() {
		return delrltbool;
	}
	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	public int getGlobalenrollbatchid() {
		return globalenrollbatchid;
	}
	public void setGlobalenrollbatchid(int globalenrollbatchid) {
		this.globalenrollbatchid = globalenrollbatchid;
	}
	
	
}
