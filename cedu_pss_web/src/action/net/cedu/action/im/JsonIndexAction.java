package net.cedu.action.im;

import net.cedu.action.BaseAction;
import net.cedu.biz.base.TaskBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonIndexAction extends BaseAction {
	@Autowired
	private TaskBiz taskBiz;
	public int count=0;
	@Action(value = "request_message", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String requestMessage() throws Exception {
		//默认没有消息
		boolean isMessage=false;
		
		while(!isMessage){
			//count = taskBiz.findUserTaskFinishByUserId(super.getUser().getUserId());
			//if(count==0){
				isMessage=true;
			//}
		}
		
		return SUCCESS;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


}
