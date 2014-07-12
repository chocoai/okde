package net.cedu.action.basesetting.enrollmentchangetype;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.EnrollmentChangeTypeBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.EnrollmentChangeType;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddEnrollmentChangeTypeAction extends BaseAction{

	private static final long serialVersionUID = -864106906239185926L;

	@Autowired
	private EnrollmentChangeTypeBiz enrollmentchangetypebiz;
	
	//'学籍异动类别设置'异动名称
	private String enrollmentchangetypename;
	//'学籍异动类别设置异动'编码
	private String enrollmentchangetypecode;
	//判断addEnrollmentChangeType方法是否成功
	private boolean addrltbool=false;
	
	/**
	 * 添加学籍异动类别信息  BY HXJ
	 */
	@Action(value="addenrollmentchangetype",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		EnrollmentChangeType enrollmentchangetype = new EnrollmentChangeType();
		Object obj=null;
		int userid = super.getUser().getUserId();
		enrollmentchangetype.setName(enrollmentchangetypename);
		enrollmentchangetype.setCode(enrollmentchangetypecode);
		enrollmentchangetype.setDeleteFlag(Constants.DELETE_FALSE);
		enrollmentchangetype.setCreatorId(userid);
		enrollmentchangetype.setCreatedTime(new Date());
		enrollmentchangetype.setUpdaterId(userid);
		enrollmentchangetype.setUpdatedTime(new Date());
		
		try{
			obj=enrollmentchangetypebiz.addEnrollmentChangeType(enrollmentchangetype);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addrltbool=false;
		}
		
		if(obj!=null&&!("").equals(obj)){
			addrltbool=true;
		}
		return SUCCESS;
	}

	//--------------------------get and set methods----------------------------------------

	
	public String getEnrollmentchangetypename() {
		return enrollmentchangetypename;
	}

	public void setEnrollmentchangetypename(String enrollmentchangetypename) {
		this.enrollmentchangetypename = enrollmentchangetypename;
	}

	public String getEnrollmentchangetypecode() {
		return enrollmentchangetypecode;
	}

	public void setEnrollmentchangetypecode(String enrollmentchangetypecode) {
		this.enrollmentchangetypecode = enrollmentchangetypecode;
	}


	public boolean isAddrltbool() {
		return addrltbool;
	}

	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}

	
}
 