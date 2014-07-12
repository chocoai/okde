package net.cedu.action.basesetting.enrollmentchangetype;

import java.util.List;

import net.cedu.biz.basesetting.EnrollmentChangeTypeBiz;
import net.cedu.entity.basesetting.EnrollmentChangeType;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewEnrollmentChangeTypeAction {

	@Autowired
	private EnrollmentChangeTypeBiz enrollmentchangetypebiz;
	
	//全局招生批次列表
	private List<EnrollmentChangeType> enrollmentchangetypelist;
	
	//判断页面加载列表是否正常
	private boolean lstrltbool=true;
	
	/**
	 * 查询所有学籍异动类别 BY  HXJ
	 * @return
	 * @throws Exception
	 */
	@Action(value="enrollmentchangetypelist",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		
		try {
			enrollmentchangetypelist=enrollmentchangetypebiz.findAllEnrollmentChangeTypes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有学籍异动类别(delete_flag=0)   BY  HXJ
	 */
	@Action(value="enrollmentchangetypelistbydeleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String GetGlobalBatchListByDeleteFlag()throws Exception{
		
		try {
			enrollmentchangetypelist=enrollmentchangetypebiz.findAllEnrollmentChangeTypeByDeleteFlag();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	//-----------------------get and set-------------------

	public List<EnrollmentChangeType> getEnrollmentchangetypelist() {
		return enrollmentchangetypelist;
	}

	public void setEnrollmentchangetypelist(
			List<EnrollmentChangeType> enrollmentchangetypelist) {
		this.enrollmentchangetypelist = enrollmentchangetypelist;
	}

	public boolean isLstrltbool() {
		return lstrltbool;
	}

	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	
	

}
