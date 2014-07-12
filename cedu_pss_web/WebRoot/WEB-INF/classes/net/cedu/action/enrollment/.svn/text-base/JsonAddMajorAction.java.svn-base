package net.cedu.action.enrollment;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddMajorAction extends BaseAction{

	private static final long serialVersionUID = -3962296555725732371L;

	@Autowired
	private MajorBiz majorbiz;
	
	private Major major;//专业实体(院校基础设置)
	private boolean addrltbool=true;//判断添加操作是否成功
	private boolean errormsg=true;//有重复数据时的判断

	/**
	 * 添加专业信息  BY HXJ
	 */
	@Action(value="add_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		
		try {
			if (major!=null) {
				int userid = super.getUser().getUserId();
				major.setDeleteFlag(Constants.DELETE_FALSE);
				major.setCreatorId(userid);
				major.setCreatedTime(new Date());
				major.setUpdaterId(userid);
				major.setUpdatedTime(new Date());
				errormsg = majorbiz.addMajor(major);
			}else{
				addrltbool = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool = false;
		}
		return SUCCESS;
	}
	
	//---------------------------------get and set methods---------------------------
	
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public boolean isAddrltbool() {
		return addrltbool;
	}
	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
	public boolean isErrormsg() {
		return errormsg;
	}
	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}

}
