package net.cedu.action.basesetting.globalenrollbatch;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateGlobalEnrollBatchAction extends BaseAction{

	private static final long serialVersionUID = 3912164665306452058L;

	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;
	
	private GlobalEnrollBatch globalEnrollBatch;
	private boolean updaterltbol=true;//判断添加全局招生批次是否成功
	private boolean sameinfobool=true;
	private int globalenrollbatchid;//GlobalEnrollBatch的ID
	/**
	 * 修改全局招生批次信息  BY HXJ
	 */
	@Action(value="updateglobalbatch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		GlobalEnrollBatch oglobalbatch = null;
		
		int userid;
		try {
			//按ID先获得实体的属性
			userid = super.getUser().getUserId();
			oglobalbatch = new GlobalEnrollBatch();
			oglobalbatch=globalEnrollBatchBiz.findGlobalEnrollBatchById(globalenrollbatchid);
			
			//根据传递过来的参数修改改实体
			oglobalbatch.setBatch(globalEnrollBatch.getBatch());
			oglobalbatch.setTitle(globalEnrollBatch.getTitle());
			oglobalbatch.setBelongYear(globalEnrollBatch.getBelongYear());
			oglobalbatch.setUpdaterId(userid);
			oglobalbatch.setUpdatedTime(new Date());
			
			sameinfobool=globalEnrollBatchBiz.modifyGlobalEnrollBatch(oglobalbatch);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbol=false;
		}
		return SUCCESS;
	}
	//--------------------------get and set methods--------------

	
	public boolean isUpdaterltbol() {
		return updaterltbol;
	}
	public GlobalEnrollBatch getGlobalEnrollBatch() {
		return globalEnrollBatch;
	}
	public void setGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch) {
		this.globalEnrollBatch = globalEnrollBatch;
	}
	public void setUpdaterltbol(boolean updaterltbol) {
		this.updaterltbol = updaterltbol;
	}
	public int getGlobalenrollbatchid() {
		return globalenrollbatchid;
	}
	public void setGlobalenrollbatchid(int globalenrollbatchid) {
		this.globalenrollbatchid = globalenrollbatchid;
	}
	public boolean isSameinfobool() {
		return sameinfobool;
	}
	public void setSameinfobool(boolean sameinfobool) {
		this.sameinfobool = sameinfobool;
	}
}
