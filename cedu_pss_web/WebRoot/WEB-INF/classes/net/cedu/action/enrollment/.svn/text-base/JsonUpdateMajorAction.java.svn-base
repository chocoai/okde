package net.cedu.action.enrollment;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateMajorAction extends BaseAction{

	private static final long serialVersionUID = 3984951229547230648L;

	@Autowired
	private MajorBiz majorbiz;
	
	private Major major;//专业实体(院校基础设置)
	private int majorid;//专业ID
	private boolean updaterltbool=true;//判断修改操作是否成
	private boolean sameinfobool=true;
	
	/**
	 * 修改专业信息  BY HXJ
	 */
	@Action(value="update_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		Major oldmajor = null; 
		
		
	
		try {
			int userid = super.getUser().getUserId();
			//按ID先获得实体的属性
			oldmajor = new Major();
			oldmajor = majorbiz.findMajorById(majorid);
			
			//根据传递过来的参数修改实体
			oldmajor.setName(major.getName());
			oldmajor.setCode(major.getCode());
			oldmajor.setBelongBaseMajorId(major.getBelongBaseMajorId());
			oldmajor.setUpdaterId(userid);
			oldmajor.setUpdatedTime(new Date());
			
			sameinfobool = majorbiz.modifyMajor(oldmajor);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool=false;
		}
		return SUCCESS;
	}
	
	//--------------------------------------------get and set methods-------------------------------
	
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public int getMajorid() {
		return majorid;
	}
	public void setMajorid(int majorid) {
		this.majorid = majorid;
	}
	public boolean isUpdaterltbool() {
		return updaterltbool;
	}
	public void setUpdaterltbool(boolean updaterltbool) {
		this.updaterltbool = updaterltbool;
	}
	public boolean isSameinfobool() {
		return sameinfobool;
	}
	public void setSameinfobool(boolean sameinfobool) {
		this.sameinfobool = sameinfobool;
	}
}
