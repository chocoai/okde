package net.cedu.action.basesetting.enrollmentchangetype;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.EnrollmentChangeTypeBiz;
import net.cedu.entity.basesetting.EnrollmentChangeType;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateEnrollmentChangeTypeAction extends BaseAction{

	private static final long serialVersionUID = -3147618013769544683L;

	@Autowired
	private EnrollmentChangeTypeBiz enrollmentchangetypebiz;
	
	//'学籍异动类别设置'异动名称
	private String enrollmentchangetypename;
	//'学籍异动类别设置异动'编码
	private String  enrollmentchangetypecode;
	private boolean updaterltbol=false;//判断添加学籍异动类别设置是否成功
	private int enrollmentchangetypeid;//ID
	/**
	 * 修改全局招生批次信息  BY HXJ
	 */
	@Action(value="updateenrollmentchangetype",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		EnrollmentChangeType nenrollmentchangetype = new EnrollmentChangeType();
		EnrollmentChangeType oenrollmentchangetype = new EnrollmentChangeType();
		//按ID先获得实体的属性
		try {
			oenrollmentchangetype=enrollmentchangetypebiz.findEnrollmentChangeTypeById(enrollmentchangetypeid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			updaterltbol=false;
		}
		
		//根据传递过来的参数修改改实体
		oenrollmentchangetype.setName(enrollmentchangetypename);
		oenrollmentchangetype.setCode(enrollmentchangetypecode);
//		oenrollmentchangetype.setUpdaterId(007);
		oenrollmentchangetype.setUpdaterId(super.getUser().getUserId());
		oenrollmentchangetype.setUpdatedTime(new Date());
		try {
			nenrollmentchangetype=enrollmentchangetypebiz.modifyEnrollmentChangeType(oenrollmentchangetype);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			updaterltbol=false;
		}
		
		//判断是否修改成功
		if(nenrollmentchangetype!=null&&!("").equals(nenrollmentchangetype)){
			updaterltbol=true;
		}
		return SUCCESS;
	}
	//--------------------------get and set methods--------------
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
	public boolean isUpdaterltbol() {
		return updaterltbol;
	}
	public void setUpdaterltbol(boolean updaterltbol) {
		this.updaterltbol = updaterltbol;
	}
	public int getEnrollmentchangetypeid() {
		return enrollmentchangetypeid;
	}
	public void setEnrollmentchangetypeid(int enrollmentchangetypeid) {
		this.enrollmentchangetypeid = enrollmentchangetypeid;
	}
	
	
}
