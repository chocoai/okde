package net.cedu.action.basesetting.globalenrollbatch;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddGlobalEnrollBatchAction extends BaseAction{
	
	private static final long serialVersionUID = -6256829191532186939L;

	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;
	
	private GlobalEnrollBatch globalEnrollBatch;
	private boolean addrltbol=true;//判断添加全局招生批次是否成功
	private boolean errormsg=true;//有重复数据时的判断
	/**
	 * 添加全局招生批次  BY HXJ
	 */
	@Action(value="addglobalenrollbatch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		
		try {
			int userid=super.getUser().getUserId();
			//给全局招生批次实体对象 globalbatch 赋值
			globalEnrollBatch.setCreatedTime(new Date());
			globalEnrollBatch.setCreatorId(userid);
			globalEnrollBatch.setUpdatedTime(new Date());
			globalEnrollBatch.setUpdaterId(userid);
			globalEnrollBatch.setDeleteFlag(Constants.DELETE_FALSE);
			//执行增加方法
			errormsg=globalEnrollBatchBiz.addGlobalEnrollBatch(globalEnrollBatch);
		} catch (Exception e) {
			e.printStackTrace();
			addrltbol=false;
		}

		return SUCCESS;
	}

	
	
	//--------------------------get and set methods--------------

	
	public boolean isAddrltbol() {
		return addrltbol;
	}
	
	public void setAddrltbol(boolean addrltbol) {
		this.addrltbol = addrltbol;
	}
	
	public GlobalEnrollBatch getGlobalEnrollBatch() {
		return globalEnrollBatch;
	}

	public void setGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch) {
		this.globalEnrollBatch = globalEnrollBatch;
	}

	public boolean isErrormsg() {
		return errormsg;
	}

	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}
	

}
