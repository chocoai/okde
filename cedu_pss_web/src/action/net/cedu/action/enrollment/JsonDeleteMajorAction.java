package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteMajorAction extends BaseAction{

	private static final long serialVersionUID = -1174454949866201185L;
	
	@Autowired
	private MajorBiz majorbiz;
	
	private int majorid;//专业ID
	private boolean delrltbool=false;//判断删除操作是否成
	

	/**
	 * 按id删除专业(物理删除)
	 * @return
	 */
	@Action(value="delete_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	
	public String execute(){
		Major major=new Major();
		try{
			major = majorbiz.deleteConfigMajor(majorid);
		}catch (Exception e) {
			e.printStackTrace();
			delrltbool=false;
		}
		if(major!=null&&!("").equals(major)){
			delrltbool=true;
		}
		return SUCCESS;
	}
	

//
//	/**
//	 * 按id删除专业(逻辑删除)
//	 * @return
//	 */
//	@Action(value="delete_major_status",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String deleteModeByStatus(){
//		int num=0;
//		try {
//			num = majorbiz.deleteMajorByFlag(majorid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			delrltbool=false;
//		}
//		
//		//判断是否修改成功
//		if(num>0){
//			delrltbool=true;
//		}
//		return SUCCESS;
//	}
//	
	//--------------------------------------get and set methods---------------------------
	
	public int getMajorid() {
		return majorid;
	}
	public void setMajorid(int majorid) {
		this.majorid = majorid;
	}
	public boolean isDelrltbool() {
		return delrltbool;
	}
	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	
	
	
}
