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
public class JsonDeleteEnrollmentChangeTypeAction extends BaseAction{

	private static final long serialVersionUID = -7591124818166376340L;

	@Autowired
	private EnrollmentChangeTypeBiz enrollmentchangetypebiz;
	
	private boolean delrltbool=false;//判断删除一条学籍异动类别设置是否成功
	private int enrollmentchangetypeid;//删除数据对象的ID
	
//	/**
//	 * 按id删除学籍异动类别设置(真)
//	 * @return
//	 */
//	@Action(value="deleteenrollmentchangetype",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String execute(){
//		//创建EnrollmentChangeType对象
//		EnrollmentChangeType enrollmentchangetype= new EnrollmentChangeType();
//		try {
//			enrollmentchangetype = enrollmentchangetypebiz.deleteEnrollmentChangeTypeById(enrollmentchangetypeid);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			delrltbool=false;
//		}
//		
//		if(enrollmentchangetype!=null&&!("").equals(enrollmentchangetype)){
//			delrltbool=true;
//		}
//		return SUCCESS;
//	}
	


	/**
	 * 按id删除学籍异动类别设置(假)
	 * @return
	 */
	@Action(value="deleteenrollmentchangetypestatus",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String deleteEnrollmentChangeTypeByStatus(){
		EnrollmentChangeType nenrollmentchangetype = new EnrollmentChangeType();
		EnrollmentChangeType oenrollmentchangetype = new EnrollmentChangeType();
		//按ID先获得实体的属性
		try {
			oenrollmentchangetype=enrollmentchangetypebiz.findEnrollmentChangeTypeById(enrollmentchangetypeid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			delrltbool=false;
		}
		
		//根据传递过来的参数修改改实体
		oenrollmentchangetype.setDeleteFlag(1);
//		oenrollmentchangetype.setUpdaterId(007);
		oenrollmentchangetype.setUpdaterId(super.getUser().getUserId());
		oenrollmentchangetype.setUpdatedTime(new Date());
		
		try {
			nenrollmentchangetype=enrollmentchangetypebiz.modifyEnrollmentChangeType(oenrollmentchangetype);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			delrltbool=false;
		}
		
		//判断是否修改成功
		if(nenrollmentchangetype!=null&&!("").equals(nenrollmentchangetype)){
			delrltbool=true;
		}
		return SUCCESS;
	}
	//--------------------------get and set methods--------------



	public boolean isDelrltbool() {
		return delrltbool;
	}



	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}



	public int getEnrollmentchangetypeid() {
		return enrollmentchangetypeid;
	}



	public void setEnrollmentchangetypeid(int enrollmentchangetypeid) {
		this.enrollmentchangetypeid = enrollmentchangetypeid;
	}
	
	
}
