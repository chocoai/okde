package net.cedu.action.basesetting.enrollmentsource;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteEnrollmentSourceAction extends BaseAction{

	private static final long serialVersionUID = 8084234518374537828L;
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	
	private boolean delrltbool=false;//判断删除一条招生途径是否成功
	private int enrollmentSourceId;//删除EnrollmentSource的ID
	
	/**
	 * 按id删除招生途径(物理删除)
	 * @return
	 */
	@Action(value="deletenrollmentsource",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		//创建GlobalBatch对象
		EnrollmentSource enrollmentSource = new EnrollmentSource();
		try {
			enrollmentSource = enrollmentSourceBiz.deleteConfigEnrollmentSource(enrollmentSourceId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			delrltbool=false;
		}
		
		if(enrollmentSource!=null&&!("").equals(enrollmentSource)){
			delrltbool=true;
		}
		return SUCCESS;
	}
	


//	/**
//	 * 按id删除招生途径(逻辑删除)
//	 * @return
//	 */
//	@Action(value="deleteenrollmentsourcestatus",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String deleteGlobalBatchByStatus(){
//		int num=0;
//		try {
//			num = enrollmentSourceBiz.deleteEnrollmentSourceByFlag(enrollmentSourceId);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			delrltbool=false;
//		}
//		
//		//判断是否修改成功
//		if(num>0){
//			delrltbool=true;
//		}
//		return SUCCESS;
//		}
	//----------------------------------get and set methods----------------------------------
	public boolean isDelrltbool() {
		return delrltbool;
	}
	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	public int getEnrollmentSourceId() {
		return enrollmentSourceId;
	}
	public void setEnrollmentSourceId(int enrollmentSourceId) {
		this.enrollmentSourceId = enrollmentSourceId;
	}
	
	
}
