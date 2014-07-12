package net.cedu.action.academy;

import java.io.Serializable;

import net.cedu.action.BaseAction;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;



/**
 * 院校列表（查看院校沟通）
 * 
 * @author gaolei
 * 
 * */

public class ListAcademyCommunicationRecordAction extends BaseAction {

	
	
	@Action(results = {@Result(name = "success", location = "list_academy_communication_record.jsp")})
			public String excute() throws Exception
			{
				return SUCCESS;
			}

}
